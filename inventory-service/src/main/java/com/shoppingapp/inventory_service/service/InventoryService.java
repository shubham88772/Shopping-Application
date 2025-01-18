package com.shoppingapp.inventory_service.service;

import com.shoppingapp.inventory_service.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    public List<InventoryResponse> isInStock(List<String> skuCode);
}
