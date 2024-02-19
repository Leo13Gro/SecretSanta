package ru.kuimov.secretsanta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuimov.secretsanta.dto.GroupDTO;
import ru.kuimov.secretsanta.dto.ParticipantDTO;
import ru.kuimov.secretsanta.entity.Group;
import ru.kuimov.secretsanta.entity.Participant;
import ru.kuimov.secretsanta.repository.GroupRepository;
import ru.kuimov.secretsanta.repository.ParticipantRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SantaServiceImpl implements SantaService{
    private final GroupRepository groupRepository;
    private final ParticipantRepository participantRepository;

    @Override
    public List<GroupDTO> getAllGroups() {
        List<Group> groupList = groupRepository.findAll();
        List<GroupDTO> groupDTOList = new ArrayList<>();
        groupList.forEach(group -> groupDTOList.add(group.toDTO()));
        return groupDTOList;
    }

    @Override
    public Long addGroup(Group group) {
        return groupRepository.save(group).getId();
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.findById(id).orElseThrow();
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
        participantRepository.deleteById(participantId);
        List<Participant> participantListInGroup = groupToDelete.getParticipants();
        participantListInGroup.remove(participantToDelete);
        groupToDelete.setParticipants(participantListInGroup);
        groupRepository.save(groupToDelete);
    }

    @Override
    public List<ParticipantDTO> tossInGroupById(Long id) {
        Group groupToToss = getGroupById(id);
        List<Participant> participantListInGroup = groupToToss.getParticipants();

    }
}
