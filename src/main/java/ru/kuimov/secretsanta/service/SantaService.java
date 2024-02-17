package ru.kuimov.secretsanta.service;

import ru.kuimov.secretsanta.dto.GroupDTO;
import ru.kuimov.secretsanta.entity.Group;

import java.util.List;

public interface SantaService {
    List<GroupDTO> getAllGroups();

    Long addGroup(Group group);

    Group getGroupById(Long id);

    void updateGroupById(Long id, GroupDTO groupDTO);
}
