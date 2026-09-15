// Define o pacote onde o ProdutoRepository está localizado.
package br.senac.tads.dsw.exemplo3.repository;


// Importa o JpaRepository do Spring Data JPA.
// Ele fornece vários métodos prontos para acessar e manipular
// os dados no banco de dados.
import org.springframework.data.jpa.repository.JpaRepository;


// Importa a classe Produto.
// Essa é a entidade que o ProdutoRepository vai controlar.
import br.senac.tads.dsw.exemplo3.model.Produto;


// Cria a interface ProdutoRepository.
//
// Ela herda as funcionalidades do JpaRepository.
//
// <Produto, Long> significa:
// Produto -> entidade que será trabalhada.
// Long    -> tipo do ID da entidade Produto.
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}