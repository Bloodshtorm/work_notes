// vars/createDslFolder.groovy

def call(String folderName) {
    // Загрузите скрипт из vars/dsl/createFolder.groovy
    def scriptText = libraryResource 'dsl/createFolder.groovy'
    
    // Запустите DSL скрипт с использованием jobDsl плагина
    jobDsl scriptText: scriptText,
           lookupStrategy: 'SEED_JOB',
           ignoreExisting: false,
           removedJobAction: "DISABLE",
           sandbox: true
}
