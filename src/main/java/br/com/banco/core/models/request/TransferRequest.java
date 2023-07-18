package br.com.banco.core.models.request;

import br.com.banco.core.models.entities.Account;
import br.com.banco.core.models.entities.Transfer;
import br.com.banco.core.models.enuns.TypeTransfer;
import br.com.banco.core.models.response.AccountResponse;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferRequest {
    private Integer id;
    private LocalDateTime transferDate;
    private BigDecimal value;
    private TypeTransfer type;
    private String operator;
    private AccountResponse account;

    public Transfer toTransfer(){
        Transfer transfer = new Transfer();
        BeanUtils.copyProperties(this, transfer);
        return transfer;
    }
}
