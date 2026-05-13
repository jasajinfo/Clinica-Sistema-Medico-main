package com.github.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.github.app.model.paciente.DadosAtualizacaoPaciente;
import com.github.app.model.paciente.DadosCadastroPaciente;
import com.github.app.model.paciente.DadosListagemPaciente;
import com.github.app.model.paciente.Paciente;
import com.github.app.model.paciente.PacienteRepository;

import jakarta.transaction.Transactional;
import lombok.experimental.var;

@RestController // SSPRING WEB- Informa para o Springboot que abaixo é uma classe controladora de requisições (GET-POST-PUT-DELETE).
@RequestMapping("pacientes") // // SPRING WEB- Cria um caminho(endpoint) para a classe PacienteController. 
public class PacienteController {

    @Autowired // Sobrescrevendo algo. É um padrão utilizado na injeção de depêndecia.
    private PacienteRepository repository;
    
    @PostMapping // SPRING WEB - Informa que o método abaixo é do tipo POST (cadastrar).
    @Transactional // SPRING - Informa q ao spring boot que o metodo  irá incluir o BD.
    public void cadastrar(@RequestBody DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));
    }

    @GetMapping("todos")  // SPRING WEB - Informa que o método abaixo é do tipo GET (buscar/ler)
    public List<Paciente> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("listar")           
    public List<DadosListagemPaciente> listar() {
        return repository.findAll().stream().map(DadosListagemPaciente::new).toList();
        // findAll() -> Método que retorna uma lista de objetos do tipo DadosListagemPaciente. 
        // stream() -> Método utilizado para transformar a lista em um fluxo de dados, permitindo aplicar operações de transformação.
        // map(DadosListagemPaciente::new) -> Método utilizado para converter cada objeto do tipo pacientepaciente em um json DadosListagempaciente, utilizando o constructor que criamos em DadosListagemPaciente.
        // toList() -> Método utilizado para coletar os resultados em uma nova lista do tipo DadosListagemPaciente, que é o formato que queremos retornar para a API.
    }

    @GetMapping         
    public Page<DadosListagemPaciente> listarPorPagina(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
        //return repository.findAll().stream().map(DadosListagemPaciente::new).toList();
    }

    @PutMapping
    @Transactional // SPRING - Informa q ao spring boot que o metodo  irá alterar o BD
        public void atualizar(@RequestBody DadosAtualizacaoPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        // var é uma palavra reservada em Java que permite declarar uma variável sem especificar seu tipo. Entender que é um campo chamado id médico, que é do tipo inteiro, e que é o id do médico a ser atualizado.   
    paciente.atualizarInformacoes(dados);
     }
@DeleteMapping("/{id}") // SPRING WEB - Informa que o método abaixo é do tipo DELETE (excluir), e que o id do médico a ser excluído será passado como parâmetro na URL.
   @Transactional //spring data jpa - informa ao spring boot que o método irá excluir algo no BD
 public void excluir(@PathVariable Integer id){ //pathvariable é utilizado para informar que o id do médico a ser excluído será passado como parâmetro na URL, ou seja, na requisição.
       repository.deleteById(id);

      //exclusão lógica, permite que o registro seja "excluido " sem ser apagado do DB. 
    //  @DeleteMapping("/{id}") // SPRING WEB - Informa que o método abaixo é do tipo DELETE (excluir), e que o id do médico a ser excluído será passado como parâmetro na URL.
    //  @Transactional //spring data jpa - informa ao spring boot que o método irá excluir algo no BD
    //   public void alteratStatus(@PathVariable Integer id){ //pathvariable é utilizado para informar que o id do médico a ser excluído será passado como parâmetro na URL, ou seja, na requisição.
    //     var paciente = repository.getReferenceById(id) ;
    //     paciente.exclusaoLogica();   

        
    }   
  
}
