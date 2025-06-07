package com.sequence.sequence_backoffice_server.system_link.usecase;

import com.sequence.sequence_backoffice_server.system_link.dto.SystemLinkDto;
import java.util.List;

public interface GetSystemLinksUseCase {
    List<SystemLinkDto> execute();
}
