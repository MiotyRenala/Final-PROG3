package com.Federation.Final.entity.dto;

import com.Federation.Final.entity.Member;
import lombok.Data;

import java.util.List;

@Data
public class MemberResponse extends MemberInformation{
    private String id;
    private List<Member> referees;

}
