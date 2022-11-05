package foodwhere.ui;

import foodwhere.model.stall.Stall;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Stall}.
 * Will be updated to a Stall for later iterations.
 */
public class StallCard extends UiPart<Region> {

    private static final String FXML = "StallListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Stall stall;

    @FXML
    private GridPane gridPane;
    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label address;
    @FXML
    private Label tags;
    @FXML
    private Label tagsLabel;

    /**
     * Creates a {@code StallCode} with the given {@code Stall} and index to display.
     */
    public StallCard(Stall stall, int displayedIndex) {
        super(FXML);
        this.stall = stall;
        id.setText(displayedIndex + ". ");
        name.setText(stall.getName().fullName);
        address.setText(stall.getAddress().value);

        if (!stall.getTags().isEmpty()) {
            tags.setText(stall.getTagString());
            tagsLabel.setText("Tags:");
        } else {
            removeRow(gridPane, GridPane.getRowIndex(tagsLabel));
        }
    }

    //@@author hikoya-reused
    /**
     * Adapted from https://stackoverflow.com/a/70961583.
     * Gets row index constrain for given node, forcefully as integer: 0 as null.
     * @param node Node to look up the constraint for.
     * @return The row index as primitive integer.
     */
    public static int getRowIndexAsInteger(Node node) {
        final var a = GridPane.getRowIndex(node);
        if (a == null) {
            return 0;
        }
        return a;
    }
    //@@author hikoya

    //@@author hikoya-reused
    /**
     * Adapted from https://stackoverflow.com/a/70961583.
     * Removes row from grid pane by index.
     *
     * @param grid Grid pane to be affected
     * @param targetRowIndexIntegerObject Target row index to be removed. Integer object type,
     *                                    because for some reason `getRowIndex` returns null
     *                                    for children at 0th row.
     */
    private void removeRow(GridPane grid, Integer targetRowIndexIntegerObject) {
        int targetRowIndex = targetRowIndexIntegerObject == null ? 1 : targetRowIndexIntegerObject;

        // Remove children from row
        grid.getChildren().removeIf(node -> (getRowIndexAsInteger(node) == targetRowIndex));

        // Update indexes of other rows, i.e., shift rows up
        grid.getChildren().forEach(node -> {
            int rowIndex = getRowIndexAsInteger(node);

            if (targetRowIndex < rowIndex) {
                GridPane.setRowIndex(node, rowIndex - 1);
            }
        });

        // Remove row constraints
        grid.getRowConstraints().remove(targetRowIndex);
    }
    //@@author


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StallCard)) {
            return false;
        }

        // state check
        StallCard card = (StallCard) other;
        return id.getText().equals(card.id.getText())
                && stall.equals(card.stall);
    }
}
