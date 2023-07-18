package br.com.banco.core.controllers;

import br.com.banco.core.models.response.TransferResponse;
import br.com.banco.core.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/bank/v1/transfers")
public class BankController {
    @Autowired
    private BankService bankService;

    @GetMapping
    public ResponseEntity<List<TransferResponse>> getTransfersByAccount(
            @RequestParam(value = "account", required = false) Integer accountNumber,
            @RequestParam(value = "name",  required = false) String name,
            @RequestParam(value = "start", required = false )
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(value = "end",   required = false )
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime end){

        List<TransferResponse> response = bankService
                .getTransfersByAccount(accountNumber, name, start, end);

        return ResponseEntity.ok(response);
    }
}
