package br.com.banco.core.services;

import br.com.banco.core.models.entities.Transfer;
import br.com.banco.core.models.response.TransferResponse;
import br.com.banco.core.repositories.TransferRepository;
import br.com.banco.core.services.transfer.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankService {

    @Autowired
    List<TransferService> transferServices;



    public List<TransferResponse> getTransfersByAccount(Integer accountNumber,
                                                                 String name,
                                                                 LocalDateTime iniDate,
                                                                 LocalDateTime endDate
    ) {
        TransferService service = transferServices.stream()
                .filter(transferService -> transferService.getTransfersByAccount(
                        accountNumber, name, iniDate, endDate) != null)
                .findFirst().
                orElseThrow(RuntimeException::new);

       return service.getTransfersByAccount(accountNumber, name, iniDate, endDate);
    }
}
