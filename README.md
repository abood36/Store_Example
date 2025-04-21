🛒 Online Shop Backend (Java + Spring Boot)
An elegant and complete backend web application for managing products in an online store. Built using Java, Spring Boot, Thymeleaf, and MySQL, the project supports all essential product operations — from adding and editing products to uploading images and deleting items.

🚀 Features
🧾 Product Management: Add, edit, delete, and view all products.

🖼️ Image Upload: Save product images with timestamp-based naming.

🧹 Image Cleanup: Automatically deletes old image files when a product is updated or deleted.

🧠 Form Validation: Validates user input using @Valid and provides custom error messages.

📦 Thymeleaf Templates: Clean server-rendered views for forms and lists.

🛡️ Error Handling: Graceful handling of common issues with logs.

📁 Technologies Used

Tech	Role
Java 17+	Programming language
Spring Boot	Backend framework
Thymeleaf	Server-side view engine
MySQL	Relational database
JPA / Hibernate	Data persistence layer
Maven	Dependency management
🧱 Project Structure
bash
نسخ
تحرير
store_example/
├── controllers/           # All controller logic
│   └── productController.java
├── models/                # DTOs and entity classes
│   ├── product.java
│   └── productDTO.java
├── repositories/
│   └── productRepo.java   # JPA Repository
├── public/images/         # Uploaded product images
├── templates/products/    # Thymeleaf views
│   ├── index.html
│   ├── createProduct.html
│   └── editProduct.html
├── application.properties # DB configuration
└── StoreExampleApplication.java
🧪 Sample Endpoints

URL	Method	Description
/products	GET	List all products
/products/create	GET	Show add product form
/products/create	POST	Submit new product
/products/edit?id=1	GET	Show edit product form
/products/edit	POST	Submit edited product
/products/delete?id=1	GET	Delete product

🧑‍💻 Author
Developed by Abdelrahman Mahrouss
