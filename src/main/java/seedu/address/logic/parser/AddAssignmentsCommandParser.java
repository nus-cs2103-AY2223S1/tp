package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENTS;

import seedu.address.logic.commands.AddAssignmentsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.position.Student;

/**
 * Parses input arguments and creates a new AddAssignmentsCommand object
 */
public class AddAssignmentsCommandParser implements Parser<AddAssignmentsCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddAssignmentsCommand}
     * and returns a {@code AddAssignmentsCommand} object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAssignmentsCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ASSIGNMENTS);

        String assignments = argMultimap.getValue(PREFIX_ASSIGNMENTS).orElse("");
        if (!Student.isValidAssignments(assignments)) {
            throw new ParseException(Student.findAssignmentIssue(assignments));
        }

        return new AddAssignmentsCommand(assignments);
    }
}
