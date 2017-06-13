package fr.esimed.easilyproject.DAO;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.path.EntityPathBase;
import fr.esimed.easilyproject.entity.Project;
import fr.esimed.easilyproject.entity.QProject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Scope("singleton")
@Transactional
public class ProjectDAO extends AbstractDAO<Project> {

    private static final QProject PROJECT = QProject.project;

    @Override
    protected Project create() {
        return new Project();
    }

    @Override
    protected EntityPathBase<Project> getPath() {
        return PROJECT;
    }

    public List<Project> findAll() {

        final JPAQuery query = getQuery();

        query.from(PROJECT);

        return query.list(PROJECT);
    }

    @Override
    public Project getById(int id) {

        final JPAQuery query = getQuery();

        query.from(PROJECT);

        query.where(PROJECT.id.eq(id));

        return query.singleResult(PROJECT);
    }
}
