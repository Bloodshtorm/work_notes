# ansible list

Тут представлен ansible playbook для postgress и airflow

Структура простая, в dwh есть папки airflow postgres, они содержат playbook.

Хосты беруться из inventory.ini

А креды из secrets.yml ( Пароль: 1234 )

```
# Шифровать/Дешифровать можно так:
ansible-vault decrypt secrets.yml
ansible-vault encrypt secrets.yml

# У меня вот так:
ansible-vault decrypt /home/bs/source/git/work_notes/microservices/ansible/dwh/secrets.yml
ansible-vault encrypt /home/bs/source/git/work_notes/microservices/ansible/dwh/secrets.yml
```

Запускать playbook так можно:
```
ansible-playbook -i inventory.ini airflow/playbook.yml --ask-become-pass --ask-vault-pass
ansible-playbook -i inventory.ini postres/playbook.yml --ask-become-pass --ask-vault-pass
```