package fr.esimed.easilyproject.controller;

import fr.esimed.easilyproject.BS.ClientBS;
import fr.esimed.easilyproject.BS.JalonBS;
import fr.esimed.easilyproject.BS.RequirementBS;
import fr.esimed.easilyproject.BS.TaskBS;
import fr.esimed.easilyproject.entity.Jalon;
import fr.esimed.easilyproject.entity.Requirement;
import fr.esimed.easilyproject.entity.Task;
import fr.esimed.easilyproject.enums.EtatJalon;
import fr.esimed.easilyproject.enums.EtatTask;
import fr.esimed.easilyproject.enums.Views;
import org.springframework.security.web.util.TextEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TaskController {

    @Inject
    private RequirementBS requirementBS;
    @Inject
    private ClientBS clientBS;
    @Inject
    private TaskBS taskBS;
    @Inject
    private JalonBS jalonBS;

    @RequestMapping({"/task/{taskId}/start/requirement/{requirementId}"})
    public String startTaskFromRequirement(Model model, @PathVariable("taskId") Integer taskId, @PathVariable("requirementId") Integer requirementId) {

        final Task task = taskBS.getById(taskId);

        if (task.getPreviousTask() != null) {
            if (!task.getPreviousTask().getEtat().equals(EtatTask.FINISHED)) {

                model.addAttribute("requirement", requirementBS.getById(requirementId));
                model.addAttribute("showErrorMessage", TextEscapeUtils.escapeEntities("La tâche '" + task.getPreviousTask().getLabel() + "' doit être terminée avant de pouvoir terminer celle-ci : '" + task.getPreviousTask().getLabel() + "'."));

                return Views.REQUIREMENT_TASKS_LIST.toString();
            }
        }

        task.setEtat(EtatTask.STARTED);
        task.setDateStart(new Date());

        taskBS.insert(task);

        model.addAttribute("requirement", requirementBS.getById(requirementId));

        return Views.REQUIREMENT_TASKS_LIST.toString();
    }

    @RequestMapping({"/task/{taskId}/start/jalon/{jalonId}"})
    public String startTaskFromJalon(Model model, @PathVariable("taskId") Integer taskId, @PathVariable("jalonId") Integer jalonId) {

        final Jalon jalon = jalonBS.getById(jalonId);
        Task task = taskBS.getById(taskId);

        if (task.getPreviousTask() != null) {
            if (!task.getPreviousTask().getEtat().equals(EtatTask.FINISHED)) {

                model.addAttribute("jalon", jalon);
                model.addAttribute("tasks", taskBS.findAllByJalon(jalonId));
                model.addAttribute("showErrorMessage", TextEscapeUtils.escapeEntities("La tâche '" + task.getPreviousTask().getLabel() + "' doit être terminée avant de pouvoir terminer celle-ci : '" + task.getPreviousTask().getLabel() + "'."));

                return Views.JALONS_TASKS_LIST.toString();
            }
        }

        task.setEtat(EtatTask.STARTED);
        task.setDateStart(new Date());

        taskBS.insert(task);

        model.addAttribute("jalon", jalon);
        model.addAttribute("tasks", taskBS.findAllByJalon(jalonId));

        return Views.JALONS_TASKS_LIST.toString();
    }

    @RequestMapping({"/task/{taskId}/finish/requirement/{requirementId}"})
    public String finishTaskFromRequirement(Model model, @PathVariable("taskId") Integer taskId, @PathVariable("requirementId") Integer requirementId) {

        Task task = taskBS.getById(taskId);

        task.setEtat(EtatTask.FINISHED);

        taskBS.insert(task);

        final Requirement requirement = requirementBS.getById(requirementId);

        model.addAttribute("requirement", requirement);

        return Views.REQUIREMENT_TASKS_LIST.toString();
    }


    @RequestMapping({"/task/{taskId}/finish/jalon/{jalonId}"})
    public String finishTaskFromJalon(Model model, @PathVariable("taskId") Integer taskId, @PathVariable("jalonId") Integer jalonId) {

        final Jalon jalon = jalonBS.getById(jalonId);
        Task task = taskBS.getById(taskId);

        task.setEtat(EtatTask.FINISHED);

        taskBS.insert(task);

        model.addAttribute("jalon", jalon);
        model.addAttribute("tasks", taskBS.findAllByJalon(jalonId));

        return Views.JALONS_TASKS_LIST.toString();
    }

    @RequestMapping({"/task/{taskId}/stop/requirement/{requirementId}"})
    public String stopTaskFromRequirement(Model model, @PathVariable("taskId") Integer taskId, @PathVariable("requirementId") Integer requirementId) {

        Task task = taskBS.getById(taskId);

        task.setEtat(EtatTask.UNSTART);

        taskBS.insert(task);

        final Requirement requirement = requirementBS.getById(requirementId);

        model.addAttribute("requirement", requirement);

        return Views.REQUIREMENT_TASKS_LIST.toString();
    }

    @RequestMapping({"/task/{taskId}/stop/jalon/{jalonId}"})
    public String stopTaskFromJalon(Model model, @PathVariable("taskId") Integer taskId, @PathVariable("jalonId") Integer jalonId) {

        final Jalon jalon = jalonBS.getById(jalonId);
        Task task = taskBS.getById(taskId);

        task.setEtat(EtatTask.UNSTART);

        taskBS.insert(task);

        model.addAttribute("jalon", jalon);
        model.addAttribute("tasks", taskBS.findAllByJalon(jalonId));

        return Views.JALONS_TASKS_LIST.toString();
    }

    @RequestMapping({"/task/jalon/{jalonId}"})
    public @ResponseBody List<Task> getTasksByJalon(Model model, @PathVariable("jalonId") Integer jalonId) {

        final List<Task> tasks = taskBS.findAllByJalon(jalonId);
        final List<Task> tasksJSON = new ArrayList<Task>();

        for (final Task task : tasks) {

            if (task.getEtat().equals(EtatTask.STARTED) || task.getEtat().equals(EtatTask.UNSTART)) {

                tasksJSON.add(task);
            }
        }

        return tasksJSON;
    }

    @RequestMapping({"/task/form/{requirementId}"})
    public String prepareAdd(Model model, @PathVariable("requirementId") Integer requirementId) {

        final Requirement requirement = requirementBS.getById(requirementId);

        return redirectToFormOrDetailsProject(model, requirement);
    }

    @RequestMapping(value = "/task/create/{requirementId}", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("task") Task task, @PathVariable("requirementId") Integer
            requirementId, BindingResult result, Model model) {

        if (result.hasErrors()) {

            final Requirement requirement = requirementBS.getById(requirementId);
            return redirectToFormOrDetailsProject(model, requirement);
        }

        final Requirement requirement = requirementBS.getById(requirementId);

        task.setEtat(EtatTask.UNSTART);
        requirement.getTasks().add(task);
        requirementBS.insert(requirement);

        final List<Jalon> jalonsDTO = jalonBS.findJalonWithState(requirement.getProject().getId());

        model.addAttribute("requirements", requirementBS.findAllByProject(requirement.getProject().getId()));
        model.addAttribute("project", requirement.getProject());
        model.addAttribute("jalons", jalonsDTO);

        return Views.PROJECT_DETAILS.toString();
    }

    private String redirectToFormOrDetailsProject(Model model, Requirement requirement) {

        final List<Jalon> jalons = jalonBS.findJalonWithState(requirement.getProject().getId());
        List<Jalon> jalonsDTO = new ArrayList<Jalon>();

        for (final Jalon jal : jalons) {
            if (jal.getEtat().equals(EtatJalon.NOT_FINISH) || jal.getEtat().equals(EtatJalon.STARTED)) {
                jalonsDTO.add(jal);
            }
        }

        if (jalonsDTO.isEmpty()) {

            final List<Requirement> requirements = requirementBS.findAllByProject(requirement.getProject().getId());

            model.addAttribute("showErrorMessage", TextEscapeUtils.escapeEntities("Aucun jalon est actuellement en cours. Veuillez en créer un."));
            model.addAttribute("project", requirement.getProject());
            model.addAttribute("requirements", requirements);
            model.addAttribute("jalons", jalons);

            return Views.PROJECT_DETAILS.toString();
        }

        // Si une des tâches de l'éxigence possède un jalon, alors les suivantes tâche doivent être du même jalon.
        if (requirement.getTasks() != null && !requirement.getTasks().isEmpty()) {
            jalonsDTO = new ArrayList<>();
            jalonsDTO.add(requirement.getTasks().get(0).getJalon());
        }

        model.addAttribute("jalons", jalonsDTO);
        model.addAttribute("task", new Task());
        model.addAttribute("managers", clientBS.findAll());
        model.addAttribute("previousTasks", new ArrayList<Task>());
        model.addAttribute("requirement", requirement);

        return Views.TASK_FORM.toString();
    }


}