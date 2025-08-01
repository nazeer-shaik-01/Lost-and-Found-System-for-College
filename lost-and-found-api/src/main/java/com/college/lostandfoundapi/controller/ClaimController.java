package com.college.lostandfoundapi.controller;

import com.college.lostandfoundapi.model.Claim;
import com.college.lostandfoundapi.security.Role;
import com.college.lostandfoundapi.service.ClaimService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClaimController {

    @Autowired
    private ClaimService claimService;

    // ✅ GET /api/claims (ADMIN only)
    @GetMapping("/claims")
    public ResponseEntity<List<Claim>> getAllClaims(HttpServletRequest request) {
        Role userRole = (Role) request.getAttribute("userRole");
        if (userRole == null || userRole != Role.ROLE_ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        List<Claim> claims = claimService.findAllClaims();
        return new ResponseEntity<>(claims, HttpStatus.OK);
    }

    // ✅ PUT /api/claims/{claimId}/approve (ADMIN only)
    @PutMapping("/claims/{claimId}/approve")
    public ResponseEntity<Void> approveClaim(@PathVariable Long claimId, HttpServletRequest request) {
        Role userRole = (Role) request.getAttribute("userRole");
        if (userRole == null || userRole != Role.ROLE_ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        boolean approved = claimService.approveClaim(claimId);
        return approved ? ResponseEntity.noContent().build() : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // ✅ PUT /api/claims/{claimId}/deny (ADMIN only)
    @PutMapping("/claims/{claimId}/deny")
    public ResponseEntity<Void> denyClaim(@PathVariable Long claimId, HttpServletRequest request) {
        Role userRole = (Role) request.getAttribute("userRole");
        if (userRole == null || userRole != Role.ROLE_ADMIN) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        boolean denied = claimService.denyClaim(claimId);
        return denied ? ResponseEntity.noContent().build() : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // ✅ POST /api/items/{itemId}/claim (User claim request)
    @PostMapping("/items/{itemId}/claim")
    public ResponseEntity<Claim> createClaim(@PathVariable Long itemId, @RequestBody Claim claimDetails, HttpServletRequest request) {
        Role userRole = (Role) request.getAttribute("userRole");
        if (userRole == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // TEMP: Store the role string as the claimerUsername
        claimDetails.setClaimerUsername(userRole.toString());

        Optional<Claim> newClaim = claimService.createClaim(itemId, claimDetails);
        return newClaim.map(claim -> new ResponseEntity<>(claim, HttpStatus.CREATED))
                       .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
