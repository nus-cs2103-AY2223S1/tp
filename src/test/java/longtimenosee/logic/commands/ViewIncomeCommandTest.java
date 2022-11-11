package longtimenosee.logic.commands;

import static longtimenosee.logic.commands.CommandTestUtil.assertCommandSuccess;
import static longtimenosee.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import longtimenosee.model.Model;
import longtimenosee.model.ModelManager;
import longtimenosee.model.UserPrefs;


public class ViewIncomeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_normalCommand_success() {
        String expectedMessage = "Income for year 2000 is 0.0";
        ViewIncomeCommand command = new ViewIncomeCommand(2000);
        assertCommandSuccess(command, model, expectedMessage, model);
    }
}
