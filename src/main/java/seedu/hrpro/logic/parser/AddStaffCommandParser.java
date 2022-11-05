package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_CONTACT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_DEPARTMENT;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_LEAVE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_NAME;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_STAFF_TITLE;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;
import java.util.stream.Stream;

import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.AddStaffCommand;
import seedu.hrpro.logic.parser.exceptions.ParseException;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.staff.StaffContact;
import seedu.hrpro.model.staff.StaffDepartment;
import seedu.hrpro.model.staff.StaffLeave;
import seedu.hrpro.model.staff.StaffName;
import seedu.hrpro.model.staff.StaffTitle;
import seedu.hrpro.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddStaffCommand object
 */
public class AddStaffCommandParser implements Parser<AddStaffCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStaffCommand
     * and returns an AddStaffCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStaffCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_STAFF_CONTACT, PREFIX_STAFF_NAME, PREFIX_STAFF_DEPARTMENT,
                        PREFIX_STAFF_LEAVE, PREFIX_STAFF_TITLE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_STAFF_CONTACT, PREFIX_STAFF_NAME, PREFIX_STAFF_DEPARTMENT,
                PREFIX_STAFF_LEAVE, PREFIX_STAFF_TITLE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStaffCommand.MESSAGE_USAGE));
        }

        StaffContact staffContact =
                ParserUtil.parseStaffContact(argMultimap.getValue(PREFIX_STAFF_CONTACT).get());
        StaffDepartment staffDepartment =
                ParserUtil.parseStaffDepartment(argMultimap.getValue(PREFIX_STAFF_DEPARTMENT).get());
        StaffLeave staffLeave =
                ParserUtil.parseStaffLeave(argMultimap.getValue(PREFIX_STAFF_LEAVE).get());
        StaffName staffName =
                ParserUtil.parseStaffName(argMultimap.getValue(PREFIX_STAFF_NAME).get());
        StaffTitle staffTitle =
                ParserUtil.parseStaffTitle(argMultimap.getValue(PREFIX_STAFF_TITLE).get());

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        // do staff creation
        Staff staff = new Staff(staffName, staffContact, staffTitle, staffDepartment, staffLeave, tagList);
        // do project name parsing
        // return new add staff command
        return new AddStaffCommand(staff, index);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}
