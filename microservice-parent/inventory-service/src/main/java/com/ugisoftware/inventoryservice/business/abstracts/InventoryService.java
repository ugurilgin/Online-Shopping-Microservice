package com.ugisoftware.inventoryservice.business.abstracts;

import com.ugisoftware.inventoryservice.entities.concretes.Inventory;
import com.ugisoftware.inventoryservice.entities.dto.response.InventoryResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface InventoryService {
    public List<InventoryResponse> isInStock(List<String> skuCode);
}
