package com.sequence.seuqnece_backoffice_server.system_link.dto;

import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public class SystemLinkDto {
    private String name;
    private String url;
    private String description;
}
