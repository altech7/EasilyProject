package fr.esimed.easilyproject.controller;

import fr.esimed.easilyproject.BS.ProjectBS;
import fr.esimed.easilyproject.entity.Project;
import fr.esimed.easilyproject.enums.Views;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

@Controller
public class IndexController {

    @Inject
    private ProjectBS projectBS;

    @RequestMapping({"/", "/index"})
    public String index(Model model) {

        final List<Project> projects = projectBS.findAllWithAvancement();

        model.addAttribute("projects", projects);

        return Views.INDEX.toString();
    }
}