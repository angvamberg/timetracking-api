package com.timetracking.timetrackingapi.service.impl;

import com.timetracking.timetrackingapi.domain.Projeto;
import com.timetracking.timetrackingapi.domain.dto.ProjetoDTO;
import com.timetracking.timetrackingapi.domain.mapper.ProjetoMapper;
import com.timetracking.timetrackingapi.repository.ProjetoRepository;
import com.timetracking.timetrackingapi.service.ProjetoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {

    private ProjetoRepository projetoRepository;

    private ProjetoMapper projetoMapper;

    @Override
    public List<ProjetoDTO> listarProjetos() {
        List<Projeto> projetos = projetoRepository.findAll();
        return projetoMapper.paraProjetosDTOList(projetos);
    }

    @Override
    public ProjetoDTO criarProjeto(ProjetoDTO projetoDTO) {
        Projeto projeto = Projeto.builder()
                .descricao(projetoDTO.getDescricao())
                .build();

        projetoRepository.saveAndFlush(projeto);

        return projetoMapper.paraProjetoDTO(projeto);
    }
}
