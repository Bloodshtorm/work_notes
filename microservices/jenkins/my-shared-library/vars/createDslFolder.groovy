// vars/createDslFolder.groovy

def call(String folderName) {
    jobDsl scriptText: """
        folder('${folderName}') {
            description("This is the folder for ${folderName}")
        }
    """
}