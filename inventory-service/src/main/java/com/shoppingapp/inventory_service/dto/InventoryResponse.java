package com.shoppingapp.inventory_service.dto;

import lombok.*;

@Builder
public class InventoryResponse {
    private String skuCode;

    public InventoryResponse() {

    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public boolean isInStock() {
        return isInStock;
    }

    public void setInStock(boolean inStock) {
        isInStock = inStock;
    }

    private boolean isInStock;

    public static class Builder {
        private String skuCode;
        private boolean isInStock;

        public Builder skuCode(String skuCode) {
            this.skuCode = skuCode;
            return this;
        }

        public Builder isInStock(boolean isInStock) {
            this.isInStock = isInStock;
            return this;
        }

        public InventoryResponse build() {
            InventoryResponse response = new InventoryResponse();
            response.skuCode = this.skuCode;
            response.isInStock = this.isInStock;
            return response;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
