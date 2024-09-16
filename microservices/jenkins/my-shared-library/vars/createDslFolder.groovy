import org.yaml.snakeyaml.Yaml

def call() {
    // Загрузка YAML файла с использованием libraryResource
    def yamlContent = libraryResource 'vars/dirrectory.yaml'
    echo "YAML загружен: ${yamlContent}"

    // Парсинг YAML файла
    def parsedYaml = readYaml text: yamlContent

    // Получаем список директорий
    def directories = parsedYaml['directories']
    echo "Директории: ${directories}"
    generateFolders(directories)
}

def generateFolders(directories) {
    directories.each { dir ->
        if (dir instanceof Map) {
            dir.each { parentDir, subDirs ->
                createFolder(parentDir)
                generateFolders(subDirs)  // Рекурсивно создаём поддиректории
            }
        } else {
            createFolder(dir)
        }
    }
}

def createFolder(String folderName) {
    try {
        def folderJob = Jenkins.instance.createProject(com.cloudbees.hudson.plugins.folder.Folder, folderName)
        folderJob.description = "This is the folder for ${folderName}"
        folderJob.save()
        echo "Папка ${folderName} создана"
    } catch (Exception e) {
        error "Ошибка при создании папки ${folderName}: ${e.message}"
    }
}

createFolder('git')
createFolder('wikidocs')
createFolder('local-repository')
