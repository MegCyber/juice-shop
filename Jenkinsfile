node {
  stage('SCM') {
    checkout scm
  }
 //  stage('Build Juice Shop') {
  //   sh 'rm -rf node_modules'
  //  sh "npm install"
     
   // }

  
stage('OWASP ZAP Active Scan') {
    def zapHome = tool 'OWASP ZAP';
    def targetUrl = "https://juice-shop.herokuapp.com/#/"
    withEnv(['ZAP_PORT=8090']) {
      sh "${zapHome}/zap.sh -daemon -port ${ZAP_PORT} -host 0.0.0.0"
      sh "sleep 10"
      sh "${zapHome}/zap-cli.py -p ${ZAP_PORT} status"
      sh "${zapHome}/zap-cli.py -p ${ZAP_PORT} open-url ${targetUrl}"
      sh "${zapHome}/zap-cli.py -p ${ZAP_PORT} spider ${targetUrl}"
      sh "${zapHome}/zap-cli.py -p ${ZAP_PORT} active-scan --recursive ${targetUrl}"
      sh "${zapHome}/zap-cli.py -p ${ZAP_PORT} report -o zap-report.html -f html"
    }
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
