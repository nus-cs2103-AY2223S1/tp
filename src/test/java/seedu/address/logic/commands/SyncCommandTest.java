package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;


class SyncCommandTest {

    private Person pastMeetingGuy = new PersonBuilder().withMeetingTimes("28-07-2020-15:00").build();
    private AddressBook addressBook = new AddressBookBuilder().withPerson(pastMeetingGuy).withPerson(CARL).build();
    private final Model model = new ModelManager(addressBook, new UserPrefs());
    private final Model expectedModel = new ModelManager(addressBook, new UserPrefs());

    @Test
    void execute() {
        assert(true);
        SyncCommand command = new SyncCommand();
        expectedModel.syncMeetingTimes();
        assertCommandSuccess(command, model, SyncCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
