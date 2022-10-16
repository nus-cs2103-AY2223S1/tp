package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.utils.CheckedFunction;
import seedu.address.model.person.*;
import seedu.address.model.server.Server;
import seedu.address.model.tag.Tag;

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

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MINECRAFT_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_SOCIAL, PREFIX_TAG, PREFIX_MINECRAFT_SERVER, PREFIX_TIMEZONE,
                        PREFIX_GAME_TYPE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MINECRAFT_NAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = (Name) parseMandatoryArgument(PREFIX_NAME, argMultimap, ParserUtil::parseName);
        MinecraftName mcName = (MinecraftName) parseMandatoryArgument(PREFIX_MINECRAFT_NAME, argMultimap,
                ParserUtil::parseMinecraftName);
        Phone phone = (Phone) parseOptionalArgument(PREFIX_PHONE, argMultimap, ParserUtil::parsePhone);
        Email email = (Email) parseOptionalArgument(PREFIX_EMAIL, argMultimap, ParserUtil::parseEmail);
        Address address = (Address) parseOptionalArgument(PREFIX_ADDRESS, argMultimap, ParserUtil::parseAddress);
        Set<Social> socialList = ParserUtil.parseSocials(argMultimap.getAllValues(PREFIX_SOCIAL));
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Server> serverList = ParserUtil.parseServers(argMultimap.getAllValues(PREFIX_MINECRAFT_SERVER));
        TimeZone timeZone = (TimeZone) parseOptionalArgument(PREFIX_TIMEZONE, argMultimap, ParserUtil::parseTimeZone);
        Set<GameType> gameTypeList = ParserUtil.parseGameType(argMultimap.getAllValues(PREFIX_GAME_TYPE));

        Person person = new Person(name, mcName, phone, email, address, socialList, tagList, serverList, timeZone,
                gameTypeList);

        return new AddCommand(person);
    }

    private Object parseOptionalArgument(Prefix prefix, ArgumentMultimap argMultimap,
                                         CheckedFunction<String, ?> parserFn) throws ParseException {
        if (argMultimap.getValue(prefix).isPresent()) {
            return parserFn.apply(argMultimap.getValue(prefix).get());
        }
        return null;
    }

    private Object parseMandatoryArgument(Prefix prefix, ArgumentMultimap argMultimap,
                                          CheckedFunction<String, ?> parserFn) throws ParseException {
        return parserFn.apply(argMultimap.getValue(prefix).get());
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
