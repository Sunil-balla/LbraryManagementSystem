package com.example.LibraryManagement.com.LibraryManagement.Models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class Member {
    @NotBlank(message = "Member ID is required")
    private String memberId;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
    
    @NotBlank(message = "Phone is required")
    private String phone;
    
    private String address;
    
    @NotNull(message = "Membership date is required")
    private LocalDate membershipDate;
    
    @NotNull(message = "Member type is required")
    private MemberType memberType;
    
    private boolean active;
    
    // Enum for member types
    public enum MemberType {
        STUDENT, FACULTY, STAFF, PUBLIC
    }
    
    // Constructors
    public Member() {
        this.active = true;
    }
    
    public Member(String memberId, String name, String email, String phone, 
                  String address, LocalDate membershipDate, MemberType memberType) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.membershipDate = membershipDate;
        this.memberType = memberType;
        this.active = true;
    }
    
    // Getters and Setters
    public String getMemberId() {
        return memberId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public LocalDate getMembershipDate() {
        return membershipDate;
    }
    
    public void setMembershipDate(LocalDate membershipDate) {
        this.membershipDate = membershipDate;
    }
    
    public MemberType getMemberType() {
        return memberType;
    }
    
    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", membershipDate=" + membershipDate +
                ", memberType=" + memberType +
                ", active=" + active +
                '}';
    }
}

