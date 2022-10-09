package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.role.Seller;
import seedu.address.testutil.PersonBuilder;

class AddSellerCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSellerCommand(null, Index.fromOneBased(1)));
    }

    @Test
    public void execute_sellerAcceptedByModel_addSuccessful() throws Exception {
        Seller validSeller = new Seller(List.of(1,2));
        Person editedPerson = getTypicalAddressBook().getPersonList().get(0);
        editedPerson.setSeller(validSeller);

        AddSellerCommand newCommand = new AddSellerCommand(validSeller, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(AddSellerCommand.MESSAGE_SUCCESS, validSeller);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), editedPerson);

        assertCommandSuccess(newCommand, model, expectedMessage, expectedModel);
    }
}
