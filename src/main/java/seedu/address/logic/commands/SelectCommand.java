package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.FunctionalInterfaces.Getter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.item.DisplayItem;

/**
 * Marks a task as complete
 */
public class SelectCommand extends PureCommand {
    public static final String SUBCOMMAND_WORD = "select";

    public static final String MESSAGE_USAGE =
        "<type> select: selects an item and execute subsequent commands with that item as context\n"
            + "e.g. task select 1 contains description";

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<index>[0-9]+)\\s+(?<commands>.*)");

    private final Index targetIndex;
    private final Command nextCmd;
    private final Getter<DisplayItem> converter;

    /**
     * Constructor to select a task
     */
    public SelectCommand(Index targetIndex, String nextCmd, Getter<DisplayItem> converter) throws ParseException {
        requireNonNull(converter);
        this.targetIndex = targetIndex;
        this.converter = converter;
        try {
            this.nextCmd = AddressBookParser.get().parseCommand(nextCmd);
        } catch (ParseException ps) {
            throw new ParseException("Syntax Error: \n" + ps.getMessage());
        }
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        nextCmd.setInput(converter.apply(model, targetIndex));
        return nextCmd.execute(model);
    }

    /**
     * Returns a parser to parse userinput for select command
     */
    public static Parser<SelectCommand> parser(Getter<DisplayItem> fun) {
        return new Parser<SelectCommand>() {
            @Override
            public SelectCommand parse(String args) throws ParseException {
                final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());

                if (!matcher.matches()) {
                    throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
                }
                Index index = ParserUtil.parseIndex(matcher.group("index"));
                return new SelectCommand(index, matcher.group("commands"), fun);
            }
        };
    }
}
