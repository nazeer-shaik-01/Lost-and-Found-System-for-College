package com.college.lostandfoundapi.service;

import com.college.lostandfoundapi.model.Item;
import com.college.lostandfoundapi.model.ItemStatus;
import com.college.lostandfoundapi.model.Claim;
import com.college.lostandfoundapi.repository.ItemRepository;
import com.college.lostandfoundapi.repository.ClaimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ClaimRepository claimRepository;

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public List<Item> findFoundItems() {
        return itemRepository.findByStatus(ItemStatus.FOUND);
    }

    public Optional<Item> findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Optional<Item> updateItem(Long id, Item updatedItem) {
        return itemRepository.findById(id).map(existingItem -> {
            existingItem.setName(updatedItem.getName());
            existingItem.setDescription(updatedItem.getDescription());
            existingItem.setLocationFound(updatedItem.getLocationFound());
            existingItem.setLocationLost(updatedItem.getLocationLost());
            existingItem.setCategory(updatedItem.getCategory());
            existingItem.setStatus(updatedItem.getStatus());
            existingItem.setItemState(updatedItem.getItemState());
            return itemRepository.save(existingItem);
        });
    }

    // Delete an item along with its associated claims
    public boolean deleteItem(Long id) {
        if (itemRepository.existsById(id)) {
            List<Claim> associatedClaims = claimRepository.findByItemId(id);
            claimRepository.deleteAll(associatedClaims);
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
