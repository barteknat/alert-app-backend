package com.alert.app.backend.service;

import com.alert.app.backend.dto.PollutionDto;
import com.alert.app.backend.mapper.PollutionMapper;
import com.alert.app.backend.repository.PollutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PollutionService {

    private final PollutionMapper pollutionMapper;
    private final PollutionRepository pollutionRepository;

    public List<PollutionDto> getAll() {
        return pollutionMapper.mapToPollutionDtoList(pollutionRepository.findAll());
    }

    public PollutionDto getById(long id) {
        return pollutionMapper.mapToPollutionDto(pollutionRepository.getById(id));
    }

    @Transactional
    public void delete(long id) {
        pollutionRepository.deleteById(id);
    }
}
