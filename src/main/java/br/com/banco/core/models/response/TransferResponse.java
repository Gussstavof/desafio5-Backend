package br.com.banco.core.models.response;

import br.com.banco.core.models.entities.Transfer;
import br.com.banco.core.models.enuns.TypeTransfer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {
    private Integer id;
    private LocalDateTime transferDate;
    private BigDecimal value;
    private TypeTransfer type;
    private String operator;
    private AccountResponse account;

    public TransferResponse(Transfer transfer){
        this.account = new AccountResponse(transfer.getAccount());
        BeanUtils.copyProperties(transfer, this);
    }

}
