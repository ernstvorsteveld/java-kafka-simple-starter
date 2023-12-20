package com.sternitc.kafkatest.accountinfo;

import com.sternitc.kafkatest.account.domain.AccountInfoProto;

public interface AccountInfoConsumer {

    void listen(
            AccountInfoProto.BankAccount message,
            String topic,
            Integer partition,
            Long offset);

}
