# LogBot

**LogBot** - это инструмент для автоматического отслеживания ошибок и уведомления разработчиков о появлении определённых
логов.

LogBot позволяет пользователям создавать конфигурации для мониторинга логов. Каждая конфигурация включает:

* название конфигурации
* регулярное выражение для поиска по логам
* сообщение, которое выводится, когда логи соответствуют регулярному выражению

Пользователи могут активировать или деактивировать конфигурации, получая уведомления только по активным.

LogBot реализован как групповой проект студентами программ "Руководитель IT-разработки" и "Распределённые веб-сервисы"
университета ИТМО.

## Команда

* [Владимир Росляков](https://github.com/maxizenit) (Project Manager, Frontend Developer)
* [Михаил Ботов](https://github.com/MishaV1) (Backend Developer)
* [Семён Шагиев](https://github.com/GesuYaro) (Backend Developer)
* [Максим Пузанов](https://github.com/maxizenit) (Fullstack Developer)
* [Фарид Курбанов](https://github.com/farid03) (DevOps)

## Технологии

* Kotlin
* Spring Boot
* Jmix
* Kafka
* PostgreSQL
* H2
* Caffeine
* Liquibase
* Flyway

## Модули

Вот пример описания модулей для README:

---

## Модули проекта

### Общие модули

* [logbot-commons](./logbot-commons) - библиотека общих классов
* [logbot-server-rest-client](./logbot-server-rest-client) - библиотека REST-клиентов для бэкенда

### Бэкенд

* [logbot-server](./logbot-server) - основной бэкенд
* [logbot-auth](./logbot-auth) - сервис авторизации

### Фронтенд

* [logbot-bot](./logbot-bot) - Telegram-бот
* [logbot-web-jmix](./logbot-web-jmix) - веб-приложение для управления конфигурациями

### Вспомогательные модули

* [logbot-log-generator](./logbot-log-generator) - генератор логов для тестирования