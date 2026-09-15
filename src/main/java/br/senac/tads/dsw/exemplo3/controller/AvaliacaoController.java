// Define o pacote onde essa classe está localizada.
package br.senac.tads.dsw.exemplo3.controller;


// Importa a classe URI.
// Será usada para criar o endereço da avaliação criada.
import java.net.URI;

// Importa List.
// Será utilizada para trabalhar com uma lista de avaliações.
import java.util.List;

// Importa Optional.
// É utilizado quando estamos procurando uma avaliação
// que pode ou não existir.
import java.util.Optional;


// Importa ResponseEntity.
// Permite controlar a resposta HTTP enviada pela API.
import org.springframework.http.ResponseEntity;

// Importa a anotação DeleteMapping.
// Será usada para criar uma rota HTTP DELETE.
import org.springframework.web.bind.annotation.DeleteMapping;

// Importa GetMapping.
// Será usada para criar uma rota HTTP GET.
import org.springframework.web.bind.annotation.GetMapping;

// Importa PathVariable.
// Permite pegar valores que estão na URL.
import org.springframework.web.bind.annotation.PathVariable;

// Importa PostMapping.
// Será usada para criar uma rota HTTP POST.
import org.springframework.web.bind.annotation.PostMapping;

// Importa PutMapping.
// Será usada para criar uma rota HTTP PUT.
import org.springframework.web.bind.annotation.PutMapping;

// Importa RequestBody.
// Permite receber dados enviados no corpo da requisição.
import org.springframework.web.bind.annotation.RequestBody;

// Importa RequestMapping.
// Define o endereço base do Controller.
import org.springframework.web.bind.annotation.RequestMapping;

// Importa RestController.
// Informa ao Spring que essa classe é um Controller REST.
import org.springframework.web.bind.annotation.RestController;

// Importa ServletUriComponentsBuilder.
// Será utilizado para construir a URL da avaliação criada.
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


// Importa a classe Avaliacao.
// É o Model que representa uma avaliação.
import br.senac.tads.dsw.exemplo3.model.Avaliacao;

// Importa AvaliacaoRepository.
// É responsável pela comunicação com o banco de dados.
import br.senac.tads.dsw.exemplo3.repository.AvaliacaoRepository;

// Importa @Valid.
// Será utilizada para validar os dados recebidos.
import jakarta.validation.Valid;


// Diz ao Spring que essa classe é um Controller REST.
//
// Isso significa que ela será responsável por receber
// requisições HTTP e devolver respostas da API.
@RestController


// Define o endereço base desse Controller.
//
// Todas as URLs dessa classe começarão com:
//
// /api/avaliacoes
@RequestMapping("/api/avaliacoes")
public class AvaliacaoController {


    // Cria um atributo chamado repository.
    //
    // Ele armazenará o AvaliacaoRepository,
    // que será utilizado para acessar o banco de dados.
    //
    // "final" significa que essa referência não será alterada.
    private final AvaliacaoRepository repository;


    // Construtor da classe.
    //
    // O Spring utiliza esse construtor para fornecer
    // o AvaliacaoRepository para o Controller.
    public AvaliacaoController(AvaliacaoRepository repository) {


        // Guarda o Repository recebido dentro
        // do atributo repository da classe.
        //
        // "this.repository" = atributo da classe.
        //
        // "repository" = parâmetro recebido pelo construtor.
        this.repository = repository;
    }


    // @PostMapping indica que esse método responde
    // a requisições HTTP POST.
    //
    // A URL será:
    //
    // POST /api/avaliacoes
    @PostMapping


    // Método responsável por criar uma nova avaliação.
    //
    // ResponseEntity<Avaliacao> significa que o método
    // retornará uma resposta HTTP contendo uma Avaliacao.
    public ResponseEntity<Avaliacao> criarAvaliacao(


        // @RequestBody pega os dados enviados no corpo
        // da requisição e transforma em um objeto Avaliacao.
        //
        // @Valid verifica as regras de validação
        // definidas na classe Avaliacao.
        @RequestBody @Valid Avaliacao avaliacao) {


        // Salva a avaliação no banco de dados.
        //
        // repository.save() é o responsável por realizar
        // a operação de salvamento.
        //
        // O resultado é armazenado em avaliacaoSalva.
        Avaliacao avaliacaoSalva = repository.save(avaliacao);


        // Cria uma variável do tipo URI.
        //
        // URI representa o endereço de um recurso.
        URI location = ServletUriComponentsBuilder


            // Pega o endereço da requisição atual.
            //
            // Nesse caso:
            //
            // /api/avaliacoes
            .fromCurrentRequest()


            // Adiciona "/{id}" ao final da URL.
            //
            // Ficaria:
            //
            // /api/avaliacoes/{id}
            .path("/{id}")


            // Substitui {id} pelo ID da avaliação salva.
            //
            // Se o ID for 5:
            //
            // /api/avaliacoes/5
            .buildAndExpand(avaliacaoSalva.getId())


            // Converte o endereço construído para um objeto URI.
            .toUri();


        // Retorna HTTP 201 Created.
        //
        // created(location):
        // informa onde o recurso foi criado.
        //
        // body(avaliacaoSalva):
        // envia a avaliação criada no corpo da resposta.
        return ResponseEntity.created(location).body(avaliacaoSalva);
    }


    // @GetMapping indica que esse método responde
    // a requisições HTTP GET.
    //
    // Como não existe "/{id}",
    // a URL será:
    //
    // GET /api/avaliacoes
    @GetMapping


    // Método responsável por buscar todas as avaliações.
    //
    // List<Avaliacao> significa que será retornada
    // uma lista de avaliações.
    public List<Avaliacao> listarTodos() {


        // findAll() busca todas as avaliações
        // existentes no banco de dados.
        //
        // Depois retorna essa lista.
        return repository.findAll();
    }


    // @GetMapping("/{id}") indica que essa rota
    // recebe um ID na URL.
    //
    // Exemplo:
    //
    // GET /api/avaliacoes/5
    @GetMapping("/{id}")


    // Método responsável por buscar uma avaliação específica.
    public ResponseEntity<Avaliacao> buscarPorId(


        // @PathVariable pega o ID que está na URL.
        //
        // Se a URL for:
        //
        // /api/avaliacoes/5
        //
        // então:
        //
        // id = 5
        @PathVariable Long id) {


        // Procura uma avaliação no banco pelo ID.
        //
        // findById() retorna um Optional porque
        // a avaliação pode existir ou não.
        Optional<Avaliacao> avaliacaoBuscada = repository.findById(id);


        // Verifica se encontrou uma avaliação.
        //
        // isPresent() significa:
        //
        // "Existe algum objeto dentro desse Optional?"
        if (avaliacaoBuscada.isPresent()) {


            // Se encontrou:
            //
            // get() pega a avaliação que está dentro do Optional.
            //
            // ResponseEntity.ok() retorna HTTP 200 OK.
            return ResponseEntity.ok(avaliacaoBuscada.get());


        } else {


            // Se não encontrou:
            //
            // retorna HTTP 404 Not Found.
            return ResponseEntity.notFound().build();
        }
    }


    // @PutMapping indica que esse método responde
    // a requisições HTTP PUT.
    //
    // PUT normalmente é utilizado para atualizar
    // um registro existente.
    //
    // Exemplo:
    //
    // PUT /api/avaliacoes/5
    @PutMapping("/{id}")


    // Método responsável por atualizar uma avaliação.
    public ResponseEntity<Avaliacao> atualizarAvaliacao(


        // Pega o ID da avaliação que será atualizada.
        @PathVariable Long id,


        // Recebe os novos dados da avaliação.
        //
        // @RequestBody transforma o JSON recebido
        // em um objeto Avaliacao.
        //
        // @Valid verifica se os dados são válidos.
        @RequestBody @Valid Avaliacao avaliacaoAtualizada) {


        // Procura a avaliação no banco utilizando o ID.
        //
        // Precisamos fazer isso para verificar
        // se a avaliação realmente existe.
        Optional<Avaliacao> avaliacaoBuscada = repository.findById(id);


        // Verifica se encontrou a avaliação.
        if (avaliacaoBuscada.isPresent()) {


            // Pega a avaliação existente de dentro do Optional.
            Avaliacao avaliacaoExistente = avaliacaoBuscada.get();


            // Pega o autor enviado na requisição
            // e coloca no objeto que já existe no banco.
            avaliacaoExistente.setAutor(avaliacaoAtualizada.getAutor());


            // Pega o comentário enviado na requisição
            // e coloca no objeto existente.
            avaliacaoExistente.setComentario(avaliacaoAtualizada.getComentario());


            // Salva a avaliação atualizada no banco.
            //
            // Como ela já possui um ID,
            // o JPA entende que deve atualizar
            // o registro existente.
            Avaliacao avaliacaoSalva = repository.save(avaliacaoExistente);


            // Retorna HTTP 200 OK
            // junto com a avaliação atualizada.
            return ResponseEntity.ok(avaliacaoSalva);


        } else {


            // Se a avaliação não existir,
            // retorna HTTP 404 Not Found.
            return ResponseEntity.notFound().build();
        }
    }


    // @DeleteMapping indica que esse método responde
    // a requisições HTTP DELETE.
    //
    // Exemplo:
    //
    // DELETE /api/avaliacoes/5
    @DeleteMapping("/{id}")


    // Método responsável por apagar uma avaliação.
    //
    // ResponseEntity<Void> significa que não será
    // retornado um objeto no corpo da resposta.
    public ResponseEntity<Void> apagarAvaliacao(


        // Pega o ID da avaliação que será apagada.
        @PathVariable Long id) {


        // Procura a avaliação no banco pelo ID.
        //
        // Isso serve para verificar se ela existe
        // antes de tentar apagá-la.
        Optional<Avaliacao> avaliacaoBuscada = repository.findById(id);


        // Verifica se encontrou a avaliação.
        if (avaliacaoBuscada.isPresent()) {


            // Apaga a avaliação utilizando o ID informado.
            repository.deleteById(id);


            // Retorna HTTP 204 No Content.
            //
            // Significa que a exclusão ocorreu com sucesso,
            // mas não existe conteúdo para retornar.
            return ResponseEntity.noContent().build();


        } else {


            // Se a avaliação não existir,
            // retorna HTTP 404 Not Found.
            return ResponseEntity.notFound().build();
        }
    }
}