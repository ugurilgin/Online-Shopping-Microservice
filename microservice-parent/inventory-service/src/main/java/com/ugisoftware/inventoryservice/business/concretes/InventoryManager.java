package com.ugisoftware.inventoryservice.business.concretes;

import com.ugisoftware.inventoryservice.business.abstracts.InventoryService;
import com.ugisoftware.inventoryservice.dataAccess.repository.InventoryRepository;
import com.ugisoftware.inventoryservice.entities.concretes.Inventory;
import com.ugisoftware.inventoryservice.entities.dto.response.InventoryResponse;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@Slf4j
public class InventoryManager implements InventoryService {
    private InventoryRepository inventoryRepository;
    public InventoryManager(InventoryRepository inventoryRepository)
    {
        this.inventoryRepository=inventoryRepository;
    }
    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        log.info("Checking Inventory");
        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build()
                ).toList();
    }
}
