package taskbook.logic.commands;

import static taskbook.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.categoryless.RedoCommand;
import taskbook.model.Model;
import taskbook.model.ModelManager;
import taskbook.model.person.Person;
import taskbook.testutil.PersonBuilder;

public class RedoCommandTest {

    private static final Person P1 = new PersonBuilder().withName("Person 1").build();

    @Test
    public void execute() {
        Model expected = new ModelManager();
        expected.addPerson(P1);
        expected.commitTaskBook();

        Model actual = new ModelManager();
        actual.addPerson(P1);
        actual.commitTaskBook();
        actual.undoTaskBook();

        assertCommandSuccess(new RedoCommand(), actual, RedoCommand.MESSAGE_SUCCESS, expected);
    }
}
