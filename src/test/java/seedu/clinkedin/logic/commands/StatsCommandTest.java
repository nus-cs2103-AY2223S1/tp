package seedu.clinkedin.logic.commands;

import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.UserPrefs;

class StatsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_unfilteredList_success() {
        StatsCommand statsCommand = new StatsCommand();
        String expectedMessage = "Statistics displayed!\n"
                + "Number of candidates used to calculate statistics: 7\n";
        assertCommandSuccess(statsCommand, model, expectedMessage, model);
    }

    @Test
void execute_filteredList_success() {
        Model filteredModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        filteredModel.updateFilteredPersonList(p -> p.getName().fullName.equals("Alice Pauline"));
        StatsCommand statsCommand = new StatsCommand();
        String expectedMessage = "Statistics displayed!\n"
                + "Number of candidates used to calculate statistics: 1\n";
        assertCommandSuccess(statsCommand, filteredModel, expectedMessage, filteredModel);
    }
}
