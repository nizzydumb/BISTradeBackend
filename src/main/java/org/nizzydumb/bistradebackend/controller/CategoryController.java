package org.nizzydumb.bistradebackend.controller;

import jakarta.validation.Valid;
import org.nizzydumb.bistradebackend.form.request.CategoryRequest;
import org.nizzydumb.bistradebackend.form.response.CategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("category")
@Validated
public interface CategoryController {

    @GetMapping("{id}")
    ResponseEntity<CategoryResponse> getById(@PathVariable("id") Long id);

    @GetMapping("all")
    ResponseEntity<List<CategoryResponse>> getAll();

    @PostMapping
    ResponseEntity<CategoryResponse> create(@RequestBody @Valid CategoryRequest request);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id);

    @PutMapping("{id}")
    ResponseEntity<CategoryResponse> update(@PathVariable("id") Long id,
                                            @RequestBody @Valid CategoryRequest request);


}
