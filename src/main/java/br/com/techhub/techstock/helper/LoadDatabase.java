package br.com.techhub.techstock.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import br.com.techhub.techstock.model.Setor;
import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.model.enums.UsuarioTipo;
import br.com.techhub.techstock.service.CategoriaService;
import br.com.techhub.techstock.service.EquipamentoService;
import br.com.techhub.techstock.service.MovimentacaoService;
import br.com.techhub.techstock.service.SetorService;
import br.com.techhub.techstock.service.TicketService;
import br.com.techhub.techstock.service.UsuarioService;

// INFO: Descomentar isso caso queira usar a carga de dados antiga
// @Configuration
public class LoadDatabase {

    @Autowired
    CategoriaService categoriaService;

    @Autowired
    EquipamentoService equipamentoService;

    @Autowired
    MovimentacaoService movimentacaoService;

    @Autowired
    SetorService setorService;

    @Autowired
    TicketService ticketService;

    @Autowired
    UsuarioService usuarioService;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {

            var setorSI = new Setor();
            setorSI.setNome("Sistemas de Informação");
            setorService.save(setorSI);
            var usuarioProfessor = new Usuario();
            usuarioProfessor.setUsuarioTipo(UsuarioTipo.USER);
            usuarioProfessor.setEmail("remo@gmail.com");
            usuarioProfessor.setCodigo("201501252426");
            usuarioProfessor.setNome("Remo Ferreira");
            usuarioProfessor.setSenha("123456");
            usuarioProfessor.setSetor(setorSI);
            usuarioService.save(usuarioProfessor);

            var setorTI = new Setor();
            setorTI.setNome("TI");
            setorService.save(setorTI);
            var usuarioAdmin = new Usuario();
            usuarioAdmin.setUsuarioTipo(UsuarioTipo.ADMIN);
            usuarioAdmin.setEmail("tarcisio@gmail.com");
            usuarioAdmin.setCodigo("201801252426");
            usuarioAdmin.setNome("Tarcisio");
            usuarioAdmin.setSenha("123456");
            usuarioAdmin.setSetor(setorTI);
            usuarioService.save(usuarioAdmin);
        };
    }
}
