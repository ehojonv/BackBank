package br.com.fiap.BackBank.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String agency;

    @NotBlank
    private String name;

    @NotBlank @Size(min = 11, max = 11)
    private String cpf;

    @PastOrPresent @Default
    private LocalDate openingDate = LocalDate.now();

    @PositiveOrZero @Default
    private BigDecimal initialBalance = BigDecimal.valueOf(0);
    
    @Default
    private boolean active = true;

    @Enumerated(EnumType.STRING)
    private AccountType type;


}
