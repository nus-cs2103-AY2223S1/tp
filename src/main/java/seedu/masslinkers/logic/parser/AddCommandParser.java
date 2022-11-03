package seedu.masslinkers.logic.parser;

import static seedu.masslinkers.commons.core.Messages.MESSAGE_INVALID_ARGUMENTS;
import static seedu.masslinkers.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_INTEREST;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import java.util.Set;
import java.util.stream.Stream;

import javafx.collections.ObservableList;
import seedu.masslinkers.logic.commands.AddCommand;
import seedu.masslinkers.logic.parser.exceptions.ParseException;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.student.Email;
import seedu.masslinkers.model.student.GitHub;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.Name;
import seedu.masslinkers.model.student.Phone;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.model.student.Telegram;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TELEGRAM, PREFIX_GITHUB, PREFIX_INTEREST, PREFIX_MOD);
        if (!argMultimap.getPreamble().isEmpty()) {
            String invalidArgs = argMultimap.getPreamble();
            throw new ParseException(String.format(MESSAGE_INVALID_ARGUMENTS, invalidArgs) + "\n"
                    + AddCommand.MESSAGE_USAGE);
        }
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_TELEGRAM)) {
            throw new ParseException(String.format(MESSAGE_MISSING_ARGUMENTS, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Telegram handle = ParserUtil.parseTelegram(argMultimap.getValue(PREFIX_TELEGRAM).get());
        GitHub gitHub = null;
        Phone phone = null;
        Email email = null;
        Set<Interest> interestSet = ParserUtil.parseInterests(argMultimap.getAllValues(PREFIX_INTEREST));
        ObservableList<Mod> modList = ParserUtil.parseMods(argMultimap.getAllValues(PREFIX_MOD));

        if (argMultimap.getValue(PREFIX_GITHUB).isPresent()) {
            gitHub = ParserUtil.parseGitHub(argMultimap.getValue(PREFIX_GITHUB).get());
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }

        Student student = new Student(name, phone, email, handle, gitHub, interestSet, modList);

        return new AddCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
