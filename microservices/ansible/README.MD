# ansible list

```
sudo apt update
sudo apt install ansible

sudo mkdir /etc/ansible
#Файл по умолчанию:
sudo nano /etc/ansible/hosts
```

Содержимое */etc/ansible/hosts*
```
[servers]
server1 ansible_host=localhost

[all:vars]
ansible_python_interpreter=/usr/bin/python3
ansible_connection=ssh
ansible_user=bs
ansible_ssh_pass=1234
```

Сработает только если в /etc/ssh/sshd_config разрешен вход под root
(Так делать нельзя!)
```
sudo nano /etc/ssh/sshd_config
PermitEmptyPasswords yes
```

Запуск ситуативных команд (опционально)
Убедившись, что узел управления Ansible может взаимодействовать с хостами, вы можете запускать на серверах ситуативные команды и плейбуки.

Любая команда, обычно запускаемая на удаленном сервере через SSH, может быть запущена с помощью Ansible на серверах, заданных в файле инвентаризации. Например, вы можете проверить использования дисковых ресурсов всеми серверами с помощью следующей команды:

```
ansible all -a "df -h" -u root
```

Как playbook мутить
```
mkdir source
cd source/
```