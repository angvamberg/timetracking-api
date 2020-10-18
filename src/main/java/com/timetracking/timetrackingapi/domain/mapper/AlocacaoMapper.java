package com.timetracking.timetrackingapi.domain.mapper;

import com.timetracking.timetrackingapi.domain.Alocacao;
import com.timetracking.timetrackingapi.domain.dto.AlocacaoDTO;
import org.mapstruct.*;

import java.util.List;

import static com.timetracking.timetrackingapi.service.util.DataUtil.transformarMinutosEmStringHora;

@Mapper(componentModel = "spring")
public abstract class AlocacaoMapper {

    @AfterMapping
    protected void chamadoQuandoSourceETargetTipo(Alocacao alocacao, @MappingTarget AlocacaoDTO alocacaoDTO) {
        alocacaoDTO.setTempoAlocado(transformarMinutosEmStringHora(alocacao.getTempoAlocadoEmMinutos()));
    }

    @Mapping(source = "projeto.id", target = "projeto")
    @Mapping(source = "usuario.id", target = "usuario")
    @Mapping(target = "diaAlocacao", dateFormat = "dd/MM/yyyy")
    public abstract AlocacaoDTO paraAlocacaoDTO(Alocacao alocacao);

    public abstract List<AlocacaoDTO> paraAlocacoesDTOList(List<Alocacao> alocacoes);

}
