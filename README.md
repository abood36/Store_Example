
# 🛍️ Online Shop Backend - Java & Spring Boot

This project is a simple yet functional backend for an online shop, built using **Java** and **Spring Boot**. It covers essential operations such as product creation, editing, listing, and deletion. The system supports file upload for product images and stores them in a public directory on the server.

---

## 🚀 Features

- Create, edit, and delete products
- Image upload with file system storage
- Simple CRUD functionality using Spring MVC
- Model validation with detailed error handling
- Data persistence using Spring Data JPA
- MVC architecture with Thymeleaf integration

---

## 🧱 Technologies

- Java 17+
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Thymeleaf
- H2 Database / MySQL (based on configuration)
- HTML + Bootstrap (in templates)
- File Upload via MultipartFile

---

## 📁 Directory Structure

```
src/
└── main/
    ├── java/
    │   └── com/store_example/store_example/
    │       ├── controllers/         # Controller for product operations
    │       │   └── productController.java
    │       ├── models/              # Product entity and DTO
    │       └── Repositories/        # Product repository (interface)
    └── resources/
        ├── templates/products/      # Views (HTML using Thymeleaf)
        │   ├── index.html
        │   ├── createProduct.html
        │   └── editProduct.html
        └── static/images/           # Image upload location
```

---

## 🔧 Setup & Run

1. Clone the repo:
```bash
git clone https://github.com/your-username/online-shop-springboot.git
```

2. Open in your IDE and run the application via `main()`.

3. Access the app:
```
http://localhost:8080/products
```

4. Create or manage products using the form interface.

---

## 📦 Sample Controller Code

```java
@GetMapping("/products")
public String getProductList(Model model) {
    List<product> products = repo.findAll();
    model.addAttribute("products", products);
    return "products/index";
}
```

---

## 👨‍💻 Author

**Abdelrahman Mahrouss**  
LinkedIn: [linkedin.com/in/abdelrahman-mahrouss-1a8bb9333](https://www.linkedin.com/in/abdelrahman-mahrouss-1a8bb9333)

---

## 📃 License

This project is licensed under the MIT License.
