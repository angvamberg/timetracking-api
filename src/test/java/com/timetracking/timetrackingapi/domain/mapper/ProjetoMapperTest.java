package com.timetracking.timetrackingapi.domain.mapper;

import com.timetracking.timetrackingapi.domain.Projeto;
import com.timetracking.timetrackingapi.domain.dto.ProjetoDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ProjetoMapperTest {

    Projeto projeto;

    @Before
    public void setUp() {
        projeto = Projeto.builder()
                .id(1L)
                .descricao("The Last Kingdom")
                .build();
    }

    @Test
    public void deveMapearProjetoParaProjetoDTO() {
        ProjetoDTO retorno = ProjetoMapper.INSTANCE.paraProjetoDTO(projeto);

        assertThat(retorno).isNotNull();
        assertThat(retorno.getId()).isEqualTo(1L);
        assertThat(retorno.getDescricao()).isEqualTo("The Last Kingdom");
    }
}
