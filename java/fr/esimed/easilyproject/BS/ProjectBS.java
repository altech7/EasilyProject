package fr.esimed.easilyproject.BS;

import fr.esimed.easilyproject.DAO.IDAO;
import fr.esimed.easilyproject.entity.Jalon;
import fr.esimed.easilyproject.entity.Project;
import fr.esimed.easilyproject.entity.Requirement;
import fr.esimed.easilyproject.enums.EtatJalon;
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
public class ProjectBS implements IBusinessService {

    private static final Logger LOG = LoggerFactory.getLogger(ProjectBS.class);

    @Inject
    private IDAO projectDAO;
    @Inject
    private JalonBS jalonBS;
    @Inject
    private RequirementBS requirementBS;
    @Inject
    private TaskBS taskBS;

    public List<Project> findAll() {
        return projectDAO.findAll();
    }

    public void insert(final Project project) {
        projectDAO.merge(project);
    }

    public Project getById(final Integer id) {
        return (Project) projectDAO.getById(id);
    }

    public Project getByIdWithStats(final Integer id) {

        final Project project = getById(id);
        final List<Requirement> requirements = requirementBS.findAllByProject(project.getId());

        project.setAvancement(getAvancement(project));

        project.setRateRequirement(getRateRequiremment(requirements));

        project.setDateEndPlanned(getDateEndPlanned(project));

        return project;
    }

    public List<Project> findAllWithAvancement() {

        final List<Project> projects = findAll();
        final List<Project> projectsDTO = new ArrayList<Project>();

        for (final Project project : projects) {

            final List<Requirement> requirements = requirementBS.findAllByProject(project.getId());

            project.setAvancement(getAvancement(project));

            project.setRateRequirement(getRateRequiremment(requirements));

            project.setDateEndPlanned(getDateEndPlanned(project));

            projectsDTO.add(project);
        }

        return projectsDTO;
    }

    private Integer getAvancement(final Project project) {

        final List<Jalon> jalonsFinished = new ArrayList<Jalon>();
        final List<Jalon> jalons = jalonBS.findJalonWithState(project.getId());
        Jalon jalonCurrent = null;
        Integer avancementCurrentJalon = 0;

        if (jalons.isEmpty()) {

            return 0;
        }

        for (final Jalon jal : jalons) {
            if (jal.getEtat().equals(EtatJalon.FINISHED)) {
                jalonsFinished.add(jal);
            }
            if (jal.getEtat().equals(EtatJalon.STARTED)) {
                jalonCurrent = jal;
            }
        }

        if (jalonCurrent == null) {
            avancementCurrentJalon = 0;
        }

        Integer avancementJalonInProject = 100 / jalons.size();
        Integer avancementCurrentJalonInProject = avancementCurrentJalon * avancementJalonInProject;

        return ((jalonsFinished.size() * 100) / jalons.size() + avancementCurrentJalonInProject);
    }

    private Integer getRateRequiremment(final List<Requirement> requirements) {

        final List<Requirement> requirementsFinished = new ArrayList<Requirement>();
        int nbRequirements = requirements.size();

        for (final Requirement req : requirements) {

            if (taskBS.checkAllTasksFinished(req.getTasks())) {

                requirementsFinished.add(req);
            }
        }
        if (requirementsFinished.size() == 0) {

            return 0;
        }

        return (100 * requirementsFinished.size() / nbRequirements);
    }

    private Date getDateEndPlanned(final Project project) {

        final List<Jalon> jalons = jalonBS.findJalonWithState(project.getId());
        final List<Jalon> jalonsFinished = jalonBS.findJalonByStateAndProject(jalons, EtatJalon.FINISHED);
        final List<Jalon> jalonsStarted = jalonBS.findJalonByStateAndProject(jalons, EtatJalon.STARTED);
        final List<Jalon> jalonsUnstarted = jalonBS.findJalonByStateAndProject(jalons, EtatJalon.NOT_FINISH);
        final Calendar c = Calendar.getInstance();
        Jalon currentJalon = null;
        Date dateToReturn = null;

        // Date livraison planned
        if (checkCurrentJalonExist(jalonsStarted)) {

            currentJalon = jalonsStarted.get(0);

            c.setTime(currentJalon.getDateLivraisonPlanned());

            dateToReturn = getDateEndPlanned(jalonsFinished, currentJalon, c, project);
        }

        // Signifie que tous les jalons sont terminiés ou pas de jalon
        if (dateToReturn == null) {

            if (jalonsFinished.isEmpty()) {

                // Aucun jalon au sein du projet.
                return null;

            } else {

                final Jalon lastJalonFinished = jalonsFinished.get(0);

                if (lastJalonFinished.getDateLivraisonReal() == null) {

                    dateToReturn = lastJalonFinished.getDateLivraisonPlanned();

                } else {

                    dateToReturn = lastJalonFinished.getDateLivraisonReal();
                }
            }

        }

        return dateToReturn;
    }

    private Date getDateEndPlanned(final List<Jalon> jalonsFinished, final Jalon jalon, final Calendar c, final Project project) {

        final Calendar calendar = Calendar.getInstance();
        Date dateLivraison = null;

        for (final Jalon jal : jalonsFinished) {

            if (jal.getDateLivraisonReal() == null) {

                dateLivraison = jal.getDateLivraisonPlanned();

            } else {

                dateLivraison = jal.getDateLivraisonReal();
            }

            calendar.setTime(dateLivraison);

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            c.add(Calendar.DAY_OF_MONTH, day);
            c.add(Calendar.MONTH, month);
            //c.add(Calendar.YEAR, year);
        }

        return c.getTime();
    }

    public int getAvancementJalons(final Project project) {

        final List<Jalon> jalons = jalonBS.findJalonWithState(project.getId());
        final List<Jalon> jalonsFinished = jalonBS.findJalonByStateAndProject(jalons, EtatJalon.FINISHED);

        if (jalonsFinished.isEmpty()) {

            return 0;
        }

        return (100 * jalonsFinished.size() / jalons.size());
    }

    private boolean checkCurrentJalonExist(final List<Jalon> jalonsStarted) {

        if (!jalonsStarted.isEmpty()) {

            return true;
        }

        return false;
    }
}
