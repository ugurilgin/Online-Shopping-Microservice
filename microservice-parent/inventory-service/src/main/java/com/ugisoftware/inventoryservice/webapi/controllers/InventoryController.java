package com.ugisoftware.inventoryservice.webapi.controllers;

import com.ugisoftware.inventoryservice.business.abstracts.InventoryService;
import com.ugisoftware.inventoryservice.entities.concretes.Inventory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory/")
public class InventoryController implements InventoryService {
    private InventoryService inventoryService;
    public  InventoryController(InventoryService inventoryService)
    {
        this.inventoryService=inventoryService;
    }
    @GetMapping("/{skuCode}")
    public boolean isInStock(@PathVariable String skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
