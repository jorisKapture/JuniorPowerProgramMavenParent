package be.kapture.quizinator.root.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class PersistentObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
