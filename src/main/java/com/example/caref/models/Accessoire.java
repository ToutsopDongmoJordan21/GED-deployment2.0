package com.example.caref.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "accessoire")
public class Accessoire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max=255)
    private String accessoireName;



    @OneToMany(
            mappedBy = "accessoire",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<CarAccessoire> carAccessoire = new HashSet<>();

    public Accessoire(Long id, String accessoirename) {
        this.id = id;
        this.accessoireName = accessoireName;
    }

    public Accessoire() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getAccessoireName() { return accessoireName;}

    public void setAccessoireName(String accessoirename) {
        this.accessoireName = accessoirename;
    }


}
