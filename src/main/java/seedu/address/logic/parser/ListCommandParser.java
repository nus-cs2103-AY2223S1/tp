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

    private boolean parametersAreValid = true;

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
        parametersAreValid = true;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_ADDRESS,
                PREFIX_CATEGORY,
                PREFIX_GENDER,
                PREFIX_TAG);

        Optional<Address> address = getFilteredAddress(argMultimap);
        Optional<Tag> tag = getFilteredTag(argMultimap);
        Optional<Category> category = getFilteredCategory(argMultimap);
        Optional<Gender> gender = getFilteredGender(argMultimap);

        return new ListCommand(address, category, gender, tag, parametersAreValid);
    }

    private Optional<Tag> getFilteredTag(ArgumentMultimap argumentMultimap) {

        List<Optional<Tag>> tag = new ArrayList<>();
        argumentMultimap.getValue(PREFIX_TAG).ifPresentOrElse(
                x -> {
                    if (Address.isValidAddress(x)) {
                        tag.add(Optional.of(new Tag(x)));
                    } else {
                        tag.add(Optional.empty());
                        parametersAreValid = false;
                    }
                }, () -> tag.add(Optional.empty()));
        assert (tag.size() == 1);
        return tag.get(0);
    }

    private Optional<Address> getFilteredAddress(ArgumentMultimap argumentMultimap) {

        List<Optional<Address>> address = new ArrayList<>();
        argumentMultimap.getValue(PREFIX_ADDRESS).ifPresentOrElse(
                x -> {
                    if (Address.isValidAddress(x)) {
                        address.add(Optional.of(new Address(x)));
                    } else {
                        address.add(Optional.empty());
                        parametersAreValid = false;
                    }
                }, () -> address.add(Optional.empty()));
        assert (address.size() == 1);
        return address.get(0);
    }

    private Optional<Category> getFilteredCategory(ArgumentMultimap argumentMultimap) {

        List<Optional<Category>> category = new ArrayList<>();
        argumentMultimap.getValue(PREFIX_CATEGORY).ifPresentOrElse(
                x -> {
                    if (Category.isValidCategoryName(x.toUpperCase())) {
                        category.add(Optional.of(new Category(x.toUpperCase())));
                    } else {
                        category.add(Optional.empty());
                        parametersAreValid = false;
                    }
                }, () -> category.add(Optional.empty()));
        assert (category.size() == 1);
        return category.get(0);
    }

    private Optional<Gender> getFilteredGender(ArgumentMultimap argumentMultimap) {

        List<Optional<Gender>> gender = new ArrayList<>();
        argumentMultimap.getValue(PREFIX_GENDER).ifPresentOrElse(
                x -> {
                    if (Gender.isValidGender(x.toUpperCase())) {
                        gender.add(Optional.of(new Gender(x.toUpperCase())));
                    } else {
                        gender.add(Optional.empty());
                        parametersAreValid = false;
                    }
                }, () -> gender.add(Optional.empty()));
        assert (gender.size() == 1);
        return gender.get(0);
    }

}
