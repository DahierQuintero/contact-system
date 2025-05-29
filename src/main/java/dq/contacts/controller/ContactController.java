package dq.contacts.controller;

import dq.contacts.service.impl.ContactServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/contact")
public class ContactController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactServiceImpl contactService;

    @GetMapping("/")
    public String start(ModelMap model) {
        var contacts = contactService.listContacts();
        contacts.forEach(contact -> LOGGER.info("{}",contact));
        model.addAttribute("contacts", contacts);
        return "index";
    }
}
