package com.github.app.model.paciente;

public record DadosListagemPaciente(
    Integer id,
    String nome,
    String email,
  
) {
    // Método construtor recebendo o objeto Medico e convertendo para json DadosListagemMedico.
    public DadosListagemPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail());
    }
    // O this é para chamar o constructor do record, passando do paciente para preencher os atributos da classe.
    // O constructor acima é utilizado para converter um objeto tipo Paciente em um json do tipo DadosListagemPaciente que é o formato que queremos devolver para nossa API.

    
}
