package com.cobuy.BE.settlement.model;

import com.cobuy.BE.settlement.enums.SettlementStatus;
import com.cobuy.BE.user.model.User;

public class DutchSettlementDetail {
    private Long id;

    //더치 정산
    private String dutchSettlementId;
    //유저
    private String userId;

    // 세부 정산 상태
    private SettlementStatus settlementStatus;
    // 정산 금액
    private Long settlememtAmount;
    // 남은 정산 금액
    private Long unpaidAmount;
}
