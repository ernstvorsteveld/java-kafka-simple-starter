package com.sternitc.kafkatest.accountinfo;

import com.sternitc.kafkatest.accountinfo.domain.AccountInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class AccountCreatedEventV1 extends SimpleEvent<AccountInfo> {
    public AccountCreatedEventV1(AccountInfo accountInfo) {
        super("1", accountInfo);
    }
}
