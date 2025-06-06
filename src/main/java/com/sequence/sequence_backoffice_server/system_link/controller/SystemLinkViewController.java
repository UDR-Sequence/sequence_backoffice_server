package com.sequence.sequence_backoffice_server.system_link.controller;

import com.sequence.sequence_backoffice_server.global.dto.ApiResponseData;
import com.sequence.sequence_backoffice_server.system_link.dto.SystemLinkDto;
import com.sequence.sequence_backoffice_server.system_link.usecase.GetSystemLinksUseCase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/system-links")
@RequiredArgsConstructor
public class SystemLinkViewController {

    private final GetSystemLinksUseCase getSystemLinksUseCase;

    @GetMapping
    public ResponseEntity<ApiResponseData<List<SystemLinkDto>>> getSystemLinks() {
        List<SystemLinkDto> systemLinks = getSystemLinksUseCase.execute();
        return ResponseEntity.ok(ApiResponseData.success(systemLinks));
    }
}
