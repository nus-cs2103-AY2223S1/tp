package seedu.foodrem.ui;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import seedu.foodrem.model.item.Item;
import seedu.foodrem.model.tag.Tag;
import seedu.foodrem.views.ItemView;

/**
 * A UI component that displays information of a {@code Item}.
 */
public class ItemCard extends UiPart<Region> {
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Item item;

    @FXML private Label name;
    @FXML private Label id;
    @FXML private Label quantity;
    @FXML private Label price;
    @FXML private Label bought;
    @FXML private Label expires;
    @FXML private HBox tags;

    /**
     * Creates a {@code ItemCode} with the given {@link Item} and index to display.
     */
    public ItemCard(Item item, int displayedIndex) {
        super("ItemListCard.fxml");
        this.item = item;
        id.setText(displayedIndex + ".");
        name.setText(item.getName().toString());
        price.setText("$" + item.getPrice());
        bought.setText(String.format("Bought: %s", ItemView.buildBoughtDateStringFrom(item)));
        expires.setText(String.format("Expires: %s", ItemView.buildExpiryDateStringFrom(item)));

        quantity.setText(ItemView.buildItemQuantityAndUnitStringFrom(item));
        quantity.setTextAlignment(TextAlignment.RIGHT);

        // TODO: Abstract this somewhere else
        Set<Tag> tagSet = item.getTagSet();
        if (tagSet.isEmpty()) {
            Label noTags = new Label("-");
            tags.getChildren().add(noTags);
        }

        List<Tag> tagList = tagSet.stream().sorted(Comparator.comparing(Tag::getName)).collect(Collectors.toList());

        int maxChars = 45;
        int currChars = 0;
        int numberOfTags = 0;
        for (Tag tag : tagList) {
            String tagName = tag.getName();
            int newCurr = currChars + tagName.length();
            if (newCurr > maxChars) {
                break;
            }
            currChars = newCurr;
            numberOfTags += 1;
            tags.getChildren().add(buildTagNodeFrom(tagName));
        }

        int restOfTags = tagSet.size() - numberOfTags;
        if (restOfTags > 0) {
            tags.getChildren().add(new Label("..."));
        }

        tags.setMaxWidth(400);
        name.setMaxWidth(240);
    }

    private static Node buildTagNodeFrom(String tagName) {
        final Label label = new Label(tagName);
        label.getStyleClass().add("item-listview-tag");
        return label;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ItemCard)) {
            return false;
        }

        ItemCard card = (ItemCard) other;
        return id.getText().equals(card.id.getText())
                && item.equals(card.item);
    }
}
