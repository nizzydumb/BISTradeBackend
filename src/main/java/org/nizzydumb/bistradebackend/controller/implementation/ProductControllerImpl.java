package org.nizzydumb.bistradebackend.controller.implementation;

import lombok.RequiredArgsConstructor;
import org.nizzydumb.bistradebackend.controller.ProductController;
import org.nizzydumb.bistradebackend.form.request.ProductRequest;
import org.nizzydumb.bistradebackend.form.response.ProductResponse;
import org.nizzydumb.bistradebackend.model.Category;
import org.nizzydumb.bistradebackend.model.Product;
import org.nizzydumb.bistradebackend.model.ProductAttribute;
import org.nizzydumb.bistradebackend.repository.CategoryRepository;
import org.nizzydumb.bistradebackend.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<Page<ProductResponse>> getAll(Long categoryId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> products = categoryId == null
                ? productRepository.findAll(pageRequest)
                : productRepository.findByCategoryId(categoryId, pageRequest);
        return ResponseEntity.ok(products.map(ProductResponse::convertFromProduct));
    }

    @Override
    public ResponseEntity<ProductResponse> getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        return ResponseEntity.ok(ProductResponse.convertFromProduct(product));
    }

    @Override
    public ResponseEntity<ProductResponse> create(ProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        product.setImageURL(request.getImageURL());
        product.setAttributes(request.getAttributes().stream().map(attribute -> {
            ProductAttribute productAttribute = new ProductAttribute();
            productAttribute.setProduct(product);
            productAttribute.setName(attribute.getName());
            productAttribute.setValue(attribute.getValue());
            return productAttribute;
        }).toList());
        productRepository.saveAndFlush(product);
        return new ResponseEntity<>(ProductResponse.convertFromProduct(product), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponse> update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        product.setImageURL(request.getImageURL());
        product.getAttributes().clear();
        product.getAttributes().addAll(request.getAttributes().stream().map(attribute -> {
            ProductAttribute productAttribute = new ProductAttribute();
            productAttribute.setProduct(product);
            productAttribute.setName(attribute.getName());
            productAttribute.setValue(attribute.getValue());
            return productAttribute;
        }).toList());
        productRepository.saveAndFlush(product);
        return new ResponseEntity<>(ProductResponse.convertFromProduct(product), HttpStatus.OK);
    }

}
