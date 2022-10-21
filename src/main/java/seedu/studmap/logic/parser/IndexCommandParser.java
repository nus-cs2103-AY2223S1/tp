package seedu.studmap.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.studmap.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.studmap.logic.parser.ParserUtil.separatePreamble;

import java.util.List;

import seedu.studmap.commons.core.index.AllIndexGenerator;
import seedu.studmap.commons.core.index.Index;
import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.commons.core.index.SingleIndexGenerator;
import seedu.studmap.logic.commands.Command;
import seedu.studmap.logic.parser.exceptions.ParseException;

/**
 * An interface that provides a framework for a parser for commands that will need to manipulate
 * a number of students based on their indices.
 *
 * @param <T> Command type that this parser will parse.
 */
public interface IndexCommandParser<T extends Command> extends Parser<T> {

    String ALL_INDEX = "all";

    /**
     * Returns an array of prefixes that will tokenize the arguments string.
     */
    Prefix[] getPrefixes();

    /**
     * Returns the usage message for the command to be produced.
     */
    String getUsageMessage();

    /**
     * Parses given arguments and returns a command.
     * The default implementation will look for an index (and in the case of the keyword 'all',
     * it returns a function that produces all the available indices.
     *
     * @param args Command arguments
     * @return Command for execution
     * @throws ParseException if any errors arise when parsing this command
     */
    default T parse(String args) throws ParseException {
        requireNonNull(args);

        Prefix[] prefixes = getPrefixes();
        requireNonNull(prefixes);
        requireAllNonNull(List.of(prefixes));

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, prefixes);

        Index index;
        try {
            String preamble = argMultimap.getPreamble();
            String[] preambleArgs = separatePreamble(preamble);

            validatePreamble(preamble);

            if (preambleArgs[0].equalsIgnoreCase(ALL_INDEX)) {
                return getIndexCommand(argMultimap, new AllIndexGenerator());
            }

            index = ParserUtil.parseIndex(preambleArgs[0]);

        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, getUsageMessage()), pe);
        }

        return getIndexCommand(argMultimap, new SingleIndexGenerator(index));
    }

    /**
     * Produces a command promised by this interface. This is called in the `parse` method to generate the command
     * after automatic tokenization.
     *
     * @param argMultimap        Argument multimap to process
     * @param indexListGenerator students affected by this command, wrapped in a function to be used later.
     * @throws ParseException if any errors arise when parsing this command.
     */
    T getIndexCommand(ArgumentMultimap argMultimap,
                      IndexListGenerator indexListGenerator) throws ParseException;

    /**
     * Validates a given preamble.
     *
     * @throws ParseException if preamble is invalid.
     */
    void validatePreamble(String preamble) throws ParseException;

}
