package seedu.rc4hdb.logic.parser.commandparsers;

import static java.util.Objects.requireNonNull;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_HOUSE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_MATRIC_NUMBER;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_ROOM;
import static seedu.rc4hdb.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.rc4hdb.logic.commands.modelcommands.FilterCommand;
import seedu.rc4hdb.logic.parser.ArgumentMultimap;
import seedu.rc4hdb.logic.parser.ArgumentTokenizer;
import seedu.rc4hdb.logic.parser.Parser;
import seedu.rc4hdb.logic.parser.ParserUtil;
import seedu.rc4hdb.logic.parser.exceptions.ParseException;
import seedu.rc4hdb.model.resident.ResidentDescriptor;
import seedu.rc4hdb.model.tag.Tag;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ROOM, PREFIX_GENDER,
                        PREFIX_HOUSE, PREFIX_MATRIC_NUMBER, PREFIX_TAG);

        ResidentDescriptor filterResidentDescriptor = new ResidentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            filterResidentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            filterResidentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            filterResidentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ROOM).isPresent()) {
            filterResidentDescriptor.setRoom(ParserUtil.parseRoom(argMultimap.getValue(PREFIX_ROOM).get()));
        }
        if (argMultimap.getValue(PREFIX_GENDER).isPresent()) {
            filterResidentDescriptor.setGender(ParserUtil.parseGender(argMultimap.getValue(PREFIX_GENDER).get()));
        }
        if (argMultimap.getValue(PREFIX_HOUSE).isPresent()) {
            filterResidentDescriptor.setHouse(ParserUtil.parseHouse(argMultimap.getValue(PREFIX_HOUSE).get()));
        }
        if (argMultimap.getValue(PREFIX_MATRIC_NUMBER).isPresent()) {
            filterResidentDescriptor.setMatricNumber(ParserUtil.parseMatricNumber(
                    argMultimap.getValue(PREFIX_MATRIC_NUMBER).get()));
        }
        parseTagsForfilter(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(filterResidentDescriptor::setTags);

        if (!filterResidentDescriptor.isAnyFieldNonNull()) {
            throw new ParseException(FilterCommand.MESSAGE_NOT_FILTERED);
        }

        return new FilterCommand(filterResidentDescriptor);
    }

    private Optional<Set<Tag>> parseTagsForfilter(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
