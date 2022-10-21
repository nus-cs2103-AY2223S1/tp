package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import modtrekt.commons.core.index.Index;
import modtrekt.logic.parser.ModtrektParser;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.model.module.ModCode;
import modtrekt.model.task.Description;

class EditTaskCommandTest {

    // === === ===
    // Positive test cases

    @Test
    public void testCommand_sameObjectReferenceEquals_returnsTrue() {
        EditTaskCommand cmd =
                new EditTaskCommand(Index.fromOneBased(1), new ModCode("CS2109S"),
                        LocalDate.parse("2000-12-11".trim(), DateTimeFormatter.ofPattern("yyyy-M-d")),
                        new Description("study for exams"));
        assertEquals(cmd, cmd);
    }

    @Test
    public void testCommand_differentObjectValuesEquals_returnsFalse() {
        EditTaskCommand a =
                new EditTaskCommand(Index.fromOneBased(1), new ModCode("CS2109S"),
                        LocalDate.parse("2000-12-11".trim(), DateTimeFormatter.ofPattern("yyyy-M-d")),
                        new Description("study for exams"));
        EditTaskCommand b =
                new EditTaskCommand(Index.fromOneBased(2), new ModCode("CS2109S"),
                        LocalDate.parse("2000-12-11".trim(), DateTimeFormatter.ofPattern("yyyy-M-d")),
                        new Description("study for exams"));
        EditTaskCommand c =
                new EditTaskCommand(Index.fromOneBased(1), new ModCode("CS2103T"),
                        LocalDate.parse("2000-12-11".trim(), DateTimeFormatter.ofPattern("yyyy-M-d")),
                        new Description("study for exams"));
        EditTaskCommand d =
                new EditTaskCommand(Index.fromOneBased(1), new ModCode("CS2109S"),
                        LocalDate.parse("2000-12-11".trim(), DateTimeFormatter.ofPattern("yyyy-M-d")),
                        new Description("preparing for exams"));
        EditTaskCommand e =
                new EditTaskCommand(Index.fromOneBased(1), new ModCode("CS2109S"),
                        LocalDate.parse("1999-12-11".trim(), DateTimeFormatter.ofPattern("yyyy-M-d")),
                        new Description("studying for exams"));
        assertNotEquals(a, b);
        assertNotEquals(a, c);
        assertNotEquals(a, d);
        assertNotEquals(a, e);
    }

    @Test
    public void testCommand_sameObjectValuesEquals_returnsTrue() {
        EditTaskCommand cmd1 =
                new EditTaskCommand(Index.fromOneBased(1), new ModCode("CS2109S"),
                        LocalDate.parse("2000-12-11".trim(), DateTimeFormatter.ofPattern("yyyy-M-d")),
                        new Description("study for exams"));
        EditTaskCommand cmd2 =
                new EditTaskCommand(Index.fromOneBased(1), new ModCode("CS2109S"),
                        LocalDate.parse("2000-12-11".trim(), DateTimeFormatter.ofPattern("yyyy-M-d")),
                        new Description("study for exams"));
        assertEquals(cmd1, cmd2);
    }

    @Test
    public void testModtRektParser_validCommandWord_returnsValidCommand() throws ParseException {
        EditTaskCommand expected =
                new EditTaskCommand(Index.fromOneBased(1), new ModCode("CS2109S"),
                        LocalDate.parse("2000-12-11".trim(), DateTimeFormatter.ofPattern("yyyy-M-d")),
                        new Description("study for exams"));
        Command actual =
                new ModtrektParser()
                        .parseCommand("edit -t 1 -c CS2109S -d 2000-12-11 -ds \"study for exams\"");
        assertEquals(expected, actual);
    }

    @Test
    public void testParser_validModule_returnsValidCommand() throws ParseException {
        EditTaskCommand ai =
                new EditTaskCommand(Index.fromOneBased(1), new ModCode("CS2109S"),
                        null, null);
        EditTaskCommand swe =
                new EditTaskCommand(Index.fromOneBased(1), new ModCode("CS2103T"),
                        null, null);

        assertEquals(ai, new ModtrektParser().parseCommand("edit -t 1 -c CS2109S"));
        assertEquals(swe, new ModtrektParser().parseCommand("edit -t 1 -c CS2103T"));
    }

    @Test
    public void testParser_validDeadline_returnsValidCommand() throws ParseException {
        EditTaskCommand date1 =
                new EditTaskCommand(Index.fromOneBased(1), null,
                        LocalDate.parse("2000-12-11".trim(), DateTimeFormatter.ofPattern("yyyy-M-d")),
                        null);
        EditTaskCommand date2 =
                new EditTaskCommand(Index.fromOneBased(1), null,
                        LocalDate.parse("1998-12-11".trim(), DateTimeFormatter.ofPattern("yyyy-M-d")),
                        null);
        assertEquals(date1, new ModtrektParser().parseCommand("edit -t 1 -d 2000-12-11"));
        assertEquals(date2, new ModtrektParser().parseCommand("edit -t 1 -d 1998-12-11"));
    }

    @Test
    public void testParser_validDescription_returnsValidCommand() throws ParseException {
        EditTaskCommand read =
                new EditTaskCommand(Index.fromOneBased(1), null, null,
                        new Description("read book"));
        EditTaskCommand buy =
                new EditTaskCommand(Index.fromOneBased(1), null, null,
                        new Description("buy book"));
        assertEquals(read, new ModtrektParser().parseCommand("edit -t 1 -ds \"read book\""));
        assertEquals(buy, new ModtrektParser().parseCommand("edit -t 1 -ds \"buy book\""));
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
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit -t 1 -p high"));
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit -a -b -c -p high"));
    }

    @Test
    public void testParser_missingFlags_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit"));
    }

    // MODULES

    @Test
    public void testParser_invalidModule_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit -t 1 -c CS@0"));
    }

    @Test
    public void testParser_multipleModules_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser()
                .parseCommand("edit -t 1 -c CS2100 CS2103T"));
    }

    @Test
    public void testParser_missingModule_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit -t 1 -c"));
    }

    // DEADLINES

    @Test
    public void testParser_invalidDeadline_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit -t 1 -d 12-12-2000"));
    }

    @Test
    public void testParser_multipleDeadline_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser()
                .parseCommand("edit -t 1 -d 2000-12-12 1999-12-12"));
    }

    @Test
    public void testParser_missingDeadline_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit -t 1 -d"));
    }

    // DESCRIPTION

    @Test
    public void testParser_missingDes_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit -t 1 -d"));
    }

    // INDICES

    @Test
    public void testParser_nonNumericIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit -t abc -c CS2103T"));
    }

    @Test
    public void testParser_zeroIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit -t 0 -c CS2103T"));
    }

    @Test
    public void testParser_negativeIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit -t -1 -c CS2103T"));
    }

    @Test
    public void testParser_multipleIndices_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit -t 1 2 3 -c CS2103T"));
    }

    @Test
    public void testParser_missingIndex_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit -t -c CS2103T"));
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("edit -c CS2103T"));
    }
}
