# 🔧 LocalServe - Local Service Provider Platform

A full-stack web application built with **Java Spring Boot**, **Oracle SQL**, and **HTML/CSS/JavaScript**.

---

## 🚀 Tech Stack

| Layer      | Technology                        |
|------------|-----------------------------------|
| Backend    | Java 17, Spring Boot 3.2          |
| Security   | Spring Security + JWT             |
| Database   | Oracle SQL (XE / 19c / 21c)       |
| Frontend   | HTML5, CSS3, Vanilla JavaScript   |

---

## ⚙️ Setup Instructions

### 1. Oracle Database Setup
```sql
-- Run schema.sql in Oracle SQL Developer or SQL*Plus
@src/main/resources/schema.sql
```

### 2. Configure Database
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:XE
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

### 4. Access the App
Open browser: **http://localhost:8080**

---

## 👤 Default Credentials

| Role     | Email                    | Password    |
|----------|--------------------------|-------------|
| Admin    | admin@localserve.com     | admin123    |
| Provider | Register via /pages/register.html | (your choice) |
| User     | Register via /pages/register.html | (your choice) |

---

## 📄 Pages

| Page                          | URL                              |
|-------------------------------|----------------------------------|
| Home / Landing                | /                                |
| Browse Services               | /pages/services.html             |
| Login                         | /pages/login.html                |
| Register                      | /pages/register.html             |
| User Dashboard                | /pages/user-dashboard.html       |
| Provider Dashboard            | /pages/provider-dashboard.html   |
| Admin Dashboard               | /pages/admin-dashboard.html      |

---

## 🔌 REST API Endpoints

### Auth
| Method | Endpoint              | Description       |
|--------|-----------------------|-------------------|
| POST   | /api/auth/register    | Register user     |
| POST   | /api/auth/login       | Login             |

### Services (Public)
| Method | Endpoint                          | Description           |
|--------|-----------------------------------|-----------------------|
| GET    | /api/services                     | All available services|
| GET    | /api/services/category/{category} | Filter by category    |
| GET    | /api/services/search?name=        | Search by name        |

### Provider (ROLE_PROVIDER)
| Method | Endpoint                    | Description         |
|--------|-----------------------------|---------------------|
| GET    | /api/provider/services      | My services         |
| POST   | /api/provider/services      | Add service         |
| PUT    | /api/provider/services/{id} | Update service      |
| DELETE | /api/provider/services/{id} | Delete service      |
| GET    | /api/provider/bookings      | My booking requests |

### User (ROLE_USER)
| Method | Endpoint              | Description      |
|--------|-----------------------|------------------|
| POST   | /api/user/bookings    | Create booking   |
| GET    | /api/user/bookings    | My bookings      |

### Shared
| Method | Endpoint                    | Description      |
|--------|-----------------------------|------------------|
| PUT    | /api/bookings/{id}/status   | Update status    |

### Admin (ROLE_ADMIN)
| Method | Endpoint                        | Description         |
|--------|---------------------------------|---------------------|
| GET    | /api/admin/dashboard            | Stats               |
| GET    | /api/admin/users                | All users           |
| GET    | /api/admin/providers            | All providers       |
| GET    | /api/admin/bookings             | All bookings        |
| PUT    | /api/admin/users/{id}/toggle    | Toggle user status  |
| DELETE | /api/admin/users/{id}           | Delete user         |

---

## 🗂️ Project Structure

```
src/main/java/com/localservice/
├── config/
│   ├── AdminSeeder.java       ← Auto-creates admin on startup
│   ├── JwtUtil.java           ← JWT token generation/validation
│   ├── JwtFilter.java         ← JWT request filter
│   └── SecurityConfig.java    ← Spring Security config
├── controller/
│   ├── AuthController.java
│   ├── ServiceController.java
│   ├── BookingController.java
│   ├── AdminController.java
│   └── GlobalExceptionHandler.java
├── model/
│   ├── User.java
│   ├── Service.java
│   ├── Booking.java
│   └── Review.java
├── repository/
│   ├── UserRepository.java
│   ├── ServiceRepository.java
│   ├── BookingRepository.java
│   └── ReviewRepository.java
├── service/
│   ├── AuthService.java
│   ├── ServiceProviderService.java
│   ├── BookingService.java
│   └── AdminService.java
└── dto/
    └── DTOs.java

src/main/resources/
├── static/
│   ├── index.html
│   ├── css/style.css
│   ├── js/api.js
│   ├── js/auth-guard.js
│   └── pages/
│       ├── login.html
│       ├── register.html
│       ├── services.html
│       ├── user-dashboard.html
│       ├── provider-dashboard.html
│       └── admin-dashboard.html
├── application.properties
└── schema.sql
```
