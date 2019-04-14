package com.checklist.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.checklist.api.entities.Usuario;
import com.checklist.api.repositories.UsuarioRepository;




@SpringBootApplication
public class AgoraVaiApplication {

	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(AgoraVaiApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Usuario usuario = new Usuario();
			usuario.setNome("Igor");
			usuario.setCpf("00097120383");
			usuario.setSenha("123456");
			usuario.setEmail("igor@teste.com");
			
			this.usuarioRepository.save(usuario);
			
			List<Usuario> usuarios = usuarioRepository.findAll();
			usuarios.forEach(System.out::println);
			
			Optional<Usuario> usuarioDb = usuarioRepository.findById((long) 1);
			System.out.println("Usuario por ID: " + usuarioDb);
			
//			usuarioDb.setNome("IgorCoutinho");
//			this.usuarioRepository.save(usuarioDb);

			Usuario usuarioCpf = usuarioRepository.findByCpf("00097120383");
			System.out.println("Usuario por CPF: " + usuarioCpf);
			
			//this.usuarioRepository.deleteById((long) 1);
			usuarios = usuarioRepository.findAll();
			System.out.println("Usuarios: " + usuarios.size());

		};
	}

}
