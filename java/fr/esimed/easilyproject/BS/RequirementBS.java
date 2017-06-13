package fr.esimed.easilyproject.BS;

import fr.esimed.easilyproject.DAO.IDAO;
import fr.esimed.easilyproject.entity.Jalon;
import fr.esimed.easilyproject.entity.Requirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Repository
@Scope("singleton")
@Transactional
public class RequirementBS implements IBusinessService {

    private static final Logger LOG = LoggerFactory.getLogger(RequirementBS.class);

    @Inject
    private IDAO requirementDAO;

    public List<Jalon> findAll() {
        return requirementDAO.findAll();
    }

    public Requirement insert(final Requirement requirement) {
        return (Requirement) requirementDAO.merge(requirement);
    }

    public List<Requirement> findAllByProject(final Integer projectId) {
        return requirementDAO.findAllByProject(projectId);
    }

    public Requirement getById(final Integer id) {
        return (Requirement) requirementDAO.getById(id);
    }

}
