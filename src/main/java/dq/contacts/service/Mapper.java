package dq.contacts.service;

import dq.contacts.dto.ContactDTO;
import dq.contacts.dto.ContactDetailDTO;
import dq.contacts.entity.Contact;

public class Mapper {

    public static Contact ContactDTOToContact(ContactDTO contactDTO) {
        return new Contact(contactDTO.id(), contactDTO.name(), contactDTO.phoneNumber(), contactDTO.email());
    }

    public static ContactDetailDTO ContactToContactDetailDTO(Contact contact) {
        return new ContactDetailDTO(contact.getName(), contact.getPhoneNumber(), contact.getEmail());
    }
}
