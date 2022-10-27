package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.LogicManager;
import seedu.address.logic.commands.CheckoutCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.AddressBookFile;

/**
 * Parses input arguments and creates a new CheckoutCommand object
 */
public class CheckoutCommandParser implements Parser<CheckoutCommand> {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    /**
     * Parses the given {@code String} of arguments in the context of the CheckoutCommand
     * and returns an CheckoutCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CheckoutCommand parse(String userInput) throws ParseException {
        AddressBookFile addressBookFile = ParserUtil.parseAddressBookFile(userInput);
        try {
            Path addressBookFilePath = Paths.get("data", addressBookFile + ".json");
            logger.info("----------------[addressBookFilePath][" + addressBookFilePath + "]");
            return new CheckoutCommand(addressBookFilePath);
        } catch (InvalidPathException | NullPointerException e) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CheckoutCommand.MESSAGE_USAGE), e);
        }
    }
}
