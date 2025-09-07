# Miniproject (Java + MySQL)

A simple console-based Java application to manage students with **CRUD** operations (Create, Read, Update, Delete).  
Built using plain Java (no Maven/Gradle) in IntelliJ IDEA, with MariaDB/MySQL as the database.

---

## ⚙️ Setup Instructions

### 1. Install XAMPP (or MySQL/MariaDB)
- Download and install [XAMPP](https://www.apachefriends.org/).
- Start the **MySQL** (MariaDB) service from the XAMPP Control Panel.

### 2. Create Database and User
Open a terminal or IntelliJ Terminal and connect to MariaDB:
```bash
C:\xampp\mysql\bin\mysql.exe -u root -p
CREATE DATABASE student_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Optional: create a dedicated user (instead of using root)
CREATE USER 'Miniproject'@'localhost' IDENTIFIED BY 'StrongPass123!';
GRANT ALL PRIVILEGES ON student_db.* TO 'Miniproject'@'localhost';
FLUSH PRIVILEGES;
```
### 3. Configure Database Credentials
```bash
private static final String USER = "Miniproject";   // or "root"
private static final String PASS = "StrongPass123!"; // or "" if root has no password
```
### 4. Add JDBC Driver

Download MySQL Connector/J (JAR) from MySQL Downloads
.
Copy the JAR into a lib/ folder in your project.

In IntelliJ:
File → Project Structure → Modules → Dependencies → + (Add JAR) → select the connector.

### 5. Run the App

In IntelliJ, right-click ConsoleApp.java → Run 'ConsoleApp.main()'.

The menu will appear:
```bash
=== Student Database App ===
1) Add student
2) List students
3) Update student
4) Delete student
0) Exit
```
### Project Structure
```bash
Miniproject/
 ├─ src/
 │   └─ app/
 │       ├─ Student.java
 │       ├─ Database.java
 │       ├─ StudentDAO.java
 │       └─ ConsoleApp.java
 └─ External Libraries/
     └─ mysql-connector-j-8.0.xx.jar

```
### Example Usage
#### Adding a student
```bash
=== Student Database App ===
1) Add student
2) List students
3) Update student
4) Delete student
0) Exit
   Select: 1
   Name  : Alice
   Email : alice@example.com
   Course: Computer Science
   Added.
```
#### Listing students
```bash
=== Student Database App ===
1) Add student
2) List students
3) Update student
4) Delete student
0) Exit
Select: 2
ID   | NAME   | EMAIL               | COURSE
-----------------------------------------------
1    | Alice  | alice@example.com   | Computer Science

```
#### Notes

Tested with MariaDB 10.4 (XAMPP).
Works with Java 8+.
