package br.com.banco.core.controllers;

import br.com.banco.core.models.enuns.TypeTransfer;
import br.com.banco.core.models.response.AccountResponse;
import br.com.banco.core.models.response.TransferResponse;
import br.com.banco.core.services.BankService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BankController.class)
@AutoConfigureMockMvc
class BankControllerTest {

    @Autowired
    BankController bankController;
    @Autowired
    MockMvc mockMvc;
    @MockBean
    BankService bankService;
    @Autowired
    ObjectMapper objectMapper;
    TransferResponse transferResponse;

    @BeforeEach
    void setUp() {
        AccountResponse account = AccountResponse.builder()
                .id(12)
                .name("Gustavo")
                .build();

        transferResponse = TransferResponse.builder()
                .id(1)
                .transferDate(LocalDateTime.of(2020, 12, 1, 12, 10))
                .value(BigDecimal.valueOf(2000.00))
                .account(account)
                .operator("Gustavo")
                .type(TypeTransfer.DEPOSITO)
                .build();
    }

    @Test
    void getTransfersByAccount() throws Exception {
        when(bankService.getTransfersByAccount(null,"Gustavo",
                                                null, null))
                .thenReturn(List.of(transferResponse));


        mockMvc.perform(get("/bank/v1/transfers?name=Gustavo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(transferResponse))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}