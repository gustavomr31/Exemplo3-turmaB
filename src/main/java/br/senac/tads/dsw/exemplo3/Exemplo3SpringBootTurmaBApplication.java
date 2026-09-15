// Define o pacote onde a classe principal está localizada.
package br.senac.tads.dsw.exemplo3;


// Importa a classe SpringApplication.
// Ela é responsável por iniciar a aplicação Spring Boot.
import org.springframework.boot.SpringApplication;


// Importa a anotação @SpringBootApplication.
// Essa anotação configura a aplicação Spring Boot
// e permite que o Spring encontre os Controllers,
// Repositories, Models etc.
import org.springframework.boot.autoconfigure.SpringBootApplication;


// Indica que esta é a classe principal de uma aplicação Spring Boot.
//
// Essa anotação reúne algumas configurações importantes
// para inicializar o Spring.
@SpringBootApplication
public class Exemplo3SpringBootTurmaBApplication {


	// Método principal do Java.
	// É por aqui que a aplicação começa a ser executada.
	public static void main(String[] args) {


		// Inicia a aplicação Spring Boot.
		//
		// Exemplo3SpringBootTurmaBApplication.class
		// indica qual é a classe principal da aplicação.
		//
		// args são os argumentos que podem ser passados
		// quando o programa é iniciado.
		SpringApplication.run(Exemplo3SpringBootTurmaBApplication.class, args);
	}

}