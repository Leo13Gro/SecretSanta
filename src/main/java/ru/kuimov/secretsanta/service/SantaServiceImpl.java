package ru.kuimov.secretsanta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuimov.secretsanta.dto.GroupDTO;
import ru.kuimov.secretsanta.dto.GroupRequestToCreate;
import ru.kuimov.secretsanta.dto.ParticipantDTO;
import ru.kuimov.secretsanta.entity.Group;
import ru.kuimov.secretsanta.entity.Participant;
import ru.kuimov.secretsanta.exception.custom.TossException;
import ru.kuimov.secretsanta.repository.GroupRepository;
import ru.kuimov.secretsanta.repository.ParticipantRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SantaServiceImpl implements SantaService{
    private final GroupRepository groupRepository;
    private final ParticipantRepository participantRepository;

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Long addGroup(GroupRequestToCreate request) {
        Group group = new Group();
        group.setName(request.getName());
        group.setDescription(request.getDescription());
        return groupRepository.save(group).getId();
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow();
    }

    @Override
    public GroupDTO getGroupDTOById(Long id){
        Group groupById = getGroupById(id);
        return groupById.toDTO();
    }

    @Override
    public void updateGroupById(Long id, GroupDTO groupDTO) {
        Group oldGroup = getGroupById(id);
        oldGroup.updateFromDto(groupDTO);
        groupRepository.save(oldGroup);
    }

    @Override
    public void deleteGroupById(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public Long addParticipantToGroupById(Long id, Participant participant) {
        Group groupToAdd = getGroupById(id);
        participant.setGroup(groupToAdd);
        Long participantId = participantRepository.saveAndFlush(participant).getId();
        List<Participant> participantListInGroup = groupToAdd.getParticipants();
        participantListInGroup.add(participant);
        groupToAdd.setParticipants(participantListInGroup);
        groupRepository.save(groupToAdd);
        return participantId;
    }

    @Override
    public void deleteParticipantFromGroupById(Long groupId, Long participantId) {
        Group groupToDelete = getGroupById(groupId);
        Participant participantToDelete = participantRepository.findById(participantId).orElseThrow();
        List<Participant> participantListInGroup = groupToDelete.getParticipants();
        participantListInGroup.remove(participantToDelete);
        groupToDelete.setParticipants(participantListInGroup);
        participantRepository.deleteById(participantId);
        groupRepository.save(groupToDelete);
    }

    @Override
    public List<ParticipantDTO> tossInGroupById(Long id) {
        Group groupToToss = getGroupById(id);
        List<Participant> participantListInGroup = groupToToss.getParticipants();

        if (participantListInGroup.size() < 3)
            throw new TossException();

        Collections.shuffle(participantListInGroup);

        for (int i = 0; i < participantListInGroup.size() - 1; i++) {
            participantListInGroup.get(i).setRecipient(participantListInGroup.get(i+1));
        }
        participantListInGroup.get(participantListInGroup.size()-1).setRecipient(participantListInGroup.get(0));
        groupToToss.setParticipants(participantListInGroup);
        groupRepository.save(groupToToss);

        List<ParticipantDTO> participantDTOList = new ArrayList<>();
        participantListInGroup.forEach(participant -> participantDTOList.add(participant.toDTO()));
        return participantDTOList;
    }

    @Override
    public Participant getRecipientById(Long groupId, Long participantId) {
//        Group group = getGroupById(groupId);
        Participant participant = participantRepository.findById(participantId).orElseThrow();
        return participant.getRecipient();
    }
}
