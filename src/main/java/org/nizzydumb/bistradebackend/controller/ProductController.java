package org.nizzydumb.bistradebackend.controller;

import jakarta.validation.Valid;
import org.nizzydumb.bistradebackend.form.request.ProductRequest;
import org.nizzydumb.bistradebackend.form.response.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("product")
@Validated
public interface ProductController {

    @GetMapping("all")
    ResponseEntity<Page<ProductResponse>> getAll(@RequestParam(value = "categoryId", required = false) Long categoryId,
                                                 @RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "10") int size);

    @GetMapping("{id}")
    ResponseEntity<ProductResponse> getById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest request);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id);

    @PutMapping("{id}")
    ResponseEntity<ProductResponse> update(@PathVariable("id") Long id,
                                           @RequestBody @Valid ProductRequest request);

}
