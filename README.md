# Проект: "SecureRestProxy"

1) [контроллер ProxyController](#контроллер-proxycontroller)
2) [контроллер SecurityController](#контроллер-securitycontroller) 
3) [класс UserCache](#класс-usercache)
4) [Модели данных](#модели-данных)
5) [Конфигурация безопасности SecurityConfig](#конфигурация-безопасности-securityconfig)
6) [Обработчик глобальных исключений](#обработчик-глобальных-исключений)


# Контроллер ProxyController

Контроллер `ProxyController` обеспечивает взаимодействие с внешним API, проксируя `BASE_URL` для получения данных и отправки данных. 
Он также управляет данными пользователей, постов и альбомов в приложении.

- `BASE_URL`: Базовый URL внешнего API.

## Методы

### GET /api/users

- Описание: Получает список пользователей из внешнего API.
- Возвращает: Список пользователей.
- Обработка ошибок: Обрабатывает возможные ошибки HTTP.

### GET /api/posts

- Описание: Получает список постов из внешнего API.
- Возвращает: Список постов.
- Обработка ошибок: Обрабатывает возможные ошибки HTTP.

### GET /api/albums

- Описание: Получает список альбомов из внешнего API.
- Возвращает: Список альбомов.
- Обработка ошибок: Обрабатывает возможные ошибки HTTP.

### POST /api/users

- Описание: Создает нового пользователя и сохраняет его в базе данных. Поддерживет механизм кэширования объектов пользователей для ускорения работы с данными.
- Входные данные: Данные нового пользователя.
- Возвращает: Созданного пользователя.
- Обработка ошибок: Обрабатывает возможные ошибки HTTP.

### POST /api/posts

- Описание: Создает новый пост и сохраняет его в базе данных.
- Входные данные: Данные нового поста.
- Возвращает: Созданный пост.
- Обработка ошибок: Обрабатывает возможные ошибки HTTP.

### POST /api/albums

- Описание: Создает новый альбом и сохраняет его в базе данных.
- Входные данные: Данные нового альбома.
- Возвращает: Созданный альбом.
- Обработка ошибок: Обрабатывает возможные ошибки HTTP.

### DELETE /api/users

- Описание: Удаляет пользователя по его имени пользователя. Поддерживет механизм кэширования объектов пользователей для ускорения работы с данными.
- Входные данные: Имя пользователя.
- Обработка ошибок: Обрабатывает возможные ошибки HTTP.

### DELETE /api/posts

- Описание: Удаляет пост по его идентификатору.
- Входные данные: Идентификатор поста.
- Обработка ошибок: Обрабатывает возможные ошибки HTTP.

### DELETE /api/albums

- Описание: Удаляет альбом по его идентификатору.
- Входные данные: Идентификатор альбома.
- Обработка ошибок: Обрабатывает возможные ошибки HTTP.


# Класс UserCache

Класс `UserCache` предоставляет механизм кэширования объектов пользователей для ускорения работы с данными.

## Поля

- `userCache`: Коллекция для хранения объектов пользователей.

## Методы

### getUserFromCache(String userName)

- Описание: Получает пользователя из кэша по его имени.
- Входные данные: Имя пользователя.
- Возвращает: Объект пользователя из кэша.

### addToCache(User user)

- Описание: Добавляет пользователя в кэш.
- Входные данные: Объект пользователя для добавления в кэш.

### containsUser(String userName)

- Описание: Проверяет наличие пользователя в кэше.
- Входные данные: Имя пользователя.
- Возвращает: `true`, если пользователь найден в кэше, иначе `false`.

### removeFromCache(String userName)

- Описание: Удаляет пользователя из кэша.
- Входные данные: Имя пользователя.

## Использование

Класс `UserCache` предназначен для ускорения работы с данными путем кэширования объектов пользователей. При необходимости получения, добавления, проверки наличия или удаления пользователя, класс `UserCache` обеспечивает быстрый доступ к данным из оперативной памяти.


# Контроллер SecurityController

Контроллер `SecurityController` отвечает за управление данными безопасности, такими как пользователи, роли и пароли.

## Методы

### GET /api/security

- Описание: Получает список данных безопасности.
- Возвращает: Список данных безопасности.
- Обработка ошибок: Обрабатывает возможные ошибки HTTP.

### POST /api/security

- Описание: Создает новую запись в данных безопасности.
- Входные данные: Логин, роль и пароль для создания новой записи.
- Возвращает: Успешное сообщение об операции.
- Обработка ошибок: Обрабатывает возможные ошибки HTTP.

### DELETE /api/security

- Описание: Удаляет запись из данных безопасности по логину.
- Входные данные: Логин пользователя для удаления.
- Возвращает: Успешное сообщение об операции.
- Обработка ошибок: Обрабатывает возможные ошибки HTTP.

Контроллер `SecurityController` предоставляет возможность управления данными безопасности, включая создание, чтение и удаление записей о пользователях, ролях и паролях.


# Модели данных

Это основные модели, используемые в проекте для хранения данных пользователей, альбомов, постов, записей аудита и информации о безопасности.

## Модель User

Модель User представляет собой информацию о пользователе, включая его имя, учетные данные, контактную информацию, адрес проживания и информацию о работе. 
Уникальный индификатор: username;

**Таблица:** `users`  
**Столбцы:**
- `id`: Уникальный идентификатор пользователя.
- `name`: Имя пользователя.
- `username`: Имя пользователя в системе.
- `email`: Электронная почта пользователя.
- `address`: Адрес пользователя.
  - `street`: Улица.
  - `suite`: Дополнительная информация об адресе.
  - `city`: Город.
  - `zipcode`: Почтовый индекс.
  - `geo`: Географические координаты.
    - `lat`: Широта.
    - `lng`: Долгота.
- `phone`: Номер телефона пользователя.
- `website`: Веб-сайт пользователя.
- `company`: Информация о компании пользователя.
  - `name`: Название компании.
  - `catchPhrase`: Фраза компании.
  - `bs`: Основная сфера деятельности.


## Модель Album

Модель Album представляет альбомы, которые принадлежат определенным пользователям.
У пользователя может быть несколько альбомов.

**Таблица:** `albums`  
**Столбцы:**
- `id`: Уникальный идентификатор альбома.
- `userId`: Идентификатор пользователя, которому принадлежит альбом.
- `title`: Название альбома.

## Модель Post

Модель Post представляет собой посты, созданные пользователями.
У пользователя может быть несколько постов.

**Таблица:** `posts`  
**Столбцы:**
- `id`: Уникальный идентификатор поста.
- `userId`: Идентификатор пользователя, создавшего пост.
- `title`: Заголовок поста.
- `body`: Тело поста.

  
## Модель AuditLog

Модель AuditLog используется для ведения аудита действий пользователей в системе.

**Таблица:** `audit_log`  
**Столбцы:**
- `id`: Уникальный идентификатор записи аудита.
- `ipAddress`: IP-адрес, с которого был выполнен запрос.
- `loggedIn`: Флаг, указывающий, был ли выполнен вход.
- `date`: Дата и время выполнения действия.
- `login`: Логин пользователя, выполнившего действие.
- `methodName`: Название метода, для которого выполнялось действие.
- `parameters`: Параметры запроса.


## Модель Security

Модель Security используется для хранения информации о пользователях, их ролях и учетных данных.

**Таблица:** `security`  
**Столбцы:**
- `id`: Уникальный идентификатор записи о безопасности.
- `role`: Роль пользователя.
- `login`: Логин пользователя.
- `password`: Пароль пользователя.

# Конфигурация безопасности SecurityConfig

Класс `SecurityConfig` отвечает за настройку безопасности веб-приложения с использованием Spring Security.

## Роли и права доступа

| Роль      | Права доступа                                   |
|-----------|------------------------------------------------|
| ADMIN     | Полные права на все ресурсы                    |
| EDITOR    | Права на редактирование                         |
| VIEWER    | Ограниченные права на чтение                   |
| USERS     | Доступ к управлению пользователями              |
| POSTS     | Доступ к управлению постами                     |
| ALBUMS    | Доступ к управлению альбомами                   |
| SECURITY  | Доступ к управлению безопасностью                |

## Механизмы аутентификации

- Базовая аутентификация HTTP используется для проверки подлинности пользователей при каждом запросе.
- Пароли пользователей хранятся в зашифрованном виде с использованием `BCryptPasswordEncoder`.

## HTTP Методы и Права доступа

Для каждого HTTP метода и конечной точки (`/api/posts/**`, `/api/users/**`, `/api/albums/**`, `/api/security/**`), определены различные права доступа в зависимости от роли пользователя.

| Метод     | Ресурс               | Права доступа                     |
|-----------|----------------------|----------------------------------|
| GET       | /api/posts/**       | POSTS, EDITOR, ADMIN             |
| GET       | /api/users/**       | USERS, EDITOR, ADMIN             |
| GET       | /api/albums/**      | ALBUMS, EDITOR, ADMIN            |
| GET       | /api/security/**    | SECURITY, EDITOR, ADMIN          |
| POST      | /api/posts/**       | POSTS, VIEWER, ADMIN             |
| POST      | /api/users/**       | USERS, VIEWER, ADMIN             |
| POST      | /api/albums/**      | ALBUMS, VIEWER, ADMIN            |
| POST      | /api/security/**    | SECURITY, VIEWER, ADMIN          |
| DELETE    | /api/posts/**       | POSTS, VIEWER, ADMIN             |
| DELETE    | /api/users/**       | USERS, VIEWER, ADMIN             |
| DELETE    | /api/albums/**      | ALBUMS, VIEWER, ADMIN            |
| DELETE    | /api/security/**    | SECURITY, VIEWER, ADMIN          |

Класс `SecurityConfig` определяет, как пользователи веб-приложения будут аутентифицироваться и авторизовываться, а также какие права доступа предоставляются каждой роли.

# Обработчик глобальных исключений

Класс `GlobalExceptionHandler` является компонентом Spring, предназначенным для обработки исключений, возникающих в приложении.

## Описание класса GlobalExceptionHandler

- Аннотация `@ControllerAdvice` указывает, что данный класс является глобальным обработчиком исключений для всех контроллеров в приложении.

- Метод `handleCustomException` отвечает за обработку исключений типа `CustomException`. При возникновении такого исключения формируется объект `ResponseEntity` с информацией об ошибке в формате JSON.

## Класс CustomException

Класс `CustomException` является пользовательским исключением, которое может быть выброшено в различных частях приложения.

### Поля класса CustomException

- `errorCode` - код ошибки
- `message` - сообщение об ошибке

## Таблица кодов ошибок и сообщений исключений

| Имя исключения           | Код ошибки |
|--------------------------|------------|
| USER_NOT_FOUND           | 1          |
| USER_ALREADY_EXISTS      | 2          |
| SECURITY_NOT_FOUND       | 3          |
| SECURITY_ALREADY_EXISTS  | 4          |
| ROLE_NOT_FOUND           | 5          |
| POST_NOT_FOUND           | 6          |
| ALBUM_NOT_FOUND          | 7          |

Исключения отлавливаются и обрабатываются автоматически, а затем формируются в ответ в виде JSON объекта с соответствующим кодом ошибки и сообщением.

