import org.yaml.snakeyaml.Yaml
import com.cloudbees.hudson.plugins.folder.Folder
import hudson.model.TopLevelItem

def createFolder(String folderName) {
    def folderJob = Jenkins.instance.getItem(folderName)
    if (folderJob != null) {
        if (folderJob instanceof Folder) {
            try {
                folderJob.delete()
                echo "Удалена существующая папка ${folderName}"
            } catch (Exception e) {
                error "Ошибка при удалении папки ${folderName}: ${e.message}"
            }
        } else {
            error "Объект с именем ${folderName} существует, но это не папка."
        }
    }
    try {
        folderJob = Jenkins.instance.createProject(Folder.class, folderName)
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

