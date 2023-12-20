package com.sternitc.kafkatest.accountinfo.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class AccountInfo {

    private String id;
    private String uuid;
    private String iban;
    private float balance;

}
