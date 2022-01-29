package com.example.repo.repository;

import com.example.repo.model.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Интерфейс RepositoryRepository  - для взаимодействия с базой данных через Spring Data JPA.
 *
 * @author Nikolay Polegaev
 * @version 1.0 29.01.2022
 */
public interface RepositoryRepository extends JpaRepository<Repository, Long> {

    List<Repository> findAllByOwnerId(int id);

    @Query("SELECT r from Repository r WHERE r.createdAt <= :creationTime")
    List<Repository> findAllWithCreationTimeBefore(
            @Param("creationTime") Date creationTime);
}
