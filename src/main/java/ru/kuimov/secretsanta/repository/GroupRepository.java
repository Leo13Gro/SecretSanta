package ru.kuimov.secretsanta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kuimov.secretsanta.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
