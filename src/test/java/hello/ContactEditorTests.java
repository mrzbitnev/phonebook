package hello;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.then;

import hello.domain.Contact;
import hello.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ContactEditorTests {

	private static final String FIRST_NAME = "Marcin";
	private static final String LAST_NAME = "Grzejszczak";

	@Mock
    ContactRepository contactRepository;
	@InjectMocks CustomerEditor editor;
	@Mock CustomerEditor.ChangeHandler changeHandler;

	@Before
	public void init() {
		editor.setChangeHandler(changeHandler);
	}

	@Test
	public void shouldStoreCustomerInRepoWhenEditorSaveClicked() {
		emptyCustomerWasSetToForm();

		this.editor.firstName.setValue(FIRST_NAME);
		this.editor.lastName.setValue(LAST_NAME);

		this.editor.save();

		then(this.contactRepository).should().save(argThat(customerMatchesEditorFields()));
	}

	@Test
	public void shouldDeleteCustomerFromRepoWhenEditorDeleteClicked() {
		customerDataWasFilled();

		editor.delete();

		then(this.contactRepository).should().delete(argThat(customerMatchesEditorFields()));
	}

	private void emptyCustomerWasSetToForm() {
		//this.editor.editCustomer(new Contact());
	}
	private void customerDataWasFilled() {
		this.editor.editCustomer(new Contact(FIRST_NAME, LAST_NAME));
	}

	private ArgumentMatcher<Contact> customerMatchesEditorFields() {
		return customer -> FIRST_NAME.equals(customer.getFirstName()) && LAST_NAME.equals(customer.getLastName());
	}

}
