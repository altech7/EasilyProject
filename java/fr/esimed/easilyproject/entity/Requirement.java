package fr.esimed.easilyproject.entity;

import fr.esimed.easilyproject.converters.TypeRequirementConverter;
import fr.esimed.easilyproject.enums.TypeRequirement;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Requirement extends AbstractIdentifiable {

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private Boolean isFonctionnal;

    @Convert(converter = TypeRequirementConverter.class)
    private TypeRequirement typeRequirement;

    @ManyToOne
    private Project project;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<Task>();

    public Requirement() {
    }

    public Requirement(String description, Boolean isFonctionnal, TypeRequirement typeRequirement, Project project) {
        this.description = description;
        this.isFonctionnal = isFonctionnal;
        this.typeRequirement = typeRequirement;
        this.project = project;
    }

    public Requirement(String description, Boolean isFonctionnal, TypeRequirement typeRequirement, Project project, List<Task> tasks) {
        this.description = description;
        this.isFonctionnal = isFonctionnal;
        this.typeRequirement = typeRequirement;
        this.project = project;
        this.tasks = tasks;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsFonctionnal() {
        return isFonctionnal;
    }

    public void setIsFonctionnal(Boolean fonctionnal) {
        isFonctionnal = fonctionnal;
    }

    public TypeRequirement getTypeRequirement() {
        return typeRequirement;
    }

    public void setTypeRequirement(TypeRequirement typeRequirement) {
        this.typeRequirement = typeRequirement;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
