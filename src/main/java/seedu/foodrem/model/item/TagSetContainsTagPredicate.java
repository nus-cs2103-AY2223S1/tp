package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.foodrem.model.tag.Tag;

/**
 * Tests that a {@code Item}'s {@code TagSet} contains the specified {@code tag}
 */
public class TagSetContainsTagPredicate implements Predicate<Item> {
    private final Tag tag;

    /**
     * @param tag Checks if tag exists in the item's tagSet
     */
    public TagSetContainsTagPredicate(Tag tag) {
        requireNonNull(tag);
        this.tag = tag;
    }

    @Override
    public boolean test(Item item) {
        return item.getTagSet().contains(tag);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TagSetContainsTagPredicate
                && tag.equals(((TagSetContainsTagPredicate) other).tag));
    }

    // TODO: Refactor and remove accessor
    public Tag getTag() {
        return this.tag;
    }
}
