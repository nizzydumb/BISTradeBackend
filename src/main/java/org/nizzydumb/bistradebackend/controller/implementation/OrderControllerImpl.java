package org.nizzydumb.bistradebackend.controller.implementation;

import lombok.RequiredArgsConstructor;
import org.nizzydumb.bistradebackend.controller.OrderController;
import org.nizzydumb.bistradebackend.form.request.OrderRequest;
import org.nizzydumb.bistradebackend.form.response.OrderResponse;
import org.nizzydumb.bistradebackend.model.Order;
import org.nizzydumb.bistradebackend.model.OrderStatus;
import org.nizzydumb.bistradebackend.model.Product;
import org.nizzydumb.bistradebackend.model.ProductOrder;
import org.nizzydumb.bistradebackend.repository.OrderRepository;
import org.nizzydumb.bistradebackend.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<OrderResponse> getById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        return ResponseEntity.ok(OrderResponse.from(order));
    }

    @Override
    public ResponseEntity<OrderResponse> create(OrderRequest request) {
        Order order = new Order();
        order.setName(request.getName());
        order.setSurname(request.getSurname());
        order.setPhone(request.getPhone());
        order.setEmail(request.getEmail());
        order.setProductOrders(request.getProductOrders().stream().map(productOrderRequest -> {
            Product product = productRepository.findById(productOrderRequest.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
            ProductOrder productOrder = new ProductOrder();
            productOrder.setProduct(product);
            productOrder.setOrder(order);
            productOrder.setQuantity(productOrderRequest.getQuantity());
            productOrder.setPrice(productOrderRequest.getPrice());
            return productOrder;
        }).toList());
        order.setStatus(OrderStatus.PENDING);
        orderRepository.saveAndFlush(order);

        return new ResponseEntity<>(OrderResponse.from(order), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Page<OrderResponse>> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Order> orders = orderRepository.findAll(pageRequest);
        return ResponseEntity.ok(orders.map(OrderResponse::from));
    }

    @Override
    public ResponseEntity<OrderResponse> process(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        order.setStatus(OrderStatus.PROCESSED);
        orderRepository.saveAndFlush(order);
        return ResponseEntity.ok(OrderResponse.from(order));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        orderRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
