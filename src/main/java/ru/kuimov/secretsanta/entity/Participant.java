package ru.kuimov.secretsanta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import ru.kuimov.secretsanta.dto.ParticipantDTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "participants")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String wish;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Participant recipient;

    public ParticipantDTO toDTO(){
        return new ParticipantDTO(id, name, wish, recipient);
    }
}
