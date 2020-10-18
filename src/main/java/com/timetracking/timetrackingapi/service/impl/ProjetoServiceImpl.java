package com.timetracking.timetrackingapi.service.impl;

import com.timetracking.timetrackingapi.domain.Projeto;
import com.timetracking.timetrackingapi.domain.dto.CadastroProjetoDTO;
import com.timetracking.timetrackingapi.domain.dto.ProjetoDTO;
import com.timetracking.timetrackingapi.domain.mapper.ProjetoMapper;
import com.timetracking.timetrackingapi.repository.ProjetoRepository;
import com.timetracking.timetrackingapi.service.ProjetoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjetoServiceImpl implements ProjetoService {

    private ProjetoRepository projetoRepository;

    private ProjetoMapper projetoMapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProjetoDTO> listarProjetos() {
        List<Projeto> projetos = projetoRepository.findAll();
        return projetoMapper.paraProjetosDTOList(projetos);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED)
    public ProjetoDTO salvarProjeto(CadastroProjetoDTO cadastroProjetoDTO) {
        Projeto projeto = Projeto.builder()
                .descricao(cadastroProjetoDTO.getDescricao())
                .build();

        projetoRepository.saveAndFlush(projeto);

        return projetoMapper.paraProjetoDTO(projeto);
    }
}
