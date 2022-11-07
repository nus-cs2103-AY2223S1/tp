package seedu.rc4hdb.logic.parser.commandparsers;

import static seedu.rc4hdb.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_FILTER_ALL;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_FILTER_ANY;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_HOUSE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.HashSet;

import seedu.rc4hdb.logic.commands.residentcommands.FilterCommand;
import seedu.rc4hdb.logic.parser.ArgumentMultimap;
import seedu.rc4hdb.logic.parser.ArgumentTokenizer;
import seedu.rc4hdb.logic.parser.Specifier;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.resident.ResidentStringDescriptor;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements CommandParser<FilterCommand> {

    private Specifier specifier;

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        assert args != null : "Argument for parser is null";
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROOM, PREFIX_GENDER,
                        PREFIX_HOUSE, PREFIX_MATRIC_NUMBER, PREFIX_TAG, PREFIX_FILTER_ALL, PREFIX_FILTER_ANY);

        if (argMultimap.getValue(PREFIX_FILTER_ANY).isPresent()) {
            specifier = new Specifier("any");
        }
        if (argMultimap.getValue(PREFIX_FILTER_ALL).isPresent()) {
            specifier = new Specifier("all");
        }

        try {
            specifier.getSpecifier();
        } catch (NullPointerException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        ResidentStringDescriptor filterResidentDescriptor = new ResidentStringDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            filterResidentDescriptor.setName(argMultimap.getValue(PREFIX_NAME).get());
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            filterResidentDescriptor.setPhone(argMultimap.getValue(PREFIX_PHONE).get());
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            filterResidentDescriptor.setEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }
        if (argMultimap.getValue(PREFIX_ROOM).isPresent()) {
            filterResidentDescriptor.setRoom(argMultimap.getValue(PREFIX_ROOM).get());
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            filterResidentDescriptor.setGender(argMultimap.getValue(PREFIX_GENDER).get());
        }
        if (argMultimap.getValue(PREFIX_HOUSE).isPresent()) {
            filterResidentDescriptor.setHouse(argMultimap.getValue(PREFIX_HOUSE).get());
        }
        if (argMultimap.getValue(PREFIX_MATRIC_NUMBER).isPresent()) {
            filterResidentDescriptor.setMatricNumber(argMultimap.getValue(PREFIX_MATRIC_NUMBER).get());
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            filterResidentDescriptor.setTags(new HashSet<>(argMultimap.getAllValues(PREFIX_TAG)));
        }
        if (!filterResidentDescriptor.isAnyFieldNonNull()) {
            throw new ParseException(FilterCommand.MESSAGE_NOT_FILTERED);
        }

        return new FilterCommand(filterResidentDescriptor, specifier);
    }
}
