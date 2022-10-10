package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskList;

/**
 * Contains utility methods used to translate user inputs for the AddressBookParser class.
 * Currently, not fully implemented.
 */
public class ParserTranslator {
    public static String translate(String commandWord, String arguments) {
        String[] args = arguments.trim().split("\\s+");
        return Arrays.stream(args).reduce((x, y) -> x + " " + y).get().trim();
    }
}
