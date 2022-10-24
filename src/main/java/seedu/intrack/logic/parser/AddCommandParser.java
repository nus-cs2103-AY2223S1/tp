package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_POSITION;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.intrack.logic.parser.CliSyntax.PREFIX_WEBSITE;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import seedu.intrack.logic.commands.AddCommand;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.internship.Email;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.internship.Name;
import seedu.intrack.model.internship.Phone;
import seedu.intrack.model.internship.Position;
import seedu.intrack.model.internship.Remark;
import seedu.intrack.model.internship.Status;
import seedu.intrack.model.internship.Task;
import seedu.intrack.model.internship.Website;
import seedu.intrack.model.tag.Tag;

/**
 * Parses input arguments and creates a new {@code AddCommand} object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddCommand}
     * and returns an {@code AddCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_POSITION, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_WEBSITE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_POSITION, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_WEBSITE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Position position = ParserUtil.parsePosition(argMultimap.getValue(PREFIX_POSITION).get());
        Status status = new Status("Progress"); // add command will add an in-progress status by default
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Website website = ParserUtil.parseWebsite(argMultimap.getValue(PREFIX_WEBSITE).get());
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Remark remark = new Remark(""); // add command does not allow adding remarks straight away

        Internship internship = new Internship(name, position, status, phone, email, website, taskList, tagList,
                remark);

        return new AddCommand(internship);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
