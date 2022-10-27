package seedu.foodrem.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.foodrem.logic.commands.itemcommands.NewCommandTest.ModelStub;
import static seedu.foodrem.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.foodrem.logic.commands.CommandResult;
import seedu.foodrem.logic.commands.exceptions.CommandException;
import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.testutil.TagBuilder;
import seedu.foodrem.viewmodels.TagsWithMessage;

public class NewTagCommandTest {
    private static final String EXPECTED_ERROR_DUPLICATE = "This tag already exists in FoodRem";
    private static final String EXPECTED_FORMAT_SUCCESS = "New tag added:";

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NewTagCommand(null));
    }

    @Test
    public void execute_tagAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTagAdded modelStub = new ModelStubAcceptingTagAdded();
        Tag validTag = new TagBuilder().build();

        CommandResult<TagsWithMessage> commandResult = new NewTagCommand(validTag).execute(modelStub);

        TagsWithMessage expectedResult = new TagsWithMessage(List.of(validTag) , EXPECTED_FORMAT_SUCCESS);

        assertEquals(expectedResult, commandResult.getOutput());
        assertEquals(List.of(validTag), modelStub.tagsAdded);
    }

    @Test
    public void execute_duplicateItem_throwsCommandException() {
        Tag validTag = new TagBuilder().build();
        NewTagCommand newTagCommand = new NewTagCommand(validTag);
        ModelStub modelStub = new ModelStubWithTag(validTag);

        assertThrows(CommandException.class,
                EXPECTED_ERROR_DUPLICATE, () -> newTagCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Tag fruitsTag = new TagBuilder().withTagName("fruits").build();
        Tag vegetableTag = new TagBuilder().withTagName("vegetable").build();

        NewTagCommand addFruitsTag = new NewTagCommand(fruitsTag);
        NewTagCommand addVegetableTag = new NewTagCommand(vegetableTag);

        // same object -> returns true
        assertEquals(addFruitsTag, addFruitsTag);

        // same values -> returns true
        NewTagCommand addFruitsTagCopy = new NewTagCommand(fruitsTag);
        assertEquals(addFruitsTag, addFruitsTagCopy);

        // different types -> returns false
        assertNotEquals(1, addFruitsTag);

        // null -> returns false
        assertNotEquals(null, addFruitsTag);

        // different item -> returns false
        assertNotEquals(addFruitsTag, addVegetableTag);
    }

    /**
     * A Model stub that contains a single item.
     */
    private static class ModelStubWithTag extends ModelStub {
        private final Tag tag;

        ModelStubWithTag(Tag tag) {
            requireNonNull(tag);
            this.tag = tag;
        }

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return this.tag.equals(tag);
        }
    }

    /**
     * A Model stub that always accept the item being added.
     */
    private static class ModelStubAcceptingTagAdded extends ModelStub {
        final ArrayList<Tag> tagsAdded = new ArrayList<>();

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return tagsAdded.stream().anyMatch(tag::equals);
        }

        @Override
        public void addTag(Tag tag) {
            requireNonNull(tag);
            tagsAdded.add(tag);
        }

        @Override
        public ReadOnlyFoodRem getFoodRem() {
            return new FoodRem();
        }
    }
}
