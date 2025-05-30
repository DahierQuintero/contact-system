package dq.contacts.service.impl;

import dq.contacts.dto.ContactDTO;
import dq.contacts.dto.ContactDetailDTO;
import dq.contacts.entity.Contact;
import dq.contacts.repository.IContactRepository;
import dq.contacts.service.IContactService;
import dq.contacts.service.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private IContactRepository iContactRepository;

    @Override
    public List<ContactDTO> listContacts() {
        return iContactRepository.findAll()
                .stream()
                .map(Mapper::ContactToContactDTO)
                .toList();
    }

    @Override
    public ContactDTO findContactById(Integer id) {
        return iContactRepository.findById(id)
                .map(Mapper::ContactToContactDTO)
                .orElseThrow(() -> new RuntimeException("Contact with ID " + id + " doesn't exist"));
    }

    @Override
    public ContactDetailDTO saveContact(ContactDTO contact) {
        var contactToSave = Mapper.ContactDTOToContact(contact);
        iContactRepository.save(contactToSave);
        return Mapper.ContactToContactDetailDTO(contactToSave);

    }

    @Override
    public void deleteContact(Contact contact) {
        iContactRepository.delete(contact);

    }
}
