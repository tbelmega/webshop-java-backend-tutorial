package de.oncoding.webshop.controller;

import de.oncoding.webshop.model.ProductCreateRequest;
import de.oncoding.webshop.model.ProductResponse;
import de.oncoding.webshop.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts(
            @RequestParam(required = false) String tag
    ) {
        return productRepository.findAll(tag);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable String id
    ) {
        Optional<ProductResponse> product = productRepository.findById(id);
        if (product.isPresent())
            return ResponseEntity.ok(product.get());
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProduct(
            @PathVariable String id
    ) {
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/products")
    public ProductResponse createProduct(
            @RequestBody ProductCreateRequest request
    ) {
        return productRepository.save(request);
    }

}
