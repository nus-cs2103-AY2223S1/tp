package tracko.logic.parser;

import tracko.logic.commands.ClearCommand;
import tracko.logic.parser.exceptions.ParseException;

/**
 * Parses confirmation input from user to allow MultiLevelCommand implementation of the ClearCommand class
 */
public class ClearCommandParser implements Parser<ClearCommand> {
    public static final String INCORRECT_USER_INPUT_FORMAT = "Please enter 'confirm' to confirm removal of all data. "
            + "Enter 'cancel' to abort clearing data. ";

    @Override
    public ClearCommand parse(String userInput) throws ParseException {
        return new ClearCommand();
    }

    /**
     * Parses the given {@code String} of confirmation argument for the given ClearCommand that is awaiting
     * further input of user confirmation.
     * @param args The user input
     * @param command The given ClearCommand
     * @return The updated command.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ClearCommand parseAndUpdate(String args, ClearCommand command) throws ParseException {
        if (args.equals("confirm")) {
            command.setAwaitingInput(false);
            return command;
        } else if (args.equals("cancel")) {
            command.setAwaitingInput(false);
            command.cancel();
            return command;
        } else {
            throw new ParseException(INCORRECT_USER_INPUT_FORMAT);
        }
    }
}
