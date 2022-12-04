package com.ugisoftware.inventoryservice.dataAccess.repository;

import com.ugisoftware.inventoryservice.entities.concretes.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Optional<Boolean> findBySkuCode(String skuCode);
}
