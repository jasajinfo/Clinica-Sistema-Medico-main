package com.github.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import com.github.app.model.medico.DadosAtualizacaoMedico;
import com.github.app.model.medico.DadosCadastroMedico;
import com.github.app.model.medico.DadosListagemMedico;
import com.github.app.model.medico.Medico;
import com.github.app.model.medico.MedicoRepository;

import jakarta.transaction.Transactional;

@RestController //anotação do spring que indica 123
//  que essa classe é um controlador de requisições REST, 
// ou seja, ela vai receber requisições HTTP e retornar respostas HTTP
@RequestMapping("medicos")// anotação do spring que indica o caminho (endpoint) base para as 
// requisições, medicocontroller
public class MedicoController {
        @Autowired// anotação do spring que indica que o spring vai injetar uma instância do MedicoRepository nessa variável, ou seja, o spring vai criar um objeto do tipo MedicoRepository e atribuir a essa variável
        private MedicoRepository repository;

        @PostMapping// anotação do spring que indica que esse método vai responder
        //  a requisições HTTP POST (cadastrar)
@Transactional// anotação do spring que indica que esse método deve ser execut
        public void cadastrar(@RequestBody DadosCadastroMedico dados){
            repository.save(new Medico(dados));
           
        }

        @GetMapping("todos")  // SPRING WEB - Informa que o método abaixo é do tipo GET (buscar/ler)
    public List<Medico> listarTodos() {
        return repository.findAll();
    }

    @GetMapping("listar")           
    public List<DadosListagemMedico> listar() {
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
        // findAll() -> Método que retorna uma lista de objetos do tipo DadosListagemMedico. 
        // stream() -> Método utilizado para transformar a lista em um fluxo de dados, permitindo aplicar operações de transformação.
        // map(DadosListagemMedico::new) -> Método utilizado para converter cada objeto do tipo medico em um json DadosListagemMedico, utilizando o constructor que criamos em DadosListagemMedico.
        // toList() -> Método utilizado para coletar os resultados em uma nova lista do tipo DadosListagemMedico, que é o formato que queremos retornar para a API.
    }

    @GetMapping         
    public Page<DadosListagemMedico> listarPorPagina(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
        //return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }

    @PutMapping
    @Transactional  // SPRING DATA JPA - Informa ao spring boot que o método irá alterar o BD.
    public void atualizar(@RequestBody DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        // var é uma palavra reservada em Java que permite declarar uma variável sem especificar seu tipo. O tipo da variável é inferido pelo compilador com base no valor que foi atribuído a ela.
        medico.atualizarInformacoes(dados);
    }

    // Exclusão - AQUI ESTOU EXCLUINDO MESMO
    @DeleteMapping("/{id}")
    @Transactional // SPRING DATA JPA - Informa ao spring boot que o método irá exluir algo no BD.
    public void excluir(@PathVariable Integer id) { //@PathVarialbe - Informa que o springboot precisa pegar o caminho variável {id} e entender que é um campo chamado id do Médico.
        repository.deleteById(id);
    }

    // Exclusão lógica - Uma regra de negócio que permite que um registro seja "excluído" sem ser apagado do banco de dados. 
    // @DeleteMapping("/{id}")
    // @Transactional
    // public void alterarStatus(@PathVariable Integer id) {
    //     var medico = repository.getReferenceById(id);
    //     medico.exclusaoLogica();
    // }
}

    

