# 🏦 Secure Bank Portal

A secure banking web portal built using Core Java web technologies with user authentication 
and essential banking operations.

## Features
- User registration and secure login with session management
- Account dashboard with balance and transaction history
- Fund transfer between accounts
- Secure SQL database integration via JDBC
- Server-side validation using Servlets and JSP

## Tech Stack
- Java, JSP, Servlets, JDBC
- Apache Tomcat (Server)
- SQL (Database)
- HTML, CSS (Frontend)

## How to Run
1. Clone the repository
2. Import the project into Eclipse or IntelliJ
3. Set up the SQL database and run the provided `.sql` schema file
4. Configure the JDBC connection in `db.properties` or `DBConnection.java`
5. Deploy the project on Apache Tomcat
6. Access at `http://localhost:8080/secure-bank-portal`

## Project Structure
```
secure-bank-portal/
├── src/
│   └── (Servlet .java files)
├── WEB-INF/
│   ├── classes
│   ├── lib (Resource files)
│   └── xml file
├── .jsp files
└── database.sql
```
