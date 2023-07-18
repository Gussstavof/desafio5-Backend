package br.com.banco.core.models.response;

import br.com.banco.core.models.entities.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private Integer id;
    private String name;

    public AccountResponse(Account account){
        BeanUtils.copyProperties(account, this);
    }
}
