package br.com.banco.core.services;

import br.com.banco.core.models.entities.Transfer;
import br.com.banco.core.models.response.TransferResponse;
import br.com.banco.core.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService {

    @Autowired
    TransferRepository transferRepository;

    public List<TransferResponse> getTransfersByAccountNumber(Integer accountNumber) {
        return toTransferResponse(
                transferRepository.findAllByAccount_Id(accountNumber)
        );
    }

    public List<TransferResponse> getTransfersByAccountNumber(Integer accountNumber,
                                                              LocalDateTime iniDate,
                                                              LocalDateTime endDate
    ) {
        return toTransferResponse(
                transferRepository
                        .findByAccount_IdAndTransferDateBetween(accountNumber, iniDate, endDate)
        );
    }

    public List<TransferResponse> getTransfersByAccountName(String name) {
        return toTransferResponse(
                transferRepository.findAllByAccount_Name(name)
        );
    }

    public List<TransferResponse> getTransfersByAccountName(String name,
                                                            LocalDateTime iniDate,
                                                            LocalDateTime endDate
    ) {
        return toTransferResponse(
                transferRepository
                        .findByAccount_NameAndTransferDateBetween(name, iniDate, endDate)
        );
    }

    public List<TransferResponse> getTransfers() {
        return toTransferResponse(transferRepository.findAll());
    }

    private List<TransferResponse> toTransferResponse(List<Transfer> transfers){
        return transfers.stream()
                .map(TransferResponse::new)
                .collect(Collectors.toList());
    }
}
