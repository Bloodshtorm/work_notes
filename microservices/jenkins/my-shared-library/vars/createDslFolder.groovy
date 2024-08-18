// vars/createDslFolder.groovy

def call(String folderName) {
    folder(folderName) {
        description("This is the folder for ${folderName}")
    }
}