package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.client.*;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

public class ClientCommandParser implements Parser<ClientCommand> {
    public static final String ADD_COMMAND_FLAG = "-a";
    public static final String EDIT_COMMAND_FLAG = "-e";
    public static final String DELETE_COMMAND_FLAG = "-d";
    public static final String TAG_COMMAND_FLAG = "-t";

    /**
     * Method to parse any commands that have to do with client (start with 'client)
     * @param flag the flag used in the command
     * @param arguments arguments used in the command
     * @return a ClientCommand
     * @throws ParseException
     */
    @Override
    public ClientCommand parse(String flag, String arguments) throws ParseException {
        // Strip needed for current parser (Conrad's one has a space in front)
        // TODO: remove
        switch (flag.strip()) {
            // TODO: Clarify add vs tag - I put this here first so that flags -t = -a
            case ADD_COMMAND_FLAG:
            case TAG_COMMAND_FLAG:
                return parseAddClientCommand(arguments);
            case EDIT_COMMAND_FLAG:
                return parseEditClientCommand(arguments);
            case DELETE_COMMAND_FLAG:
                return parseDeleteClientCommand(arguments);
        }
        System.out.println(flag);
        System.out.println(arguments);
        return null;
    }

    // TODO: revise syntax
    /**
     * Parse a string of arguments for an add client command
     * From original AB3 code
     * @param args string of arguments
     * @return an AddClientCommand object
     * @throws ParseException
     */
    private AddClientCommand parseAddClientCommand(String args) throws ParseException {
        // TODO: This does not support optional arguments
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_PHONE, PREFIX_EMAIL)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddClientCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Person person = new Person(name, phone, email, address, tagList);

        return new AddClientCommand(person);
    }

    // TODO: revise syntax
    /**
     * Parse a string of arguments for an edit client command
     * From original AB3 code
     * @param args a string of arguments
     * @return an EditClientCommand object
     * @throws ParseException
     */
    private EditClientCommand parseEditClientCommand(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditClientCommand.MESSAGE_USAGE), pe);
        }

        EditClientCommand.EditPersonDescriptor editPersonDescriptor = new EditClientCommand.EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditClientCommand.MESSAGE_NOT_EDITED);
        }

        return new EditClientCommand(index, editPersonDescriptor);
    }

    // TODO: revise syntax
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * From original AB3 code
     * @param args string of arguments
     * @return return statement
     * @throws ParseException if the user input does not conform the expected format
     */
    private DeleteClientCommand parseDeleteClientCommand(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeleteClientCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteClientCommand.MESSAGE_USAGE), pe);
        }
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     * Used in AddClientCommand - from original AB3 code
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     * Used in EditClientCommand - from original AB3 code
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }
}
