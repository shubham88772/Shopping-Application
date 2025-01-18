package com.shoppingapp.inventory_service.service.inventoryserviceimpl;

import com.shoppingapp.inventory_service.dto.InventoryResponse;
import com.shoppingapp.inventory_service.repository.InventoryRepository;
import com.shoppingapp.inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).
                stream().map(inventory ->
                    InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode()).
                            isInStock(inventory.getQuantity()>0)
                            .build()
                ).toList();
    }
}
