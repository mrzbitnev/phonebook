package hello;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import hello.domain.Contact;
import hello.repository.ContactRepository;
import org.springframework.util.StringUtils;

@Route
public class MainView extends VerticalLayout {

	private final ContactRepository repo;
	private final ContactEditor editor;
	final Grid<Contact> grid;
	final TextField filter;
	private final Button addNewBtn;

	public MainView(ContactRepository repo, ContactEditor editor) {
		this.repo = repo;
		this.editor = editor;
		this.grid = new Grid<>(Contact.class);
		this.filter = new TextField();
		this.addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());

		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		add(actions, grid, editor);

		grid.setHeight("300px");
		grid.setColumns("id", "firstName", "lastName", "phoneNumber", "contactField1",
				"contactField2", "contactField3", "contactField4");
		grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

		filter.setPlaceholder("Filter by last name");

		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listContact(e.getValue()));

		grid.asSingleSelect().addValueChangeListener(e -> {
			editor.editContact(e.getValue());
		});

		addNewBtn.addClickListener(e -> editor.editContact(new Contact("", "",
				"","", "", "", "")));

		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listContact(filter.getValue());
		});

		listContact(null);
	}

	void listContact(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repo.findAll());
		}
		else {
			grid.setItems(repo.findByName(filterText));
		}
	}

}
