package com.checklist.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.checklist.api.entities.Atividade;
import com.checklist.api.entities.Tarefa;
import com.checklist.api.entities.Usuario;
import com.checklist.api.enums.TipoPeriodicidade;
import com.checklist.api.repositories.AtividadeRepository;
import com.checklist.api.repositories.TarefaRepository;
import com.checklist.api.repositories.UsuarioRepository;
import com.checklist.api.services.UsuarioService;




@SpringBootApplication
public class AgoraVaiApplication {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private AtividadeRepository atividadeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AgoraVaiApplication.class, args);
	}
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args-> {
		this.usuarioService.testarUsuarioService();
			};
		}
	
	
	
	//LOGO A BAIXO TEMOS UM EXEMPLO DA INSERÇAO E REALIZAÇAO DE UMA TAREFA MANUALMENTE.
	//@Bean
//		return args -> {
//			Usuario usuario = new Usuario();
//			usuario.setNome("Igor");
//			usuario.setCpf("00097120383");
//			usuario.setSenha("123456");
//			usuario.setEmail("igor@teste.com");
//			
//			this.usuarioRepository.save(usuario);
//			
//			List<Usuario> usuarios = usuarioRepository.findAll();
//			usuarios.forEach(System.out::println);
//			
//			Optional<Usuario> usuarioDb = usuarioRepository.findById((long) 1);
//			System.out.println("Usuario por ID: " + usuarioDb);
//			
////			usuarioDb.setNome("IgorCoutinho");
////			this.usuarioRepository.save(usuarioDb);
//
//			Usuario usuarioCpf = usuarioRepository.findByCpf("00097120383");
//			System.out.println("Usuario por CPF: " + usuarioCpf);
//			
//			//this.usuarioRepository.deleteById((long) 1);
//			usuarios = usuarioRepository.findAll();
//			System.out.println("Usuarios: " + usuarios.size());
//			
//			Tarefa tarefa = new Tarefa();
//			tarefa.setNomeTarefa("Limpar a casa");
//			tarefa.setTipo(TipoPeriodicidade.SEMANAL);
//			tarefa.setUsuario(usuarioCpf);
//			tarefa.setFinalizada(false);
//			
//			this.tarefaRepository.save(tarefa);
//			List<Tarefa> tarefas = tarefaRepository.findAll();
//			tarefas.forEach(System.out::println);
//
//			Atividade atividade = new Atividade();
//			atividade.setNome("Passar pano");
//			atividade.setRealizada(true);
//			atividade.setTarefa(tarefa);
//			this.atividadeRepository.save(atividade);
//			List<Atividade> atividades = atividadeRepository.findAll();
//			atividades.forEach(System.out::println);
//			
//		};
//	}

}
