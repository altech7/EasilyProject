package fr.esimed.easilyproject.entity;

import fr.esimed.easilyproject.converters.EtatTaskConverter;
import fr.esimed.easilyproject.enums.EtatTask;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Task extends AbstractIdentifiable {

    @NotNull
    @Column(nullable = false)
    private String label;

    @NotNull
    @Column(nullable = false)
    private String description;

    @Convert(converter = EtatTaskConverter.class)
    private EtatTask etat;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column
    private Date dateStart;

    @NotNull
    @Column(nullable = false)
    private Integer charge;

    @NotNull
    @ManyToOne
    private Client manager;

    @OneToOne
    private Task previousTask;

    @ManyToOne
    private Jalon jalon;

    public Task() {
    }

    public Task(String label, String description, Date dateStart, Integer charge, Client manager, Task previousTask, Jalon jalon) {
        this.label = label;
        this.description = description;
        this.dateStart = dateStart;
        this.charge = charge;
        this.manager = manager;
        this.previousTask = previousTask;
        this.jalon = jalon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public Client getManager() {
        return manager;
    }

    public void setManager(Client manager) {
        this.manager = manager;
    }

    public Task getPreviousTask() {
        return previousTask;
    }

    public void setPreviousTask(Task previousTask) {
        this.previousTask = previousTask;
    }

    public Jalon getJalon() {
        return jalon;
    }

    public void setJalon(Jalon jalon) {
        this.jalon = jalon;
    }

    public EtatTask getEtat() {
        return etat;
    }

    public void setEtat(EtatTask etat) {
        this.etat = etat;
    }
}
