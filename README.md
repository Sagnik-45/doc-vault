# 📂 Document Management System (Spring Boot)

A backend REST API built using **Spring Boot** to manage documents through upload, storage, categorization, and retrieval.
The system is designed to simulate how enterprise content platforms handle **document storage, metadata management, and workflow processing**.

---

## 🚀 Features

* 📤 Upload and store documents
* 🗂️ Maintain metadata (name, type, tags, status)
* 🔍 Search and filter documents based on metadata
* 🔄 Basic workflow management (Uploaded → Approved → Rejected)
* ⚡ RESTful APIs with clean architecture
* 🛡️ Global exception handling for consistent error responses

---

## 🏗️ System Design

The system follows a **separation of concerns** approach:

* **File Storage** → Stored on disk
* **Metadata Storage** → Stored in MySQL

This design improves:

* Query performance (no need to load files during search)
* Scalability (can move to distributed storage later)

---

## 🧠 Key Concepts Used

* REST API design
* Layered Architecture (Controller → Service → Repository)
* DTO pattern for clean API contracts
* JPA & Hibernate for persistence
* Basic workflow/state management
* Input validation and exception handling

---

## ⚙️ Tech Stack

* **Backend:** Java, Spring Boot
* **Database:** MySQL
* **ORM:** Hibernate, JPA
* **Tools:** IntelliJ IDEA, Git

---

## 📌 API Overview

### Upload Document

`POST /documents/upload`

### Get All Documents

`GET /documents`

### Search Documents

`GET /documents/search?name=&type=`

### Update Status (Workflow)

`PUT /documents/{id}/status`

---

## 🔄 Workflow Logic

Each document follows a simple lifecycle:

```
UPLOADED → APPROVED → REJECTED
```

This simulates real-world approval workflows in enterprise systems.

---

## 📈 Scalability Considerations

This project is designed with scalability in mind:

* Database indexing for faster search
* Pagination for large datasets
* Can be extended with:

  * Cloud storage (AWS S3, etc.)
  * Caching (Redis)
  * Search engines (Elasticsearch)

---

## 🎯 Learning Outcome

This project helped me understand:

* How document management systems are designed
* How to separate data storage from file handling
* How workflows can be modeled in backend systems
* How to build scalable and maintainable APIs

---

## 📎 Future Improvements

* Role-based access control (RBAC)
* File versioning
* Full-text search
* Distributed file storage

---


## 👨‍💻 Author

**Sagnik Chatterjee**
B.Tech CSE, KIIT University
