package org.example.service;

import jakarta.persistence.criteria.Order;
import org.aspectj.weaver.ast.Or;
import org.example.dto.CategoryDTO;
import org.example.dto.OrderDTO;
import org.example.dto.ProductDTO;
import org.example.entity.OrderEntity;
import org.example.entity.ProductEntity;
import org.example.enums.OrderStatus;
import org.example.exp.AppBadException;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDTO create(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(orderDTO.getId());
        orderEntity.setProductName(orderDTO.getProductName());
        orderEntity.setQuantity(orderDTO.getQuantity());
        orderEntity.setOrderStatus(OrderStatus.PENDING);
        orderRepository.save(orderEntity);
        return orderToDTO(orderEntity);
    }

    private OrderDTO orderToDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setProductName(orderEntity.getProductName());
        orderDTO.setQuantity(orderEntity.getQuantity());
        orderDTO.setOrderStatus(orderEntity.getOrderStatus());
        return orderDTO;
    }

    public List<OrderDTO> getAll() {
        List<OrderEntity> all = orderRepository.findAll();
        List<OrderDTO> orderDTOList = new LinkedList<>();
        for (OrderEntity orderEntity : all) {
            orderDTOList.add(orderToDTO(orderEntity));
        }
        return orderDTOList;
    }

    public OrderDTO update(Integer id, OrderDTO orderDTO) {
        Optional<OrderEntity> byId = orderRepository.findById(id);
        if (byId.isEmpty()) {
            throw new AppBadException("order not found");
        }
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(id);
        orderEntityTo(orderDTO);
        orderRepository.save(orderEntity);
        return orderToDTO(orderEntity);
    }

    private OrderEntity orderEntityTo(OrderDTO dto) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setProductName(dto.getProductName());
        orderEntity.setQuantity(dto.getQuantity());
        orderEntity.setOrderStatus(dto.getOrderStatus());
        return orderEntity;
    }

    public void deleteId(Integer id) {
        orderRepository.findById(id);
    }
}
