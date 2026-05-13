package com.github.app.model.paciente;

public record DadosListagemPaciente(// O record é uma classe imutável, ou seja, seus atributos não podem ser alterados depois de criados. Ele é utilizado para representar dados de forma simples e concisa, sem a necessidade de criar métodos getters e setters.
    String nome,
    String email,
    String cpf

  
) {
    // Método construtor recebendo o objeto Medico e convertendo para json DadosListagemMedico.
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
    // O this é para chamar o constructor do record, passando do paciente para preencher os atributos da classe.
    // O constructor acima é utilizado para converter um objeto tipo Paciente em um json do tipo DadosListagemPaciente que é o formato que queremos devolver para nossa API.

    
}
