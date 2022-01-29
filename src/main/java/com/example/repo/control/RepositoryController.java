package com.example.repo.control;

import com.example.repo.model.Repository;
import com.example.repo.service.RepositoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * Класс RepositoryController - контроллер для работы с репозиториями.
 *
 * @author Nikolay Polegaev
 * @version 1.0 29.01.2022
 */
@RestController
@AllArgsConstructor
@RequestMapping("/repository")
public class RepositoryController {
    private final RepositoryService repositoryService;

    @GetMapping("/")
    public ResponseEntity<List<Repository>> findAll() {
        var repositories = repositoryService.findAll();
        return new ResponseEntity<List<Repository>>(repositories,
                repositories.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repository> findById(@PathVariable int id) {
        var passport = this.repositoryService.findById(id);
        return new ResponseEntity<Repository>(
                passport.orElse(new Repository()),
                passport.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<List<Repository>> findAllByOwnerId(@PathVariable Integer id) {
        var repositories = repositoryService.findAllByOwnerId(id);
        return new ResponseEntity<List<Repository>>(repositories,
                repositories.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping("/created/")
    public ResponseEntity<List<Repository>> findAllWithCreationTimeBefore(@RequestBody Map<String,
            String> body)
            throws ParseException {
        var time = body.get("time");
        var repositories = repositoryService.findAllWithCreationTimeBefore(time);
        return new ResponseEntity<List<Repository>>(repositories,
                repositories.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<Repository> create(@RequestBody Repository repository) {
        return new ResponseEntity<Repository>(
                repositoryService.save(repository),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Repository repository) {
        repositoryService.update(repository);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        repositoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
