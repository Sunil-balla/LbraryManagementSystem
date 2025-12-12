package com.example.LibraryManagement.com.LibraryManagement.Services;

import com.example.LibraryManagement.com.LibraryManagement.Models.Member;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final Map<String, Member> memberRepository = new HashMap<>();
    
    // Add a new member
    public Member addMember(Member member) {
        if (memberRepository.containsKey(member.getMemberId())) {
            throw new IllegalArgumentException("Member with ID " + member.getMemberId() + " already exists");
        }
        memberRepository.put(member.getMemberId(), member);
        return member;
    }
    
    // Get all members
    public List<Member> getAllMembers() {
        return new ArrayList<>(memberRepository.values());
    }
    
    // Get member by ID
    public Member getMemberById(String memberId) {
        Member member = memberRepository.get(memberId);
        if (member == null) {
            throw new NoSuchElementException("Member with ID " + memberId + " not found");
        }
        return member;
    }
    
    // Update member
    public Member updateMember(String memberId, Member updatedMember) {
        if (!memberRepository.containsKey(memberId)) {
            throw new NoSuchElementException("Member with ID " + memberId + " not found");
        }
        updatedMember.setMemberId(memberId);
        memberRepository.put(memberId, updatedMember);
        return updatedMember;
    }
    
    // Delete member
    public void deleteMember(String memberId) {
        if (!memberRepository.containsKey(memberId)) {
            throw new NoSuchElementException("Member with ID " + memberId + " not found");
        }
        memberRepository.remove(memberId);
    }
    
    // Deactivate member
    public Member deactivateMember(String memberId) {
        Member member = getMemberById(memberId);
        member.setActive(false);
        return member;
    }
    
    // Activate member
    public Member activateMember(String memberId) {
        Member member = getMemberById(memberId);
        member.setActive(true);
        return member;
    }
    
    // Get active members
    public List<Member> getActiveMembers() {
        return memberRepository.values().stream()
                .filter(Member::isActive)
                .collect(Collectors.toList());
    }
    
    // Search members by name
    public List<Member> searchByName(String name) {
        return memberRepository.values().stream()
                .filter(member -> member.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Search members by email
    public List<Member> searchByEmail(String email) {
        return memberRepository.values().stream()
                .filter(member -> member.getEmail().toLowerCase().contains(email.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    // Get members by type
    public List<Member> getMembersByType(Member.MemberType memberType) {
        return memberRepository.values().stream()
                .filter(member -> member.getMemberType() == memberType)
                .collect(Collectors.toList());
    }
    
    // Check if member is active
    public boolean isMemberActive(String memberId) {
        Member member = getMemberById(memberId);
        return member.isActive();
    }
}

