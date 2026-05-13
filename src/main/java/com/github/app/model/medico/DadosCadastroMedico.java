package com.github.app.model.medico;
import com.github.app.model.endereco.DadosCadastroEndereco;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroMedico(

    @NotBlank // Verifica se o campo está preenchido -> Em Strings
    String nome, 

    @Email
    @NotBlank
    String email, 

    String telefone,

    String crm,

    @NotNull
    Especialidade especialidade,

    @NotNull @Valid
    DadosCadastroEndereco endereco
) {
    
}

// Essa classe é responsável por pegar os dados que estão vindo
// do simulador de requisição (insomnia) e transformar em atributos(variáveis) de uma entidade chamada Médico.
// A classe do tipo record já cria todos os getters, setters, constructors, hashcode e equals.