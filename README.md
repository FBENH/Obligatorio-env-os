# Package Shipping Management System

This is a web-based package shipping platform built with the **Spring Framework**, **Spring Security**, **Spring Data JPA**, **Thymeleaf**, and **MySQL**. The system is designed for a shipping company with two types of users: **company employees** and **registered customers**.

## Key Features

- **User Management:** Secure login, registration, and profile management. Employees can manage other employees, while customers can register and track their shipments.
  
- **Employee Management:** Employees can manage their own accounts, assign themselves to specific locations, and update their information as needed.

- **Package Management:** Customers can register packages and track their status (e.g., "in transit", "delivered"). Employees can update package data, including status changes and category assignments.

- **Category & Status Management:** Employees can create and manage package categories and tracking statuses. Categories and statuses cannot be deleted if they are associated with active packages.

- **Advanced Search & Filtering:** Both employees and customers can search packages by criteria such as package ID, customer ID, and status. The system supports pagination for easy navigation through large datasets.

- **Self-Registration for Customers:** Customers can register and manage their information. Accounts can be deleted logically if the customer has active packages.

## Technologies Used

- **Spring Framework**
- **Spring Security** (for authentication and authorization)
- **Spring Data JPA** (for database management)
- **Thymeleaf** (for frontend rendering)
- **MySQL** (for database storage)
