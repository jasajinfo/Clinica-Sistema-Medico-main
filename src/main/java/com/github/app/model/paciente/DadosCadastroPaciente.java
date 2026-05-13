package com.github.app.model.paciente;
import com.github.app.model.endereco.DadosCadastroEndereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPaciente(

    @NotBlank // Verifica se o campo está preenchido -> Em Strings
    String nome, 

    @Email
    @NotBlank
    String email, 

    String telefone,


    @NotNull @Valid
    DadosCadastroEndereco endereco
) {
    
}
