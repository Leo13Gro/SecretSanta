package ru.kuimov.secretsanta.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kuimov.secretsanta.dto.GroupDTO;

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

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Participant> participants;

    public GroupDTO toDTO() {
        return new GroupDTO(id, name, description);
    }

    public void updateFromDto(GroupDTO groupDTO){
        name = groupDTO.getName();
        description = groupDTO.getDescription();
    }
}
