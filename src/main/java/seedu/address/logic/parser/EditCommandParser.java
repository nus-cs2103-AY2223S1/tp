package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAME_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINECRAFT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINECRAFT_SERVER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_INTERVAL;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.GameType;
import seedu.address.model.person.ITimesAvailable;
import seedu.address.model.person.Server;
import seedu.address.model.person.Social;
import seedu.address.model.person.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {

        requireNonNull(args);

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MINECRAFT_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_SOCIAL, PREFIX_TAG, PREFIX_MINECRAFT_SERVER, PREFIX_COUNTRY,
                        PREFIX_GAME_TYPE, PREFIX_TIME_INTERVAL);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }
        assert index.getZeroBased() > 0 : "index should be more than 0";
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_MINECRAFT_NAME).isPresent()) {
            editPersonDescriptor.setMinecraftName(ParserUtil.parseMinecraftName(
                    argMultimap.getValue(PREFIX_MINECRAFT_NAME).get()
                    )
            );
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
        if (argMultimap.getValue(PREFIX_COUNTRY).isPresent()) {
            editPersonDescriptor.setCountry(ParserUtil.parseCountry(argMultimap.getValue(PREFIX_COUNTRY).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG))
                .ifPresent(editPersonDescriptor::setTags);
        parseSocialsForEdit(argMultimap.getAllValues(PREFIX_SOCIAL))
                .ifPresent(editPersonDescriptor::setSocials);
        parseMinecraftServersForEdit(argMultimap.getAllValues(PREFIX_MINECRAFT_SERVER))
                .ifPresent(editPersonDescriptor::setServers);
        parseGameTypesForEdit(argMultimap.getAllValues(PREFIX_GAME_TYPE))
                .ifPresent(editPersonDescriptor::setGameTypes);
        parseTimeIntervalsForEdit(argMultimap.getAllValues(PREFIX_TIME_INTERVAL))
                .ifPresent(editPersonDescriptor::setTimeIntervals);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    private Optional<Set<Social>> parseSocialsForEdit(Collection<String> socials) throws ParseException {
        assert socials != null;

        if (socials.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> socialSet = socials.size() == 1 && socials.contains("") ? Collections.emptySet() : socials;
        return Optional.of(ParserUtil.parseSocials(socialSet));
    }

    private Optional<Set<Server>> parseMinecraftServersForEdit(Collection<String> servers) throws ParseException {
        assert servers != null;

        if (servers.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> serverSet = servers.size() == 1 && servers.contains("") ? Collections.emptySet() : servers;
        return Optional.of(ParserUtil.parseServers(serverSet));
    }

    private Optional<Set<GameType>> parseGameTypesForEdit(Collection<String> gameTypes) throws ParseException {
        assert gameTypes != null;

        if (gameTypes.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> gameTypesSet =
                gameTypes.size() == 1 && gameTypes.contains("") ? Collections.emptySet() : gameTypes;
        return Optional.of(ParserUtil.parseGameTypes(gameTypesSet));
    }

    private Optional<Set<ITimesAvailable>> parseTimeIntervalsForEdit(Collection<String> timeIntervals)
            throws ParseException {
        assert timeIntervals != null;

        if (timeIntervals.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> timeIntervalSet =
                timeIntervals.size() == 1 && timeIntervals.contains("") ? Collections.emptySet() : timeIntervals;
        return Optional.of(ParserUtil.parseTimeIntervals(timeIntervalSet));
    }
}
