package br.com.banco.core.services;

import br.com.banco.core.models.entities.Account;
import br.com.banco.core.models.entities.Transfer;
import br.com.banco.core.models.enuns.TypeTransfer;
import br.com.banco.core.models.response.TransferResponse;
import br.com.banco.core.repositories.TransferRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
public class BankServiceTest {

    @InjectMocks
    private BankService bankService;
    @Mock
    private TransferRepository repository;
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
    void getTransfersByAccountNumber() {
        when(repository.findAllByAccount_Id(12))
                .thenReturn(transfers);

        List<TransferResponse> result = bankService.getTransfersByAccountNumber(12);

        assertEquals(transferResponses, result);
    }

    @Test
    void testGetTransfersByAccountNumber() {
        LocalDateTime start = LocalDateTime
                .of(2020,1, 12, 1, 13, 10 );

        LocalDateTime end = LocalDateTime
                .of(2021,1, 12, 1, 13, 10 );

        Integer accountId = 12;

        transferResponses.removeIf(transfer -> transfer.getId() != 1);

        when(repository.findByAccount_IdAndTransferDateBetween(accountId, start, end))
                .thenReturn(Collections.singletonList(transfer1));

        List<TransferResponse> result = bankService
                .getTransfersByAccountNumber(accountId, start, end);

        assertEquals(transferResponses, result);
    }

    @Test
    void getTransfersByAccountName() {
        when(repository.findAllByAccount_Name("Gustavo"))
                .thenReturn(transfers);

        List<TransferResponse> result = bankService.getTransfersByAccountName("Gustavo");

        assertEquals(transferResponses, result);
    }

    @Test
    void testGetTransfersByAccountName() {

        LocalDateTime start = LocalDateTime
                .of(2020,1, 12, 1, 13, 10 );
        LocalDateTime end = LocalDateTime
                .of(2021,1, 12, 1, 13, 10 );

        String name = "Gustavo";

        when(repository.findByAccount_NameAndTransferDateBetween(name,start,end))
                .thenReturn(Collections.singletonList(transfer1));

        transferResponses
                .removeIf( transfer -> transfer.getId() != 1);

        List<TransferResponse> result = bankService
                .getTransfersByAccountName(name, start, end);

        assertEquals(transferResponses, result);
    }

    @Test
    void getTransfers() {
        when(repository.findAll())
                .thenReturn(transfers);

        List<TransferResponse> result = bankService.getTransfers();

        assertEquals(transferResponses, result);
    }


}
