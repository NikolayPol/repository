package com.example.repo.service;

import com.example.repo.model.Repository;
import com.example.repo.repository.RepositoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

/**
 * Класс RepositoryService - содержит логику обработки данных
 * и связывает контрллер с репозиторием.
 *
 * @author Nikolay Polegaev
 * @version 1.0 29.01.2022
 */
@Service
@AllArgsConstructor
public class RepositoryService {
    private final RepositoryRepository repositoryRepository;

    public List<Repository> findAll() {
        return repositoryRepository.findAll();
    }

    public Optional<Repository> findById(long id) {
        return repositoryRepository.findById(id);
    }

    public List<Repository> findAllByOwnerId(int id) {
        return repositoryRepository.findAllByOwnerId(id);
    }

    public List<Repository> findAllWithCreationTimeBefore(String time) throws ParseException {
            return repositoryRepository
                    .findAllWithCreationTimeBefore(new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .parse(time));
    }

    public Repository save(Repository repository) {
        return repositoryRepository.save(repository);
    }

    public void update(Repository repository) {
        Optional<Repository> personFromDB = repositoryRepository
                .findById(repository.getId());
        if (personFromDB.isPresent()) {
            repository.setCreatedAt(personFromDB.get().getCreatedAt());
            repositoryRepository.save(repository);
        } else {
            repositoryRepository.save(repository);
        }
    }

    public void delete(long id) {
        repositoryRepository.delete(repositoryRepository.findById(id).get());
    }
}
