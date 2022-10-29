package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND_AT_PANEL;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.BackCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteAttributeCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DetailHelpCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ResetCommand;
import seedu.address.logic.commands.SetCommand;
import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.ui.MainPanelName;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final List<CommandFactory> AVAILABLE_COMMANDS = Arrays.asList(
            new CommandFactory(
                    AddCommand.COMMAND_WORD,
                    AddCommand::canExecuteAt,
                    args -> new AddCommandParser().parse(args)),
            new CommandFactory(
                    DeleteCommand.COMMAND_WORD,
                    DeleteCommand::canExecuteAt,
                    args -> new DeleteCommandParser().parse(args)),
            new CommandFactory(
                    DeleteAttributeCommand.COMMAND_WORD,
                    DeleteAttributeCommand::canExecuteAt,
                    args -> new DeleteAttributeCommandParser().parse(args)),
            new CommandFactory(
                    FindCommand.COMMAND_WORD,
                    FindCommand::canExecuteAt,
                    args -> new FindCommandParser().parse(args)),
            new CommandFactory(
                    HelpCommand.COMMAND_WORD,
                    HelpCommand::canExecuteAt,
                    args -> new HelpCommandParser().parse(args)),
            new CommandFactory(
                    DetailHelpCommand.COMMAND_WORD,
                    DetailHelpCommand::canExecuteAt,
                    args -> new DetailHelpCommandParser().parse(args)),
            new CommandFactory(
                    SetCommand.COMMAND_WORD,
                    SetCommand::canExecuteAt,
                    args -> new SetCommandParser().parse(args)),
            new CommandFactory(
                    SortCommand.COMMAND_WORD,
                    SortCommand::canExecuteAt,
                    args -> new SortCommandParser().parse(args)),
            new CommandFactory(
                    ClearCommand.COMMAND_WORD,
                    ClearCommand::canExecuteAt,
                    args -> new ClearCommand()),
            new CommandFactory(
                    ResetCommand.COMMAND_WORD,
                    ResetCommand::canExecuteAt,
                    args -> new ResetCommand()),
            new CommandFactory(
                    BackCommand.COMMAND_WORD,
                    BackCommand::canExecuteAt,
                    args -> new BackCommand()),
            new CommandFactory(
                    ExitCommand.COMMAND_WORD,
                    ExitCommand::canExecuteAt,
                    args -> new ExitCommand())
    );

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, MainPanelName mainPanelName) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        boolean belongsToOtherPanel = false;
        for (CommandFactory commandFactory : AVAILABLE_COMMANDS) {
            if (commandFactory.match(commandWord)) {
                if (commandFactory.canExecuteAt(mainPanelName)) {
                    return commandFactory.build(arguments);
                } else {
                    belongsToOtherPanel = true;
                }
            }
        }

        if (belongsToOtherPanel) {
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND_AT_PANEL);
        }

        throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
    }

    /**
     * A class to build command and check whether command match the user input
     */
    private static class CommandFactory {

        private final String commandWord;
        private final Function<MainPanelName, Boolean> canExecute;
        private final CommandBuilder builder;

        public CommandFactory(String commandWord,
                              Function<MainPanelName, Boolean> canExecute, CommandBuilder commandBuilder) {
            this.commandWord = commandWord;
            this.canExecute = canExecute;
            this.builder = commandBuilder;
        }

        public boolean match(String userCommandWord) {
            return commandWord.equals(userCommandWord);
        }

        public boolean canExecuteAt(MainPanelName name) {
            return this.canExecute.apply(name);
        }

        public Command build(String args) throws ParseException {
            return builder.build(args);
        }
    }

    @FunctionalInterface
    private interface CommandBuilder {
        Command build(String args) throws ParseException;
    }

}
