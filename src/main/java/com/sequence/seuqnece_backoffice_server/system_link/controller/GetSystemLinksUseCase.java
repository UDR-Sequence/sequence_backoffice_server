package com.sequence.seuqnece_backoffice_server.system_link.controller;

import com.sequence.seuqnece_backoffice_server.system_link.dto.SystemLinkDto;
import java.util.List;

public interface GetSystemLinksUseCase {
    List<SystemLinkDto> execute();
}
