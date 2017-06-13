package fr.esimed.easilyproject.BS;

import fr.esimed.easilyproject.DAO.IDAO;
import fr.esimed.easilyproject.entity.Jalon;
import fr.esimed.easilyproject.entity.Task;
import fr.esimed.easilyproject.enums.EtatJalon;
import fr.esimed.easilyproject.enums.EtatTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@Scope("singleton")
@Transactional
public class JalonBS implements IBusinessService {

    private static final Logger LOG = LoggerFactory.getLogger(JalonBS.class);

    @Inject
    private IDAO jalonDAO;
    @Inject
    private TaskBS taskBS;

    public List<Jalon> findAll() {
        return jalonDAO.findAll();
    }

    public List<Jalon> findAllByProject(Integer id) {
        return jalonDAO.findAllByProject(id);
    }

    public void insert(final Jalon jalon) {
        jalonDAO.merge(jalon);
    }

    public Jalon getById(final Integer id) {
        return (Jalon) jalonDAO.getById(id);
    }

    public int getAvancement(final List<Task> tasks) {

        final List<Task> tasksFinished = new ArrayList<Task>();

        for (final Task task : tasks) {
            if (task.getEtat().equals(EtatTask.FINISHED)) {
                tasksFinished.add(task);
            }
        }

        if (tasks == null || tasks.isEmpty()) {

            return 0;
        }

        return (100 * tasksFinished.size() / tasks.size());
    }

    public List<Jalon> findJalonByStateAndProject(final List<Jalon> jalons, final EtatJalon state) {

        final List<Jalon> jalonsState = new ArrayList<Jalon>();

        for (final Jalon jal : jalons) {

            if (jal.getEtat().equals(state)) {

                jalonsState.add(jal);
            }
        }

        return jalonsState;
    }

    public List<Jalon> findJalonWithState(final Integer projectId) {

        List<Jalon> jalons = new ArrayList<Jalon>();

        for (final Jalon jal : findAllByProject(projectId)) {

            final List<Task> tasks = taskBS.findAllByJalon(jal.getId());

            setState(jal, tasks);

            jal.setAvancement(getAvancement(tasks));

            setDateLivraisonPlanned(jal);

            jalons.add(jal);
        }

        return jalons;
    }

    private void setState(final Jalon jal, final List<Task> tasks) {

        if (!tasks.isEmpty()) {

            if (taskBS.checkAllTasksUnstart(tasks)) {

                jal.setEtat(EtatJalon.NOT_FINISH);

            } else if (taskBS.checkAllTasksFinished(tasks)) {

                jal.setEtat(EtatJalon.FINISHED);

            } else {

                jal.setEtat(EtatJalon.STARTED);
            }

        } else {

            jal.setEtat(EtatJalon.NOT_FINISH);
        }
    }

    private void setDateLivraisonPlanned(final Jalon jalon) {

        final Calendar c = Calendar.getInstance();
        Date dateLivraisonPlanned = null;

        final List<Object[]> lastTasks = taskBS.findAllLastTasksByJalon(jalon.getId());

        if (!lastTasks.isEmpty()) {
            for (int i = 0; i < lastTasks.size(); i++) {

                Object[] obj = lastTasks.get(i);

                final Date date = (Date) obj[0];
                final Integer charge = (Integer) obj[2];

                c.setTime(date);
                c.add(Calendar.DATE, charge);

                final Date dateLivraison = c.getTime();

                if (i == 0) {

                    dateLivraisonPlanned = dateLivraison;

                } else if (dateLivraison.after(dateLivraisonPlanned)) {

                    dateLivraisonPlanned = dateLivraison;
                }
            }

            jalon.setDateLivraisonPlanned(dateLivraisonPlanned);
        }
    }
}
