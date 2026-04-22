package com.Federation.Final.controller;

import com.Federation.Final.entity.Member;
import com.Federation.Final.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<List<Member>> createMembers(@RequestBody List<Member> members) {
        List<Member> createdMembers = memberService.createMembers(members);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMembers);
    }
}
