Spring Boot Microservices (User + Order)
======================================

Contenido: dos microservicios Spring Boot:
- user-service: expone /users/{id}
- order-service: expone /orders/{id} y consulta user-service

Cómo usar (requiere Docker & Docker Compose):
1. Desde la carpeta donde esté docker-compose.yml:
   docker-compose up --build

2. Endpoints:
   - User:  http://localhost:8081/users/1
   - Order: http://localhost:8082/orders/1

Notas:
- Internamente cada servicio corre en el puerto 8080 dentro del contenedor.
- order-service toma la URL del user-service vía la variable de entorno USER_SERVICE_URL.
