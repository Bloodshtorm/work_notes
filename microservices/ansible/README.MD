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


