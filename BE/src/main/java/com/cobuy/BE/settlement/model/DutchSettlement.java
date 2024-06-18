package com.cobuy.BE.settlement.model;

import com.cobuy.BE.settlement.enums.SettlementStatus;
import com.cobuy.BE.user.model.User;

public class DutchSettlement {

    private Long id;

    private String userId;

    //정산 금액
    private Long settlementAmount;

    //더치페이 참여자수
    private Integer dutchPeopleCount;

    //1인당 정산 금액
    private Long divigeAmount;

    // 나머지 정산금액 (1/n 이후 나머지 )
    private Long spareAmount;

    //
    private SettlementStatus settlementStatus;
}
