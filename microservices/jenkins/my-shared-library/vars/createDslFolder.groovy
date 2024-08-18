// vars/createDslFolder.groovy

def call(String folderName) {
    def scriptText = libraryResource 'dsl/createFolder.groovy'
    echo "Loaded script: ${scriptText}"
    
    jobDsl scriptText: scriptText,
           lookupStrategy: 'SEED_JOB',
           ignoreExisting: false,
           removedJobAction: "DISABLE",
           sandbox: true
}