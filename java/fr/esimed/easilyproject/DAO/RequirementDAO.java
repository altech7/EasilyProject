package fr.esimed.easilyproject.DAO;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.EntityPathBase;
import fr.esimed.easilyproject.entity.QRequirement;
import fr.esimed.easilyproject.entity.Requirement;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Scope("singleton")
@Transactional
public class RequirementDAO extends AbstractDAO<Requirement> {

    private static final QRequirement REQUIREMENT = QRequirement.requirement;

    @Override
    protected Requirement create() {
        return new Requirement();
    }

    @Override
    protected EntityPathBase<Requirement> getPath() {
        return REQUIREMENT;
    }

    @Override
    public Requirement getById(int id) {

        final JPAQuery query = getQuery();

        query.from(REQUIREMENT);

        query.where(REQUIREMENT.id.eq(id));

        return query.singleResult(REQUIREMENT);
    }

    public List<Requirement> findAll() {

        final JPAQuery query = getQuery();

        query.from(REQUIREMENT);

        return query.list(REQUIREMENT);
    }

    public List<Requirement> findAllByProject(final Integer projectId) {

        final JPAQuery query = getQuery();

        query.from(REQUIREMENT);

        query.where(REQUIREMENT.project.id.eq(projectId));

        return query.list(REQUIREMENT);
    }
}
