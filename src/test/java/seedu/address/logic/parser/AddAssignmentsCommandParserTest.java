package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAssignmentsCommand;
import seedu.address.model.person.position.Student;

public class AddAssignmentsCommandParserTest {

    private AddAssignmentsCommandParser parser = new AddAssignmentsCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(Student.ASSIGNMENT_CONSTRAINTS));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, " assignments/Ass 1 w/50",
                String.format(Student.ASSIGNMENT_INVALID_SUM_OF_WEIGHTAGE));
    }

    @Test
    public void parse_duplicateAssignment_throwsParseException() {
        assertParseFailure(parser, " assignments/Ass 1 w/50, Ass 1 w/50", String.format(Student.ASSIGNMENT_DUPLICATE));
    }

    @Test
    public void parse_tooManyAssignments_throwsParseException() {
        assertParseFailure(parser, " assignments/Ass 1 w/10, Ass 2 w/10, "
                + "Ass 3 w/10, Ass 4 w/10, Ass 5 w/10, Ass 6 w/10, Ass 7 w/10, Ass 8 w/10, "
                + "Ass 9 w/10, Ass 10 w/5, Ass 11 w/5", String.format(Student.TOO_MANY_ASSIGNMENT));
    }

    @Test
    public void parse_validArgs_returnsAddAssignmentsCommand() {
        assertParseSuccess(parser, " assignments/Ass 1 w/50, Ass 2 w/50",
                new AddAssignmentsCommand("Ass 1 w/50, Ass 2 w/50"));
    }


}
