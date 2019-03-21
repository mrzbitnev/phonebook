package hello;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import hello.domain.Contact;
import hello.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class ContactEditor extends VerticalLayout implements KeyNotifier {

    public interface ChangeHandler {
        void onChange();
    }

    private final ContactRepository repository;

    TextField firstName = new TextField("First name");
    TextField lastName = new TextField("Last name");
    TextField phoneNumber = new TextField("Phone number");
    TextField contactField1 = new TextField("Field");
    TextField contactField2 = new TextField("Field");
    TextField contactField3 = new TextField("Field");
    TextField contactField4 = new TextField("Field");

    private Contact contact;
    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<Contact> binder = new Binder<>(Contact.class);
    private ChangeHandler changeHandler;

    @Autowired
    public ContactEditor(ContactRepository repository) {
        this.repository = repository;

        add(actions, firstName, lastName, phoneNumber,
                contactField1, contactField2, contactField3,
                contactField4);

        binder.bindInstanceFields(this);
        setSpacing(true);
        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editContact(contact));
        setVisible(false);
    }

    void delete() {
        repository.delete(contact);
        changeHandler.onChange();
    }

    void save() {
        repository.save(contact);
        changeHandler.onChange();
    }


    public final void editContact(Contact c) {
        if (c == null) {
            setVisible(false);
            return;
        }
        if (c.getId() != null) {
            contact = repository.findById(c.getId()).orElse(c);
        } else {
            contact = c;
        }
        binder.setBean(contact);
        setVisible(true);
        firstName.focus();
    }

    public void setChangeHandler(ChangeHandler h) {
        changeHandler = h;
    }

}
