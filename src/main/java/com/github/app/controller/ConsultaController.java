package com.github.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.app.model.consulta.Consulta;
import com.github.app.model.consulta.ConsultaRepository;
import com.github.app.model.consulta.DadosAgendamentoConsulta;
import com.github.app.model.medico.MedicoRepository;
import com.github.app.model.paciente.PacienteRepository;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    //cadastro de umaa nova consulta
    @PostMapping()
    public Consulta agendar(@RequestBody DadosAgendamentoConsulta dados) {
        // Pegar o id médico.
        var medico = medicoRepository.getReferenceById(dados.medicoId());
        // Pegar o id Paciente.
        var paciente = pacienteRepository.getReferenceById(dados.pacienteId());
        // Criar um objeto do tipo Consulta, passando os objetos médico e paciente encontrados no banco de dados, e a data e hora da consulta recebidos na requisição.
        var consulta = new Consulta(dados);
            consulta.setMedico(medico);
            consulta.setPaciente(paciente);
            // Salvar a consulta no banco de dados, usando o método save do repositório de consultas, passando o objeto consulta criado.
        
        // Retornar a consulta salva no banco de dados.
        return consultaRepository.save(consulta);
    }
}