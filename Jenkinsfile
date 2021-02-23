pipeline{
  agent {
    docker {
      image 'maven:3-alpine'
      args '-v /root/.m2:/root/.m2'
    }
  }
  stages {
    stage('Maven-Clean'){
      steps{
        sh 'mvn clean'
      }
    }
    stage('Maven-Compile'){
      steps{
        sh 'mvn compile'
      }
    }
    stage('Maven_Test'){
      steps{
        sh 'mvn test'
      }
    }
    stage('Maven-Package'){
      steps{
        sh 'mvn package'
      }
    }
   stage('quality check status')
    {
      steps{
        script{
          withSonarQubeEvn("sonarserver")
          {
            sh "mvn sonar:sonar"
          }
        }
        sh 'mvn clean install'
      }
    }
    stage('collect artifact'){
     steps{
     archiveArtifacts artifacts: 'target/*.jar', followSymlinks: false
     }
     }
     stage('deploy to artifactory')
     {
     steps{
     
     rtUpload (
    serverId: 'artifactory-server',
    spec: '''{
          "files": [
            {
              "pattern": "target/*.jar",
              "target": "art-doc-dev-loc"
            }
         ]
    }''',
 
    // Optional - Associate the uploaded files with the following custom build name and build number,
    // as build artifacts.
    // If not set, the files will be associated with the default build name and build number (i.e the
    // the Jenkins job name and number).
    buildName: 'holyFrog',
    buildNumber: '42'
)
     }}
       
      
  
  
          

  }
}
