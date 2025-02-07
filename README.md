# Database Locking Mechanism in Spring Boot

## 📌 Overview
This project demonstrates **Optimistic and Pessimistic Locking** mechanisms in **Spring Boot with JPA and Hibernate** to handle concurrent database access efficiently. It is a practical implementation of how to manage concurrent seat booking transactions while preventing **dirty reads, non-repeatable reads, and phantom reads**.

---

## 🚀 Problem Statement
When multiple users attempt to book the same seat concurrently, data inconsistency can occur. To solve this:
1. **Optimistic Locking** prevents multiple users from updating the same record simultaneously by using a **version number**.
2. **Pessimistic Locking** prevents concurrent modifications by **locking the record until the transaction is completed**.

---

## 🛠️ Tech Stack & Tools Used
- **Backend:** Java, Spring Boot, Spring Data JPA
- **Database:** MySQL (or PostgreSQL)
- **Concurrency Management:** Optimistic & Pessimistic Locking
- **Logging:** SLF4J & Logback
- **Build Tool:** Maven
- **Version Control:** Git & GitHub

---

## 🔥 Features Implemented
- **Optimistic Locking:** Uses a version field to detect conflicts.
- **Pessimistic Locking:** Uses database-level locking to prevent concurrent modifications.
- **Thread Simulation:** Uses multiple threads to simulate concurrent seat booking.
- **REST APIs:** Endpoints for testing both locking strategies.
- **Logging:** Logs every step for debugging and monitoring.

---

## 📂 Project Structure
```
📦 database-locking
┣ 📂 src/main/java/com/javanik/in/databaselocking
┃ ┣ 📂 controller
┃ ┃ ┗ 📜 BookingController.java
┃ ┣ 📂 entity
┃ ┃ ┗ 📜 SeatDetails.java
┃ ┣ 📂 repo
┃ ┃ ┗ 📜 SeatRepo.java
┃ ┣ 📂 service
┃ ┃ ┣ 📜 TicketBooking.java
┃ ┃ ┗ 📜 ByOptimisticBookingService.java
┃ ┃ ┗ 📜 ByPessimisticBookingService.java
┃ ┣ 📜 DatabaseLockingApplication.java
┣ 📜 pom.xml
┣ 📜 README.md
```

---

## ⚡ API Endpoints

### 1️⃣ **Optimistic Locking**
```http
GET /api/optimistic/{seatId}
```
- **Description:** Tries to book a seat using optimistic locking.
- **Response:** `"Optimistic locking started"`

### 2️⃣ **Pessimistic Locking**
```http
GET /api/pessimistic/{seatId}
```
- **Description:** Tries to book a seat using pessimistic locking.
- **Response:** `"Pessimistic locking started"`

---

## 📌 How to Run the Project
### 1️⃣ Clone the Repository
```sh
git clone https://github.com/NikhilRajOfficial/database-locking.git
cd database-locking
```

### 2️⃣ Configure Database
Update `application.properties` with your MySQL/PostgreSQL database credentials.
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### 3️⃣ Run the Application
```sh
mvn spring-boot:run
```

### 4️⃣ Test APIs
- Use **Postman** or **cURL** to hit the APIs.
- Monitor logs to observe the locking behavior.

---

## 🛠️ How It Works
1. **Optimistic Locking:** Uses a `version` field in `SeatDetails`. If the version is outdated during an update, the transaction fails.
2. **Pessimistic Locking:** Uses `@Lock(LockModeType.PESSIMISTIC_WRITE)` to lock the record, preventing other transactions from accessing it until the first one completes.

---

## 🏗️ Future Improvements
- Implement **Redisson Distributed Locking** for better scalability.
- Enhance logging with **Prometheus & Grafana**.
- Add **Unit Tests (JUnit & Mockito)**.
- Introduce **Kafka for Event-Driven Booking System**.

---

## ⚖️ License
This project is **open-source**. You are free to use, modify, and distribute it.

---

## 👥 Contributing
Feel free to contribute by:
- Submitting bug reports
- Suggesting new features
- Improving documentation

Fork the repository and raise a **Pull Request (PR)**! 🚀

---

## 📞 Contact
🔗 **GitHub:** [NikhilRajOfficial](https://github.com/NikhilRajOfficial)  
🔗 **LinkedIn:** [Nikhil Raj](https://www.linkedin.com/in/nikhilraj620/)  
📧 **Email:** nikhilraj2277@gmail.com  

