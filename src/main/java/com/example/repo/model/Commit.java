package com.example.repo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс Commit - модель коммита для репозитория.
 *
 * @author Nikolay Polegaev
 * @version 1.0 29.01.2022
 */
@Entity
@Table(name = "commit", schema = "repository", catalog = "repository")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Commit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "repo_id", referencedColumnName = "id")
    private Repository repository;

    private String sha;

    private String author;

    private String committer;

    private String message;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Commit commit = (Commit) o;
        return id == commit.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Commit{"
                + "id=" + id
                + ", repository=" + repository
                + ", sha='" + sha + '\''
                + ", author='" + author + '\''
                + ", committer='" + committer + '\''
                + ", message='" + message + '\''
                + '}';
    }
}
