package friday.model.alias;

import static java.util.Objects.requireNonNull;

import java.util.List;

import friday.logic.commands.AddCommand;
import friday.logic.commands.AliasCommand;
import friday.logic.commands.AliasListCommand;
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
import friday.logic.commands.UnaliasCommand;

/**
 * Represents a ReservedKeyword in friday.
 * Guarantees: immutable; is always valid
 */
public class ReservedKeyword {

    public static final List<String> LIST_RESERVED_KEYWORDS = List.of(AddCommand.COMMAND_WORD,
            AliasCommand.COMMAND_WORD, ClearCommand.COMMAND_WORD, DeleteCommand.COMMAND_WORD, EditCommand.COMMAND_WORD,
            ExitCommand.COMMAND_WORD, FindCommand.COMMAND_WORD, HelpCommand.COMMAND_WORD, ListCommand.COMMAND_WORD,
            MarkMasteryCheckCommand.COMMAND_WORD, RemarkCommand.COMMAND_WORD, SortCommand.COMMAND_WORD,
            UgCommand.COMMAND_WORD, UnaliasCommand.COMMAND_WORD, AliasListCommand.COMMAND_WORD);

    public static final String MESSAGE_CONSTRAINTS = "Reserved keyword given is not in the list of reserved keywords";

    private final String value;

    /**
     * Constructs a {@code ReservedKeyword}.
     *
     * @param reservedKeyword A string reserved keyword
     */
    public ReservedKeyword(String reservedKeyword) {
        requireNonNull(reservedKeyword);
        value = reservedKeyword;
    }

    public static boolean isValidReservedKeyword(ReservedKeyword test) {
        return LIST_RESERVED_KEYWORDS.contains(test.toString());
    }

    public static boolean isValidReservedKeyword(String test) {
        return LIST_RESERVED_KEYWORDS.contains(test);
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
