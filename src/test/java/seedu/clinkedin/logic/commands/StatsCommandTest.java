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
                + "Number of persons used to calculate statistics: 7\n"
                + "Average tags per person: 0.57\n"
                + "Highest number of tags a single person has: 2\n"
                + "Lowest number of tags a single person has: 0\n"
                + "Total number of tags added to displayed persons: 4";
        assertCommandSuccess(statsCommand, model, expectedMessage, model);
    }

    @Test
void execute_filteredList_success() {
        Model filteredModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        filteredModel.updateFilteredPersonList(p -> p.getName().fullName.equals("Alice Pauline"));
        StatsCommand statsCommand = new StatsCommand();
        String expectedMessage = "Statistics displayed!\n"
                + "Number of persons used to calculate statistics: 1\n"
                + "Average tags per person: 1.00\n"
                + "Highest number of tags a single person has: 1\n"
                + "Lowest number of tags a single person has: 1\n"
                + "Total number of tags added to displayed persons: 1";
        assertCommandSuccess(statsCommand, filteredModel, expectedMessage, filteredModel);
    }
}
