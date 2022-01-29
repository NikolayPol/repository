package com.example.repo.repository;

import com.example.repo.model.Commit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Интерфейс CommitRepository - для взаимодействия с базой данных через Spring Data JPA.
 *
 * @author Nikolay Polegaev
 * @version 1.0 29.01.2022
 */
public interface CommitRepository extends JpaRepository<Commit, Long> {
    List<Commit> findAllByAuthor(String author);
}
