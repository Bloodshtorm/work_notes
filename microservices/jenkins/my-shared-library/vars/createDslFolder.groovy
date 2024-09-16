import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

def call() {
    // Загрузка YAML файла с использованием libraryResource
    def yamlContent = libraryResource 'vars/dirrectory.yaml'

    // Парсинг YAML файла
    Yaml yaml = new Yaml(new Constructor(Map))
    Map parsedYaml = yaml.load(yamlContent)

    // Получаем список директорий
    def directories = parsedYaml['directories']
    generateFolders(directories)
}

def generateFolders(directories) {
    directories.each { dir ->
        if (dir instanceof Map) {
            dir.each { parentDir, subDirs ->
                createFolder(parentDir)
                subDirs.each { subDir ->
                    createFolder("${parentDir}/${subDir}")
                }
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
