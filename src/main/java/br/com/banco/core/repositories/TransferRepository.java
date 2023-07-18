package br.com.banco.core.repositories;

import br.com.banco.core.models.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
    List<Transfer> findAllByAccount_Id(Integer account_id);
    List<Transfer> findByAccount_IdAndTransferDateBetween(Integer account_id, LocalDateTime iniDate,
                                                          LocalDateTime endDate);
    List<Transfer> findByAccount_NameAndTransferDateBetween(String name, LocalDateTime iniDate, LocalDateTime endDate);
    List<Transfer> findAllByAccount_Name(String name);
}
