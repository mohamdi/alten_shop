package com.alten.shop.service;

import com.alten.shop.exception.NotFoundException;
import com.alten.shop.model.entity.Product;
import com.alten.shop.repository.ProductRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    @PreAuthorize("@security.isAdmin(authentication)")
    public Product save(Product product) {
        return repository.save(product);
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @PreAuthorize("@security.isAdmin(authentication)")
    public Product patch(Long id, Product newData) {
        Product product = findById(id);
        product.setCode(newData.getCode());
        product.setName(newData.getName());
        product.setDescription(newData.getDescription());
        product.setImage(newData.getImage());
        product.setCategory(newData.getCategory());
        product.setPrice(newData.getPrice());
        product.setQuantity(newData.getQuantity());
        product.setInternalReference(newData.getInternalReference());
        product.setShellId(newData.getShellId());
        product.setInventoryStatus(newData.getInventoryStatus());
        product.setRating(newData.getRating());
        return repository.save(product);
    }

    @PreAuthorize("@security.isAdmin(authentication)")
    public void delete(Long id) {
        Product product = findById(id);
        repository.delete(product);
    }
}
