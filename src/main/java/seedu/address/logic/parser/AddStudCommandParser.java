package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddStudCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Class;
import seedu.address.model.student.Id;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddStudCommand object
 */
public class AddStudCommandParser implements Parser<AddStudCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudCommand
     * and returns an AddStudCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STUDENT_NAME, PREFIX_ID, PREFIX_CLASS,
                        PREFIX_PARENT_NAME, PREFIX_PHONE, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_STUDENT_NAME, PREFIX_ID, PREFIX_CLASS, PREFIX_PARENT_NAME,
                PREFIX_PHONE, PREFIX_ADDRESS)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudCommand.MESSAGE_USAGE));
        }

        Name studentName = ParserUtil.parseName(argMultimap.getValue(PREFIX_STUDENT_NAME).get());
        Id id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        Class className = ParserUtil.parseClass(argMultimap.getValue(PREFIX_CLASS).get());
        Name parentName = ParserUtil.parseName(argMultimap.getValue(PREFIX_PARENT_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Student person = new Student(studentName, id, className, parentName, phone, address, tagList);

        return new AddStudCommand(person);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
