package fr.esimed.easilyproject.controller;

import fr.esimed.easilyproject.BS.ClientBS;
import fr.esimed.easilyproject.entity.Client;
import fr.esimed.easilyproject.enums.Views;
import org.springframework.security.web.util.TextEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.Valid;

@Controller
public class ClientController {

    @Inject
    private ClientBS clientBS;

    @RequestMapping({"/clients"})
    public String findAll(Model model) {

        model.addAttribute("clients", clientBS.findAll());

        return Views.CLIENTS_LIST.toString();
    }

    @RequestMapping({"/client/form"})
    public String prepareAdd(Model model) {

        return redirectToForm(model);
    }

    @RequestMapping(value = "/client/create", method = RequestMethod.POST)
    public String create(@Valid @ModelAttribute("client") Client client, BindingResult result, Model model) {

        if (result.hasErrors()) {

            return redirectToForm(model);
        }

        clientBS.insert(client);

        model.addAttribute("clients", clientBS.findAll());

        return Views.CLIENTS_LIST.toString();
    }

    private String redirectToForm(Model model) {

        model.addAttribute("client", new Client());

        return Views.CLIENT_FORM.toString();
    }

}