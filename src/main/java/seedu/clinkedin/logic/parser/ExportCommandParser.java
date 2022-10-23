package seedu.clinkedin.logic.parser;

import static seedu.clinkedin.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.clinkedin.logic.parser.CliSyntax.PREFIX_PATH;

import java.util.stream.Stream;

import seedu.clinkedin.logic.commands.ExportCommand;
import seedu.clinkedin.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new ExportCommand object.
 */
public class ExportCommandParser implements Parser<ExportCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of ExportCommand and returns an ExportCommand object
     * for execution.
     * @throws ParseException If the user input does not conform the expected format.
     */
    public ExportCommand parse(String args) throws ParseException {
        if (args.trim().length() == 0) {
            return new ExportCommand();
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.getPrefixes());
        if (!arePrefixesPresentAndUnique(argMultimap, PREFIX_PATH)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ExportCommand.MESSAGE_USAGE));
        }
        String filePath = argMultimap.getValue(PREFIX_PATH).get().trim();
        ParserUtil.FileType fileType = ParserUtil.getFileType(argMultimap.getValue(PREFIX_PATH).get().trim());
        return new ExportCommand(filePath, fileType);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} vslues in the given
     * {@code ArgumentMultimap} and all prefixes occur exactly once.
     */
    private static boolean arePrefixesPresentAndUnique(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent()
                && argumentMultimap.getAllValues(prefix).size() == 1);
    }
}
