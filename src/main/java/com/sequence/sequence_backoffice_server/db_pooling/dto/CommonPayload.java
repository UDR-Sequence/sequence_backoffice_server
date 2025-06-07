package com.sequence.sequence_backoffice_server.db_pooling.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonPayload<T> {
    private T after;
    private String op;
}