package ru.kuimov.secretsanta.service;

import ru.kuimov.secretsanta.dto.GroupDTO;
import ru.kuimov.secretsanta.dto.GroupRequestToCreateOrUpdate;
import ru.kuimov.secretsanta.dto.ParticipantDTO;
import ru.kuimov.secretsanta.entity.Group;
import ru.kuimov.secretsanta.entity.Participant;

import java.util.List;

public interface SantaService {
    List<Group> getAllGroups();

    Long addGroup(GroupRequestToCreateOrUpdate request);

    Group getGroupById(Long id);

    GroupDTO getGroupDTOById(Long id);

    void updateGroupById(Long id, GroupDTO groupDTO);

    void deleteGroupById(Long id);

    Long addParticipantToGroupById(Long id, Participant participant);

    void deleteParticipantFromGroupById( Long groupId, Long participantId);

    List<ParticipantDTO> tossInGroupById(Long id);

    Participant getRecipientById(Long groupId, Long participantId);
}
