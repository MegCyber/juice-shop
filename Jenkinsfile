node {
  stage('SCM') {
    checkout scm
  }
 //  stage('Build Juice Shop') {
  //   sh 'rm -rf node_modules'
  //  sh "npm install"
     
   // }

  
 stage('OWASP ZAP Scanning') {
        agent any
        parameters {
            choice(choices: ['Baseline', 'APIS', 'Full'], 
                   description: 'Type of scan that is going to perform inside the container',
                   name: 'SCAN_TYPE')
            booleanParam(defaultValue: true,
                          description: 'Parameter to know if wanna generate report.',
                          name: 'GENERATE_REPORT')
        }
        steps {
            script {
                def zapContainer = docker.image('owasp/zap2docker-stable').run('-dt')
                def zapContainerId = zapContainer.id
                try {
                    sh "sleep 30" // Give some time for the container to start up

                    def target = 'https://juice-shop.herokuapp.com/#/'
                    def scanType = "${params.SCAN_TYPE}"

                    sh "docker exec $zapContainerId zap.sh -cmd -quickurl $target"

                    if (scanType == 'Baseline') {
                        sh "docker exec $zapContainerId zap-baseline.py -t $target"
                    } else if (scanType == 'APIS') {
                        sh "docker exec $zapContainerId zap-api-scan.py -t $target"
                    } else if (scanType == 'Full') {
                        sh "docker exec $zapContainerId zap-full-scan.py -t $target"
                    } else {
                        echo 'Invalid scan type specified'
                        return
                    }

                    if (params.GENERATE_REPORT) {
                        sh "docker exec $zapContainerId cp /zap/wrk/*.html /zap/wrk/report.html"
                        sh "docker cp $zapContainerId:/zap/wrk/report.html ${WORKSPACE}/zap-report.html"
                    }
                } finally {
                    sh "docker stop $zapContainerId"
                    sh "docker rm $zapContainerId"
                }
  
    stage('SonarQube Analysis') {
    def scannerHome = tool 'SonarScanner';
    withSonarQubeEnv() {
      sh "${scannerHome}/bin/sonar-scanner"
    }
  }
 

  //stage('Git Secrets') {
    // Run Trufflehog
    //sh ' trufflehog https://github.com/MegCyber/juice-shop.git --json'
 // }
  stage('Check Dependencies') {
                // Install trivy
                sh ' curl -sfL https://raw.githubusercontent.com/aquasecurity/trivy/main/contrib/install.sh | sh -s -- -b /usr/local/bin v0.18.3 || true'
                sh ' curl -sfL https://raw.githubusercontent.com/aquasecurity/trivy/main/contrib/html.tpl > html.tpl || true'

                // Scan all vuln levels
                sh ' mkdir -p reports'
                sh ' trivy filesystem --ignore-unfixed --vuln-type os,library --format template --template "@html.tpl" -o reports/nodjs-scan.html ./nodejs || true'
                publishHTML target : [
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports',
                    reportFiles: 'nodjs-scan.html',
                    reportName: 'Trivy Scan',
                    reportTitles: 'Trivy Scan'
                ]

                // Scan again and fail on CRITICAL vulns
                sh 'trivy filesystem --ignore-unfixed --vuln-type os,library --exit-code 1 --severity CRITICAL ./nodejs || true'

            }
  
  stage ('DAST Analysis') {
      sshagent(['zap']) {
         sh 'docker run -t owasp/zap2docker-stable zap-baseline.py -t https://aopartnersdev.com.ng/devsecops/ || true '
      }
    }

}
