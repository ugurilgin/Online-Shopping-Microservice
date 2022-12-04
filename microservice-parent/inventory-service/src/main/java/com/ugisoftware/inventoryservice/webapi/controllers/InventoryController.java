package com.ugisoftware.inventoryservice.webapi.controllers;

import com.ugisoftware.inventoryservice.business.abstracts.InventoryService;
import com.ugisoftware.inventoryservice.business.concretes.InventoryManager;
import com.ugisoftware.inventoryservice.entities.concretes.Inventory;
import com.ugisoftware.inventoryservice.entities.dto.response.InventoryResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory/")
public class InventoryController implements InventoryService {
    private InventoryManager inventoryService;
    public  InventoryController(InventoryManager inventoryService)
    {
        this.inventoryService=inventoryService;
    }
    @GetMapping
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {

        return inventoryService.isInStock(skuCode);
    }
}
