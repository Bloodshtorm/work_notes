### Иногда при обновлении системы Ubuntu до версии 22.04 или до Linux Mint 21 «Vanessa» и при установке пакетов из сторонних репозиториев или ppa при apt update выскакивает предупреждение:
* ... Key is stored in legacy trusted.gpg keyring (/etc/apt/trusted.gpg), see the DEPRECATION section in apt-key(8) for details. *  
Это связано с тем, что Ubuntu 22.04 перешла от использования /etc/apt/trusted.gpg для хранения gpg ключей к использованию отдельных файлов .gpg, расположенных в /etc/apt/trusted.gpg.d. Само по себе это предупреждение ничего страшного не означает — в этой версии всё будет работать как прежде, но все warnings как то напрягают и не известно, что будет в следующих версиях.

Какой выход? Рассмотрим на примере программы keepassxc — достаточно популярного и надёжного менеджера паролей. После рекомендованных на сайте производителя подключения ppa:
```
sudo add-apt-repository ppa:phoerious/keepassxc
```
и
```
apt update
```
получаем предупреждение

*W: http://ppa.launchpad.net/phoerious/keepassxc/ubuntu/dists/jammy/InRelease: Key is stored in legacy trusted.gpg keyring (/etc/apt/trusted.gpg), see the DEPRECATION section in apt-key(8) for details.*
Можно конечно не обращать внимания, но лучше исправить в соответствии с требованиями новой версии. Сначала при помощи команды sudo apt-key list ищем точку входа для keepassxc.

```
sudo apt-key list
```

*Warning: apt-key is deprecated. Manage keyring files in trusted.gpg.d instead (see apt-key(8)).
/etc/apt/trusted.gpg
pub rsa4096 2017-10-23 [SC]
D89C 66D0 E31F EA28 74EB D205 6192 2AB6 0068 FCD6
uid [ неизвестно ] Launchpad PPA for Janek Bevendorff*

Затем мы конвертируем эту запись в файл .gpg, используя последние 8 цифровых символов, указанных выше.

```
apt-key export 0068FCD6 | sudo gpg --dearmour -o /etc/apt/trusted.gpg.d/keepassxc_key.gpg
```

При желании вы можете удалить устаревший ключ из /etc/apt/trusted.gpg, запустив:

```
sudo apt-key --keyring /etc/apt/trusted.gpg del 0068FCD6
```

Проверяем при помощи apt update и все предупреждения должны пропасть.