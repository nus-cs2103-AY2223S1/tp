package friday.model.alias;

import friday.logic.commands.AddCommand;
import friday.logic.commands.AliasCommand;
import friday.logic.commands.ClearCommand;
import friday.logic.commands.DeleteCommand;
import friday.logic.commands.EditCommand;
import friday.logic.commands.ExitCommand;
import friday.logic.commands.FindCommand;
import friday.logic.commands.HelpCommand;
import friday.logic.commands.ListCommand;
import friday.logic.commands.MarkMasteryCheckCommand;
import friday.logic.commands.RemarkCommand;
import friday.logic.commands.SortCommand;
import friday.logic.commands.UgCommand;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Objects.requireNonNull;

/**
 * Represents a ReservedKeyword in friday.
 * Guarantees: immutable; is always valid
 */
public class ReservedKeyword {

    public final String value;

    private static final String[] ARRAY_RESERVED_KEYWORDS = new String[]{AddCommand.COMMAND_WORD,
            AliasCommand.COMMAND_WORD, ClearCommand.COMMAND_WORD, DeleteCommand.COMMAND_WORD, EditCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD, FindCommand.COMMAND_WORD, HelpCommand.COMMAND_WORD, ListCommand.COMMAND_WORD,
            MarkMasteryCheckCommand.COMMAND_WORD, RemarkCommand.COMMAND_WORD, SortCommand.COMMAND_WORD,
            UgCommand.COMMAND_WORD};

    public static final ArrayList<String> LIST_RESERVED_KEYWORDS = new ArrayList<>(Arrays.
            asList(ARRAY_RESERVED_KEYWORDS));

    public static final String MESSAGE_CONSTRAINTS = "Reserved keyword given is not in the list of reserved keywords";

    /**
     * Constructs a {@code ReservedKeyword}.
     *
     * @param reservedKeyword A string reserved keyword
     */
    public ReservedKeyword(String reservedKeyword) {
        requireNonNull(reservedKeyword);
        value = reservedKeyword;
    }

    public static boolean isValidReservedKeyword(String test) {
        return this.LIST_RESERVED_KEYWORDS.contains(test);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof friday.model.alias.ReservedKeyword // instanceof handles nulls
                && value.equals(((friday.model.alias.ReservedKeyword) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
