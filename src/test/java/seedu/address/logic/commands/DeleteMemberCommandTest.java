package seedu.address.logic.commands;

import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalTruthTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMemberCommand}.
 */
// TODO: Add implementation for tests
public class DeleteMemberCommandTest {

    private Model model = new ModelManager(getTypicalTruthTable(), new UserPrefs());

    @BeforeEach
    public void setUp() {
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        model.getTeam().addMember(personToDelete);
    }

    @Test
    public void execute_validIndex_success() {
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
    }

    @Test
    public void equals() {
    }
}
