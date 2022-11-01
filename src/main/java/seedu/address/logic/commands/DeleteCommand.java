package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.FunctionalInterfaces.Changer;
import seedu.address.commons.util.FunctionalInterfaces.Getter;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.item.DisplayItem;

/**
 * Marks a task as complete
 */
public class DeleteCommand<U extends DisplayItem> extends Command {
    public static final String SUBCOMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE =
        "<type> delete: delete the item with the given index\n"
            + "e.g. task delete 1";

    public static final String MESSAGE_DELETE_SUCCESS = "Deleted Object: %s";


    private static final String NUMBER = "\\s*[\\-+]?[0-9]+\\s*";


    private final Index targetIndex;
    private final Getter<U> converter;
    private final Changer<U> deleter;
    private final Predicate<Object> verifier;
    private U toDelete = null;

    /**
     * Constructor to select a task
     */
    public DeleteCommand(Index targetIndex, Getter<U> converter, Changer<U> deleter, Predicate<Object> verifier) {
        requireNonNull(converter);
        this.targetIndex = targetIndex;
        this.converter = converter;
        this.deleter = deleter;
        this.verifier = verifier;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (toDelete == null && targetIndex == null) {
            throw new CommandException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        if (toDelete == null) {
            toDelete = converter.apply(model, targetIndex);
        }
        deleter.apply(model, toDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_SUCCESS, toDelete.toString()));
    }



    /**
     * Returns a parser to parse userinput for select command
     */
    public static <U extends DisplayItem> Parser<DeleteCommand<U>> parser(Getter<U> converter,
        Changer<U> deleter, Predicate<Object> verifier) {

        return new Parser<DeleteCommand<U>>() {
            @Override
            public DeleteCommand<U> parse(String args) throws ParseException {
                if (args == null || args.trim().equals("")) {
                    return new DeleteCommand<U>(null, converter, deleter, verifier);
                } else if (!args.matches(NUMBER)) {
                    throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
                }
                Index index = ParserUtil.parseIndex(args);
                return new DeleteCommand<U>(index, converter, deleter, verifier);
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Override
    public Command setInput(Object additionalData) throws CommandException {
        if (additionalData == null || !verifier.test(additionalData)) {
            return this;
        }
        // this is verified by verifier
        toDelete = (U) additionalData;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj
            || (obj instanceof DeleteCommand
                && ((targetIndex != null && targetIndex.equals(((DeleteCommand<?>) obj).targetIndex))
                    || ((targetIndex == null) && (((DeleteCommand<?>) obj).targetIndex == null)))
                && (((toDelete != null) && (toDelete.equals(((DeleteCommand<?>) obj).toDelete)))
                    || (toDelete == ((DeleteCommand<?>) obj).toDelete)));
    }
}
