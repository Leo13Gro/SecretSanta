package ru.kuimov.secretsanta.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kuimov.secretsanta.dto.GroupDTO;
import ru.kuimov.secretsanta.entity.Group;
import ru.kuimov.secretsanta.repository.GroupRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SantaServiceImpl implements SantaService{
    private final GroupRepository groupRepository;

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
}
