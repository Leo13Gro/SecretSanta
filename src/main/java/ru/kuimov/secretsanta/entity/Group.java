package ru.kuimov.secretsanta.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kuimov.secretsanta.dto.GroupDTO;
import ru.kuimov.secretsanta.dto.ParticipantDTO;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Participant> participants;

    public GroupDTO toDTO() {
        List<ParticipantDTO> participantDTOList = new ArrayList<>();
        participants.forEach(participant -> participantDTOList.add(participant.toDTO()));
        return new GroupDTO(id, name, description, participantDTOList);
    }

    public void updateFromDto(GroupDTO groupDTO){
        name = groupDTO.getName();
        description = groupDTO.getDescription();
    }


}
