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

/**
 * A simple example to introduce building forms. As your real application is probably much
 * more complicated than this example, you could re-use this form in multiple places. This
 * example component is only used in MainView.
 * <p>
 * In a real world application you'll most likely using a common super class for all your
 * forms - less code, better UX.
 */
@SpringComponent
@UIScope
public class ContactEditor extends VerticalLayout implements KeyNotifier {

	public interface ChangeHandler {
		void onChange();
	}

	private final ContactRepository repository;

	/**
	 * The currently edited contact
	 */
	private Contact contact;

	/* Fields to edit properties in Contact entity */
	TextField firstName = new TextField("First name");
	TextField lastName = new TextField("Last name");
	TextField phoneNumber = new TextField("Phone number");

	/* Action buttons */
	// TODO why more code?
	Button save = new Button("Save", VaadinIcon.CHECK.create());
	Button cancel = new Button("Cancel");
	Button delete = new Button("Delete", VaadinIcon.TRASH.create());
	HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

	Binder<Contact> binder = new Binder<>(Contact.class);
	private ChangeHandler changeHandler;

	@Autowired
	public ContactEditor(ContactRepository repository) {
		this.repository = repository;

		add(firstName, lastName, phoneNumber, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);

		save.getElement().getThemeList().add("primary");
		delete.getElement().getThemeList().add("error");

		addKeyPressListener(Key.ENTER, e -> save());

		// wire action buttons to save, delete and reset
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
			// Find fresh entity for editing
			contact = repository.findById(c.getId()).orElse(c);
		}
		else {
			contact = c;
		}
		//cancel.setVisible(persisted);

		// Bind contact properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(contact);

		setVisible(true);

		// Focus first name initially
		firstName.focus();
	}

	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		changeHandler = h;
	}

}
