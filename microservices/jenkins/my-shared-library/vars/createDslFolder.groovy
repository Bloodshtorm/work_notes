def call() {
    // Загрузка YAML файла с использованием readYaml
    def yamlContent = libraryResource 'vars/dirrectory.yaml'
    def parsedYaml = readYaml text: yamlContent

    if (!yamlContent) {
        error "Не удалось загрузить YAML файл: ${yamlFilePath}"
    }

    echo "YAML загружен: ${yamlContent}"

    // Получаем список директорий
    def directories = yamlContent['directories']
    if (!directories) {
        error "В YAML файле отсутствует ключ 'directories'"
    }

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
