package jarvis.logic.parser;

import static jarvis.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static jarvis.logic.parser.CliSyntax.PREFIX_MATRIC_NUM;
import static jarvis.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import jarvis.logic.commands.AddStudentCommand;
import jarvis.logic.parser.exceptions.ParseException;
import jarvis.model.GradeProfile;
import jarvis.model.MatricNum;
import jarvis.model.Student;
import jarvis.model.StudentName;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentCommand
     * and returns an AddStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MATRIC_NUM);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MATRIC_NUM) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        StudentName studentName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        MatricNum matricNum = ParserUtil.parseMatricNum(argMultimap.getValue(PREFIX_MATRIC_NUM).get());

        Student student = new Student(studentName, matricNum, new GradeProfile());

        return new AddStudentCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
