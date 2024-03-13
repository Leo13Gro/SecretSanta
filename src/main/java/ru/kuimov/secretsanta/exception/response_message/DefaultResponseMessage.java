package ru.kuimov.secretsanta.exception.response_message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DefaultResponseMessage {
    private String date;
    private String message;
    private String path;
}
