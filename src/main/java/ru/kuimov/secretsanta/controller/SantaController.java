package ru.kuimov.secretsanta.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.*;
import ru.kuimov.secretsanta.dto.GroupDTO;
import ru.kuimov.secretsanta.dto.ParticipantDTO;
import ru.kuimov.secretsanta.entity.Group;
import ru.kuimov.secretsanta.entity.Participant;
import ru.kuimov.secretsanta.service.SantaService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/group")
public class SantaController {

    private final SantaService santaService;

    @PostMapping
    public Long addGroup(@RequestBody Group group){
        return santaService.addGroup(group);
    }

    @GetMapping
    public List<GroupDTO> getAllGroups(){
        return santaService.getAllGroups();
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable Long id){
        return santaService.getGroupById(id);
    }

    @PutMapping("/{id}")
    public void updateGroupById(@PathVariable Long id, @RequestBody GroupDTO groupDTO){
        santaService.updateGroupById(id, groupDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteGroupById(@PathVariable Long id){
        santaService.deleteGroupById(id);
    }

    @PostMapping("/{id}/participant")
    public Long addParticipantToGroupById(@PathVariable Long id, @RequestBody Participant participant){
        return santaService.addParticipantToGroupById(id, participant);
    }

    @DeleteMapping("/{groupId}/participant/{participantId}")
    public void deleteParticipantFromGroupById(@PathVariable Long groupId, @PathVariable Long participantId){
        santaService.deleteParticipantFromGroupById(groupId, participantId);
    }

    @PostMapping("/{id}/toss")
    public List<ParticipantDTO> tossInGroupById(@PathVariable Long id){

    }
}
