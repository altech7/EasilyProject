package fr.esimed.easilyproject.entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Implémentation abstraite de {@link IIdentifiable}. Représente les champs
 * communs à toutes les entités du schéma.
 *
 */
@MappedSuperclass
public abstract class AbstractIdentifiable implements IIdentifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Override
    public Integer getId() {

        return id;
    }

    @Override
    public void setId(final Integer id) {

        this.id = id;
    }

}