package org.example.controller;

import org.example.dto.CategoryDTO;
import org.example.dto.OrderDTO;
import org.example.service.OrderService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/adm/v1/create")
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO orderDTO) {
        OrderDTO response = orderService.create(orderDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/adm/v2/categoryAll")
    public ResponseEntity<List<OrderDTO>> getAllProducts() {
        List<OrderDTO> all = orderService.getAll();
        return ResponseEntity.ok().body(all);
    }
    @PutMapping("/adm/v3/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestBody OrderDTO orderDTO) {
        orderService.update(id, orderDTO);
        return ResponseEntity.ok().body(true);
    }

    @DeleteMapping("/adm/v4/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
        orderService.deleteId(id);
        return ResponseEntity.ok().body(true);
    }
}
