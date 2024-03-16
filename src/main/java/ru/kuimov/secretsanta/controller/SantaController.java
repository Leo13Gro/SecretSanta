package ru.kuimov.secretsanta.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kuimov.secretsanta.dto.GroupDTO;
import ru.kuimov.secretsanta.dto.GroupRequestToCreateOrUpdate;
import ru.kuimov.secretsanta.dto.ParticipantDTO;
import ru.kuimov.secretsanta.entity.Group;
import ru.kuimov.secretsanta.entity.Participant;
import ru.kuimov.secretsanta.service.SantaService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/group")
@Tag(name = "Secret Santa API", description = "API for Secret Santa Game")
public class SantaController {

    private final SantaService santaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new group")
    public Long addGroup(@Valid @RequestBody GroupRequestToCreateOrUpdate request){
        return santaService.addGroup(request);
    }

    @GetMapping
    @Operation(summary = "Get all groups")
    public List<Group> getAllGroups(){
        return santaService.getAllGroups();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get group by id")
    public GroupDTO getGroupById(@PathVariable Long id){
        return santaService.getGroupDTOById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Update group by id")
    public void updateGroupById(@PathVariable Long id, @RequestBody GroupDTO groupDTO){
        santaService.updateGroupById(id, groupDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGroupById(@PathVariable Long id){
        santaService.deleteGroupById(id);
    }

    @PostMapping("/{id}/participant")
    @ResponseStatus(HttpStatus.CREATED)
    public Long addParticipantToGroupById(@PathVariable Long id, @RequestBody Participant participant){
        return santaService.addParticipantToGroupById(id, participant);
    }

    @DeleteMapping("/{groupId}/participant/{participantId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParticipantFromGroupById(@PathVariable Long groupId, @PathVariable Long participantId){
        santaService.deleteParticipantFromGroupById(groupId, participantId);
    }

    @PostMapping("/{id}/toss")
    public List<ParticipantDTO> tossInGroupById(@PathVariable Long id){
        return santaService.tossInGroupById(id);
    }

    @GetMapping("/{groupId}/participant/{participantId}/recipient")
    public Participant getRecipientById(@PathVariable Long groupId, @PathVariable Long participantId){
        return santaService.getRecipientById(groupId, participantId);
    }
}
