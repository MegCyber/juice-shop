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
  
  stage('Functional Test') { 
    git 'https://github.com/MegCyber/FunctionalSecurityTest.git'
    sh 'mvn clean compile'
    sh 'mvn clean test'
    cucumber buildStatus: 'null', customCssFiles: '', customJsFiles: '', failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: '**/*.json', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
    }
 
  stage('DAST Analysis') {
    sh 'docker run -v $(pwd):/zap/wrk/:rw --user root owasp/zap2docker-stable zap-full-scan.py -t http://20.208.138.194:3000 -c gen.conf -r JS_DAST_report.html'
    //sh 'docker run -v $(pwd):/zap/wrk/:rw -t owasp/zap2docker-stable zap-full-scan.py     -t http://20.208.138.194:3000 -g gen.conf -r testreport1.html || true'
            }
}
