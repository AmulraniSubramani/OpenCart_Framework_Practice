pipeline{
    agent any
    stages{
        
        stage("build"){
            steps{
                echo("building the project")
            }
        }
        
         stage("run my unit level test cases"){
            steps{
                echo("run unit level testcases")
            }
        }
        
        stage("Deploy on DEV env"){
            steps{
                echo("Deploy on DEV env")
            }
        }
        
         stage("Deploy on QA env"){
            steps{
                echo("Deploy on QA env")
            }
        }
        
         stage("sanity testcases on QA"){
            steps{
                echo("sanity testcases")
            }
        }
        
         stage("regression testcases on QA"){
            steps{
                echo("regression testcases")
            }
        }
         stage("Deploy on stage"){
            steps{
                echo("Deploy on stage")
            }
        }
        
         stage("sanity testcases on stage"){
            steps{
                echo("sanity testcases")
            }
        }
        
         stage("Deploy on prod"){
            steps{
                echo("Deploy on prod")
            }
        }
    }
}