## Профили сборки

dev - сборка для запуска на локальной машине с java. предполагается что mongo запущена та той-же машине и доступна по localhost

test - сборка для запуска в docker контейнере. mongo должна запускаться в другом контейнере c именем `mongo`.

## Создание docker образа

```
mvn clean install dockerfile:build
```

## Запуск контейнера

```
docker run -d --name quest-server --link mongo -p <port>:8080 -e DISCORD_CLIENT_ID=<...> -e DISCORD_CLIENT_SECRET=<...> -e DISCORD_BOT_TOKEN=<...> mak256/quest-server
```

`<port>` - порт на котором будет доступно приложение
