// src/dsl/CreateFolder.groovy
package dsl

class CreateFolder {
    static String generateScript(String folderName) {
        return """
        folder('${folderName}') {
            description("This is the folder for ${folderName}")
        }
        """
    }
}