package com.example.LibraryManagement.com.LibraryManagement.Controllers;

import com.example.LibraryManagement.com.LibraryManagement.Models.ApiResponse;
import com.example.LibraryManagement.com.LibraryManagement.Models.LibraryStats;
import com.example.LibraryManagement.com.LibraryManagement.Services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {
    
    @Autowired
    private DashboardService dashboardService;
    
    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<LibraryStats>> getLibraryStats() {
        try {
            LibraryStats stats = dashboardService.getLibraryStats();
            return ResponseEntity.ok(ApiResponse.success("Statistics retrieved successfully", stats));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error retrieving statistics: " + e.getMessage()));
        }
    }
}

