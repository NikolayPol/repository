package com.example.repo.service;

import com.example.repo.model.Commit;
import com.example.repo.repository.CommitRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Класс CommitService - содержит логику обработки данных
 * и связывает контрллер с репозиторием.
 *
 * @author Nikolay Polegaev
 * @version 1.0 29.01.2022
 */
@Service
@AllArgsConstructor
public class CommitService {
    private final CommitRepository commitRepository;

    public List<Commit> findAll() {
        return commitRepository.findAll();
    }

    public Optional<Commit> findById(long id) {
        return commitRepository.findById(id);
    }

    public List<Commit> findAllByAuthor(String author) {
        return commitRepository.findAllByAuthor(author);
    }

    public Commit save(Commit commit) {
        return commitRepository.save(commit);
    }

    public void update(Commit commit) {
        Optional<Commit> personFromDB = commitRepository
                .findById(commit.getId());
        if (personFromDB.isPresent()) {
            commit.setId(personFromDB.get().getId());
            commitRepository.save(commit);
        } else {
            commitRepository.save(commit);
        }
    }

    public void delete(long id) {

        commitRepository.delete(commitRepository.findById(id).get());
    }
}
