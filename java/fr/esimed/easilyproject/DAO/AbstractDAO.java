package fr.esimed.easilyproject.DAO;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.EntityPathBase;
import fr.esimed.easilyproject.entity.IIdentifiable;
import fr.esimed.easilyproject.enums.EtatTask;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Définition d'un DAO.
 */
public abstract class AbstractDAO<T extends IIdentifiable> implements IDAO<T> {

    @PersistenceContext
    protected EntityManager em;

    /**
     * La classe de l'instance courante.
     */
    protected Class<T> instanceClass;

    protected AbstractDAO() {
        super();
    }

    protected AbstractDAO(Class<T> persistentClass) {
        this();
        instanceClass = persistentClass;
    }

    /**
     * Récupère le chemin {@code Query DSL} pour contruire une requête.
     *
     * @return Le chemin
     */
    protected abstract EntityPathBase<T> getPath();

    /**
     * Récupère une instance vierge utilisée par
     * {@link AbstractDAO#getNewInstance()}.
     *
     * @return Une instance vierge
     */
    protected abstract T create();

    @Override
    public T getNewInstance() {
        final T entity = create();
        return entity;
    }

    /**
     * Récupère la classe de l'instance.
     *
     * @return La classe de l'instance
     */
    protected Class<T> getInstanceClass() {
        return instanceClass;
    }

    /**
     * Récupère une requête {@code Query DSL} utilisant un ordre de tri définit
     * si possible.
     *
     * @return La requête
     */
    protected JPAQuery getQuery() {

        final JPAQuery query = new JPAQuery(em);

        return query;
    }

    @Override
    public T getById(int id) {
        return em.find(getInstanceClass(), id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>();
    }

    @Override
    public boolean persist(final T instance) {

        em.persist(instance);
        return true;
    }

    @Override
    public boolean update(final T instance) {

        em.merge(instance);
        return true;
    }

    @Override
    public T merge(final T instance) {
        return em.merge(instance);
    }

    @Override
    public boolean delete(final T instance) {

        if (instance == null) {
            return false;
        }

        em.remove(instance); // remove the attached entity

        return true;
    }

    public void detach(final T instance) {
        em.detach(instance);
    }

    public boolean contains(final T instance) {
        return em.contains(instance);
    }

    public int count() {

        return (int) getQuery().count();
    }

    @Override
    public List<T> findAllByProject(Integer id) {
        return null;
    }
    @Override
    public List<T> findAllByRequirement(Integer id) {
        return null;
    }
    @Override
    public List<T> findAllByState(EtatTask state) {
        return null;
    }
    @Override
    public List<T> findAllByJalon(Integer id) {
        return null;
    }
    @Override
    public List<Object[]> findAllLastTasksByJalon(final Integer jalonId)  {
        return null;
    }
}