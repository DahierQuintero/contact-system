package dq.contacts.controller;

import dq.contacts.dto.ContactDTO;
import dq.contacts.entity.Contact;
import dq.contacts.service.impl.ContactServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        contacts.forEach(contact -> LOGGER.info("{}", contact));
        model.addAttribute("contacts", contacts);
        return "index";
    }

    @GetMapping("/add")
    public String showAdd() {
        return "add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("contactForm") ContactDTO contact) {
        LOGGER.info("Contact to add: " + contact);
        contactService.saveContact(contact);
        return "redirect:/api/v1/contact/";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable(value = "id") Integer id, ModelMap modelo) {
        ContactDTO contactDTO = contactService.findContactById(id);
        LOGGER.info("Contact to edit (mostrar): " + contactDTO);
        modelo.addAttribute("contact", contactDTO);
        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("contact") ContactDTO contact) {
        LOGGER.info("Contact to save (edit): " + contact);
        contactService.saveContact(contact);
        return "redirect:/api/v1/contact/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Integer id) {
        Contact contact = new Contact();
        contact.setId(id);
        contactService.deleteContact(contact);
        return "redirect:/api/v1/contact/";
    }

}
