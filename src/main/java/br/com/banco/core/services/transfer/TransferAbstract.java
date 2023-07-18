package br.com.banco.core.services.transfer;

import br.com.banco.core.models.entities.Transfer;
import br.com.banco.core.models.response.TransferResponse;
import br.com.banco.core.repositories.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class TransferAbstract implements TransferService {
    protected List<TransferResponse> toTransferResponse(List<Transfer> transfers){
        return transfers
                .stream()
                .map(TransferResponse::new)
                .collect(Collectors.toList());
    }
}
