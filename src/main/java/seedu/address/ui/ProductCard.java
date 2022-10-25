package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.model.product.Product;

/**
 * A UI component that displays information of a {@code Product}.
 */
public class ProductCard extends UiPart<Region> {

    private static final String FXML = "ProductListCard.fxml";
    private final Product product;

    @FXML
    private Label id;
    @FXML
    private Label name;

    /**
     * Creates a {@code ProductCard} with the given {@code Product} and index to display.
     */
    public ProductCard(Product product, int displayedIndex) {
        super(FXML);
        this.product = product;
        id.setText(displayedIndex + ". ");
        name.setText(product.getProductName());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProductCard)) {
            return false;
        }

        // state check
        ProductCard card = (ProductCard) other;
        return id.getText().equals(card.id.getText())
                && product.equals(card.product);
    }
}
