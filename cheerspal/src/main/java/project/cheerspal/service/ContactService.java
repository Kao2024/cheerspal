package project.cheerspal.service;

/**
 *
 * @author Kiki
 */
import org.springframework.beans.factory.annotation.Autowired;
import project.cheerspal.Contact;
import project.cheerspal.ContactRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}

