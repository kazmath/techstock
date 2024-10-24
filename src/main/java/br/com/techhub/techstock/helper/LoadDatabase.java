package br.com.techhub.techstock.helper;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.techhub.techstock.model.Categoria;
import br.com.techhub.techstock.model.Equipamento;
import br.com.techhub.techstock.model.Movimentacao;
import br.com.techhub.techstock.model.Setor;
import br.com.techhub.techstock.model.Ticket;
import br.com.techhub.techstock.model.Usuario;
import br.com.techhub.techstock.model.enums.EquipamentoStatus;
import br.com.techhub.techstock.model.enums.MovimentacaoTipo;
import br.com.techhub.techstock.model.enums.TicketStatus;
import br.com.techhub.techstock.model.enums.UsuarioTipo;
import br.com.techhub.techstock.service.CategoriaService;
import br.com.techhub.techstock.service.EquipamentoService;
import br.com.techhub.techstock.service.MovimentacaoService;
import br.com.techhub.techstock.service.SetorService;
import br.com.techhub.techstock.service.TicketService;
import br.com.techhub.techstock.service.UsuarioService;

@Configuration
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
            var categoria = new Categoria();
            categoria.setNome("Audio e Video");
            categoria.setDescricao("Tudo que envolve audio e vídeo");
            categoriaService.save(categoria);

            var equipamento = new Equipamento();
            equipamento.setAno_fabricacao(new Date());
            equipamento.setTombamento("2002OKLAKS");
            equipamento.setCategoria(categoria);
            equipamento.setDescricao("Caixa de som legal");
            equipamento.setDt_saida(null);
            equipamento.setFabricante("Wolksvagem");
            equipamento.setModelo("Gol");
            equipamento.setNome("Caixa de som");
            equipamento.setStatus(EquipamentoStatus.EM_MANUTENCAO);
            equipamento.setUsuarioComEquipamento(null);
            equipamentoService.save(equipamento);

            categoria = new Categoria();
            categoria.setNome("Slideshow");
            categoria.setDescricao("Slideshow legal");
            categoriaService.save(categoria);

            equipamento = new Equipamento();
            equipamento.setAno_fabricacao(new Date());
            equipamento.setTombamento("2002OKLAKD");
            equipamento.setCategoria(categoria);
            equipamento.setDescricao("Um slideshow que é show");
            equipamento.setDt_saida(null);
            equipamento.setFabricante("Yamaha");
            equipamento.setModelo("Fiat Uno");
            equipamento.setNome("SlideShow");
            equipamento.setStatus(EquipamentoStatus.DISPONIVEL);
            equipamento.setUsuarioComEquipamento(null);
            equipamentoService.save(equipamento);

            var setor = new Setor();
            setor.setNome("TI");
            setorService.save(setor);

            var usuario = new Usuario();
            usuario.setEmail("daniel@gmail.com");
            usuario.setCodigo("202001252356");
            usuario.setNome("Daniel");
            // String encryptedPassword = new BCryptPasswordEncoder().encode(
            //     "123456"
            // );
            usuario.setSenha("123456");
            usuario.setSetor(setor);
            usuario.setUsuarioTipo(UsuarioTipo.ADMIN);
            usuarioService.save(usuario);

            equipamento = new Equipamento();
            equipamento.setAno_fabricacao(new Date());
            equipamento.setTombamento("2002OKLAKG");
            equipamento.setCategoria(categoria);
            equipamento.setDescricao("Cadeira azul");
            equipamento.setDt_saida(new Date());
            equipamento.setFabricante("Ford");
            equipamento.setModelo("Ford ka");
            equipamento.setNome("Cadeira");
            equipamento.setStatus(EquipamentoStatus.INDISPONIVEL);
            equipamento.setUsuarioComEquipamento(usuario);
            equipamentoService.save(equipamento);


            var ticket = new Ticket();
            ticket.setDt_reserva(
                Date.from(Instant.now().plus(2, ChronoUnit.DAYS))
            );
            ticket.setDt_devolucao(
                Date.from(Instant.now().plus(2, ChronoUnit.DAYS))
            );
            ticket.setEquipamento(equipamento);
            ticket.setStatus(TicketStatus.AGUARDANDO);
            ticket.setUsuario(usuario);
            ticketService.save(ticket);

            var movimentacao = new Movimentacao();
            movimentacao.setData(new Date());
            movimentacao.setTicket(ticket);
            movimentacao.setTipo(MovimentacaoTipo.SAIDA);
            movimentacao.setUsuario(usuario);
            movimentacaoService.save(movimentacao);

            ticket = new Ticket();
            ticket.setDt_reserva(
                Date.from(Instant.now().plus(2, ChronoUnit.DAYS))
            );
            ticket.setDt_devolucao(
                Date.from(Instant.now().plus(10, ChronoUnit.DAYS))
            );
            ticket.setEquipamento(equipamento);
            ticket.setStatus(TicketStatus.RESERVADO);
            ticket.setObservacao(
                "Dolor tempor consectetur ex aliqua aliqua ea reprehenderit irure pariatur excepteur labore. Ad dolore in laboris in culpa nulla reprehenderit. Sunt pariatur do pariatur ex nostrud. Id occaecat qui commodo enim irure laboris proident reprehenderit magna."
            );
            ticket.setUsuario(usuario);
            ticketService.save(ticket);

            movimentacao = new Movimentacao();
            movimentacao.setData(new Date());
            movimentacao.setTicket(ticket);
            movimentacao.setTipo(MovimentacaoTipo.SAIDA);
            movimentacao.setUsuario(usuario);

            usuario = new Usuario();
            usuario.setUsuarioTipo(UsuarioTipo.ADMIN);
            usuario.setEmail("matheus@gmail.com");
            usuario.setCodigo("202001252426");
            usuario.setNome("Matheus");
            usuario.setSenha("123456");
            usuario.setSetor(setor);
            usuarioService.save(usuario);

            movimentacao.setUsuarioAdm(usuario);
            movimentacaoService.save(movimentacao);


        };
    }
}
