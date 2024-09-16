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
        // Генерация и выполнение DSL скрипта для создания папок
        def scriptText = """
        folder('${folderName}') {
            description("This is the folder for ${folderName}")
        }
        """
        echo "Скрипт сгенерирован:\n${scriptText}"

        jobDsl scriptText: scriptText,
                lookupStrategy: 'SEED_JOB',
                ignoreExisting: false,
                removedJobAction: "DISABLE",
                sandbox: false
    } catch (Exception e) {
        error "Ошибка при создании папки ${folderName}: ${e.message}"
    }
}
