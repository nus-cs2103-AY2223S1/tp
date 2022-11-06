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

import seedu.rc4hdb.logic.commands.residentcommands.RemoveCommand;
import seedu.rc4hdb.logic.parser.ArgumentMultimap;
import seedu.rc4hdb.logic.parser.ArgumentTokenizer;
import seedu.rc4hdb.logic.parser.Specifier;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.resident.ResidentStringDescriptor;


/**
 * Parses input arguments and creates a new RemoveCommand object
 */
public class RemoveCommandParser implements CommandParser<RemoveCommand> {

    private Specifier specifier;

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveCommand
     * and returns a RemoveCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveCommand parse(String args) throws ParseException {
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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
        }

        ResidentStringDescriptor removeResidentDescriptor = new ResidentStringDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            removeResidentDescriptor.setName(argMultimap.getValue(PREFIX_NAME).get());
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            removeResidentDescriptor.setPhone(argMultimap.getValue(PREFIX_PHONE).get());
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            removeResidentDescriptor.setEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        }
        if (argMultimap.getValue(PREFIX_ROOM).isPresent()) {
            removeResidentDescriptor.setRoom(argMultimap.getValue(PREFIX_ROOM).get());
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            removeResidentDescriptor.setGender(argMultimap.getValue(PREFIX_GENDER).get());
        }
        if (argMultimap.getValue(PREFIX_HOUSE).isPresent()) {
            removeResidentDescriptor.setHouse(argMultimap.getValue(PREFIX_HOUSE).get());
        }
        if (argMultimap.getValue(PREFIX_MATRIC_NUMBER).isPresent()) {
            removeResidentDescriptor.setMatricNumber(argMultimap.getValue(PREFIX_MATRIC_NUMBER).get());
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            removeResidentDescriptor.setTags(new HashSet<>(argMultimap.getAllValues(PREFIX_TAG)));
        }

        if (!removeResidentDescriptor.isAnyFieldNonNull()) {
            throw new ParseException(RemoveCommand.MESSAGE_NOT_REMOVED);
        }

        return new RemoveCommand(removeResidentDescriptor, specifier);
    }
}

