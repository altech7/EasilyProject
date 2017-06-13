package fr.esimed.easilyproject.entity;

import fr.esimed.easilyproject.enums.EtatJalon;
import org.springframework.data.annotation.Transient;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Jalon extends AbstractIdentifiable {

    @NotNull
    @Column(nullable = false)
    private String label;

    @Temporal(TemporalType.DATE)
    @Transient
    private Date dateLivraisonPlanned;

    @Future(message = "Veuillez saisir une date supérieure à la date du jour")
    @Temporal(TemporalType.DATE)
    @Column
    private Date dateLivraisonReal;

    @Column
    private EtatJalon etat;

    @NotNull(message = "Veuillez saisir un responsable pour ce jalon")
    @ManyToOne
    private Client manager;

    @ManyToOne
    private Project project;

    @Transient
    private Integer avancement;

    public Jalon() {
    }

    public Jalon(String label, Date dateLivraisonPlanned, Date dateLivraisonReal, Client manager) {
        this.label = label;
        this.dateLivraisonPlanned = dateLivraisonPlanned;
        this.dateLivraisonReal = dateLivraisonReal;
        this.manager = manager;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getDateLivraisonPlanned() {
        return dateLivraisonPlanned;
    }

    public void setDateLivraisonPlanned(Date dateLivraisonPlanned) {
        this.dateLivraisonPlanned = dateLivraisonPlanned;
    }

    public Date getDateLivraisonReal() {
        return dateLivraisonReal;
    }

    public void setDateLivraisonReal(Date dateLivraisonReal) {
        this.dateLivraisonReal = dateLivraisonReal;
    }

    public Client getManager() {
        return manager;
    }

    public void setManager(Client manager) {
        this.manager = manager;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public EtatJalon getEtat() {
        return etat;
    }

    public void setEtat(EtatJalon etat) {
        this.etat = etat;
    }

    public Integer getAvancement() {
        return avancement;
    }

    public void setAvancement(Integer avancement) {
        this.avancement = avancement;
    }
}

