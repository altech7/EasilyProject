package fr.esimed.easilyproject.controller;

import fr.esimed.easilyproject.BS.ClientBS;
import fr.esimed.easilyproject.BS.JalonBS;
import fr.esimed.easilyproject.BS.ProjectBS;
import fr.esimed.easilyproject.BS.RequirementBS;
import fr.esimed.easilyproject.entity.Client;
import fr.esimed.easilyproject.entity.Jalon;
import fr.esimed.easilyproject.entity.Project;
import fr.esimed.easilyproject.entity.Requirement;
import fr.esimed.easilyproject.enums.Views;
import org.springframework.security.web.util.TextEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ProjectController {

    @Inject
    private ClientBS clientBS;
    @Inject
    private ProjectBS projectBS;
    @Inject
    private RequirementBS requirementBS;
    @Inject
    private JalonBS jalonBS;

    @RequestMapping({"/project/form"})
    public String prepareAdd(Model model) {

        return redirectToForm(model);
    }

    @RequestMapping({"/project/{id}/details"})
    public String getDetails(Model model, @PathVariable("id") Integer id) {

        final Project project = projectBS.getByIdWithStats(id);
        final List<Requirement> requirements = requirementBS.findAllByProject(project.getId());
        final List<Jalon> jalonsDTO = jalonBS.findJalonWithState(project.getId());

        model.addAttribute("project", project);
        model.addAttribute("requirements", requirements);
        model.addAttribute("jalons", jalonsDTO);

        return Views.PROJECT_DETAILS.toString();
    }

    @RequestMapping({"/project/{id}/timeline"})
    public String getTimeline(Model model, @PathVariable("id") Integer id) {

        final Project project = projectBS.getByIdWithStats(id);
        final List<Jalon> jalons = jalonBS.findJalonWithState(project.getId());

        model.addAttribute("project", project);
        model.addAttribute("jalons", jalons);

        return Views.PROJECT_TIMELINE.toString();
    }

    @RequestMapping(value = "/project/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("project") Project project, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return redirectToForm(model);
        }

        projectBS.insert(project);

        model.addAttribute("projects", projectBS.findAllWithAvancement());

        return Views.INDEX.toString();
    }

    private String redirectToForm(Model model) {

        final List<Client> clients = clientBS.findAll();

        if (clients.isEmpty()) {

            model.addAttribute("projects", projectBS.findAllWithAvancement());
            model.addAttribute("showErrorMessage", TextEscapeUtils.escapeEntities("Vous devez ajouter une ressources avant de pouvoir ajouter un nouveau projet."));

            return Views.INDEX.toString();
        }

        model.addAttribute("project", new Project());
        model.addAttribute("managers", clients);

        return Views.PROJECT_FORM.toString();
    }
}