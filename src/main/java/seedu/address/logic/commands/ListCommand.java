package seedu.address.logic.commands;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.category.Category;
import seedu.address.model.person.Address;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Lists all nurses and patients enrolled in the healthcareXpress database to the user,
 * filtered by given specifications.
 */
public class ListCommand extends Command {

    public static final String MESSAGE_ARGUMENTS = "ADDRESS: %s, CATEGORY: %s, GENDER: %s, TAG: %s\n";

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons with specifications:\n" + MESSAGE_ARGUMENTS;

    public static final String MESSAGE_INVALID_PARAMETERS_IGNORED =
            "WARNING: One or more invalid input parameters were ignored\n.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all enrolled users who fit the specified criteria, "
            + "or all enrolled users if no criteria were specified.\n"
            + "Parameters: \n"
            + "<optional> c/ [CATEGORY]\n"
            + "<optional> g/ [GENDER]\n"
            + "<optional> a/ [ADDRESS]\n"
            + "<optional> t/ [TAG]\n"
            + "Example: " + COMMAND_WORD + "  "
            + "c/ n";

    private final Optional<Address> address;
    private final Optional<Category> category;
    private final Optional<Gender> gender;
    private final Optional<Tag> tag;
    private final boolean parametersAreValid;

    /**
     * @param a address to be filtered
     * @param c category (nurse/patient) to be filtered
     * @param g gender to be filtered
     * @param t tag to be filtered
     */
    public ListCommand(Optional<Address> a, Optional<Category> c, Optional<Gender> g, Optional<Tag> t) {
        address = a;
        category = c;
        gender = g;
        tag = t;
        parametersAreValid = true;
    }

    /**
     * @param a address to be filtered
     * @param c category (nurse/patient) to be filtered
     * @param g gender to be filtered
     * @param t tag to be filtered
     * @param p true if all parameters are valid, false if one or more are invalid.
     */
    public ListCommand(Optional<Address> a, Optional<Category> c, Optional<Gender> g, Optional<Tag> t, boolean p) {
        address = a;
        category = c;
        gender = g;
        tag = t;
        parametersAreValid = p;
    }

    @Override
    public CommandResult execute(Model model) {
        applyFilter(model);

        String filteredAddress = getFilteredAddress();
        String filteredGender = getFilteredGender();
        String filteredCategory = getFilteredCategory();
        String filteredTag = getFilteredTag();

        String result = String.format(MESSAGE_SUCCESS,
                filteredAddress,
                filteredCategory,
                filteredGender,
                filteredTag);
        if (!parametersAreValid) {
            result += MESSAGE_INVALID_PARAMETERS_IGNORED;
        }
        return new CommandResult(result);
    }

    private String getFilteredAddress() {
        return address.orElse(new Address("NIL")).value;
    }

    private String getFilteredGender() {
        final String[] filteredGender = new String[1];
        gender.ifPresentOrElse(x -> filteredGender[0] = x.gender, () -> filteredGender[0] = "NIL");
        return filteredGender[0];
    }

    private String getFilteredCategory() {
        final String[] filteredCategory = new String[1];
        category.ifPresentOrElse(x -> filteredCategory[0] = x.categoryName, () -> filteredCategory[0] = "NIL");
        return filteredCategory[0];
    }

    private String getFilteredTag() {
        return tag.orElse(new Tag("NIL")).tagName;
    }

    private void applyFilter(Model model) {
        Predicate<Person> addressMatch = x -> x.getAddress().value.toLowerCase()
                .contains(address.orElse(x.getAddress()).value.toLowerCase());
        Predicate<Person> categoryMatch = x -> x.getCategory().equalsIgnoreCase(category.orElse(x.getCategory()));
        Predicate<Person> genderMatch = x -> x.getGender().equalsIgnoreCase(gender.orElse(x.getGender()));
        Predicate<Person> tagMatch = x -> {
            if (x.getTags().isEmpty()) {
                return tag.isEmpty();
            } else {
                Predicate<Tag> tagPredicate = y -> {
                    Tag tagToCompare = tag.orElse((Tag) x.getTags().toArray()[0]);
                    return y.equals(tagToCompare);
                };
                return x.getTags().stream().anyMatch(tagPredicate);
            }
        };
        Predicate<Person> predicate = addressMatch
                .and(categoryMatch)
                .and(genderMatch)
                .and(tagMatch);
        model.updateFilteredPersonList(predicate);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListCommand)) {
            return false;
        }

        // state check
        ListCommand e = (ListCommand) other;
        return address.equals(e.address)
                && category.equals(e.category)
                && gender.equals(e.gender)
                && tag.equals(e.tag);
    }
}
