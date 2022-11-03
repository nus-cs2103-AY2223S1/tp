package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsHealthContact;

import org.junit.jupiter.api.Test;


import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class RedoCommandTest {
    private final Model model = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());


    @Test
    public void execute() throws CommandException {
        // multiple undoable states in model
        try {
            expectedModel.redo();
        } catch (CommandException e) {
            assertEquals(e.getMessage(), "Undo cannot be done as there was no previous change in data");
        }
    }
}
