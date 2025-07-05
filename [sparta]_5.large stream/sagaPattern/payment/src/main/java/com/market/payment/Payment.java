package com.market.payment;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Payment {
    private UUID paymentId;
    private String userId;

    private String payAmount;

    private String payStatus;
}