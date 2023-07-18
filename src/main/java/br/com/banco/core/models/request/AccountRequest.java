package br.com.banco.core.models.request;

import br.com.banco.core.models.entities.Account;
import org.springframework.beans.BeanUtils;

public class AccountRequest {
    private Integer id;
    private String name;

    public Account toAccount(){
        Account account = new Account();
        BeanUtils.copyProperties(this, account);
        return account;
    }
}
