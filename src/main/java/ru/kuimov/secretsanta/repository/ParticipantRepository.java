package ru.kuimov.secretsanta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuimov.secretsanta.entity.Participant;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {

    Participant findByRecipient_Id(Long id);
}
