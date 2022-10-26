package seedu.classify.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.testutil.TypicalStudents.getTypicalStudentRecord;

import org.junit.jupiter.api.Test;

import seedu.classify.model.Model;
import seedu.classify.model.ModelManager;
import seedu.classify.model.UserPrefs;
import seedu.classify.model.student.Class;
import seedu.classify.model.student.ClassPredicate;


class ViewStatsCommandTest {
    private Model model = new ModelManager(getTypicalStudentRecord(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStudentRecord(), new UserPrefs());

    @Test
    public void equals() {
        ClassPredicate firstPredicate =
                new ClassPredicate(new Class("First"));
        ClassPredicate secondPredicate =
                new ClassPredicate(new Class("Second"));

        ViewStatsCommand firstCommand = new ViewStatsCommand(
                firstPredicate, "4a", "ca1", true);
        ViewStatsCommand secondCommand = new ViewStatsCommand(
                secondPredicate, "4a", "ca1", true);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ViewStatsCommand firstCommandCopy = new ViewStatsCommand(firstPredicate, "4a", "ca1", true);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different classes -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute() {
    }

}