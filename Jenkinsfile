pipeline{
  agent any
  tools{
    maven 'maven'
    jdk 'jdk'
  }
  
  stages {
    stage('Maven-Clean'){
      steps{
        bat 'mvn clean'
      }
    }
    stage('Maven-Compile'){
      steps{
        bat 'mvn compile'
      }
    }
    stage('Maven_Test'){
      steps{
        bat 'mvn test'
      }
    }
    stage('Maven-Package'){
      steps{
        bat 'mvn package'
      }
    }
   stage('quality check status')
    {
      steps{
        script{
          withSonarQubeEnv("sonarserver")
          {
            bat "mvn sonar:sonar"
          }
        }
        bat 'mvn clean install'
      }
    }
    stage('SonarQube Quality Gate')
    {
      steps{
        sleep(60)
        timeout(time:1,unit:'HOURS')
        {
          script{
            def qg = waitForQualityGate()
            if(qg.status!='OK')
            {
              error "Pipeline aborted due to quality gate failure.${qg.status}"
            }
          }
        }
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
