package com.shoppingapp.order_service.service.OrderServiceImpl;

import com.shoppingapp.order_service.dto.InventoryResponse;
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
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
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

    @Autowired
    private WebClient webClient;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> collect = orderRequest.getOrderLineItemsDtoList().stream().map(i -> modelMapper.map(i, OrderLineItems.class)).collect(Collectors.toList());
        order.setOrderLineItems(collect);
        List<String> skuCodes = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();

        //Call inventory service
        InventoryResponse[] inventoryResponsesArray = webClient.get()
                .uri("http://localhost9092/api/inventory",uriBuilder ->uriBuilder
                        .queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isInStock);
        if (allProductsInStock){
            orderRepository.save(order);
        }else {
            throw new IllegalArgumentException("Product is out of stock");
        }
    }
}
