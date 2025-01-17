package com.shoppingapp.order_service.service.OrderServiceImpl;

import com.shoppingapp.order_service.dto.OrderLineItemsDto;
import com.shoppingapp.order_service.dto.OrderRequest;
import com.shoppingapp.order_service.model.Order;
import com.shoppingapp.order_service.model.OrderLineItems;
import com.shoppingapp.order_service.repository.OrderRepository;
import com.shoppingapp.order_service.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> collect = orderRequest.getOrderLineItemsDtoList().stream().map(i -> modelMapper.map(i, OrderLineItems.class)).collect(Collectors.toList());
        order.setOrderLineItems(collect);
        orderRepository.save(order);
    }
}
