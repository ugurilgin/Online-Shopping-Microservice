package com.ugisoftware.inventoryservice.business.abstracts;

import com.ugisoftware.inventoryservice.entities.concretes.Inventory;

public interface InventoryService {
    public boolean isInStock( String skuCode);
}
