
#### Описание проекта Репозиторий.

Репозиторий - это RESTful-приложение с использованием технологий Javа, Spring и архитектуры REST.

Данные передаются в формате JSON.

Для хранения данных используется PostgreSQL.

Схема базы данных:

![alt text](src/main/resources/pictures/db.png)

SQL - запросы для создания таблиц - в db/update.sql

API приложения:

![alt text](src/main/resources/pictures/api/api_repository.png)

![alt text](src/main/resources/pictures/api/api_commit.png)

Обработка исключений:
реализована в классе GlobalExceptionHandler.

![alt text](src/main/resources/pictures/exception/ExceptionMessage.png)

Скриншоты тестирования в Postman - в /resources/pictures/postman/:

![alt text](src/main/resources/pictures/postman/repository/findAll.png)

Используемые технологии:
- Java 11, Maven;
- Spring boot, Spring REST, Spring Data
- PostgreSQL;
- Postman;

