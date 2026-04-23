package com.Federation.Final.service;

import com.Federation.Final.entity.CollectivityTransaction;
import com.Federation.Final.entity.Member;
import com.Federation.Final.entity.MemberPayment;
import com.Federation.Final.entity.MembershipFee;
import com.Federation.Final.entity.dto.CreateMemberPayment;
import com.Federation.Final.entity.dto.MemberPaymentResponse;
import com.Federation.Final.entity.validator.MemberPaymentValidator;
import com.Federation.Final.repository.CollectivityTransactionRepository;
import com.Federation.Final.repository.MemberPaymentRepository;
import com.Federation.Final.repository.MemberRepository;
import com.Federation.Final.repository.MembershipFeeRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberPaymentService {
    private final MemberPaymentRepository memberPaymentRepository;
    private final MemberRepository memberRepository;
    private final MembershipFeeRepository membershipFeeRepository;
    private final CollectivityTransactionRepository transactionRepository;
    private final MemberPaymentValidator validator;

    public MemberPaymentService(
            MemberPaymentRepository memberPaymentRepository,
            MemberRepository memberRepository,
            MembershipFeeRepository membershipFeeRepository,
            CollectivityTransactionRepository transactionRepository,
            MemberPaymentValidator validator) {
        this.memberPaymentRepository = memberPaymentRepository;
        this.memberRepository = memberRepository;
        this.membershipFeeRepository = membershipFeeRepository;
        this.transactionRepository = transactionRepository;
        this.validator = validator;
    }

    public List<MemberPaymentResponse> createPayments(String memberId, List<CreateMemberPayment> dtos) throws SQLException, SQLException {

        // Vérifier que le membre existe
        var memberOpt = memberRepository.findById(memberId);
        if (memberOpt.isEmpty()) {
            throw new IllegalArgumentException("Member not found with id: " + memberId);
        }

        Member member = memberOpt.get();


        String collectivityId = member.getCollectivityId();
        if (collectivityId == null) {
            throw new IllegalArgumentException("Member is not affiliated to any collectivity");
        }

        List<MemberPaymentResponse> responses = new ArrayList<>();

        for (CreateMemberPayment dto : dtos) {
            validator.validate(dto);
            var feeOpt = membershipFeeRepository.findById(dto.getMembershipFeeIdentifier());
            if (feeOpt.isEmpty()) {
                throw new IllegalArgumentException("Membership fee not found: " + dto.getMembershipFeeIdentifier());
            }

            MembershipFee fee = feeOpt.get();


            if (fee.getEligibleFrom().isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Member is not yet eligible for this fee");
            }


            MemberPayment payment = new MemberPayment();
            payment.setMemberId(memberId);
            payment.setMembershipFeeId(dto.getMembershipFeeIdentifier());
            payment.setAmount(dto.getAmount());
            payment.setPaymentMode(dto.getPaymentMode());
            payment.setCreationDate(LocalDate.now());

            payment = memberPaymentRepository.save(payment);

            // Créer la transaction associée pour la collectivité
            CollectivityTransaction transaction = new CollectivityTransaction();
            transaction.setCollectivityId(collectivityId);
            transaction.setCreationDate(LocalDate.now());
            transaction.setAmount(dto.getAmount());
            transaction.setPaymentMode(dto.getPaymentMode());
            transaction.setMemberDebitedId(memberId);

            transactionRepository.save(transaction);

            // Construire la réponse
            MemberPaymentResponse response = new MemberPaymentResponse();
            response.setId(payment.getId());
            response.setAmount(payment.getAmount());
            response.setPaymentMode(payment.getPaymentMode());
            response.setCreationDate(payment.getCreationDate());

            responses.add(response);
        }
        return responses;
    }

    public List<MemberPaymentResponse> getMemberPayments(String memberId) throws SQLException {
        List<MemberPayment> payments = memberPaymentRepository.findByMemberId(memberId);
        List<MemberPaymentResponse> responses = new ArrayList<>();

        for (MemberPayment payment : payments) {
            MemberPaymentResponse response = new MemberPaymentResponse();
            response.setId(payment.getId());
            response.setAmount(payment.getAmount());
            response.setPaymentMode(payment.getPaymentMode());
            response.setCreationDate(payment.getCreationDate());

            responses.add(response);
        }

        return responses;
    }

    public double getTotalPaymentsByMember(String memberId) throws SQLException {
        List<MemberPayment> payments = memberPaymentRepository.findByMemberId(memberId);
        return payments.stream().mapToDouble(MemberPayment::getAmount).sum();
    }

    public List<MemberPaymentResponse> getPaymentsByPeriod(String memberId, LocalDate from, LocalDate to) throws SQLException {
        List<MemberPayment> payments = memberPaymentRepository.findByMemberId(memberId);

        List<MemberPayment> filteredPayments = payments.stream()
                .filter(p -> !p.getCreationDate().isBefore(from) && !p.getCreationDate().isAfter(to))
                .toList();

        List<MemberPaymentResponse> responses = new ArrayList<>();
        for (MemberPayment payment : filteredPayments) {
            MemberPaymentResponse response = new MemberPaymentResponse();
            response.setId(payment.getId());
            response.setAmount(payment.getAmount());
            response.setPaymentMode(payment.getPaymentMode());
            response.setCreationDate(payment.getCreationDate());
            responses.add(response);
        }

        return responses;
    }
}
