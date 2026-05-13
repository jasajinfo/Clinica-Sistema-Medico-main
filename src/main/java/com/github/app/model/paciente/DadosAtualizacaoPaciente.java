package com.github.app.model.paciente;

import com.github.app.model.endereco.DadosCadastroEndereco;

// Classe responsável por receber os dados de atualização do paciente, utilizando o recurso record.
// DadosCadastroEndereco é um DTO, então como o endereço pode ser alterado em qualquer campo,
// estamos reusando (reuso) para a alteração do médico.
public record DadosAtualizacaoPaciente(Integer id, String nome, String email, DadosCadastroEndereco endereco) {
    
}
