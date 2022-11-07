package taskbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static taskbook.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import taskbook.logic.commands.contacts.ContactFindCommand;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.model.Model;
import taskbook.model.ModelManager;
import taskbook.model.UserPrefs;
import taskbook.model.person.Person;
import taskbook.testutil.TypicalTaskBook;

public class ContactFindCommandTest {
    @Test
    public void execute_allFieldsPresent_success() {
        Predicate<Person> pred = (p -> p.isQueryInPerson("pa"));

        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        expectedModel.updateFilteredPersonListPredicate(pred);
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        ContactFindCommand command = new ContactFindCommand(pred, "pa");
        assertCommandSuccess(command, model, String.format(ContactFindCommand.MESSAGE_SUCCESS + "\n"
                + expectedModel.getFilteredPersonList().size() + " persons listed.\nQuery: pa"), expectedModel);
    }

    @Test
    public void execute_allFieldsPresentNoMatches_success() {
        Predicate<Person> pred = (p -> p.isQueryInPerson("x"));

        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        expectedModel.updateFilteredPersonListPredicate(pred);
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        ContactFindCommand command = new ContactFindCommand(pred, "x");
        assertCommandSuccess(command, model, String.format(ContactFindCommand.MESSAGE_SUCCESS + "\n"
                + expectedModel.getFilteredPersonList().size() + " persons listed.\nQuery: x"), expectedModel);
    }

    @Test
    public void execute_allFieldsPresent_incorrectModel() {
        Predicate<Person> pred = (p -> p.isQueryInPerson("pa"));

        Model expectedModel = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        Model model = new ModelManager(TypicalTaskBook.getTypicalTaskBook(), new UserPrefs());
        ContactFindCommand command = new ContactFindCommand(pred, "ca");
        try {
            command.execute(model);
        } catch (CommandException e) {
            assert false;
        }
        assertFalse(model.equals(expectedModel));
    }
}
