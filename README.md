# 🎒 Lost and Found System for College

A full-stack web application to help students and administrators manage lost and found items on a college campus. Built using **Spring Boot**, **Angular**, and **MySQL**.

---

## 🚀 Features

* 🔍 Report lost or found items with details and optional images  
* 🧾 Submit claims for found items  
* ✅ Admin approval or denial of claims  
* 🗃️ View all lost or found items  
* 🔐 Secure login with role-based access (USER & ADMIN)  
* 🌐 CORS and token-based authentication included  
* 🖼️ Image upload and static serving  

---

## 🛠️ Tech Stack

| Layer        | Technology                     |
| ------------ | ------------------------------ |
| Frontend     | Angular, HTML, CSS, TypeScript |
| Backend      | Spring Boot (REST API)         |
| Database     | MySQL                          |
| Auth         | Token-based (UUID)             |
| Image Upload | Multipart + Static Access      |

---

## 👤 User Roles

| Role   | Capabilities                                            |
| ------ | ------------------------------------------------------- |
| Public | View all items, view images                             |
| USER   | Report lost/found item, claim found item                |
| ADMIN  | View all claims, approve/deny claims, edit/delete items |

---

## 🧪 API Overview

### 🔹 Item APIs

* `GET /api/items` — All items  
* `GET /api/items/found` — Found items only  
* `POST /api/items/lost` — Report lost item  
* `POST /api/items` — Add item  
* `PUT /api/items/{id}` — Update item (ADMIN)  
* `DELETE /api/items/{id}` — Delete item & claims (ADMIN)  

### 🔹 Claim APIs

* `POST /api/items/{id}/claim` — Claim an item  
* `GET /api/claims` — View all claims (ADMIN)  
* `PUT /api/claims/{id}/approve` — Approve claim (ADMIN)  
* `PUT /api/claims/{id}/deny` — Deny claim (ADMIN)  

### 🔹 Auth API

* `POST /api/auth/login` — Login and receive token  

---

## 📸 Screenshots

> Click on any image to view full screen on GitHub.

### 🔐 Login Page

![Login Page](https://github.com/nazeer-shaik-01/Lost-and-Found-System-for-College/blob/main/Login-Page.png?raw=true)

### 🏠 Home Page

![Home Page](https://github.com/nazeer-shaik-01/Lost-and-Found-System-for-College/blob/main/Home%20Page.png?raw=true)

### 🧾 Item Lost Details

![Item Lost Details](https://github.com/nazeer-shaik-01/Lost-and-Found-System-for-College/blob/main/Item%20Lost%20Details.png?raw=true)

### 🧳 Item Found Details

![Item Found Details](https://github.com/nazeer-shaik-01/Lost-and-Found-System-for-College/blob/main/Item%20Found%20Details.png?raw=true)

### 📦 All Found Items

![All Found Items](https://github.com/nazeer-shaik-01/Lost-and-Found-System-for-College/blob/main/All%20Found%20Items.png?raw=true)

### 🧾 Report a New Item

![Report a New Item](https://github.com/nazeer-shaik-01/Lost-and-Found-System-for-College/blob/main/Report%20a%20New%20Item.png?raw=true)

### 📝 Submit a Claim

![Submit a Claim](https://github.com/nazeer-shaik-01/Lost-and-Found-System-for-College/blob/main/Submit%20a%20Claim.png?raw=true)

### ✅ Claimed Items (Admin)

![Claimed Items](https://github.com/nazeer-shaik-01/Lost-and-Found-System-for-College/blob/main/Claimed%20Items.png?raw=true)

### ⚙️ Admin Item Management

![Admin Item Management](https://github.com/nazeer-shaik-01/Lost-and-Found-System-for-College/blob/main/Admin%20Item%20Management.png?raw=true)

---


