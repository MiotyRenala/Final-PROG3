package com.Federation.Final.controller;

import com.Federation.Final.entity.dto.CreateMemberPayment;
import com.Federation.Final.entity.dto.MemberPaymentResponse;
import com.Federation.Final.service.MemberPaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping("/members")
public class MemberPaymentController {

        private final MemberPaymentService paymentService;

        public MemberPaymentController(MemberPaymentService paymentService) {
            this.paymentService = paymentService;
        }

        @PostMapping("/{id}/payments")
        public ResponseEntity<List<MemberPaymentResponse>> createPayments(
                @PathVariable("id") String id,
                @RequestBody List<CreateMemberPayment> dtos) throws SQLException {

            List<MemberPaymentResponse> responses = paymentService.createPayments(id, dtos);
            return ResponseEntity.status(HttpStatus.CREATED).body(responses);
        }
}
