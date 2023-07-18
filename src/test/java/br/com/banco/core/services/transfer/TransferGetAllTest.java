package br.com.banco.core.services.transfer;

import br.com.banco.core.models.entities.Account;
import br.com.banco.core.models.entities.Transfer;
import br.com.banco.core.models.enuns.TypeTransfer;
import br.com.banco.core.models.response.TransferResponse;
import br.com.banco.core.repositories.TransferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TransferGetAllTest {

    @InjectMocks
    private TransferGetAll transferGetAll;
    @Mock
    private TransferRepository repository;
    private Transfer transfer;
    private Account account;
    private List<Transfer> transfers;
    private  List<TransferResponse> transferResponses;

    @BeforeEach
    public  void setup(){
        account = Account.builder()
                .id(12)
                .name("Gustavo")
                .build();

        transfer = Transfer.builder()
                .id(1)
                .transferDate(LocalDateTime.of(2020, 12, 1, 12, 10))
                .value(BigDecimal.valueOf(2000.00))
                .account(account)
                .operator("Gustavo")
                .type(TypeTransfer.DEPOSITO)
                .build();

        transfers = Collections.singletonList(transfer);

        transferResponses = transfers.stream()
                .map(TransferResponse::new)
                .collect(Collectors.toList());

    }
    @Test
    void getTransfersByAccount() {
        when(repository.findAll())
                .thenReturn(transfers);

        List<TransferResponse> result = transferGetAll.getTransfersByAccount(
                null,
                null,
                null,
                null);

        assertEquals(transferResponses, result);
        assertEquals(transferResponses.get(0).getId(), result.get(0).getId());
        assertInstanceOf(transferResponses.getClass(), result);
    }

    @Test
    void getTransfersByAccountShouldReturnNull() {

        List<TransferResponse> result = transferGetAll.getTransfersByAccount(
                null,
                "Gustavo",
                null,
                null);

        assertNull(result);
    }

}