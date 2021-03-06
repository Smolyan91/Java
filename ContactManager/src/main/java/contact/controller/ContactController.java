package contact.controller;

import contact.model.Contact;
import contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ContactController {

    private ContactService contactService;

    @Autowired
    @Qualifier(value = "contactService")
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "contacts", method = RequestMethod.GET)
    public String listContacts(Model model){
        model.addAttribute("contact", new Contact());
        model.addAttribute("contactList", this.contactService.getContacts());
        return "contacts";
    }

    @RequestMapping(value = "/contacts/add", method = RequestMethod.POST)
    public String addContact(@ModelAttribute("contact") Contact contact){
        if (contact.getId() == 0){
            this.contactService.createContact(contact);
        }else {
            this.contactService.updateContact(contact);
        }
        return "redirect:/contacts";
    }

    @RequestMapping("/remove/{id}")
    public String removeContact(@PathVariable("id") int id){
        this.contactService.deleteContact(id);
        return "redirect:/contacts";
    }

    @RequestMapping("/edit/{id}")
    public String editContact(@PathVariable("id") int id, Model model){
        model.addAttribute("contact", this.contactService.getContactById(id));
        model.addAttribute("contactList", this.contactService.getContacts());
        return "contacts";
    }

    @RequestMapping("/contacts_data/{id}")
    public String contactData(@PathVariable("id") int id, Model model){
        model.addAttribute("contact", this.contactService.getContactById(id));
        return "contacts_data";
    }
}
