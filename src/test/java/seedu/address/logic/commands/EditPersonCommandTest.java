package seedu.address.logic.commands;

import static seedu.address.logic.commands.EditPersonCommand.MESSAGE_EDIT_PERSON_SUCCESS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.*;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.team.Link;
import seedu.address.model.team.Team;
import seedu.address.model.team.Task;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditPersonCommand.
 */
// TODO: Add implementation for tests
public class EditPersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = model;
    private final Command commandToBeTested = new EditPersonCommand();
    private final CommandLine commandLine = new CommandLine(commandToBeTested)
            .registerConverter(Index.class, new IndexConverter())
            .registerConverter(Name.class, new NameConverter())
            .registerConverter(Email.class, new EmailConverter())
            .registerConverter(Phone.class, new PhoneConverter())
            .registerConverter(Address.class, new AddressConverter());


    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Person validPerson = new PersonBuilder(TypicalPersons.ALICE).build();
        commandLine.parseArgs(PersonUtil.convertEditPersonToArgs(validPerson));
        CommandResult expectedResult = new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, validPerson));
        assertCommandSuccess(commandToBeTested, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
    }

    @Test
    public void execute_filteredList_success() {
    }

    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
    }

    @Test
    public void equals() {
    }

}
