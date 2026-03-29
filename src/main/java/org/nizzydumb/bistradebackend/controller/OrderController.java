package org.nizzydumb.bistradebackend.controller;

import org.nizzydumb.bistradebackend.form.request.OrderRequest;
import org.nizzydumb.bistradebackend.form.response.OrderResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("order")
@Validated
public interface OrderController {

    @GetMapping("{id}")
    ResponseEntity<OrderResponse> getById(@PathVariable("id") Long id);

    @PostMapping
    ResponseEntity<OrderResponse> create(@Validated @RequestBody OrderRequest request);

    @GetMapping("all")
    ResponseEntity<Page<OrderResponse>> getAll(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size);

    @PatchMapping("{id}/process")
    ResponseEntity<OrderResponse> process(@PathVariable("id") Long id);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id);

}
