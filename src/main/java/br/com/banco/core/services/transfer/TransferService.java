package br.com.banco.core.services.transfer;

import br.com.banco.core.models.response.TransferResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface TransferService {
    public List<TransferResponse> getTransfersByAccount(Integer accountNumber,
                                                        String name,
                                                        LocalDateTime iniDate,
                                                        LocalDateTime endDate);
}
