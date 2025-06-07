package com.sequence.sequence_backoffice_server.system_link.service;

import com.sequence.sequence_backoffice_server.system_link.dto.SystemLinkDto;
import com.sequence.sequence_backoffice_server.system_link.repository.SystemLinkJpaRepository;
import com.sequence.sequence_backoffice_server.system_link.usecase.GetSystemLinksUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSystemLinkService implements GetSystemLinksUseCase {

    private final SystemLinkJpaRepository systemLinkJpaRepository;

    @Override
    public List<SystemLinkDto> execute() {
        return systemLinkJpaRepository.findAllByIsDeletedFalse()
                .stream()
                .map(SystemLinkDto::from)
                .toList();
    }
}
