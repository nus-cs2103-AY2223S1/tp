package soconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static soconnect.commons.util.CollectionUtil.requireAllNonNull;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static soconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static soconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static soconnect.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.List;

import soconnect.logic.commands.exceptions.CommandException;
import soconnect.logic.parser.Prefix;
import soconnect.model.Model;
import soconnect.model.tag.Tag;

/**
 * Sorts the list of contacts in SoConnect.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Sort the list of contacts displayed by certain parameter(s).\n"
        + "Insert a ! after a / to sort in reverse.\n"
        + "Sort with multiple parameters, succeeding parameters will be used as tiebreakers "
        + "when sorting with preceding parameters.\n"
        + "Parameters: "
        + "[" + PREFIX_NAME + "] "
        + "[" + PREFIX_PHONE + "] "
        + "[" + PREFIX_EMAIL + "] "
        + "[" + PREFIX_ADDRESS + "] "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " " + PREFIX_TAG + "!friend";

    public static final String MESSAGE_SUCCESS = "List has been sorted.";
    public static final String MESSAGE_WRONG_PREFIX = "Invalid parameter(s).";

    private final List<SortArgument> argList;

    /**
     * Constructs a {@code SortCommand} to sort the list of contacts SoConnect.
     */
    public SortCommand(List<SortArgument> argList) {
        requireAllNonNull(argList);
        this.argList = argList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Start sorting from the last argument. Each sort is stable, preserving the previous order.
        for (int i = argList.size() - 1; i >= 0; i--) {
            SortArgument currSort = argList.get(i);
            Prefix prefixParam = currSort.getPrefix();
            boolean isReverse = currSort.isReverse();

            if (prefixParam.equals(PREFIX_NAME)) {
                model.sortByName(isReverse);
            } else if (prefixParam.equals(PREFIX_PHONE)) {
                model.sortByPhone(isReverse);
            } else if (prefixParam.equals(PREFIX_EMAIL)) {
                model.sortByEmail(isReverse);
            } else if (prefixParam.equals(PREFIX_ADDRESS)) {
                model.sortByAddress(isReverse);
            } else if (prefixParam.equals(PREFIX_TAG)) {
                model.sortByTag(currSort.getTag(), isReverse);
            } else {
                throw new CommandException(MESSAGE_WRONG_PREFIX);
            }
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SortCommand)) {
            return false;
        }
        if (argList.size() != ((SortCommand) other).argList.size()) {
            return false;
        }
        boolean result = true;
        for (int i = 0; i < argList.size(); i++) {
            result = argList.get(i).equals(((SortCommand) other).argList.get(i));
            if (result == false) {
                break;
            }
        }
        return result;
    }

    /**
     * Represents an argument for a {@code SortCommand}.
     * Consists of a prefix, whether the sort is reversed, and a tag if the prefix is PREFIX_TAG.
     */
    public static class SortArgument {
        private final Prefix prefix;
        private final boolean isSortingReversed;
        private final Tag tag;

        /**
         * Constructs a {@code SortArgument}.
         *
         * @param prefix            The prefix to use as the parameter.
         * @param isSortingReversed Whether the sorting order should be reversed.
         * @param tag               The tag to sort with, if the prefix is PREFIX_TAG.
         */
        public SortArgument(Prefix prefix, boolean isSortingReversed, Tag tag) {
            this.prefix = prefix;
            this.isSortingReversed = isSortingReversed;
            this.tag = tag;
        }

        public Prefix getPrefix() {
            return prefix;
        }

        public boolean isReverse() {
            return isSortingReversed;
        }

        public Tag getTag() {
            return tag;
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SortArgument)) {
                return false;
            }
            SortArgument otherSort = (SortArgument) other;
            if (tag == null && otherSort.tag != null) {
                return false;
            }
            if (tag == otherSort.tag) {
                return isSortingReversed == otherSort.isSortingReversed && prefix.equals(otherSort.prefix);
            }
            return isSortingReversed == otherSort.isSortingReversed
                && prefix.equals(otherSort.prefix)
                && tag.equals(otherSort.tag);
        }
    }
}
