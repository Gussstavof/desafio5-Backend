package br.com.banco.core.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "data_transferencia ")
    private LocalDateTime dataTransferencia;
    private BigDecimal valor;
    private String tipo;
    @Column(name = "nome_operador_transacao")
    private String operador;
    @ManyToOne
    private Account account;
}
