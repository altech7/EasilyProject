package fr.esimed.easilyproject.controller;

import fr.esimed.easilyproject.BS.*;
import fr.esimed.easilyproject.entity.Jalon;
import fr.esimed.easilyproject.entity.Project;
import fr.esimed.easilyproject.enums.Views;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.web.util.TextEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
public class JalonController {

    @Inject
    private ClientBS clientBS;
    @Inject
    private JalonBS jalonBS;
    @Inject
    private ProjectBS projectBS;
    @Inject
    private RequirementBS requirementBS;
    @Inject
    private TaskBS taskBS;

    @RequestMapping({"/jalon/{id}/deliver/form"})
    public String addDateLivraisonReal(Model model, @PathVariable("id") Integer id) {

        final Jalon jalon = jalonBS.getById(id);

        model.addAttribute("jalon", jalon);

        return Views.JALON_DELIVER_FORM.toString();
    }

    @RequestMapping(value = "/jalon/deliver/update", method = RequestMethod.POST)
    public String updateWithDateLivraisonReal(Model model, @DateTimeFormat(pattern = "dd/MM/yyyy") @RequestParam("dateLivraisonReal") Date dateLivraisonReal, @RequestParam("id") Integer id) {

        final Jalon jalon = jalonBS.getById(id);

        if (dateLivraisonReal.before(new Date())) {

            model.addAttribute("jalon", jalon);
            model.addAttribute("showErrorMessage", TextEscapeUtils.escapeEntities("Veuillez saisir une date supérieure à la date du jour."));

            return Views.JALON_DELIVER_FORM.toString();
        }

        jalon.setDateLivraisonReal(dateLivraisonReal);

        jalonBS.insert(jalon);

        final List<Jalon> jalonsDTO = jalonBS.findJalonWithState(jalon.getProject().getId());

        model.addAttribute("project", jalon.getProject());
        model.addAttribute("requirements", requirementBS.findAllByProject(jalon.getProject().getId()));
        model.addAttribute("jalons", jalonsDTO);

        return Views.PROJECT_DETAILS.toString();
    }

    @RequestMapping({"/jalon/form/project/{projectId}"})
    public String prepareAdd(Model model, @PathVariable("projectId") Integer projectId) {

        return redirectToForm(model, projectId);
    }

    @RequestMapping(value = "/jalon/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("jalon") Jalon jalon, @RequestParam("projectId") Integer projectId, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return redirectToForm(model, projectId);
        }

        final Project project = projectBS.getByIdWithStats(projectId);

        jalon.setProject(project);

        jalonBS.insert(jalon);

        final List<Jalon> jalonsDTO = jalonBS.findJalonWithState(project.getId());

        model.addAttribute("project", project);
        model.addAttribute("requirements", requirementBS.findAllByProject(project.getId()));
        model.addAttribute("jalons", jalonsDTO);

        return Views.PROJECT_DETAILS.toString();
    }

    @RequestMapping({"/jalon/{id}/tasks"})

    public String findAllTasksByJalon(Model model, @PathVariable("id") Integer id) {

        model.addAttribute("jalon", jalonBS.getById(id));
        model.addAttribute("tasks", taskBS.findAllByJalon(id));

        return Views.JALONS_TASKS_LIST.toString();
    }

    private String redirectToForm(Model model, Integer projectId) {

        model.addAttribute("jalon", new Jalon());
        model.addAttribute("project", projectBS.getById(projectId));
        model.addAttribute("managers", clientBS.findAll());

        return Views.JALON_FORM.toString();
    }
}