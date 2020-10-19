package com.timetracking.timetrackingapi.service.impl;

import com.timetracking.timetrackingapi.domain.*;
import com.timetracking.timetrackingapi.domain.dto.CadastroRegistroDTO;
import com.timetracking.timetrackingapi.domain.dto.PeriodoCompletoDiaDTO;
import com.timetracking.timetrackingapi.domain.dto.RegistroDTO;
import com.timetracking.timetrackingapi.domain.dto.UsuarioDTO;
import com.timetracking.timetrackingapi.domain.mapper.RegistroMapper;
import com.timetracking.timetrackingapi.repository.RegistroRepository;
import com.timetracking.timetrackingapi.service.PeriodoDiaService;
import com.timetracking.timetrackingapi.service.TipoPeriodoService;
import com.timetracking.timetrackingapi.service.TipoRegistroService;
import com.timetracking.timetrackingapi.service.UsuarioService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.NoResultException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

import static com.timetracking.timetrackingapi.domain.TipoPeriodo.MATUTINO;
import static com.timetracking.timetrackingapi.domain.TipoPeriodo.VESPERTINO;
import static com.timetracking.timetrackingapi.domain.TipoRegistro.ENTRADA;
import static com.timetracking.timetrackingapi.domain.TipoRegistro.SAIDA;
import static com.timetracking.timetrackingapi.service.util.DataUtil.transformarStringDataHoraParaLocalDateTime;
import static com.timetracking.timetrackingapi.service.util.DataUtil.transformarStringDataParaLocalDate;
import static com.timetracking.timetrackingapi.service.util.MessageLoader.mensagemNaoEncontrado;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RegistroServiceImplTest {

    @InjectMocks
    private RegistroServiceImpl registroService;

    @Mock
    private RegistroRepository registroRepository;

    @Mock
    private UsuarioService usuarioService;
    @Mock
    private TipoPeriodoService tipoPeriodoService;
    @Mock
    private TipoRegistroService tipoRegistroService;
    @Mock
    private PeriodoDiaService periodoDiaService;

    @Mock
    private RegistroMapper registroMapper;

    private Usuario usuario;
    private TipoPeriodo tipoPeriodo;
    private TipoRegistro tipoRegistro;

    @Before
    public void setUp() {
        usuario = Usuario.builder().id(1L).nome("Angleby Vamberg").build();
        tipoPeriodo = TipoPeriodo.builder().id(MATUTINO).descricao("Turno da manhã").build();
        tipoRegistro = TipoRegistro.builder().id(ENTRADA).descricao("Entrada").build();
    }

    @Test
    public void deveRegistrarHorarioDeEntradaNoPeriodoMatutinoQuandoNaoHaRegistrosParaODia() {
        int tipoRegistroNaoExistente = 3;

        CadastroRegistroDTO cadastroDTO = CadastroRegistroDTO.builder()
                .data("19/10/2020")
                .horario("08:00")
                .tipoPeriodo(MATUTINO)
                .tipoRegistro(tipoRegistroNaoExistente)
                .usuario(1L)
                .build();

        Registro registro = Registro.builder()
                .dataHorario(transformarStringDataHoraParaLocalDateTime(cadastroDTO.getData() + " " + cadastroDTO.getHorario()))
                .usuario(usuario)
                .tipoRegistro(tipoRegistro)
                .tipoPeriodo(tipoPeriodo)
                .build();

        PeriodoCompletoDiaDTO periodoCompletoDiaDTO = PeriodoCompletoDiaDTO.builder()
                .dia(transformarStringDataParaLocalDate("19/10/2020"))
                .totalMinutosDoDia(0L)
                .entradaManha(transformarStringDataHoraParaLocalDateTime("19/10/2020 08:00"))
                .usuario(UsuarioDTO.builder().id(usuario.getId()).build())
                .build();

        PeriodoTotalDia periodoTotalDia = PeriodoTotalDia.builder()
                .usuario(usuario)
                .dia(transformarStringDataParaLocalDate("19/10/2020"))
                .totalMinutosDoDia(0L)
                .id(1L)
                .build();

        when(usuarioService.obterUsuarioPorId(cadastroDTO.getUsuario())).thenReturn(usuario);
        when(tipoPeriodoService.obterTipoPeriodoPorId(cadastroDTO.getTipoPeriodo())).thenReturn(tipoPeriodo);
        when(tipoRegistroService.obterTipoRegistroPorId(cadastroDTO.getTipoRegistro())).thenReturn(tipoRegistro);
        when(registroRepository.findAllByDataHorarioBetweenAndUsuarioId(registro.getDataHorario().with(LocalTime.MIN),
                        registro.getDataHorario().with(LocalTime.MAX), usuario.getId())).thenReturn(new ArrayList<>());
        when(periodoDiaService.construirPeriodoDiaDTOPorRegistros(Collections.singletonList(registro))).thenReturn(periodoCompletoDiaDTO);
        when(registroRepository.countAllByDataHorarioBetweenAndUsuarioIdAndTipoPeriodoId(registro.getDataHorario().with(LocalTime.MIN),
                registro.getDataHorario().with(LocalTime.MAX), periodoCompletoDiaDTO.getUsuario().getId(), VESPERTINO))
                .thenReturn(0);
        when(periodoDiaService.criarOuBuscarPeriodoDia(periodoCompletoDiaDTO)).thenReturn(periodoTotalDia);
        when(periodoDiaService.salvarPeriodoDia(periodoTotalDia)).thenReturn(periodoTotalDia);
        when(registroMapper.paraRegistroDTO(registro)).thenReturn(RegistroDTO.builder().data("19/10/2020").horario("08:00").build());

        RegistroDTO retorno = registroService.salvarRegistro(cadastroDTO);

        assertThat(retorno).isNotNull();
        assertThat(retorno.getData()).isEqualTo("19/10/2020");
        assertThat(retorno.getHorario()).isEqualTo("08:00");
    }

    @Test(expected = NoResultException.class)
    public void deveLancarExcecaoQuandoTipoRegistroNaoExistirNoBanco() {
        int tipoRegistroNaoExistente = 3;

        CadastroRegistroDTO cadastroDTO = CadastroRegistroDTO.builder()
                .data("19/10/2020")
                .horario("08:00")
                .tipoPeriodo(MATUTINO)
                .tipoRegistro(tipoRegistroNaoExistente)
                .usuario(1L)
                .build();

        when(usuarioService.obterUsuarioPorId(cadastroDTO.getUsuario())).thenReturn(usuario);
        when(tipoPeriodoService.obterTipoPeriodoPorId(cadastroDTO.getTipoPeriodo())).thenReturn(tipoPeriodo);
        when(tipoRegistroService.obterTipoRegistroPorId(cadastroDTO.getTipoRegistro()))
                .thenThrow(mensagemNaoEncontrado("Tipo de Registro"));

        registroService.salvarRegistro(cadastroDTO);
    }

    @Test(expected = NoResultException.class)
    public void deveLancarExcecaoQuandoTipoPeriodoNaoExistirNoBanco() {
        int tipoPeriodoNaoExistente = 3;

        CadastroRegistroDTO cadastroDTO = CadastroRegistroDTO.builder()
                .data("19/10/2020")
                .horario("08:00")
                .tipoPeriodo(tipoPeriodoNaoExistente)
                .tipoRegistro(ENTRADA)
                .usuario(1L)
                .build();

        when(usuarioService.obterUsuarioPorId(cadastroDTO.getUsuario())).thenReturn(usuario);
        when(tipoPeriodoService.obterTipoPeriodoPorId(cadastroDTO.getTipoPeriodo()))
                .thenThrow(mensagemNaoEncontrado("Tipo Período"));

        registroService.salvarRegistro(cadastroDTO);
    }

    @Test(expected = NoResultException.class)
    public void deveLancarExcecaoQuandoUsuarioNaoExistirNoBanco() {
        long idUsuarioNaoRegistradoNoBanco = 1L;

        CadastroRegistroDTO cadastroDTO = CadastroRegistroDTO.builder()
                .data("20/10/2020")
                .horario("09:00")
                .tipoPeriodo(VESPERTINO)
                .tipoRegistro(SAIDA)
                .usuario(idUsuarioNaoRegistradoNoBanco)
                .build();

        when(usuarioService.obterUsuarioPorId(cadastroDTO.getUsuario())).thenThrow(mensagemNaoEncontrado("Usuário"));

        registroService.salvarRegistro(cadastroDTO);
    }
}
