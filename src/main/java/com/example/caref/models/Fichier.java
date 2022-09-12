package com.example.caref.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table( name ="fichiers")
public class Fichier extends AbstractIforce5Audit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Size(max = 30)
    private String fichierTitle;

    @DateTimeFormat
    private Date fichierAddedDate;

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id")
    private User user;

    public Fichier() {}

    public Fichier(Long id,
                   String fichierTitle,
                   Date fichierAddedDate) {
        this.id = id;
        this.fichierTitle = fichierTitle;
        this.fichierAddedDate = fichierAddedDate;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getFichierTitle() { return fichierTitle; }

    public void setFichierTitle(String fichierTitle) {
        this.fichierTitle = fichierTitle;
    }

    public Date getFichierAddedDate(Date date) { return fichierAddedDate; }

    public void setFichierAddedDate(Date fichierAddedDate)  {
        this.fichierAddedDate = fichierAddedDate;
    }


}
