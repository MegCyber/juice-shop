node{
  stage('SCM') {
    checkout scm
  }
  
  stage('OWASP ZAP Scan') {
    agent any
    parameters {
      choice  choices: ["Baseline", "APIS", "Full"],
        description: 'Type of scan that is going to perform inside the container',
        name: 'SCAN_TYPE'

      string defaultValue: "https://example.com",
        description: 'Target URL to scan',
        name: 'TARGET'

      booleanParam defaultValue: true,
        description: 'Parameter to know if wanna generate report.',
        name: 'GENERATE_REPORT'
    }
    steps {
      script {
        echo "<--Parameter Initialization-->"
        echo """
          The current parameters are:
          Scan Type: ${params.SCAN_TYPE}
          Target: ${params.TARGET}
          Generate report: ${params.GENERATE_REPORT}
        """

        echo "Pulling up last OWASP ZAP container --> Start"
        sh 'docker pull owasp/zap2docker-stable'
        echo "Pulling up last VMS container --> End"
        echo "Starting container --> Start"
        sh """
          docker run -dt --name owasp \
          owasp/zap2docker-stable \
          /bin/bash
        """

        if(params.GENERATE_REPORT) {
          sh """
            docker exec owasp \
            mkdir /zap/wrk
          """
        }

        def scan_type = "${params.SCAN_TYPE}"
        echo "----> scan_type: $scan_type"
        def target = "${params.TARGET}"
        if(scan_type == "Baseline") {
          sh """
            docker exec owasp \
            zap-baseline.py \
            -t $target \
            -x report.xml \
            -I
          """
        }
        else if(scan_type == "APIS") {
          sh """
            docker exec owasp \
            zap-api-scan.py \
            -t $target \
            -x report.xml \
            -I
          """
        }
        else if(scan_type == "Full") {
          sh """
            docker exec owasp \
            zap-full-scan.py \
            -t $target \
            //-x report.xml
            -I
          """
          //-x report-$(date +%d-%b-%Y).xml
        }
        else {
          echo "Something went wrong..."
        }

        if(params.GENERATE_REPORT) {
          sh '''
            docker cp owasp:/zap/wrk/report.xml ${WORKSPACE}/report.xml
          '''
        }

        echo "Removing container"
        sh '''
          docker stop owasp
          docker rm owasp
        '''
      }
    }
  }

  stage('Build Juice Shop') {
    sh 'rm -rf node_modules'
    sh "npm install"
  }
  
  stage('SonarQube Analysis') {
    def scannerHome = tool 'SonarScanner';
    withSonarQubeEnv() {
      sh "${scannerHome}/bin/sonar-scanner"
    }
  }
}
