package fr.esimed.easilyproject.entity;

import org.springframework.data.annotation.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Project extends AbstractIdentifiable {

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private String trigramme;

    @NotNull
    @ManyToOne
    private Client manager;

    @Transient
    private Integer avancement;

    @Transient
    private Integer rateRequirement;

    @Transient
    private Date dateEndPlanned;

    public Project() {
    }

    public Project(String name, String trigramme, Client manager) {
        this.name = name;
        this.trigramme = trigramme;
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrigramme() {
        return trigramme;
    }

    public void setTrigramme(String trigramme) {
        this.trigramme = trigramme;
    }

    public Client getManager() {
        return manager;
    }

    public void setManager(Client manager) {
        this.manager = manager;
    }

    public Integer getAvancement() {
        return avancement;
    }

    public void setAvancement(Integer avancement) {
        this.avancement = avancement;
    }

    public Integer getRateRequirement() {
        return rateRequirement;
    }

    public void setRateRequirement(Integer rateRequirement) {
        this.rateRequirement = rateRequirement;
    }

    public Date getDateEndPlanned() {
        return dateEndPlanned;
    }

    public void setDateEndPlanned(Date dateEndPlanned) {
        this.dateEndPlanned = dateEndPlanned;
    }
}
