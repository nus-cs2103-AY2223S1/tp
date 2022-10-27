package taskbook.logic.commands;

import static taskbook.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.categoryless.UndoCommand;
import taskbook.model.Model;
import taskbook.model.ModelManager;
import taskbook.model.person.Person;
import taskbook.testutil.PersonBuilder;

public class UndoCommandTest {

    private static final Person P1 = new PersonBuilder().withName("Person 1").build();

    @Test
    public void execute() {
        Model expected = new ModelManager();
        Model actual = new ModelManager();
        actual.addPerson(P1);
        actual.commitTaskBook();

        assertCommandSuccess(new UndoCommand(), actual, UndoCommand.MESSAGE_SUCCESS, expected);
    }
}
