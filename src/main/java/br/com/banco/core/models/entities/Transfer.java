package br.com.banco.core.models.entities;

import br.com.banco.core.models.enuns.TypeTransfer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transferencia")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "data_transferencia ")
    private LocalDateTime transferDate;
    @Column(name = "valor")
    private BigDecimal value;
    @Column(name = "tipo")
    @Enumerated(value = EnumType.STRING)
    private TypeTransfer type;
    @Column(name = "nome_operador_transacao")
    private String operator;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id")
    private Account account;
}
