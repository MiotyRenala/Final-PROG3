package com.Federation.Final.entity.dto;

import com.Federation.Final.entity.Member;
import lombok.Data;

import java.util.List;

@Data
public class CollectivityResponse {
    private String id;
    private String location;
    private CollectivityStructure structure;
    private List<Member> members;

}
