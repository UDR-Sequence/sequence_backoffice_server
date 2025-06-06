package com.sequence.sequence_backoffice_server.global.enums;

import com.sequence.sequence_backoffice_server.global.exception.EnumInvalidValueException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DBOpertation {
    CREATE("c"),
    READ("r"),
    UPDATE("u"),
    DELETE("d");

    private final String operation;

    public static DBOpertation from(String value) {
        for (DBOpertation operation : DBOpertation.values()) {
            if (operation.operation.equalsIgnoreCase(value)) {
                return operation;
            }
        }
        throw new EnumInvalidValueException();
    }
}
