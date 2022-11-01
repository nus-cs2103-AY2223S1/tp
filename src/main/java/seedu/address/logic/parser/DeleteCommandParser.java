package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {
    private Model model;

    /**
     * Constructs a {@code DeleteCommandParser}
     * @param model the model of the current state
     */
    public DeleteCommandParser(Model model) {
        this.model = model;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        Index index;
        String preamble = args.trim();
        try {
            index = ParserUtil.parseIndex(preamble);
        } catch (ParseException pe) {
            model.filterPersonListByName(preamble, DeleteCommand.MESSAGE_USAGE, pe);
            index = Index.fromOneBased(1);
        }

        return new DeleteCommand(index);
    }
}
