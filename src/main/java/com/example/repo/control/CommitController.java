package com.example.repo.control;

import com.example.repo.model.Commit;
import com.example.repo.service.CommitService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Класс CommitController - контроллер для работы с коммитами.
 *
 *
 * @author Nikolay Polegaev
 * @version 1.0 29.01.2022
 */
@RestController
@AllArgsConstructor
@RequestMapping("/commit")
public class CommitController {
    private final CommitService commitService;

    @GetMapping("/")
    public ResponseEntity<List<Commit>> findAll() {
        var commits = commitService.findAll();
        return new ResponseEntity<List<Commit>>(commits,
                commits.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commit> findById(@PathVariable int id) {
        var commit = this.commitService.findById(id);
        return new ResponseEntity<Commit>(
                commit.orElse(new Commit()),
                commit.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/author")
    public ResponseEntity<List<Commit>> findAllByAuthor(@RequestBody Map<String, String> body) {
        var author = body.get("author");
        var commits = commitService.findAllByAuthor(author);
        return new ResponseEntity<List<Commit>>(commits,
                commits.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Commit> create(@RequestBody Commit commit) {
        return new ResponseEntity<Commit>(
                commitService.save(commit),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Commit commit) {
        commitService.update(commit);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        commitService.delete(id);
        return ResponseEntity.ok().build();
    }
}
