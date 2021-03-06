package com.SpringBootFarzamMohammadiAssign3Appplication.products_info.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.SocketOption;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {
        return productRepository.getById(id);
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }

    public void addNewProduct(Product product){
        Optional<Product> productByName = productRepository.findProductByName(product.getName());

        // If product exists throw exception
        if (productByName.isPresent()){
            throw new IllegalStateException("Product Already Exists.");
        }
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        Product productToUpdate = getProductById(product.getId());

        if (productToUpdate.getName() != null){
            productRepository.save(product);
        }
        else{
            throw new IllegalStateException("Product does not exist.");
        }
    }
}
