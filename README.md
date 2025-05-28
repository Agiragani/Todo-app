# Todo-app

dockerized todo-app steps:
🔹 1. Prepare Your Spring Boot App
Make sure your app works locally first with application.properties using environment variables:
properties

spring.datasource.url=${SPRING_DATASOURCE_URL}
spring.datasource.username=${SPRING_DATASOURCE_USERNAME}
spring.datasource.password=${SPRING_DATASOURCE_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

🔹 2. Create Dockerfile for Spring Boot App
In your Spring Boot project root:
dockerfile

# Dockerfile

# Start from Java base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy jar into container
COPY target/app.jar app.jar

# Expose the port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
Ensure your jar is named app.jar. You can rename it with Maven:

CopyEdit
mvn clean package
mv target/your-app-name.jar target/app.jar

🔹 3. Build Docker Image
In your project root:

docker build -t todo-app .

🔹 4. Run MySQL Container

docker run -d \
--name mysql-db \
-e MYSQL_ROOT_PASSWORD=Mysql123@ \
-e MYSQL_DATABASE=todo_management \
-p 3306:3306 \
mysql


Make sure MySQL is running before moving to the next step:

docker ps

🔹 5. Run Spring Boot Container Linked to MySQL

docker run -d \
--name todo-app \
-p 8080:8080 \
--link mysql-db:mysql \
-e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/todo_management \
-e SPRING_DATASOURCE_USERNAME=root \
-e SPRING_DATASOURCE_PASSWORD=Mysql123@ \
todo-app
💡 --link mysql-db:mysql makes the hostname mysql resolvable inside the app container.

🔹 6. Test Application
Open your browser or Postman:

arduino

http://localhost:8080
Or use docker logs to check your app container logs:

docker logs -f todo-app

🔹 7. Clean Up (Optional)

docker stop todo-app mysql-db
docker rm todo-app mysql-db
docker rmi todo-app


Test application

Post :
http://localhost:8080/api/todos
Body (raw → JSON):
{
"title": "Workout",
"description": "Cardio and weights",
"completed": false
}


GET /api/todos/{id}

PUT /api/todos/{id}

PATCH /api/todos/{id}/complete

PATCH /api/todos/{id}/incomplete

DELETE /api/todos/{id}