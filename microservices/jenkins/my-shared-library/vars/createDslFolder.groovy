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
    def folderJob = Jenkins.instance.getItem(folderName)
    if (folderJob != null) {
        try {
            Jenkins.instance.removeItem(folderJob)
            echo "Удалена существующая папка ${folderName}"
        } catch (Exception e) {
            error "Ошибка при удалении папки ${folderName}: ${e.message}"
        }
    }
    try {
        folderJob = Jenkins.instance.createProject(com.cloudbees.hudson.plugins.folder.Folder, folderName)
        folderJob.description = "This is the folder for ${folderName}"
        folderJob.save()
        echo "Папка ${folderName} создана"
    } catch (Exception e) {
        error "Ошибка при создании папки ${folderName}: ${e.message}"
    }
}

def directories = ['git', 'wikidocs', 'local-repository']

directories.each { dir ->
    createFolder(dir)
}

