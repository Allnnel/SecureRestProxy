
# Проект: RESTful API для JSONPlaceholder

Проект представляет собой RESTful API, разработанное с использованием Java >=17. Его основная цель - проксирование запросов к JSONPlaceholder (https://jsonplaceholder.typicode.com/) с реализацией базовой авторизации, ролевой модели доступа, аудита действий и ведения кэша.

## Особенности проекта:

- **Проксирование запросов:** Приложение позволяет проксировать запросы к различным конечным точкам JSONPlaceholder.
- **Безопасность и управление доступом:** Реализуется базовая авторизация и ролевая модель доступа, которая включает в себя роли ROLE_ADMIN, ROLE_POSTS, ROLE_USERS, ROLE_ALBUMS.
- **Аудит действий:** Ведется журналирование действий, включая дату-время, пользователя, действие и параметры запроса.
- **Управление кэшем:** Для снижения нагрузки на JSONPlaceholder реализуется in-memory кэш, где изменения данных сначала происходят в кэше, а затем отправляются запросы на JSONPlaceholder.
- **Простота запуска:** Проект должен легко запускаться с использованием инструментов сборки, таких как Gradle или Maven.
- **Использование базы данных:** Предполагается использование базы данных для ведения аудита и хранения данных пользователей.
- **Расширенная ролевая модель и другие функции:** Включает в себя создание пользователей, расширенную ролевую модель, написание юнит-тестов и реализацию конечной точки для запросов по WebSocket.

## Цель проекта:
Предоставить возможность потенциальным разработчикам продемонстрировать свои навыки разработки, понимание безопасности и управления данными.

# Требования

1) **Реализация обработчиков HTTP методов:**
    - GET, POST, PUT, DELETE запросы, которые проксируются к https://jsonplaceholder.typicode.com/
    - Конечные точки:
        - /api/posts/**
        - /api/users/**
        - /api/albums/**

2) **Базовая авторизация:**
    - Несколько аккаунтов с разными ролями:
        - ROLE_ADMIN - имеет доступ ко всем обработчикам
        - ROLE_POSTS - имеет доступ к обработчикам /posts/**
        - ROLE_USERS - имеет доступ к обработчикам /users/**
        - ROLE_ALBUMS - имеет доступ к обработчикам /albums/**

3) **Ролевая модель доступа:**
    - Минимум 4 роли, каждая с определенными привилегиями.

4) **Ведение аудита действий:**
    - Регистрация даты-времени, пользователя, параметров запроса и доступа.

5) **In-memory кэш:**
    - Уменьшение числа запросов к jsonplaceholder.
    - Изменения данных сначала происходят в кэше, а затем отправляются запросы на jsonplaceholder.

6) **Плюсы:**
    - Простота запуска приложения с использованием инструментов сборки, таких как Gradle или Maven.
    - Использование базы данных:
        - Для ведения аудита.
        - Для хранения данных пользователей.
        - Добавление REST API для создания пользователей.
    - Расширенная ролевая модель с новыми ролями, например, ROLE_POSTS_VIEWER, ROLE_POSTS_EDITOR, и т.д.
    - Написание юнит-тестов для проверки корректности работы кода.
    - Реализация конечной точки для запросов по WebSocket (/ws), перенаправляющая запросы на echo server https://websocket.org/tools/websocket-echo-server/
    - Базовая авторизация и ролевая модель, ведение аудита запросов. (Кэш не требуется).
    - Использование wss://echo.websocket.org для подключения по WebSocket.


