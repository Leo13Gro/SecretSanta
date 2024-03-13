package ru.kuimov.secretsanta.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GroupRequestToCreate {
    private Long id;
    @NotBlank
    private String name;
    private String description;
}
