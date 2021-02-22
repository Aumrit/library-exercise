
pipeline {
2
   agent any
3
4
   environment {
5
     
9
   }
10
11
   stages {
12
      stage('Build') {
13
         steps {
14
            // Get some code from a GitHub repository
15
            git 'https://github.com/Aumrit/library-exercise.git'
16
17
            // Run Maven on a Unix agent.
18
            sh "./mvnw -Dmaven.test.failure.ignore=true clean package"
19
20
            // To run Maven on a Windows agent, use
21
            // bat "mvn -Dmaven.test.failure.ignore=true clean package"
22
         }
23
24
         post {
25
            // If Maven was able to run the tests, even if some of the test
26
            // failed, record the test results and archive the jar file.
27
            success {
28
               junit '**/target/surefire-reports/TEST-*.xml'
29
               archiveArtifacts 'target/*.jar'
30
            }
31
         }
32
      }
33
   }
34
}