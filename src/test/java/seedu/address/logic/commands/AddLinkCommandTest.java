package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class AddLinkCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

}
