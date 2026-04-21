package com.Federation.Final.controller;


import com.Federation.Final.entity.Collectivity;
import com.Federation.Final.entity.Member;
import com.Federation.Final.service.CollectivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class CollectivityController {

    private final CollectivityService service;

    public CollectivityController(CollectivityService service) {
        this.service = service;
    }

    @PostMapping("/collectivities")
    public ResponseEntity<List<Collectivity>> createCollectivities(@RequestBody List<Collectivity> collectivities) {
        List<Collectivity> result = service.createCollectivities(collectivities);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

//    @PostMapping("/members")
//    public ResponseEntity<List<Member>> createMembers(@RequestBody List<Member> members) {
//        List<Member> result = service.createMembers(members);
//        return ResponseEntity.status(HttpStatus.CREATED).body(result);
//    }
}
