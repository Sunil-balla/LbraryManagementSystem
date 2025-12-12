package com.example.LibraryManagement.com.LibraryManagement.Controllers;

import com.example.LibraryManagement.com.LibraryManagement.Models.ApiResponse;
import com.example.LibraryManagement.com.LibraryManagement.Models.Member;
import com.example.LibraryManagement.com.LibraryManagement.Services.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*")
public class MemberController {
    
    @Autowired
    private MemberService memberService;
    
    // Get all members
    @GetMapping
    public ResponseEntity<ApiResponse<List<Member>>> getAllMembers() {
        try {
            List<Member> members = memberService.getAllMembers();
            return ResponseEntity.ok(ApiResponse.success("Members retrieved successfully", members));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error retrieving members: " + e.getMessage()));
        }
    }
    
    // Get member by ID
    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<Member>> getMemberById(@PathVariable String memberId) {
        try {
            Member member = memberService.getMemberById(memberId);
            return ResponseEntity.ok(ApiResponse.success("Member retrieved successfully", member));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Member not found: " + e.getMessage()));
        }
    }
    
    // Add a new member
    @PostMapping
    public ResponseEntity<ApiResponse<Member>> addMember(@Valid @RequestBody Member member) {
        try {
            Member newMember = memberService.addMember(member);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Member added successfully", newMember));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error("Error adding member: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error adding member: " + e.getMessage()));
        }
    }
    
    // Update a member
    @PutMapping("/{memberId}")
    public ResponseEntity<ApiResponse<Member>> updateMember(@PathVariable String memberId, 
                                                             @Valid @RequestBody Member member) {
        try {
            Member updatedMember = memberService.updateMember(memberId, member);
            return ResponseEntity.ok(ApiResponse.success("Member updated successfully", updatedMember));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Error updating member: " + e.getMessage()));
        }
    }
    
    // Delete a member
    @DeleteMapping("/{memberId}")
    public ResponseEntity<ApiResponse<Void>> deleteMember(@PathVariable String memberId) {
        try {
            memberService.deleteMember(memberId);
            return ResponseEntity.ok(ApiResponse.success("Member deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Error deleting member: " + e.getMessage()));
        }
    }
    
    // Deactivate a member
    @PatchMapping("/{memberId}/deactivate")
    public ResponseEntity<ApiResponse<Member>> deactivateMember(@PathVariable String memberId) {
        try {
            Member member = memberService.deactivateMember(memberId);
            return ResponseEntity.ok(ApiResponse.success("Member deactivated successfully", member));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Error deactivating member: " + e.getMessage()));
        }
    }
    
    // Activate a member
    @PatchMapping("/{memberId}/activate")
    public ResponseEntity<ApiResponse<Member>> activateMember(@PathVariable String memberId) {
        try {
            Member member = memberService.activateMember(memberId);
            return ResponseEntity.ok(ApiResponse.success("Member activated successfully", member));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error("Error activating member: " + e.getMessage()));
        }
    }
    
    // Get active members
    @GetMapping("/active")
    public ResponseEntity<ApiResponse<List<Member>>> getActiveMembers() {
        try {
            List<Member> members = memberService.getActiveMembers();
            return ResponseEntity.ok(ApiResponse.success("Active members retrieved", members));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error retrieving members: " + e.getMessage()));
        }
    }
    
    // Search members by name
    @GetMapping("/search/name/{name}")
    public ResponseEntity<ApiResponse<List<Member>>> searchByName(@PathVariable String name) {
        try {
            List<Member> members = memberService.searchByName(name);
            return ResponseEntity.ok(ApiResponse.success("Members found", members));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error searching members: " + e.getMessage()));
        }
    }
    
    // Get members by type
    @GetMapping("/type/{memberType}")
    public ResponseEntity<ApiResponse<List<Member>>> getMembersByType(@PathVariable Member.MemberType memberType) {
        try {
            List<Member> members = memberService.getMembersByType(memberType);
            return ResponseEntity.ok(ApiResponse.success("Members retrieved by type", members));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error("Error retrieving members: " + e.getMessage()));
        }
    }
}

