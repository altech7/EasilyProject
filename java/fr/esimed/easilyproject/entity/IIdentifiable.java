package fr.esimed.easilyproject.entity;


/**
 * Définition d'un objet ayant un ID en base.
 *
 */
public interface IIdentifiable {

    /**
     * Récupère l'ID.
     *
     * @return L'ID
     */
    Integer getId();

    /**
     * Change l'ID.
     *
     * @param id
     *            L'ID
     */
    void setId(final Integer id);

}
