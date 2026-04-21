package com.Federation.Final.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Collectivity {
    private String id;
    private String location;
    private boolean federationApproval;
    private CollectivityStructure collectivityStructure;
    private List<Member> members;
    private List<String> memberIds;


}
