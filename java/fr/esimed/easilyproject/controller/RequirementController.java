package fr.esimed.easilyproject.controller;

import fr.esimed.easilyproject.BS.JalonBS;
import fr.esimed.easilyproject.BS.ProjectBS;
import fr.esimed.easilyproject.BS.RequirementBS;
import fr.esimed.easilyproject.BS.TaskBS;
import fr.esimed.easilyproject.entity.Jalon;
import fr.esimed.easilyproject.entity.Project;
import fr.esimed.easilyproject.entity.Requirement;
import fr.esimed.easilyproject.enums.TypeRequirement;
import fr.esimed.easilyproject.enums.Views;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.List;

@Controller
public class RequirementController {

    @Inject
    private RequirementBS requirementBS;
    @Inject
    private ProjectBS projectBS;
    @Inject
    private JalonBS jalonBS;
    @Inject
    private TaskBS taskBS;

    @RequestMapping({"/requirement/{requirementId}/tasks"})
    public String getById(Model model, @PathVariable("requirementId") Integer requirementId) {

        final Requirement requirement = requirementBS.getById(requirementId);

        model.addAttribute("requirement", requirement);

        return Views.REQUIREMENT_TASKS_LIST.toString();
    }

    @RequestMapping({"/requirement/form/{projetId}"})
    public String prepareAdd(Model model, @PathVariable("projetId") Integer projetId) {

        final Project project = projectBS.getById(projetId);
        final List<TypeRequirement> typeRequirements = TypeRequirement.findAll();

        model.addAttribute("requirement", new Requirement());
        model.addAttribute("project", project);
        model.addAttribute("typeRequirements", typeRequirements);

        return Views.REQUIREMENT_FORM.toString();
    }

    @RequestMapping(value = "/requirement/create", method = RequestMethod.POST)
    public String create(@RequestParam("description") String description, @RequestParam(value = "typeRequirement", required = false) String typeRequirementCode, @RequestParam(value = "isFonctionnal", required = false) Boolean isFonctionnal, @RequestParam("projectId") Integer projectId, Model model) {

        TypeRequirement typeRequirement = null;
        if (isFonctionnal == null) {
            isFonctionnal = false;
        }

        if (isFonctionnal) {
            if (typeRequirementCode.equals(" ")) {
                typeRequirement = TypeRequirement.DATA;
            } else {
                typeRequirement = TypeRequirement.getTypeRequirement(Integer.parseInt(typeRequirementCode));
            }
        }

        final Project project = projectBS.getById(projectId);
        final Requirement requirement = new Requirement(description, isFonctionnal, typeRequirement, project);

        requirementBS.insert(requirement);

        final List<Jalon> jalonsDTO = jalonBS.findJalonWithState(requirement.getProject().getId());

        model.addAttribute("project", project);
        model.addAttribute("requirements", requirementBS.findAllByProject(project.getId()));
        model.addAttribute("jalons", jalonsDTO);

        return Views.PROJECT_DETAILS.toString();
    }


}