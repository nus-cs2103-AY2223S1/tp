package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.logic.commands.ListCommand;
import seedu.address.model.category.Category;
import seedu.address.model.person.Address;
import seedu.address.model.person.Gender;
import seedu.address.model.tag.Tag;

/**
 * Parses user input for the list command.
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses user input for the list command.
     *
     * @param args user input, for filtering the list of displayed users
     * @return Filtered list, or list of all users if no filters were specified.
     */
    public ListCommand parse(String args) {
        if (args.length() == 0) {
            return new ListCommand(Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
        }
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_ADDRESS,
                PREFIX_CATEGORY,
                PREFIX_GENDER,
                PREFIX_TAG);

        Optional<Address> address = argMultimap.getValue(PREFIX_ADDRESS).map(Address::new);
        Optional<Tag> tag = argMultimap.getValue(PREFIX_TAG).map(Tag::new);

        boolean[] parametersAreValid = new boolean[]{true};
        List<Optional<Category>> category = new ArrayList<>();
        argMultimap.getValue(PREFIX_CATEGORY).ifPresentOrElse(
                x -> {
                    if (Category.isValidCategoryName(x.toUpperCase())) {
                        category.add(Optional.of(new Category(x.toUpperCase())));
                    } else {
                        category.add(Optional.empty());
                        parametersAreValid[0] = false;
                    }
                }, () -> category.add(Optional.empty()));
        assert (category.size() == 1);

        List<Optional<Gender>> gender = new ArrayList<>();
        argMultimap.getValue(PREFIX_GENDER).ifPresentOrElse(
                x -> {
                    if (Gender.isValidGender(x.toUpperCase())) {
                        gender.add(Optional.of(new Gender(x.toUpperCase())));
                    } else {
                        gender.add(Optional.empty());
                        parametersAreValid[0] = false;
                    }
                }, () -> gender.add(Optional.empty()));
        assert (gender.size() == 1);
        assert (parametersAreValid.length == 1);
        return new ListCommand(address, category.get(0), gender.get(0), tag, parametersAreValid[0]);
    }

}
