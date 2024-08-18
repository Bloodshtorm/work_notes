pipeline {
    agent any

    stages {
        stage('Создание папки DSL') {
            steps {
                script {
                    folder('dsl') {
                        displayName('DSL')
                        description('Папка для хранения DSL скриптов')
                    }
                }
            }
        }
    }
}
