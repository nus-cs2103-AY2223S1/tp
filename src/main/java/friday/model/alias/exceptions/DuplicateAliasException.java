package friday.model.alias.exceptions;

/**
 * Signals that the operation will result in duplicate Alias (Aliases are considered duplicates if they have the
 * same identity).
 */
public class DuplicateAliasException extends RuntimeException {
    public DuplicateAliasException() {
        super("Operation would result in duplicate aliases");
    }
}
