package com.sequence.seuqnece_backoffice_server.system_link.controller;

import com.sequence.seuqnece_backoffice_server.global.dto.ApiResponseData;
import com.sequence.seuqnece_backoffice_server.system_link.dto.SystemLinkDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/system-links")
@RequiredArgsConstructor
public class SystemLinkViewController {

    private final GetSystemLinksUseCase getSystemLinksUseCase;

    public ResponseEntity<ApiResponseData<List<SystemLinkDto>>> getSystemLinks() {
        List<SystemLinkDto> systemLinks = getSystemLinksUseCase.execute();
        return ResponseEntity.ok(ApiResponseData.success(systemLinks));
    }
}
