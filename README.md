# 📝 TaskManagement Application

TaskManagement is a Spring Boot-based web application that allows Admins and Users to manage and track tasks efficiently. The system supports email verification, OTP authentication, and scheduled token cleanup.

---

## 🚀 Features

### 👤 User Management
- User Registration with Email Verification
- Email-based OTP Verification for account activation
- OTP-based login mechanism
- Secure authentication with Spring Security

### 🗂️ Task Management
- Create, Assign, and Update Tasks
- Role-based access for Admin and User
- Task status updates: Pending, In Progress, Completed

### 🛡️ Admin Panel
- Manage Users and Roles
- Assign tasks to users
- View task statistics

### 📧 Email Services
- Sends email verification links upon registration
- OTP-based login with expiry
- Configurable SMTP support using Gmail

### 🔐 OTP & Token Handling
- OTPs expire after 5 minutes
- Token cleanup using Spring Scheduler every 10 minutes

---

## ⚙️ Tech Stack

| Layer         | Technology                   |
|---------------|-------------------------------|
| Backend       | Spring Boot, Spring Security  |
| Database      | PostgreSQL                    |
| Email Service | JavaMailSender (SMTP)         |
| Scheduler     | Spring Task Scheduler         |
| ORM           | Spring Data JPA               |

---

## 📦 Project Structure

```plaintext
com.project.taskManagement
├── controller       # REST Controllers
├── entity           # JPA Entities (User, Task, OTP, Token)
├── repository       # Spring Data Repositories
├── service          # Business Logic (EmailService, UserService, etc.)
├── scheduler        # Scheduled cleanup jobs
└── config           # Security and Application Config
