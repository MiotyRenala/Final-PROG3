package com.Federation.Final.entity.dto;

import com.Federation.Final.entity.Member;
import lombok.Data;

@Data
public class CollectivityStructure {
    private Member president;
    private Member vicePresident;
    private Member treasurer;
    private Member secretary;
}
