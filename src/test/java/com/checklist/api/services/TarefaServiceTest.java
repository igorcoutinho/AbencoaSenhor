package com.checklist.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.checklist.api.entities.Tarefa;
import com.checklist.api.repositories.TarefaRepository;




@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TarefaServiceTest {
	
	
	@MockBean
	private TarefaRepository tarefaRepository;
	
	@Autowired
	private TarefaService tarefaService;
	
	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.tarefaRepository.save(Mockito.any(Tarefa.class))).willReturn(new Tarefa());
		BDDMockito.given(this.tarefaRepository.getOne(Mockito.anyLong())).willReturn(new Tarefa());
		
	}
	
	@Test
	public void testPersistirTarefa() {
		Tarefa tarefa = this.tarefaService.persistir(new Tarefa());

		assertNotNull(tarefa);
	}

	

	
}
