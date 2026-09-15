// Define o pacote onde o AvaliacaoRepository está localizado.
package br.senac.tads.dsw.exemplo3.repository;


// Importa o JpaRepository do Spring Data JPA.
// Ele já possui vários métodos prontos para trabalhar com o banco de dados.
import org.springframework.data.jpa.repository.JpaRepository;


// Importa a classe Avaliacao.
// É a entidade que esse Repository vai controlar.
import br.senac.tads.dsw.exemplo3.model.Avaliacao;


// Cria uma interface chamada AvaliacaoRepository.
//
// "extends JpaRepository" significa que AvaliacaoRepository
// vai herdar vários métodos prontos do JpaRepository.
//
// <Avaliacao, Long> significa:
// Avaliacao -> é a entidade que será trabalhada.
// Long      -> é o tipo do ID da Avaliacao.
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

}