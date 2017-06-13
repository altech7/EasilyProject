package fr.esimed.easilyproject.DAO;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.EntityPathBase;
import fr.esimed.easilyproject.entity.QTask;
import fr.esimed.easilyproject.entity.Task;
import fr.esimed.easilyproject.enums.EtatTask;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Scope("singleton")
@Transactional
public class TaskDAO extends AbstractDAO<Task> {

    private static final QTask TASK = QTask.task;

    @Override
    protected Task create() {
        return new Task();
    }

    @Override
    protected EntityPathBase<Task> getPath() {
        return TASK;
    }

    public List<Task> findAllByState(final EtatTask state) {

        final JPAQuery query = getQuery();

        query.from(TASK);

        query.where(TASK.etat.eq(state));

        return query.list(TASK);
    }

    public List<Task> findAllByJalon(final Integer id) {

        final JPAQuery query = getQuery();

        query.from(TASK);

        query.where(TASK.jalon.id.eq(id));

        return query.list(TASK);
    }

    public List<Task> findAll() {

        final JPAQuery query = getQuery();

        query.from(TASK);

        return query.list(TASK);
    }

    @Override
    public Task getById(int id) {

        final JPAQuery query = getQuery();

        query.from(TASK);

        query.where(TASK.id.eq(id));

        return query.singleResult(TASK);
    }

    public List<Object[]> findAllLastTasksByJalon(final Integer jalonId) {

        return em.createQuery("select max(t.dateStart), t.id, t.charge from Task t where t.jalon.id = " + jalonId + "group by t.id").getResultList();
    }
}
