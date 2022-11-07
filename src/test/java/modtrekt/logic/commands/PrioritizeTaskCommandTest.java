package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import modtrekt.commons.core.index.Index;
import modtrekt.logic.parser.ModtrektParser;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.model.task.Task;

public class PrioritizeTaskCommandTest {
    // === === ===
    // Positive test cases

    @Test
    public void testCommand_sameObjectReferenceEquals_returnsTrue() {
        EditTaskCommand cmd =
                EditTaskCommand.editPriority(Index.fromOneBased(1), Task.Priority.HIGH);
        assertEquals(cmd, cmd);
    }

    @Test
    public void testCommand_differentObjectValuesEquals_returnsFalse() {
        EditTaskCommand a = EditTaskCommand.editPriority(Index.fromOneBased(1), Task.Priority.HIGH);
        EditTaskCommand b = EditTaskCommand.editPriority(Index.fromOneBased(1), Task.Priority.LOW);
        EditTaskCommand c = EditTaskCommand.editPriority(Index.fromOneBased(2), Task.Priority.LOW);
        assertNotEquals(a, b);
        assertNotEquals(b, c);
        assertNotEquals(a, c);
    }

    @Test
    public void testCommand_sameObjectValuesEquals_returnsTrue() {
        EditTaskCommand a = EditTaskCommand.editPriority(Index.fromOneBased(1), Task.Priority.HIGH);
        EditTaskCommand b = EditTaskCommand.editPriority(Index.fromOneBased(1), Task.Priority.HIGH);
        assertEquals(a, b);
    }

    @Test
    public void testModtRektParser_validCommandWord_returnsValidCommand() throws ParseException {
        EditTaskCommand expected =
                EditTaskCommand.editPriority(Index.fromOneBased(1), Task.Priority.HIGH);
        Command actual = new ModtrektParser().parseCommand("edit task 1 -p high");
        assertEquals(expected, actual);
    }

    @Test
    public void testParser_validPriorities_returnsValidCommand() throws ParseException {
        EditTaskCommand high =
                EditTaskCommand.editPriority(Index.fromOneBased(1), Task.Priority.HIGH);
        EditTaskCommand medium =
                EditTaskCommand.editPriority(Index.fromOneBased(2), Task.Priority.MEDIUM);
        EditTaskCommand low =
                EditTaskCommand.editPriority(Index.fromOneBased(Integer.MAX_VALUE), Task.Priority.LOW);
        assertEquals(high, new ModtrektParser().parseCommand("edit task 1 -p high"));
        assertEquals(medium, new ModtrektParser().parseCommand("edit task 2 -p medium"));
        assertEquals(low, new ModtrektParser().parseCommand("edit task 2147483647 -p low"));
    }

    // === === ===
    // Negative test cases

    // NULL

    @Test
    public void testParser_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModtrektParser().parseCommand(null));
    }

    // FLAGS

    @Test
    public void testParser_invalidFlags_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit task 1 -p high -a -b -c"));
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("prioritize -a -b -c -t 1 -p high"));
    }

    @Test
    public void testParser_duplicateFlags_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser()
                .parseCommand("edit task 1 -t 2 -t 3 -p high"));
        assertThrows(ParseException.class, () -> new ModtrektParser()
                .parseCommand("edit task 1 -p high -p medium -p low"));
        assertThrows(ParseException.class, () -> new ModtrektParser()
                .parseCommand("edit task 1 -2 -t 3 -p high -p medium -p low"));
    }

    @Test
    public void testParser_missingFlags_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("prioritize"));
    }

    // PRIORITIES

    @Test
    public void testParser_invalidPriority_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit task 0 -p abc"));
    }

    @Test
    public void testParser_multiplePriorities_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser()
                .parseCommand("edit task 0 -p low medium high"));
    }

    @Test
    public void testParser_missingPriority_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit task 0 -p"));
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit task 0"));
    }

    // INDICES

    @Test
    public void testParser_nonNumericIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit task abc -p high"));
    }

    @Test
    public void testParser_zeroIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit task 0 -p high"));
    }

    @Test
    public void testParser_negativeIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit task -1 -p high"));
    }

    @Test
    public void testParser_multipleIndices_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit task 1 2 3 -p high"));
    }

    @Test
    public void testParser_missingIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit task -p high"));
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("prioritize -p high"));
    }
}
