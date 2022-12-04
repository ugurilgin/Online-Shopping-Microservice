package com.ugisoftware.inventoryservice.business.concretes;

import com.ugisoftware.inventoryservice.business.abstracts.InventoryService;
import com.ugisoftware.inventoryservice.dataAccess.repository.InventoryRepository;
import com.ugisoftware.inventoryservice.entities.concretes.Inventory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryManager implements InventoryService {
    private InventoryRepository inventoryRepository;
    public InventoryManager(InventoryRepository inventoryRepository)
    {
        this.inventoryRepository=inventoryRepository;
    }
    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode) {
        return inventoryRepository.findBySkuCode(skuCode).isPresent();
    }
}
