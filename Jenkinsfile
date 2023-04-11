node {
  stage('SCM') { 
    checkout scm
  }
  
     stage('Run functional tests') {
        withEnv(['JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64']) {
          sh 'mvn clean test'
      }
     }

  stage('SonarQube Analysis') {
    def scannerHome = tool 'SonarScanner';
    withSonarQubeEnv() {
      sh "${scannerHome}/bin/sonar-scanner"
    }
  }
 
 stage('Git Secrets') {
                // Run Trufflehog
                sh ' trufflehog https://github.com/MegCyber/juice-shop.git --json || true'

            }
 
  stage('DAST Analysis') {
                // Run ZAP
                sh 'docker run -v $(pwd):/zap/wrk/:rw -t owasp/zap2docker-stable zap-full-scan.py     -t https://aopartnersdev.com.ng -g gen.conf -r testreport.html || true'

            }
}
