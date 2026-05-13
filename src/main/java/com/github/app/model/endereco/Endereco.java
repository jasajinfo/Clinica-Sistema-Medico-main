package com.github.app.model.endereco;
import jakarta.persistence.Embeddable;
import lombok.*;
import com.github.app.model.endereco.DadosCadastroEndereco;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable


public class Endereco{
    //Atributos caracteristica  variaveis   

    private String cep;
    private String complemento;
    private String cidade;
    private String uf;
    private String logradouro;
    private String bairro;
//Constructor recebendo os dados convertidos DTO
 
public Endereco (DadosCadastroEndereco dados){
    this.logradouro = dados.logradouro();
    this.bairro = dados.bairro();
    this.cep =  dados.cep() ;
    this.complemento = dados.complemento();
    this.cidade = dados.cidade();    
    this.uf = dados.uf();   

}

 // Método para atualizar as informações do endereco, recebendo um objeto do tipo DadosCadastroEndereco e atualizando os atributos do endereco com os dados recebidos.
    public void atualizarInformacoes(DadosCadastroEndereco dados) {
        if(dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }
        if(dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if(dados.cep() != null) {
            this.cep = dados.cep();
        }
        if(dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
        if(dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if(dados.uf() != null) {
            this.uf = dados.uf();
        }

    }
}






