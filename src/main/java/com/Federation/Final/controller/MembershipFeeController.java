package com.Federation.Final.controller;

import com.Federation.Final.entity.MembershipFee;
import com.Federation.Final.service.MembershipFeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/collectivities")
public class MembershipFeeController {


        private final MembershipFeeService service;

        public MembershipFeeController(MembershipFeeService service) {
            this.service = service;
        }

        //GET /collectivities/{id}/membershipFees
        @GetMapping("/{id}/membershipFees")
        public List<MembershipFee> getMembershipFees(@PathVariable String id) {
            return service.getByCollectivity(id);
        }

        //POST /collectivities/{id}/membershipFees
        @PostMapping("/{id}/membershipFees")
        public List<MembershipFee> createMembershipFees(
                @PathVariable String id,
                @RequestBody List<MembershipFee> fees) {

            return service.createAll(id, fees);
        }
}
