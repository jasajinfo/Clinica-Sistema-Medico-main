package com.github.app.model.medico;
//classe modelo responsp por criar tabelas e colunas no bd
import com.github.app.model.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;


@Getter //lombok cria get para todos os atributos
@Setter // lombok cria set para todos os atributos
@AllArgsConstructor //lombok cria um construtor com todos os argumentos
@NoArgsConstructor //lombok cria um construtor sem argumentos 
@EqualsAndHashCode  (of = "id")//lombok cria o hash code e equals usando apenas o id, ou seja, o id é o atributo que define a identidade do médico
@Entity// anotação do spring que indica que essa classe é uma entidade, ou seja, ela vai ser mapeada para uma tabela no banco de dados
@Table(name = "medicos")// anotação do spring que indica o nome da tabela no banco de dados, nesse caso, medicos   
//SRING JPA *oPCIONAL, GERA UMA TABELA COM O NOME MÉDICOS NO bd 
public class Medico {
    @Id // anotação do spring que indica que esse atributo é a chave primária da tabela no banco de dados
    @GeneratedValue(strategy = GenerationType.IDENTITY)// anotação do spring que indica que o valor desse atributo 
    // vai ser gerado automaticamente pelo banco de dados, usando a estratégia de identidade (auto-incremento)  
    private Integer id; // não está vindo do insomnia.  Vem do Sripring JPA e cria o id de forma utomática
    private String nome;
    private String email;
    private String telefone;
    private String crm;
  private Boolean ativo = true;
    @Enumerated(EnumType.STRING) // anotação do spring que indica que esse atributo é um enum e deve ser armazenado como string no banco de dados
    private Especialidade especialidade;

    @Embedded // anotação do spring que indica que esse atributo é um objeto embutido, ou seja, ele vai ser mapeado para colunas na mesma tabela do médico, e não em uma tabela separada
  
  
  private Endereco endereco;
  
  //Construtor que recebe um objeto do tipo DadosCadastroMedico e inicializa os atributos do médico com os valores desse objeto
  public Medico(DadosCadastroMedico dados) {
    this.nome = dados.nome();
    this.email = dados.email();
     this.telefone = dados.telefone();
    this.crm = dados.crm();
    this.especialidade = dados.especialidade();
     this.endereco = new Endereco(dados.endereco());
  }
  // Método para verificar a atualização do médico, recebendo um objeto do tipo DadosAtualizacaoMedico e atualizando os atributos do médico com os dados recebidos na requisição.
    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        // Verifica se o nome recebido é diferente de null, ou seja, se o nome foi enviado na requisição da atualização (PUT), e se for diferente de null, atualiza o nome do médico com o novo nome recebido.
        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.email() != null) {
            this.email = dados.email();
        }
        if(dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    // Método responsável por alterar o status do médico de true para false
    public void exclusaoLogica() {
        this.ativo = false;
    }

}