package seedu.studmap.logic.parser;

import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_GIT;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_HANDLE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.studmap.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.studmap.logic.commands.AddCommand;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.student.Student;
import seedu.studmap.model.student.StudentData;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    private static List<Prefix> getMissingPrefixes(ArgumentMultimap argumentMultimap, Prefix... requiredPrefixes) {
        return Stream.of(requiredPrefixes)
                .filter(prefix -> !argumentMultimap.getValue(prefix).isPresent())
                .collect(Collectors.toList());
    }

    public static String getMissingPrefixesMessage(List<Prefix> missingPrefixes) {
        return "MISSING: " + missingPrefixes
                .stream()
                .map(Prefix::toString)
                .collect(Collectors.joining(", "));
    }


    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_MODULE,
                        PREFIX_ID, PREFIX_GIT, PREFIX_HANDLE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MODULE, PREFIX_ID)) {
            List<Prefix> missingPrefixes = getMissingPrefixes(argMultimap, PREFIX_NAME, PREFIX_MODULE, PREFIX_ID);
            String missingPrefixesMessage = getMissingPrefixesMessage(missingPrefixes);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE)
                    + "\n" + missingPrefixesMessage);
        } else if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        StudentData studentData = new StudentData();

        Optional<String> nameString = argMultimap.getValue(PREFIX_NAME);
        if (nameString.isPresent()) {
            studentData.setName(ParserUtil.parseName(nameString.get()));
        }

        Optional<String> phoneString = argMultimap.getValue(PREFIX_PHONE);
        if (phoneString.isPresent()) {
            studentData.setPhone(ParserUtil.parsePhone(phoneString.get()));
        }

        Optional<String> emailString = argMultimap.getValue(PREFIX_EMAIL);
        if (emailString.isPresent()) {
            studentData.setEmail(ParserUtil.parseEmail(emailString.get()));
        }

        Optional<String> moduleString = argMultimap.getValue(PREFIX_MODULE);
        if (moduleString.isPresent()) {
            studentData.setModule(ParserUtil.parseModule(moduleString.get()));
        }

        Optional<String> idString = argMultimap.getValue(PREFIX_ID);
        if (idString.isPresent()) {
            studentData.setId(ParserUtil.parseId(idString.get()));
        }

        Optional<String> gitUserString = argMultimap.getValue(PREFIX_GIT);
        if (gitUserString.isPresent()) {
            studentData.setGitUser(ParserUtil.parseGitName(gitUserString.get()));
        }

        Optional<String> teleHandleString = argMultimap.getValue(PREFIX_HANDLE);
        if (teleHandleString.isPresent()) {
            studentData.setTeleHandle(ParserUtil.parseHandle(teleHandleString.get()));
        }

        studentData.setTags(ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG)));

        Student student = new Student(studentData);

        return new AddCommand(student);
    }
}
