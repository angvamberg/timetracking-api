package com.timetracking.timetrackingapi.web.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.timetracking.timetrackingapi.domain.dto.AlocacaoDTO;
import com.timetracking.timetrackingapi.domain.dto.CadastroAlocacaoDTO;
import com.timetracking.timetrackingapi.service.AlocacaoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AlocacaoResourceTest {

    private static final String URL_PADRAO = "/alocacoes";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlocacaoService alocacaoService;

    private AlocacaoDTO alocacaoDTO;

    @Before
    public void setup() {
        alocacaoDTO = new AlocacaoDTO(1L, "03:00", "19/10/2020", 1L, 1L);
    }

    @Test
    public void deveRetornar200AoListarAlocacoes() throws Exception {
        List<AlocacaoDTO> resultadoDaBusca = Collections.singletonList(alocacaoDTO);

        when(alocacaoService.listarAlocacoes()).thenReturn(resultadoDaBusca);

        mockMvc.perform(get((URL_PADRAO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRetornar201AoSalvarAlocacao() throws Exception {
        CadastroAlocacaoDTO cadastroAlocacaoDTO = CadastroAlocacaoDTO.builder()
                .diaAlocacao("19/10/2020")
                .projeto(1L)
                .tempoAlocado("03:00")
                .usuario(1L)
                .build();

        when(alocacaoService.salvarAlocacao(cadastroAlocacaoDTO)).thenReturn(alocacaoDTO);

        mockMvc.perform(post((URL_PADRAO))
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(cadastroAlocacaoDTO)))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}
