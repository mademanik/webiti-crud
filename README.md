# webiti-crud

### Feature
1. Java Springboot
2. Jwt Authentification
3. Refresh Token
4. Role Base Access Control (RBAC)
5. Create Read Update Delete (CRUD)

### Prerequisite
1. Java version: 17
   ```
   java version
   java version "17.0.6" 2023-01-17 LTS
   ```

### Installation Steps
1. Clone this repo
   ```
   git clone https://github.com/mademanik/webiti-crud.git
   ```

2. Run postgresql database
   ```
   cd docker/postgres
   docker compose up -d
   ```

3. Run application
   ```
   mvnw.cmd clean install
   mvnw.cmd spring-boot:run
   ```

4. Test auth using curl:
   ```
   curl -X POST "http://localhost:8080/auth/login" \
     -H "Content-Type: application/json" \
     -d '{
           "email": "super.admin@email.com",
           "password": "123456"
         }'
   ```
5. Open postman directory for more api, import webiti-crud.postman_collection.json to postman
6. Done
