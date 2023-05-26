package com.ppv.storageservice.controller;

import com.ppv.storageservice.dto.StorageRequest;
import com.ppv.storageservice.dto.StorageResponse;
import com.ppv.storageservice.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/storage")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;

    @GetMapping
    public List<StorageResponse> getAllItems() {
        return storageService.getAllItems();
    }

    @PostMapping
    public void saveItem(@RequestBody StorageRequest request) {
        storageService.saveItem(request);
    }
}
