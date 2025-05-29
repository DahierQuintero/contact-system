package dq.contacts.service;

import dq.contacts.dto.ContactDTO;
import dq.contacts.dto.ContactDetailDTO;
import dq.contacts.entity.Contact;

import java.util.List;

public interface IContactService {

    List<ContactDTO>  listContacts();

    ContactDetailDTO findContactById(Integer id);

    ContactDetailDTO saveContact(ContactDTO contact);

    void deleteContact(Contact contact);

}
