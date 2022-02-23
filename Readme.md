1. Пвраметры для подключение к БД:
   url=jdbc:postgresql://localhost:5432/testdb
   
   username=testuser
   
   password=1234
   
2. Запрос на аутентификацию для получения токена:
   http://localhost:8087/taskuser/api/security/auth
    
    Далее для всех остальных запросов необходимо добавить 
токен в заголовок Authorization по шаблону "Bearer ${token}"
   
3. После миграции в БД сразу заносится пользователь c
логин/пароль admin/admin. У данного пользователя есть
   все необходимы роли (ROLE_USER, ROLE_ADMIN) для выполнения 
   всех запросов. 
   
4. Адрес веб страницы для swagger:
   http://localhost:8087/taskuser/swagger-ui/
   