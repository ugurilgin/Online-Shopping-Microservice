package com.ugisoftware.inventoryservice.webapi.controllers;

import com.ugisoftware.inventoryservice.business.abstracts.InventoryService;
import com.ugisoftware.inventoryservice.business.concretes.InventoryManager;
import com.ugisoftware.inventoryservice.entities.concretes.Inventory;
import com.ugisoftware.inventoryservice.entities.dto.response.InventoryResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController implements InventoryService {
    private InventoryManager inventoryService;
    public  InventoryController(InventoryManager inventoryService)
    {
        this.inventoryService=inventoryService;
    }
    // http://localhost:8082/api/inventory/iphone-13,iphone13-red

    // http://localhost:8082/api/inventory?skuCode=iphone-13&skuCode=iphone13-red
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
        return inventoryService.isInStock(skuCode);
    }
}
