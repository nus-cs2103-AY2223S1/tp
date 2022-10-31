package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.util.FunctionalInterfaces.Retriever;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AddressBookParser;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.item.DisplayItem;

/**
 * Marks a task as complete
 */
public class ForEachCommand extends PureCommand {
    public static final String SUBCOMMAND_WORD = "foreach";

    public static final String MESSAGE_USAGE =
        "<type> foreach: for each item in the chosen list, execute subsequent commands with that item as context\n"
            + "e.g. task for each mark";

    private static final String ON_COMPLETE = "Completed task loop! (failed: %d/%d executions)";

    private final Command nextCmd;
    private final Retriever<List<? extends DisplayItem>> retriever;

    /**
     * Constructor for task foreach command
     */
    public ForEachCommand(String nextCmd, Retriever<List<? extends DisplayItem>> retriever) throws ParseException {
        requireNonNull(retriever);
        try {
            this.nextCmd = AddressBookParser.get().parseCommand(nextCmd);
        } catch (ParseException ps) {
            throw new ParseException("Syntax Error: \n" + ps.getMessage());
        }
        this.retriever = retriever;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<? extends DisplayItem> lastShownList = new ArrayList<>(retriever.apply(model));
        int[] skipped = {0, lastShownList.size()};
        lastShownList.forEach(t -> {
            try {
                nextCmd.setInput(t);
                nextCmd.execute(model);
            } catch (CommandException e) {
                skipped[0]++;
            }
        });
        return new CommandResult(String.format(ON_COMPLETE, skipped[0], skipped[1]));
    }


    /**
     * Returns a parser to parse user input for ForEach commands
     *
     * @param retriever SAM to retrieve the list of item from model
     * @return parser of foreach command
     */
    public static Parser<ForEachCommand> parser(Retriever<List<? extends DisplayItem>> retriever) {
        return new Parser<ForEachCommand>() {
            @Override
            public ForEachCommand parse(String userInput) throws ParseException {
                return new ForEachCommand(userInput.trim(), retriever);
            }
        };
    }
}
