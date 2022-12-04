package com.ugisoftware.inventoryservice.business.concretes;

import com.ugisoftware.inventoryservice.business.abstracts.InventoryService;
import com.ugisoftware.inventoryservice.dataAccess.repository.InventoryRepository;
import com.ugisoftware.inventoryservice.entities.concretes.Inventory;
import com.ugisoftware.inventoryservice.entities.dto.response.InventoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class InventoryManager implements InventoryService {
    private InventoryRepository inventoryRepository;
    public InventoryManager(InventoryRepository inventoryRepository)
    {
        this.inventoryRepository=inventoryRepository;
    }
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
}
