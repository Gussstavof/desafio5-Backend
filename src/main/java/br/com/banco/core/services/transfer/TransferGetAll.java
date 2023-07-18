package br.com.banco.core.services.transfer;

import br.com.banco.core.models.response.TransferResponse;
import br.com.banco.core.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class TransferGetAll extends TransferAbstract{
    @Autowired
    TransferRepository repository;

    @Override
    public List<TransferResponse> getTransfersByAccount(Integer accountNumber, String name,
                                                        LocalDateTime iniDate, LocalDateTime endDate) {
        if (accountNumber == null && name == null && iniDate == null && endDate == null){
            return toTransferResponse(repository
                    .findAll());
        }
        return null;
    }
}
