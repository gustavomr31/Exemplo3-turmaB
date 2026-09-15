// Define o pacote onde a classe Produto está localizada.
package br.senac.tads.dsw.exemplo3.model;


// Importa a anotação @Entity.
// Ela informa ao JPA que essa classe representa uma entidade
// que será armazenada no banco de dados.
import jakarta.persistence.Entity;

// Importa @GeneratedValue.
// É utilizada para definir como o ID será gerado.
import jakarta.persistence.GeneratedValue;

// Importa GenerationType.
// Define a estratégia utilizada para gerar o ID.
import jakarta.persistence.GenerationType;

// Importa @Id.
// Indica qual atributo será a chave primária da tabela.
import jakarta.persistence.Id;


// Importa @NotBlank.
// Será utilizada para impedir que o nome fique vazio.
import jakarta.validation.constraints.NotBlank;

// Importa @NotNull.
// Será utilizada para impedir que o preço seja null.
import jakarta.validation.constraints.NotNull;

// Importa @Positive.
// Será utilizada para garantir que o preço seja maior que zero.
import jakarta.validation.constraints.Positive;


// @Entity informa ao JPA que essa classe é uma entidade.
//
// O JPA utilizará essa classe para representar uma tabela
// no banco de dados.
@Entity
public class Produto {


    // @Id indica que o atributo id será a chave primária.
    //
    // A chave primária identifica cada produto de forma única.
    @Id


    // @GeneratedValue indica que o ID será gerado automaticamente.
    //
    // GenerationType.IDENTITY significa que normalmente
    // o próprio banco de dados será responsável por gerar o ID.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // @NotBlank indica que o nome não pode ser:
    //
    // null
    // ""
    // "   "
    //
    // Portanto, o nome precisa possuir algum conteúdo.
    @NotBlank
    private String nome;


    // @NotNull indica que o preço não pode ser null.
    //
    // Ou seja, é obrigatório informar um preço.
    @NotNull


    // @Positive indica que o valor precisa ser positivo.
    //
    // Por exemplo:
    //
    // 100.0  -> válido
    // 50.0   -> válido
    // 0.0    -> inválido
    // -10.0  -> inválido
    @Positive
    private Double preco;


    // Construtor vazio.
    //
    // O JPA precisa de um construtor sem argumentos
    // para conseguir criar objetos dessa entidade.
    public Produto() {
    }


    // Construtor que recebe nome e preço.
    //
    // Ele permite criar um produto já informando
    // seus principais dados.
    //
    // Exemplo:
    //
    // Produto produto = new Produto("Notebook", 3000.0);
    public Produto(String nome, Double preco) {


        // Recebe o nome enviado pelo construtor
        // e coloca no atributo nome da classe.
        this.nome = nome;


        // Recebe o preço enviado pelo construtor
        // e coloca no atributo preco da classe.
        this.preco = preco;
    }


    // Getter do ID.
    //
    // O getter serve para PEGAR/CONSULTAR o valor do ID.
    public Long getId() {
        return id;
    }


    // Setter do ID.
    //
    // O setter serve para ALTERAR/DEFINIR o valor do ID.
    public void setId(Long id) {
        this.id = id;
    }


    // Getter do nome.
    //
    // Retorna o nome do produto.
    public String getNome() {
        return nome;
    }


    // Setter do nome.
    //
    // Permite alterar o nome do produto.
    public void setNome(String nome) {
        this.nome = nome;
    }


    // Getter do preço.
    //
    // Retorna o preço do produto.
    public Double getPreco() {
        return preco;
    }


    // Setter do preço.
    //
    // Permite alterar o preço do produto.
    public void setPreco(Double preco) {
        this.preco = preco;
    }

}