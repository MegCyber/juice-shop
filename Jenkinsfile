node {
  stage('SCM') { 
    checkout scm
  }
  
    

  stage('SonarQube Analysis') {
    def scannerHome = tool 'SonarScanner';
    withSonarQubeEnv() {
      sh "${scannerHome}/bin/sonar-scanner"
    }
  }
 

 
  stage('DAST Analysis') {
                // Run ZAP
                //sh 'docker run -v $(pwd):/zap/wrk/:rw -t owasp/zap2docker-stable zap-full-scan.py     -t https://juice-shop.herokuapp.com/ -g gen.conf -r testreport.html || true'
                sh 'docker run -v $(pwd)/out:/zap/wrk/:rw -t owasp/zap2docker-live zap-api-scan.py    -t https://juice-shop.herokuapp.com/ -f openapi -d -r dastScanReport.html'

            }
}
