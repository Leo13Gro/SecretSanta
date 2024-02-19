package ru.kuimov.secretsanta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantDTO {
    private Long id;
    private String name;
    private String wish;
    private ParticipantDTO recipient;
}
