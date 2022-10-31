package seedu.address.logic.parser;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ListCommand;
import seedu.address.model.category.Category;
import seedu.address.model.person.Address;
import seedu.address.model.person.Gender;
import seedu.address.model.tag.Tag;

public class ListCommandParserTest {

    @Test
    public void parse_noFiltersApplied_listAll() {
        Command expectedCommand = new ListCommand(Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty());
        Command actualCommand = new ListCommandParser().parse("");
        assert (actualCommand.equals(expectedCommand));
    }

    @Test
    public void parse_addressFilterApplied_listAddress() {
        Command expectedCommand = new ListCommand(Optional.of(new Address("Jurong")), Optional.empty(),
                Optional.empty(), Optional.empty());
        Command actualCommand = new ListCommandParser().parse("list a/ Jurong");
        assert (actualCommand.equals(expectedCommand));
    }

    @Test
    public void parse_categoryFilterApplied_listCategory() {
        Command expectedCommand = new ListCommand(Optional.empty(), Optional.of(new Category(Category.PATIENT_SYMBOL)),
                Optional.empty(), Optional.empty());
        Command actualCommand = new ListCommandParser().parse("list c/ P");
        assert (actualCommand.equals(expectedCommand));
    }

    @Test
    public void parse_genderFilterApplied_listGender() {
        Command expectedCommand = new ListCommand(Optional.empty(), Optional.empty(),
                Optional.of(new Gender(Gender.FEMALE_SYMBOL)), Optional.empty());
        Command actualCommand = new ListCommandParser().parse("list g/ F");
        assert (actualCommand.equals(expectedCommand));
    }

    @Test
    public void parse_tagFilterApplied_listTag() {
        Command expectedCommand = new ListCommand(Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.of(new Tag("friends")));
        Command actualCommand = new ListCommandParser().parse("list t/ friends");
        assert (actualCommand.equals(expectedCommand));
    }

    @Test
    public void parse_allFiltersApplied_listAllFilters() {
        Command expectedCommand = new ListCommand(Optional.of(new Address("Jurong")),
                Optional.of(new Category(Category.PATIENT_SYMBOL)),
                Optional.of(new Gender(Gender.FEMALE_SYMBOL)),
                Optional.of(new Tag("friends")));
        Command actualCommand = new ListCommandParser().parse("list a/Jurong c/ P g/ F t/ friends");

        assert (actualCommand.equals(expectedCommand));
    }
}
