node {
  stage('SCM') { 
    checkout scm
  }
  stage('SAST Analysis') {
    def scannerHome = tool 'SonarScanner';
    withSonarQubeEnv() {
      sh "${scannerHome}/bin/sonar-scanner"
    }
  }
  
  stage('Functional Security Test') { 
    git 'https://github.com/MegCyber/FunctionalSecurityTest.git'
    sh 'mvn clean install'
    cucumber buildStatus: 'null', customCssFiles: '', customJsFiles: '', failedFeaturesNumber: -1, failedScenariosNumber: -1, failedStepsNumber: -1, fileIncludePattern: 'target/cucumber-reports/cucumber.json', pendingStepsNumber: -1, skippedStepsNumber: -1, sortingMethod: 'ALPHABETICAL', undefinedStepsNumber: -1
  }
 
  stage('DAST Analysis') {
    sh 'docker run -v $(pwd):/zap/wrk/:rw --user root owasp/zap2docker-stable zap-full-scan.py -t https://juice-shop.herokuapp.com/ -g gen.conf -r JS_DAST_report.html || true'
  }
}
