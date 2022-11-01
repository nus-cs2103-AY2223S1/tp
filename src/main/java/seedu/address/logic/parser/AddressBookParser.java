package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_WITH_HELP_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_WITH_HELP_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS_FORMAT;

import picocli.CommandLine;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.RootCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.team.Description;
import seedu.address.model.team.Task;
import seedu.address.model.team.TeamName;
import seedu.address.model.team.Url;

/**
 * Parses user input.
 */
public class AddressBookParser {
    private final CommandLine commandLine = new CommandLine(new RootCommand())
            .registerConverter(Name.class, new NameConverter())
            .registerConverter(Email.class, new EmailConverter())
            .registerConverter(Phone.class, new PhoneConverter())
            .registerConverter(Index.class, new IndexConverter())
            .registerConverter(Url.class, new UrlConverter())
            .registerConverter(Task.class, new TaskConverter())
            .registerConverter(TeamName.class, new TeamNameConverter())
            .registerConverter(Description.class, new DescriptionConverter())
            .registerConverter(Order.class, new OrderConverter());

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        try {
            String[] args = ArgumentTokenizer.tokenize(userInput.trim());

            CommandLine.ParseResult parseResult = commandLine.parseArgs(args);
            CommandLine.ParseResult commandExecuted = parseResult.subcommand();

            // Since all commands are defined to be subcommands of RootCommand, there will always be a non-null
            // subcommand inside the parsed result
            assert commandExecuted != null;

            // Deepest subcommand is the actual command executed
            while (commandExecuted.subcommand() != null) {
                commandExecuted = commandExecuted.subcommand();
            }

            return (Command) commandExecuted.commandSpec().userObject();
        } catch (CommandLine.MissingParameterException e) {
            String commandName = e.getCommandLine().getCommandSpec().qualifiedName().trim();
            throw new ParseException(String.format(MESSAGE_MISSING_ARGUMENTS_FORMAT, e.getMessage(), commandName));
        } catch (CommandLine.UnmatchedArgumentException e) {
            String commandName = e.getCommandLine().getCommandSpec().qualifiedName().trim();
            if (commandName.equals("")) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_WITH_HELP_COMMAND);
            }
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_WITH_HELP_FORMAT, commandName));
        } catch (CommandLine.PicocliException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, e.getMessage()));
        }
    }

}
