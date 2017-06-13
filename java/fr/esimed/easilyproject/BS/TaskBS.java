package fr.esimed.easilyproject.BS;

import fr.esimed.easilyproject.DAO.IDAO;
import fr.esimed.easilyproject.entity.Task;
import fr.esimed.easilyproject.enums.EtatTask;
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
public class TaskBS implements IBusinessService {

    private static final Logger LOG = LoggerFactory.getLogger(TaskBS.class);

    @Inject
    private IDAO taskDAO;
    @Inject
    private JalonBS jalonBS;

    public List<Task> findAll() {
        return taskDAO.findAll();
    }

    public List<Task> findAllByState(final EtatTask state) {
        return taskDAO.findAllByState(state);
    }

    public List<Task> findAllByJalon(final Integer id) {
        return taskDAO.findAllByJalon(id);
    }

    public Task insert(final Task task) {
        return (Task) taskDAO.merge(task);
    }

    public List<Task> findAllByRequirement(final Integer requirementId) {
        return taskDAO.findAllByRequirement(requirementId);
    }

    public Task getById(final Integer id) {
        return (Task) taskDAO.getById(id);
    }

    public boolean checkOneTaskIsStarted(final List<Task> tasks) {

        boolean started = false;
        boolean oneStarted = false;

        for (final Task task : tasks) {
            if (task.getEtat().equals(EtatTask.STARTED)) {
                oneStarted = true;
                started = true;
            }
            if (oneStarted && task.getEtat().equals(EtatTask.FINISHED)) {
                started = true;
            }
        }

        return started;
    }

    public boolean checkAllTasksFinished(final List<Task> tasks) {

        if (tasks.isEmpty()) {
            return false;
        }

        for (final Task task : tasks) {
            if (task.getEtat() != null) {
                if (!task.getEtat().equals(EtatTask.FINISHED)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkAllTasksUnstart(final List<Task> tasks) {

        for (final Task task : tasks) {
            if (!task.getEtat().equals(EtatTask.UNSTART)) {
                return false;
            }
        }

        return true;
    }

    public List<Object[]> findAllLastTasksByJalon(final Integer jalonId) {

        return taskDAO.findAllLastTasksByJalon(jalonId);
    }
}
