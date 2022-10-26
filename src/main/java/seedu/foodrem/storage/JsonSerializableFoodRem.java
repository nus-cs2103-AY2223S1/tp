package seedu.foodrem.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.ReadOnlyFoodRem;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;

/**
 * An Immutable FoodRem that is serializable to JSON format.
 */
@JsonRootName(value = "foodrem")
class JsonSerializableFoodRem {
    public static final String MESSAGE_DUPLICATE_ITEMS = "Items list contains duplicate item(s).";
    public static final String MESSAGE_DUPLICATE_TAGS = "Tag list contains duplicate tag(s).";

    private final List<JsonAdaptedItem> items = new ArrayList<>();
    private final List<JsonAdaptedTag> tagList = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableFoodRem} with the given items.
     */
    @JsonCreator
    public JsonSerializableFoodRem(@JsonProperty("items") List<JsonAdaptedItem> items,
                                   @JsonProperty("tagList") List<JsonAdaptedTag> tagList) {
        this.items.addAll(items);
        this.tagList.addAll(tagList);
    }

    /**
     * Converts a given {@code ReadOnlyFoodRem} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableFoodRem}.
     */
    public JsonSerializableFoodRem(ReadOnlyFoodRem source) {
        items.addAll(source.getItemList().stream().map(JsonAdaptedItem::new).collect(Collectors.toList()));
        tagList.addAll(source.getTagList().stream().map(JsonAdaptedTag::new).collect(Collectors.toList()));
    }

    /**
     * Converts this FoodRem into the model's {@code FoodRem} object.
     *
     * @throws IllegalArgumentException if there were any data constraints violated.
     */
    public FoodRem toModelType() throws IllegalArgumentException {
        FoodRem foodRem = new FoodRem();
        for (JsonAdaptedItem jsonAdaptedItem : items) {
            Item item = jsonAdaptedItem.toModelType();
            if (foodRem.hasItem(item)) {
                throw new IllegalArgumentException(MESSAGE_DUPLICATE_ITEMS);
            }
            for (Tag tag : item.getTagSet()) {
                if (!foodRem.hasTag(tag)) {
                    foodRem.addTag(tag);
                }
            }
            foodRem.addItem(item);
        }

        for (JsonAdaptedTag jsonAdaptedTag : tagList) {
            Tag tag = jsonAdaptedTag.toModelType();
            if (!foodRem.hasTag(tag)) {
                foodRem.addTag(tag);
            }
        }
        return foodRem;
    }
}
