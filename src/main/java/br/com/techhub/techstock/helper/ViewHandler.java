package br.com.techhub.techstock.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/techstock", produces = MediaType.TEXT_HTML_VALUE)
public class ViewHandler {

    private String staticDir = System.getProperty("user.dir") + File.separator
        + "src/main/resources/static/website" + File.separator;

    private String getHtml(String filename) throws FileNotFoundException {
        var result = "";

        File file = new File(staticDir + filename);
        try (
            Scanner myReader = new Scanner(file)
        ) {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                result += data;
            }

            return result;
        }
    }

    private ResponseEntity<String> handleViewRequest(String filename) {
        try {
            return ResponseEntity.ok(getHtml(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<String> index() {
        return handleViewRequest("index.html");
    }

    @GetMapping("/dashboard")
    public ResponseEntity<String> dashboard() {
        // TODO: Autenticação
        return handleViewRequest("adm_inicial.html");
    }

    @GetMapping("/usuario")
    public ResponseEntity<String> usuario() {
        // TODO: Autenticação
        return handleViewRequest("usuario.html");
    }

    @GetMapping("/usuario/cadastro")
    public ResponseEntity<String> usuario_cadastro() {
        // TODO: Autenticação
        return handleViewRequest("cadastro_user.html");
    }

    @GetMapping("/usuario/editar")
    public ResponseEntity<String> usuario_editar() {
        // TODO: Autenticação
        return handleViewRequest("cadastro_user.html");
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<String> usuario_alterar(Long id) {
        // TODO: Autenticação
        return handleViewRequest("alteracao_usuario.html");
    }

    @GetMapping("/equipamento")
    public ResponseEntity<String> equipamento() {
        // TODO: Autenticação
        return handleViewRequest("equipamentos.html");
    }

    @GetMapping("/equipamento/cadastro")
    public ResponseEntity<String> equipamento_cadastro() {
        // TODO: Autenticação
        return handleViewRequest("cadastro_equipamentos.html");
    }

    @GetMapping("/equipamento/{id}")
    public ResponseEntity<String> equipamento_alterar(Long id) {
        // TODO: Autenticação
        return handleViewRequest("alteracao_equipamentos.html");
    }

    //TODO: Implementar logica para a tela de ticket do usuario
    @GetMapping("/ticket")
    public ResponseEntity<String> tickets() {
        return handleViewRequest("ticket.html");
    }

    @GetMapping("/ticket/criar")
    public ResponseEntity<String> ticket_criar() {
        return handleViewRequest("criarticket.html");
    }

    @GetMapping("/movimentacao")
    public ResponseEntity<String> movimentacao() {
        // TODO: Autenticação
        return handleViewRequest("log.html");
    }

    // TODO: Remover
    @GetMapping("/home")
    public ResponseEntity<String> home() {
        return handleViewRequest("home.html");
    }

    // TODO: Remover
    @GetMapping("/error")
    public ResponseEntity<String> error() {
        return handleViewRequest("home.html");
    }

}
