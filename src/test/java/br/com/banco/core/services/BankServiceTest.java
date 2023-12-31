package br.com.banco.core.services;

import br.com.banco.core.models.entities.Account;
import br.com.banco.core.models.entities.Transfer;
import br.com.banco.core.models.enuns.TypeTransfer;
import br.com.banco.core.models.response.TransferResponse;
import br.com.banco.core.services.transfer.TransferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
public class BankServiceTest {

    @InjectMocks
    private BankService bankService;
    @Mock
    private List<TransferService> transferServices;
    @Mock
    private TransferService transferService;
    private  Transfer transfer2;
    private Transfer transfer1;
    private  Account account;
    private  List<Transfer> transfers;
    private  List<TransferResponse> transferResponses;

    @BeforeEach
    public  void setup(){
        account = Account.builder()
                .id(12)
                .name("Gustavo")
                .build();

        transfer1 = Transfer.builder()
                .id(1)
                .transferDate(LocalDateTime.of(2020, 12, 1, 12, 10))
                .value(BigDecimal.valueOf(2000.00))
                .account(account)
                .operator("Gustavo")
                .type(TypeTransfer.DEPOSITO)
                .build();

        transfer2 = Transfer.builder()
                .id(2)
                .transferDate(LocalDateTime.of(2022, 12, 1, 13, 10))
                .value(BigDecimal.valueOf(-200.00))
                .account(account)
                .operator("Gustavo")
                .type(TypeTransfer.SAQUE)
                .build();

        transfers = Arrays.asList(transfer1, transfer2);

        transferResponses = transfers.stream()
                .map(TransferResponse::new)
                .collect(Collectors.toList());
    }

    @Test
    void getTransfersByAccount(){

        when(transferService.getTransfersByAccount(12, null, null, null))
                .thenReturn(transferResponses);
        when(transferServices.stream())
                .thenReturn(Stream.of(transferService));

        List<TransferResponse> result = bankService
                .getTransfersByAccount(12, null, null, null);

        assertEquals(transferResponses, result);
        assertEquals(transferResponses.get(0).getId(), result.get(0).getId());
        assertInstanceOf(transferResponses.getClass(), result);
    }
}
