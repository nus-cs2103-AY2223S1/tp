package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CustomCommandBuilder;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RenameCommand;
import seedu.address.logic.commands.creationcommand.AliasCommand;
import seedu.address.logic.commands.creationcommand.CreateCommand;
import seedu.address.logic.commands.creationcommand.DeleteCustomCommand;
import seedu.address.logic.commands.creationcommand.ExecuteCommand;
import seedu.address.logic.commands.creationcommand.FloatCommand;
import seedu.address.logic.commands.creationcommand.IntCommand;
import seedu.address.logic.commands.creationcommand.StringCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.fields.FieldCommand;
import seedu.address.logic.commands.logicalcommand.CheckTaskCompleteCommand;
import seedu.address.logic.commands.logicalcommand.CmpCommand;
import seedu.address.logic.commands.logicalcommand.ContainsAttributeCommand;
import seedu.address.logic.commands.logicalcommand.IfCommand;
import seedu.address.logic.commands.logicalcommand.SeqCommand;
import seedu.address.logic.commands.operators.OpsCommand;
import seedu.address.logic.commands.operators.PrintCommand;
import seedu.address.logic.commands.operators.StringReplaceCommand;
import seedu.address.logic.commands.persons.PersonCommand;
import seedu.address.logic.commands.tasks.MarkTaskCommand;
import seedu.address.logic.commands.tasks.TaskCommand;
import seedu.address.logic.commands.tasks.UnmarkTaskCommand;
import seedu.address.logic.commands.teams.AddUserToTeamCommand;
import seedu.address.logic.commands.teams.ChangeTeamCommand;
import seedu.address.logic.commands.teams.TeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.fields.FieldCommandParser;
import seedu.address.logic.parser.logiccommands.CheckTaskCompleteCommandParser;
import seedu.address.logic.parser.logiccommands.ContainsAttributeCommandParser;
import seedu.address.logic.parser.logiccommands.IfCommandParser;
import seedu.address.logic.parser.persons.PersonCommandParser;
import seedu.address.logic.parser.tasks.MarkTaskCommandParser;
import seedu.address.logic.parser.tasks.TaskCommandParser;
import seedu.address.logic.parser.tasks.UnmarkTaskCommandParser;
import seedu.address.logic.parser.teams.TeamCommandParser;

/**
 * Parses user input.
 */
public class AddressBookParser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Pattern NAME_CHECK = Pattern.compile("([a-zA-Z][a-zA-Z0-9_\\-]*)");
    private static AddressBookParser bp = null;
    private static Map<String, ThrowFunction<String, Command>> defaultMapper;

    private final Map<String, CustomCommandBuilder> bonusMapper;
    private final Map<String, String> aliasMapper;

    @FunctionalInterface
    private interface ThrowFunction<T, R> {
        R apply(T t) throws ParseException;
    }

    static {
        defaultMapper = new HashMap<>();
        defaultMapper.put(ClearCommand.COMMAND_WORD, k -> new ClearCommand());
        defaultMapper.put(ListCommand.COMMAND_WORD, k -> new ListCommand());
        defaultMapper.put(ExitCommand.COMMAND_WORD, k -> new ExitCommand());
        defaultMapper.put(HelpCommand.COMMAND_WORD, k -> new HelpCommand());
        defaultMapper.put(ClearCommand.COMMAND_WORD, k -> new ClearCommand());
        defaultMapper.put(ChangeTeamCommand.COMMAND_WORD, k -> new ChangeTeamCommandParser().parse(k));
        defaultMapper.put(TaskCommand.COMMAND_WORD, k -> new TaskCommandParser().parse(k));
        defaultMapper.put(AddUserToTeamCommand.COMMAND_WORD, k -> new AddUserToTeamCommandParser().parse(k));
        defaultMapper.put(TeamCommand.COMMAND_WORD, k -> new TeamCommandParser().parse(k));
        defaultMapper.put(CheckTaskCompleteCommand.COMMAND_WORD, k -> new CheckTaskCompleteCommandParser().parse(k));
        defaultMapper.put(ContainsAttributeCommand.COMMAND_WORD, k -> new ContainsAttributeCommandParser().parse(k));
        defaultMapper.put(IfCommand.COMMAND_WORD, k -> new IfCommandParser().parse(k));
        defaultMapper.put(MarkTaskCommand.SUBCOMMAND_WORD, k -> new MarkTaskCommandParser().parse(k));
        defaultMapper.put(UnmarkTaskCommand.SUBCOMMAND_WORD, k -> new UnmarkTaskCommandParser().parse(k));
        defaultMapper.put(RenameCommand.COMMAND_WORD, k -> new RenameCommandParser().parse(k));
        defaultMapper.put(PersonCommand.COMMAND_WORD, k -> new PersonCommandParser().parse(k));
        defaultMapper.put(FloatCommand.COMMAND_WORD, k -> FloatCommand.parser().parse(k));
        defaultMapper.put(IntCommand.COMMAND_WORD, k -> IntCommand.parser().parse(k));
        defaultMapper.put(StringCommand.COMMAND_WORD, k -> StringCommand.parser().parse(k));
        defaultMapper.put(SeqCommand.COMMAND_WORD, k -> SeqCommand.parser().parse(k));
        defaultMapper.put(OpsCommand.COMMAND_WORD, k -> OpsCommand.parser().parse(k));
        defaultMapper.put(PrintCommand.COMMAND_WORD, k -> PrintCommand.parser().parse(k));
        defaultMapper.put(StringReplaceCommand.COMMAND_WORD, k -> StringReplaceCommand.parser().parse(k));
        defaultMapper.put(CreateCommand.COMMAND_WORD, k -> CreateCommand.parser().parse(k));
        defaultMapper.put(DeleteCustomCommand.COMMAND_WORD, k -> DeleteCustomCommand.parser().parse(k));
        defaultMapper.put(AliasCommand.COMMAND_WORD, k -> AliasCommand.parser().parse(k));
        defaultMapper.put(FieldCommand.COMMAND_WORD, k -> new FieldCommandParser().parse(k));
        defaultMapper.put(CmpCommand.COMMAND_WORD, k -> CmpCommand.parser().parse(k));
        defaultMapper.put(ExecuteCommand.COMMAND_WORD, k -> ExecuteCommand.parser().parse(k));
    }

    private AddressBookParser() {
        bonusMapper = new HashMap<>();
        aliasMapper = new HashMap<>();
    }

    public static AddressBookParser get() {
        if (bp == null) {
            bp = new AddressBookParser();
        }
        return bp;
    }

    /**
     * Checks if the name is a valid command name
     */
    public static boolean isValidName(String test) {
        return NAME_CHECK.matcher(test.trim()).matches();
    }

    /**
     * Checks if the name provided is currently available to be used
     */
    public boolean isKeyAvailable(String key) {
        return !defaultMapper.containsKey(key)
            && !bonusMapper.containsKey(key)
            && !aliasMapper.containsKey(key);
    }

    /**
     * Adds a custom command macro
     */
    public void addCommand(CustomCommandBuilder builder) {
        bonusMapper.put(builder.getRepr(), builder);
    }

    /**
     * Adds an alias to an existing command
     */
    public void addAlias(String alias, String command) {
        if (aliasMapper.containsKey(command)) {
            addAlias(alias, aliasMapper.get(command));
            return;
        }
        if (defaultMapper.containsKey(command) || bonusMapper.containsKey(command)) {
            aliasMapper.put(alias, command);
        }
    }

    public Map<String, String> getAliasMapper() {
        return aliasMapper;
    }

    public Map<String, CustomCommandBuilder> getBonusMapper() {
        return bonusMapper;
    }

    /**
     * Deletes the repr command from parser of it exist.
     */
    public void deleteCommand(String repr) {
        if (repr == null) {
            return;
        }
        if (aliasMapper.containsKey(repr)) {
            aliasMapper.remove(repr);
            return;
        }

        if (bonusMapper.containsKey(repr)) {
            bonusMapper.remove(repr);
        }

        for (String value : aliasMapper.keySet()) {
            if (aliasMapper.get(value).equals(repr)) {
                aliasMapper.remove(value);
            }
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        String commandWord = matcher.group("commandWord").trim();
        final String arguments = matcher.group("arguments");

        while (aliasMapper.containsKey(commandWord)) {
            commandWord = aliasMapper.get(commandWord);
        }
        if (defaultMapper.containsKey(commandWord)) {
            return defaultMapper.get(commandWord).apply(arguments);
        } else if (bonusMapper.containsKey(commandWord)) {
            return bonusMapper.get(commandWord).build();
        }
        throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
    }

    /**
     * Returns a command that runs to execute and parse in ctx as the input
     */
    public static Command quickCommand(String toExecute, Object ctx) throws CommandException {
        try {
            Command ret = AddressBookParser.get().parseCommand(toExecute);
            ret.setInput(ctx);
            return ret;
        } catch (ParseException e) {
            throw new CommandException(e.getMessage());
        }
    }
}
