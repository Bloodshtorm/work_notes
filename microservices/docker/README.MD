# docker list

*Избавиться от sudo*

```
sudo groupadd docker
sudo usermod -aG docker ${USER}
sudo usermod -aG docker bs
sudo chown "$USER":"$USER" /home/"$USER"/.docker -R
sudo chmod g+rwx "$HOME/.docker" -R
sudo chmod 666 /var/run/docker.sock
docker run hello-world
```

*Сборка приложения*
```
docker build -t webAPI-image -f Dockerfile .
docker create --name webapi-counter webapi-image
```
