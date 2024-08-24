def call(String folderName) {
    try {
        // Прямо генерируем DSL-скрипт для создания папки
        def scriptText = """
        folder('${folderName}') {
            description("This is the folder for ${folderName}")
        }
        """
        echo "Скрипт сгенерирован:\n${scriptText}"

        // Запуск DSL-скрипта с использованием плагина jobDsl
        jobDsl scriptText: scriptText,
            lookupStrategy: 'SEED_JOB',
            ignoreExisting: false,
            removedJobAction: "DISABLE",
            sandbox: false
    } catch (Exception e) {
        error "Ошибка при выполнении скрипта: ${e.message}"
    }
}
