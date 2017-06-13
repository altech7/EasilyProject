package fr.esimed.easilyproject.DAO;


import fr.esimed.easilyproject.entity.IIdentifiable;
import fr.esimed.easilyproject.enums.EtatTask;

import java.io.Serializable;
import java.util.List;

public interface IDAO<T extends IIdentifiable> extends Serializable {

    /**
     * Récupère une nouvelle instance.
     *
     * @return Une nouvelle instance
     */
    T getNewInstance();

    /**
     * Récupère l'éléments en base par son id.
     *
     * @return L'élément
     */
    T getById(int id);

    /**
     * Récupère la liste des éléments en base.
     *
     * @return La liste
     */
    List<T> findAll();

    /**
     * Insertion d'une entité en base.
     *
     * @param entity Entité
     * @return {@code true} si tout s'est bien passé
     */
    boolean persist(final T entity);

    /**
     * Mise à jour d'une entité en base.
     *
     * @param entity Entité
     * @return {@code true} si tout s'est bien passé
     */
    boolean update(final T entity);

    /**
     * Creation ou mise a jour d'une entité en base.
     *
     * @param entity Entité
     * @return {@code true} si tout s'est bien passé
     */
    T merge(final T entity);

    /**
     * Suppression d'une entité en base.
     *
     * @param entity Entité
     * @return {@code true} si tout s'est bien passé
     */
    boolean delete(final T entity);

    List<T> findAllByProject(Integer id);

    List<T> findAllByRequirement(Integer id);

    List<T> findAllByState(EtatTask state);

    List<T> findAllByJalon(Integer id);

    List<Object[]> findAllLastTasksByJalon(final Integer jalonId);
}
