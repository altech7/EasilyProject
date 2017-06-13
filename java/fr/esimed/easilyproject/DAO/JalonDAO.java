package fr.esimed.easilyproject.DAO;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.EntityPathBase;
import fr.esimed.easilyproject.entity.Jalon;
import fr.esimed.easilyproject.entity.QJalon;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Scope("singleton")
@Transactional
public class JalonDAO extends AbstractDAO<Jalon> {

    private static final QJalon JALON = QJalon.jalon;

    @Override
    protected Jalon create() {
        return new Jalon();
    }

    @Override
    protected EntityPathBase<Jalon> getPath() {
        return JALON;
    }

    public List<Jalon> findAll() {

        final JPAQuery query = getQuery();

        query.from(JALON);

        return query.list(JALON);
    }

    public List<Jalon> findAllByProject(final Integer id) {

        final JPAQuery query = getQuery();

        query.from(JALON);

        query.where(JALON.project.id.eq(id));

        query.orderBy(JALON.id.desc());

        return query.list(JALON);
    }

    @Override
    public Jalon getById(int id) {

        final JPAQuery query = getQuery();

        query.from(JALON);

        query.where(JALON.id.eq(id));

        return query.singleResult(JALON);
    }
}
