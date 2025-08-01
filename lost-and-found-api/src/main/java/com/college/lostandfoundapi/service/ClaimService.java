package com.college.lostandfoundapi.service;

import com.college.lostandfoundapi.model.Claim;
import com.college.lostandfoundapi.model.Item;
import com.college.lostandfoundapi.model.ItemState;
import com.college.lostandfoundapi.model.ItemStatus;
import com.college.lostandfoundapi.repository.ClaimRepository;
import com.college.lostandfoundapi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<Claim> findAllClaims() {
        return claimRepository.findAll();
    }

    public Optional<Claim> createClaim(Long itemId, Claim claimDetails) {
        return itemRepository.findById(itemId).flatMap(item -> {
            if (item.getStatus() == ItemStatus.FOUND) {
                item.setItemState(ItemState.CLAIM_PENDING);
                itemRepository.save(item);

                claimDetails.setItem(item);
                Claim newClaim = claimRepository.save(claimDetails);
                return Optional.of(newClaim);
            }
            return Optional.empty();
        });
    }

    // Approve a claim: set item state to RESOLVED and delete the claim
    public boolean approveClaim(Long claimId) {
        return claimRepository.findById(claimId).map(claim -> {
            Item item = claim.getItem();
            item.setItemState(ItemState.RESOLVED); // Updated from CLAIMED to RESOLVED
            itemRepository.save(item);
            claimRepository.delete(claim);
            return true;
        }).orElse(false);
    }

    // Deny a claim: set item state to UNCLAIMED and delete the claim
    public boolean denyClaim(Long claimId) {
        return claimRepository.findById(claimId).map(claim -> {
            Item item = claim.getItem();
            item.setItemState(ItemState.UNCLAIMED);
            itemRepository.save(item);
            claimRepository.delete(claim);
            return true;
        }).orElse(false);
    }
}
