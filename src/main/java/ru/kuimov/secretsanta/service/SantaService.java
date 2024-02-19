package ru.kuimov.secretsanta.service;

import ru.kuimov.secretsanta.dto.GroupDTO;
import ru.kuimov.secretsanta.dto.ParticipantDTO;
import ru.kuimov.secretsanta.entity.Group;
import ru.kuimov.secretsanta.entity.Participant;

import java.util.List;

public interface SantaService {
    List<GroupDTO> getAllGroups();

    Long addGroup(Group group);

    Group getGroupById(Long id);

    void updateGroupById(Long id, GroupDTO groupDTO);

    void deleteGroupById(Long id);

    Long addParticipantToGroupById(Long id, Participant participant);

    void deleteParticipantFromGroupById( Long groupId, Long participantId);

    List<ParticipantDTO> tossInGroupById(Long id);
}
