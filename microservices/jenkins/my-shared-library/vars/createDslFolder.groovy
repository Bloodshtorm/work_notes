import dsl.*

def call(String folderName) {
    try {
        // Получение скрипта из класса CreateFolder
        def scriptText = CreateFolder.generateScript(folderName)
        echo "Script content:\n${scriptText}"

        // Запуск DSL скрипта с использованием jobDsl плагина
        jobDsl scriptText: scriptText,
            lookupStrategy: 'SEED_JOB',
            ignoreExisting: false,
            removedJobAction: "DISABLE",
            sandbox: true
    } catch (Exception e) {
        error "Ошибка при выполнении скрипта: ${e.message}"
    }
}