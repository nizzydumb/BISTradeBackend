package org.nizzydumb.bistradebackend.controller.implementation;

import lombok.RequiredArgsConstructor;
import org.nizzydumb.bistradebackend.controller.CategoryController;
import org.nizzydumb.bistradebackend.form.request.CategoryRequest;
import org.nizzydumb.bistradebackend.form.response.CategoryResponse;
import org.nizzydumb.bistradebackend.model.Category;
import org.nizzydumb.bistradebackend.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<CategoryResponse> getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        return new ResponseEntity<>(CategoryResponse.convertFromCategory(category), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return ResponseEntity.ok(categoryRepository.findAll().stream().map(CategoryResponse::convertFromCategory).toList());
    }

    @Override
    public ResponseEntity<CategoryResponse> create(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setImageURL(request.getImageURL());
        categoryRepository.saveAndFlush(category);
        return new ResponseEntity<>(CategoryResponse.convertFromCategory(category), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryResponse> update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setImageURL(request.getImageURL());
        categoryRepository.saveAndFlush(category);
        return new ResponseEntity<>(CategoryResponse.convertFromCategory(category), HttpStatus.OK);
    }

}
