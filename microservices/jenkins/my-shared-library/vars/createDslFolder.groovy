// vars/createDslFolder.groovy
import dsl.CreateFolder

def call(String folderName) {
    try {
        echo "Вызов generateScript из CreateFolder"
        def scriptText = CreateFolder.generateScript(folderName)
        echo "Сгенерированный скрипт:\n${scriptText}"

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
