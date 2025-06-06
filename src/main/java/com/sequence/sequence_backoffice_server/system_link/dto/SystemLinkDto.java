package com.sequence.sequence_backoffice_server.system_link.dto;

import com.sequence.sequence_backoffice_server.system_link.entity.SystemLink;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class SystemLinkDto {
    private String name;
    private String url;
    private String description;

    public static SystemLinkDto from(SystemLink systemLink) {
        return SystemLinkDto.builder()
                .name(systemLink.getName())
                .url(systemLink.getUrl())
                .description(systemLink.getDescription())
                .build();
    }
}
