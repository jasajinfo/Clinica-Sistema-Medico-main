package com.github.app.model.medico;

public record DadosListagemMedico(
    Integer id,
    String nome,
    String email,
    String crm,
    Especialidade especialidade
) {
    // Método construtor recebendo o objeto Medico e convertendo para json DadosListagemMedico.
    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
    // O this é para chamar o constructor do record, passando do medico para preencher os atributos da classe.
    // O constructor acima é utilizado para converter um objeto tipo Medico em um json do tipo DadosListagemMedico, que é o formato que queremos devolver para nossa API.

    
}
