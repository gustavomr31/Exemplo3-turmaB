// Define o pacote onde essa classe está localizada.
package br.senac.tads.dsw.exemplo3.controller;


// Importa a classe URI.
// Ela será usada para criar o endereço do produto depois que ele for cadastrado.
import java.net.URI;

// Importa a interface List.
// Ela será usada para representar uma lista de produtos.
import java.util.List;

// Importa a classe Optional.
// É usada quando estamos procurando um produto que pode ou não existir.
import java.util.Optional;


// Importa ResponseEntity.
// Permite controlar a resposta HTTP que será enviada para quem chamou a API.
import org.springframework.http.ResponseEntity;

// Importa a anotação DeleteMapping.
// Ela será usada para criar uma rota HTTP DELETE.
import org.springframework.web.bind.annotation.DeleteMapping;

// Importa a anotação GetMapping.
// Ela será usada para criar rotas HTTP GET.
import org.springframework.web.bind.annotation.GetMapping;

// Importa PathVariable.
// Permite pegar uma informação que está dentro da URL.
import org.springframework.web.bind.annotation.PathVariable;

// Importa PostMapping.
// Será usada para criar uma rota HTTP POST.
import org.springframework.web.bind.annotation.PostMapping;

// Importa PutMapping.
// Será usada para criar uma rota HTTP PUT.
import org.springframework.web.bind.annotation.PutMapping;

// Importa RequestBody.
// Permite receber os dados enviados no corpo da requisição.
import org.springframework.web.bind.annotation.RequestBody;

// Importa RequestMapping.
// Será usada para definir o endereço base do Controller.
import org.springframework.web.bind.annotation.RequestMapping;

// Importa RestController.
// Informa ao Spring que essa classe será um Controller REST.
import org.springframework.web.bind.annotation.RestController;

// Importa ServletUriComponentsBuilder.
// É utilizada para construir a URL do produto que acabou de ser criado.
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


// Importa a classe Produto.
// Essa é a classe/modelo que representa um produto.
import br.senac.tads.dsw.exemplo3.model.Produto;

// Importa ProdutoRepository.
// Ele será responsável por acessar o banco de dados dos produtos.
import br.senac.tads.dsw.exemplo3.repository.ProdutoRepository;

// Importa @Valid.
// É usada para validar os dados recebidos antes de salvar ou atualizar.
import jakarta.validation.Valid;


// Diz para o Spring que essa classe é um Controller REST.
// Isso significa que ela vai receber requisições HTTP e retornar dados.
@RestController


// Define o endereço base desse Controller.
// Todas as URLs desse Controller começarão com /api/produtos.
@RequestMapping("/api/produtos")
public class ProdutoController {


    // Cria uma variável chamada repository.
    // Ela vai guardar o ProdutoRepository.
    // "final" significa que essa referência não será alterada depois.
    private final ProdutoRepository repository;


    // Construtor da classe ProdutoController.
    // O Spring vai utilizar esse construtor para entregar o ProdutoRepository.
    public ProdutoController(ProdutoRepository repository) {

        // Guarda o repository recebido pelo construtor
        // dentro do atributo repository da classe.
        this.repository = repository;
    }


    // Define que esse método será executado quando receber uma requisição POST.
    //
    // Como não existe outro caminho especificado aqui,
    // a URL será:
    //
    // POST /api/produtos
    @PostMapping

    
    // Método responsável por criar um novo produto.
    //
    // ResponseEntity<Produto> significa que esse método
    // vai retornar uma resposta HTTP contendo um Produto.
    public ResponseEntity<Produto> criarProduto(

        // @RequestBody pega o JSON enviado na requisição
        // e transforma esse JSON em um objeto Produto.
        //
        // @Valid pede para o Spring validar os dados
        // de acordo com as regras definidas na classe Produto.
        @RequestBody @Valid Produto produto) {


        // Envia o produto para o Repository.
        //
        // repository.save() salva o produto no banco de dados.
        //
        // O produto salvo é colocado na variável produtoSalvo.
        //
        // Normalmente é nesse momento que o ID do produto
        // pode ser gerado pelo banco.
        Produto produtoSalvo = repository.save(produto);


        // Cria uma variável URI.
        //
        // URI representa o endereço de um recurso na aplicação.
        URI location = ServletUriComponentsBuilder


            // Pega a URL utilizada na requisição atual.
            //
            // Nesse caso:
            // /api/produtos
            .fromCurrentRequest()


            // Adiciona "/{id}" ao final da URL.
            //
            // Ficaria inicialmente:
            // /api/produtos/{id}
            .path("/{id}")


            // Substitui {id} pelo ID do produto que acabou de ser salvo.
            //
            // Por exemplo:
            // /api/produtos/10
            .buildAndExpand(produtoSalvo.getId())


            // Converte o endereço construído para um objeto URI.
            .toUri();


        // Retorna uma resposta HTTP 201 Created.
        //
        // created(location):
        // informa onde o novo produto foi criado.
        //
        // body(produtoSalvo):
        // coloca o produto salvo dentro da resposta.
        return ResponseEntity.created(location).body(produtoSalvo);
    }


    // Define uma requisição GET.
    //
    // Como não existe "/{id}",
    // essa rota representa:
    //
    // GET /api/produtos
    @GetMapping


    // Método responsável por buscar todos os produtos.
    //
    // List<Produto> significa que ele vai retornar
    // uma lista contendo vários produtos.
    public List<Produto> listarTodos() {


        // Chama o método findAll() do Repository.
        //
        // findAll() busca todos os produtos existentes
        // no banco de dados.
        //
        // Depois retorna essa lista para quem fez a requisição.
        return repository.findAll();
    }


    // Define uma requisição GET com um ID na URL.
    //
    // Exemplo:
    //
    // GET /api/produtos/5
    @GetMapping("/{id}")


    // Método responsável por buscar apenas um produto.
    public ResponseEntity<Produto> buscarPorId(

        // @PathVariable pega o ID que está na URL.
        //
        // Se a URL for:
        // /api/produtos/5
        //
        // então:
        // id = 5
        @PathVariable Long id) {


        // Procura no banco de dados um produto
        // que tenha o ID informado.
        //
        // findById() retorna um Optional porque
        // o produto pode existir ou não.
        Optional<Produto> produtoBuscado = repository.findById(id);


        // Verifica se o Optional possui um produto.
        //
        // isPresent() significa:
        // "Existe um produto aqui?"
        if (produtoBuscado.isPresent()) {


            // Se o produto existir:
            //
            // produtoBuscado.get()
            // pega o produto que está dentro do Optional.
            //
            // ResponseEntity.ok()
            // retorna HTTP 200 OK.
            return ResponseEntity.ok(produtoBuscado.get());


        } else {


            // Se não encontrou o produto:
            //
            // retorna HTTP 404 Not Found.
            //
            // Significa:
            // "O produto não foi encontrado."
            return ResponseEntity.notFound().build();
        }
    }


    // Define uma requisição HTTP PUT.
    //
    // PUT normalmente é utilizado para atualizar
    // um recurso que já existe.
    //
    // Exemplo:
    //
    // PUT /api/produtos/5
    @PutMapping("/{id}")


    // Método responsável por atualizar um produto.
    public ResponseEntity<Produto> atualizarProduto(

        // Pega o ID do produto que será atualizado.
        //
        // Exemplo:
        // /api/produtos/5
        //
        // id = 5
        @PathVariable Long id,


        // Recebe os novos dados do produto no corpo da requisição.
        //
        // @RequestBody transforma o JSON em um objeto Produto.
        //
        // @Valid verifica se os dados são válidos.
        @RequestBody @Valid Produto produtoAtualizado) {


        // Primeiro procura o produto que será atualizado.
        //
        // Isso é necessário porque precisamos verificar
        // se o produto realmente existe.
        Optional<Produto> produtoBuscado = repository.findById(id);


        // Verifica se encontrou o produto.
        if (produtoBuscado.isPresent()) {


            // Pega o produto existente de dentro do Optional.
            //
            // Agora produtoExistente representa
            // o produto que está atualmente no banco.
            Produto produtoExistente = produtoBuscado.get();


            // Altera o nome do produto existente.
            //
            // getNome() pega o nome enviado na requisição.
            //
            // setNome() coloca esse nome no produto existente.
            produtoExistente.setNome(produtoAtualizado.getNome());


            // Altera o preço do produto existente.
            //
            // getPreco() pega o preço enviado na requisição.
            //
            // setPreco() coloca esse preço no produto existente.
            produtoExistente.setPreco(produtoAtualizado.getPreco());


            // Salva novamente o produto no banco.
            //
            // Como o produto já possui um ID,
            // o Spring/JPA entende que deve atualizar
            // o registro existente.
            Produto produtoSalvo = repository.save(produtoExistente);


            // Retorna HTTP 200 OK.
            //
            // Também envia o produto atualizado na resposta.
            return ResponseEntity.ok(produtoSalvo);


        } else {


            // Se o produto não existir,
            // retorna HTTP 404 Not Found.
            return ResponseEntity.notFound().build();
        }
    }


    // Define uma requisição HTTP DELETE.
    //
    // DELETE será utilizado para excluir um produto.
    //
    // Exemplo:
    //
    // DELETE /api/produtos/5
    @DeleteMapping("/{id}")


    // Método responsável por apagar um produto.
    //
    // ResponseEntity<Void> significa que
    // não precisamos retornar um Produto no corpo da resposta.
    public ResponseEntity<Void> apagarProduto(


        // Pega o ID do produto que será apagado.
        @PathVariable Long id) {


        // Procura o produto no banco utilizando o ID.
        //
        // Isso permite verificar se o produto realmente existe
        // antes de tentar apagar.
        Optional<Produto> produtoBuscado = repository.findById(id);


        // Verifica se encontrou o produto.
        if (produtoBuscado.isPresent()) {


            // Apaga o produto do banco de dados
            // utilizando o ID informado.
            repository.deleteById(id);


            // Retorna HTTP 204 No Content.
            //
            // Significa que a operação foi realizada com sucesso,
            // mas não existe conteúdo para retornar.
            return ResponseEntity.noContent().build();


        } else {


            // Se o produto não existir,
            // retorna HTTP 404 Not Found.
            return ResponseEntity.notFound().build();
        }
    }
}