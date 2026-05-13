package com.github.app.model.consulta;

import java.time.LocalDateTime;

import com.github.app.model.medico.Medico;
import com.github.app.model.paciente.Paciente;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id") // Lombok - Gera os métodos equals() e hashCode() com base no campo id, garantindo que a comparação de objetos seja feita corretamente.   
@Table(name = "consultas")  

public class Consulta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA - Informa que o campo id é auto-incrementável, ou seja, o valor será gerado automaticamente pelo banco de dados.
    private Integer id;
    @JoinColumn(name = "medicoId") // JPA - Informa que o campo medico é uma chave estrangeira que referencia a tabela de médicos, e que o nome da coluna no banco de dados será medicoId.
    @ManyToOne
    private Medico medico;
    @JoinColumn(name = "pacienteId") // JPA - Informa que o campo paciente é uma chave estrangeira que referencia a tabela de pacientes, e que o nome da coluna no banco de dados será pacienteId.
    @ManyToOne
    private Paciente paciente;
    @Enumerated(EnumType.STRING) // JPA - Informa que o campo status é do tipo enumerado, e que os valores serão armazenados como strings no banco de dados.       
    private Status status;
    private String observacao;
    private LocalDateTime data;    
    
    // terceiro constructor da classe consulta que recebe a conversão que a classe DTO DadosAgendamentoConsulta etá realizando . Json-> OBJ.
    public Consulta(DadosAgendamentoConsulta dados) {
     //   this.medico = dados.medicoId();
       // this.paciente  = dados.pacienteId();
        this.status = dados.status();
        this.observacao = dados.observacao();   
        this.data = dados.data();
    }

}
