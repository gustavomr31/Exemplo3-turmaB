// Define o pacote onde a classe Avaliacao está localizada.
package br.senac.tads.dsw.exemplo3.model;


// Importa @Entity.
// Essa anotação informa ao JPA que essa classe representa uma entidade do banco de dados.
import jakarta.persistence.Entity;

// Importa @GeneratedValue.
// É usada para definir como o ID será gerado.
import jakarta.persistence.GeneratedValue;

// Importa GenerationType.
// Define a estratégia utilizada para gerar o ID.
import jakarta.persistence.GenerationType;

// Importa @Id.
// Indica qual atributo será a chave primária da tabela.
import jakarta.persistence.Id;

// Importa @JoinColumn.
// Define a coluna responsável pelo relacionamento entre as tabelas.
import jakarta.persistence.JoinColumn;

// Importa @ManyToOne.
// Define um relacionamento de muitos para um.
import jakarta.persistence.ManyToOne;


// Importa @Max.
// Define um valor máximo permitido para um atributo.
import jakarta.validation.constraints.Max;

// Importa @Min.
// Define um valor mínimo permitido para um atributo.
import jakarta.validation.constraints.Min;

// Importa @NotBlank.
// Impede que um campo String fique vazio ou contenha apenas espaços.
import jakarta.validation.constraints.NotBlank;

// Importa @NotNull.
// Impede que o atributo receba o valor null.
import jakarta.validation.constraints.NotNull;


// @Entity informa ao JPA que essa classe será uma entidade do banco de dados.
//
// Na prática, o JPA vai utilizar essa classe para representar
// uma tabela no banco.
@Entity
public class Avaliacao {


    // @Id indica que esse atributo será a chave primária da tabela.
    //
    // A chave primária serve para identificar cada avaliação
    // de forma única.
    @Id


    // @GeneratedValue define que o valor do ID será gerado automaticamente.
    //
    // GenerationType.IDENTITY normalmente significa que
    // o próprio banco de dados ficará responsável por gerar o ID.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // @NotBlank significa que o autor não pode ser:
    //
    // null
    // ""
    // "   "
    //
    // Ou seja, não pode ficar vazio ou somente com espaços.
    @NotBlank
    private String autor;


    // @NotNull significa que o comentário não pode ser null.
    //
    // Atenção:
    // @NotNull não impede uma String vazia "".
    //
    // Se você quiser impedir também texto vazio,
    // poderia utilizar @NotBlank.
    @NotNull
    private String comentario;


    // @NotNull significa que a nota é obrigatória.
    @NotNull


    // @Min determina o menor valor permitido.
    //
    // Nesse caso, a nota precisa ser no mínimo 1.
    //
    // Se alguém enviar 0, a validação apresentará
    // a mensagem definida abaixo.
    @Min(value = 1, message = "A nota mínima é 1")


    // @Max determina o maior valor permitido.
    //
    // Nesse caso, a nota pode ser no máximo 5.
    //
    // Se alguém enviar 6, a validação apresentará
    // a mensagem definida abaixo.
    @Max(value = 5, message = "A nota máxima é 5")
    private Integer nota;


    // @ManyToOne cria um relacionamento de muitos para um.
    //
    // Significa que:
    //
    // Várias avaliações podem pertencer a um mesmo produto.
    //
    // Exemplo:
    //
    // Produto: Notebook
    //
    // Avaliação 1 -> Notebook
    // Avaliação 2 -> Notebook
    // Avaliação 3 -> Notebook
    //
    // Então temos:
    //
    // Muitas Avaliações -> Um Produto
    @ManyToOne


    // @JoinColumn define a coluna utilizada para fazer
    // o relacionamento com a tabela Produto.
    //
    // name = "produto_id" significa que na tabela de Avaliacao
    // haverá uma coluna chamada produto_id.
    //
    // Essa coluna armazenará o ID do produto relacionado.
    @JoinColumn(name = "produto_id")
    private Produto produto;


    // Construtor vazio.
    //
    // O JPA precisa de um construtor sem argumentos
    // para conseguir criar objetos dessa entidade.
    public Avaliacao() {
    }


    // Construtor utilizado para criar uma Avaliacao
    // já passando os valores necessários.
    //
    // Exemplo:
    //
    // new Avaliacao(
    //     "Gustavo",
    //     1L,
    //     "Produto muito bom",
    //     5,
    //     produto
    // );
    public Avaliacao(
            String autor,
            Long id,
            String comentario,
            Integer nota,
            Produto produto) {


        // Recebe o ID enviado pelo construtor
        // e coloca no atributo id da classe.
        this.id = id;


        // Recebe o autor e coloca no atributo autor.
        this.autor = autor;


        // Recebe o comentário e coloca no atributo comentario.
        this.comentario = comentario;


        // Recebe a nota e coloca no atributo nota.
        this.nota = nota;


        // Recebe o produto e coloca no atributo produto.
        //
        // Aqui estamos associando a avaliação a um produto.
        this.produto = produto;
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


    // Getter do autor.
    //
    // Retorna o autor da avaliação.
    public String getAutor() {
        return autor;
    }


    // Setter do autor.
    //
    // Permite alterar o autor da avaliação.
    public void setAutor(String autor) {
        this.autor = autor;
    }


    // Getter do comentário.
    //
    // Retorna o comentário da avaliação.
    public String getComentario() {
        return comentario;
    }


    // Setter do comentário.
    //
    // Permite alterar o comentário da avaliação.
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }


    // Getter da nota.
    //
    // Retorna a nota da avaliação.
    public Integer getNota() {
        return nota;
    }


    // Setter da nota.
    //
    // Permite alterar a nota da avaliação.
    public void setNota(Integer nota) {
        this.nota = nota;
    }


    // Getter do produto.
    //
    // Retorna o produto relacionado à avaliação.
    public Produto getProduto() {
        return produto;
    }


    // Setter do produto.
    //
    // Permite definir ou alterar o produto relacionado à avaliação.
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}