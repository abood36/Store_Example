package com.store_example.store_example.controllers;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.store_example.store_example.Repositories.productRepo;
import com.store_example.store_example.models.product;
import com.store_example.store_example.models.productDTO;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/products")
public class productController {
    
    // Dependency Injection
    @Autowired
    public productRepo repo;

    // Fetch all products
    @GetMapping({"", "/"})
    public String getProductList(Model model) {
        
        List<product> products = repo.findAll();
        model.addAttribute("products", products);
        return "products/index";
    }



    // showing (Create Product) page
    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("productDTO", new productDTO());
        return "products/createProduct";
    }

    // showing (Edit Product) page
    @PostMapping("/create")
    @SuppressWarnings("UseSpecificCatch")
    // Submit data from tje form
    public String createProduct(@Valid @ModelAttribute productDTO productDTO, BindingResult result) {
        
        // check image
        if (productDTO.getImageFile().isEmpty()) {
            result.addError(new FieldError("productDTO", "imageFile", "The image file is required"));
        }

        if (result.hasErrors()) {
            return "products/createProduct";
        }

        // save image in the (images file)
        MultipartFile image = productDTO.getImageFile();
        Date createdAt = new Date();
        String fileName = createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, Paths.get(uploadDir + fileName),
                StandardCopyOption.REPLACE_EXISTING);
            }
            
        } catch (Exception e) {
            System.out.println("Error saving image file: " + e.getMessage());
        }

        // save product to database
        product product = new product();
        product.setName(productDTO.getName());
        product.setBrand(productDTO.getBrand());
        product.setCategory(productDTO.getCategory());
        product.setPrice(productDTO.getPrice());

        product.setCreatedAt(createdAt);
        product.setImageFileName(fileName);

        repo.save(product);
        
        return ("redirect:/products");
    }

    

    @GetMapping("/edit")
    public String showEditPage(@RequestParam int id, Model model) {

        try {

            product products = repo.findById(id).get();
            model.addAttribute("product", products);

            productDTO productDTO = new productDTO();
            productDTO.setName(products.getName());
            productDTO.setBrand(products.getBrand());
            productDTO.setCategory(products.getCategory());
            productDTO.setPrice(products.getPrice());

            model.addAttribute("productDTO", productDTO);

        } catch (Exception e) {
            System.out.println("Error finding product: " + e.getMessage());
            return "redirect:/products";
        }


        return "products/editProduct";
    }


    @PostMapping("/edit")
    @SuppressWarnings("UseSpecificCatch")
    public String updateProduct(@RequestParam int id, @Valid @ModelAttribute productDTO productDTO, BindingResult result, Model model) {

        try {
            // read product details from db
            product product = repo.findById(id).get();
            model.addAttribute("product", product);

            // any errors in the image
            if (result.hasErrors()) {
            return "products/editProduct";
            }

            
            // check having image or not
            if (!productDTO.getImageFile().isEmpty()) {
                // delete old image
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());

                try {
                    Files.delete(oldImagePath);
                } catch (Exception e) {
                    System.out.println("Error deleting old image: " + e.getMessage());
                }

                // save new image file
                MultipartFile image = productDTO.getImageFile();
                String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                
                // saving in server
                try (InputStream inputStream = image.getInputStream()) {
                    Files.copy(inputStream, Paths.get(uploadDir + fileName),
                        StandardCopyOption.REPLACE_EXISTING);
                }
                // saving in object to save in db later
                product.setImageFileName(fileName);
            }

            product.setName(productDTO.getName());
            product.setBrand(productDTO.getBrand());
            product.setCategory(productDTO.getCategory());
            product.setPrice(productDTO.getPrice());

            // now saving in db
            repo.save(product);
        

        } catch (Exception e) {
            System.out.println("Error updating product: " + e.getMessage());
        }

        return "redirect:/products";
    }



    @GetMapping("/delete")
    @SuppressWarnings("UseSpecificCatch")
    public String deleteProduct(@RequestParam int id) {

        try {
            // reading product by (id) from database
            product product = repo.findById(id).get();

            // delete image file
            String uploadDir = "public/images/";
            Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());

            try {
                Files.delete(oldImagePath);
            } catch (Exception e) {
                System.out.println("Error deleting old image: " + e.getMessage());
            }

            // delete product from db
            repo.delete(product);
            
        } catch (Exception e) {
            System.out.println("Error deleting product: " + e.getMessage());
        }

        return "redirect:/products";
    }

}
