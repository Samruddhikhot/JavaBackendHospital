# 🏥 Hospital Management System - Backend

This is the **backend service** for the Hospital Management System, built using **Java Spring Boot** and **MySQL**.  
It provides REST APIs for managing doctors, patients, appointments, and prescriptions.

---

## ⚙️ Tech Stack
- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Maven

---

## 🚀 Features
- Role-based authentication (Admin, Doctor, Patient)
- Manage doctors, patients, and appointments
- Prescription handling
- REST APIs for frontend integration
- MySQL database support

---

## 🔧 How to Run in Eclipse

1. Copy the project folder path of this repository.  
2. Open **Eclipse IDE** → Go to **File → Import → Existing Maven Project**.  
3. Paste the copied folder path and click **Finish**.  
4. Open `src/main/resources/application.properties` and update your MySQL details:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/hospitaldb
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
Right-click the project → Run As → Spring Boot App.

The backend will start at:
http://localhost:8080


📂 Example API Endpoints

POST /api/auth/login → User login

GET /api/doctors → Fetch all doctors

POST /api/appointments → Book appointment

GET /api/patients/{id} → Get patient details

You can find frontend -> https://github.com/Samruddhikhot/ReactFrontendHospital
