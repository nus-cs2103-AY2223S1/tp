package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.product.Product;

/**
 * Panel containing the list of products.
 */
public class ProductListPanel extends ListPanel {
    private static final String FXML = "ProductListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ProductListPanel.class);

    @FXML
    private ListView<Product> productListView;

    @FXML
    private Label numProducts;

    /**
     * Creates a {@code ProductListPanel} with the given {@code ObservableList}.
     */
    public ProductListPanel(ObservableList<Product> productList) {
        super(FXML);
        productListView.setItems(productList);
        productListView.setCellFactory(listView -> new ProductListViewCell());
        numProducts.setText(numRecordsString(productList));
        productList.addListener((ListChangeListener<? super Product>)
                c -> numProducts.setText(numRecordsString(productList)));
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Product} using a {@code ProductCard}.
     */
    class ProductListViewCell extends ListCell<Product> {
        @Override
        protected void updateItem(Product product, boolean empty) {
            super.updateItem(product, empty);

            if (empty || product == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ProductCard(product, getIndex() + 1).getRoot());
            }
        }
    }

}
