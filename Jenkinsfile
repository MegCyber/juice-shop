node {
  stage('SCM') { 
    checkout scm
  }
  
    

  stage('SonarQube Analysis') {
    def scannerHome = tool 'SonarScanner';
    withSonarQubeEnv() {
      sh "${scannerHome}/bin/sonar-scanner"
    }
 
  stage('DAST Analysis') {
                // Run ZAP
                sh 'docker run -v $(pwd):/zap/wrk/:rw -t owasp/zap2docker-stable zap-full-scan.py     -t http://20.2.249.83:3000/#/ -g gen.conf -r testreport1.html || true'
            }
}
