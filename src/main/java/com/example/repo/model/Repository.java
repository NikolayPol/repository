package com.example.repo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Класс Repository - модель репозитория.
 * Репозиторий содержит коммиты.
 *
 * @author Nikolay Polegaev
 * @version 1.0 29.01.2022
 */
@Entity
@Table(name = "repository", schema = "repository", catalog = "repository")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Repository {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String fullName;
    private Integer ownerId;
    private String ownerType;
    private Boolean privateRepo;
    private Boolean fork;
    private String homepage;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = Timestamp.valueOf(LocalDateTime.now());

    @JsonIgnore
    @OneToMany(mappedBy = "repository", orphanRemoval = true)
    private Set<Commit> commits = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Repository that = (Repository) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Repository{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", fullName='" + fullName + '\''
                + ", ownerId=" + ownerId
                + ", ownerType=" + ownerType
                + ", privateRepo=" + privateRepo
                + ", fork=" + fork
                + ", homepage='" + homepage + '\''
                + ", createdAt=" + createdAt
                + '}';
    }
}
