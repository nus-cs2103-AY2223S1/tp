package soconnect.ui;

import static soconnect.commons.core.GuiSettings.DEFAULT_ORDER;
import static soconnect.logic.commands.customise.CustomiseCommand.NONE;
import static soconnect.logic.commands.customise.CustomiseCommand.NUMBER_OF_CUSTOMISABLE_ATTRIBUTES;

import java.util.Comparator;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import soconnect.logic.Logic;
import soconnect.model.person.Person;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on SoConnect level 4</a>
     */

    public final Person person;

    private Logic logic;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane attributeA;
    @FXML
    private FlowPane attributeB;
    @FXML
    private FlowPane attributeC;
    @FXML
    private FlowPane attributeD;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, Logic logic) {
        super(FXML);
        this.person = person;
        this.logic = logic;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        setAttributes();
    }

    /**
     * Sets the attributes in the order required.
     */
    private void setAttributes() {
        int[] order = filterAttributes(orderAttributes());
        FlowPane[] flowpanes = new FlowPane[NUMBER_OF_CUSTOMISABLE_ATTRIBUTES + 1];
        flowpanes[order[0]] = attributeA;
        flowpanes[order[1]] = attributeB;
        flowpanes[order[2]] = attributeC;
        flowpanes[order[3]] = attributeD;

        if (flowpanes[0] != null) {
            flowpanes[0].maxWidthProperty().bind(Bindings.add(-100, cardPane.widthProperty()));
            Label address = new Label(person.getAddress().value);
            address.maxWidthProperty().bind(flowpanes[0].maxWidthProperty());
            address.setStyle("-fx-font-size: 12;-fx-font-family: \"Karla\"; -fx-text-fill: #FFDFEA; "
                    + "-fx-wrap-text: true;");
            flowpanes[0].getChildren().add(address);
        }
        if (flowpanes[1] != null) {
            flowpanes[1].maxWidthProperty().bind(Bindings.add(-100, cardPane.widthProperty()));
            Label email = new Label(person.getEmail().value);
            email.maxWidthProperty().bind(flowpanes[0].maxWidthProperty());
            email.setStyle("-fx-font-size: 12;-fx-font-family: \"Karla\"; -fx-text-fill: #FFDFEA; "
                    + "-fx-wrap-text: true;");
            flowpanes[1].getChildren().add(email);
        }
        if (flowpanes[2] != null) {
            flowpanes[2].maxWidthProperty().bind(Bindings.add(-100, cardPane.widthProperty()));
            Label phone = new Label(person.getPhone().value);
            phone.maxWidthProperty().bind(flowpanes[0].maxWidthProperty());
            phone.setStyle("-fx-font-size: 12;-fx-font-family: \"Karla\"; -fx-text-fill: #FFDFEA; "
                    + "-fx-wrap-text: true;");
            flowpanes[2].getChildren().add(phone);
        }
        if (flowpanes[3] != null) {
            person.getTags().stream()
                    .sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> flowpanes[3].getChildren().add((new Label(tag.tagName))));
            flowpanes[3].maxWidthProperty().bind(Bindings.add(-100, cardPane.widthProperty()));
            flowpanes[3].getChildren().forEach(label -> label.setStyle("-fx-background-color: #3142D3;"
                    + "-fx-font-size: 12; -fx-label-padding: 3 7 3 7; -fx-background-radius: 15;"
                    + "-fx-font-family: \"Karla\"; -fx-border-radius: 2;"
                    + "-fx-padding: 1 3 1 3; -fx-text-fill: #FFDFEA; -fx-wrap-text: true;"));
        }
    }

    /**
     * Generates the order of the attributes based on the order set by the user.
     *
     * @return The required order.
     */
    private int[] orderAttributes() {
        int[] order = new int[NUMBER_OF_CUSTOMISABLE_ATTRIBUTES];
        String orderStr = logic.getAttributeOrder();
        String[] orderArr = orderStr.trim().split(">");

        if (orderArr.length != NUMBER_OF_CUSTOMISABLE_ATTRIBUTES) {
            //Returns default order when the orderStr is not in correct format.
            orderStr = DEFAULT_ORDER;
            orderArr = orderStr.trim().split(">");
        }

        try {
            for (int i = 0; i < NUMBER_OF_CUSTOMISABLE_ATTRIBUTES; i++) {
                order[i] = convertToIndex(orderArr[i]);
            }
        } catch (IllegalArgumentException e) {
            //Returns default order when the orderStr is not in correct format.
            orderStr = DEFAULT_ORDER;
            orderArr = orderStr.trim().split(">");
            for (int i = 0; i < NUMBER_OF_CUSTOMISABLE_ATTRIBUTES; i++) {
                order[i] = convertToIndex(orderArr[i]);
            }
        }

        return order;
    }

    /**
     * Filters the attributes based on what attributes were chosen to be hidden by setting the hidden attributes'
     * associated index to the index of the empty last FlowPane element.
     *
     * @param order The order that is unfiltered.
     * @return The order with attributes filtered.
     */
    private int[] filterAttributes(int[] order) {
        boolean[] isHidden = new boolean[NUMBER_OF_CUSTOMISABLE_ATTRIBUTES];
        String currHiddenAttributes = logic.getHiddenAttributes();

        if (!currHiddenAttributes.equals(NONE)) {
            String[] strArr = currHiddenAttributes.trim().split(",");
            try {
                readHidden(strArr, isHidden);
            } catch (IllegalArgumentException e) {
                isHidden = new boolean[NUMBER_OF_CUSTOMISABLE_ATTRIBUTES];
            }
        }

        for (int i = 0; i < NUMBER_OF_CUSTOMISABLE_ATTRIBUTES; i++) {
            //Sets the element in the array to the index of the empty last FlowPane element.
            order[i] = isHidden[order[i]] ? NUMBER_OF_CUSTOMISABLE_ATTRIBUTES : order[i];
        }

        return order;
    }

    /**
     * Reads the string array and sets the array elements to true if attributes are hidden.
     *
     * @param strArr An array of string representations of the attributes.
     * @param isHidden A boolean array where index 0 represents address, index 1 represents email
     *                 index 2 represents phone and index 3 represents tags.
     */
    private void readHidden(String[] strArr, boolean[] isHidden) {
        for (String s : strArr) {
            isHidden[convertToIndex(s)] = true;
        }
    }

    /**
     * Converts the given attribute into the index corresponding to the attribute.
     *
     * @param attribute The string representation of the attribute.
     * @return An index that corresponds to the attribute.
     */
    private int convertToIndex(String attribute) {
        switch(attribute) {
        case "ADDRESS":
            return 0;
        case "EMAIL":
            return 1;
        case "PHONE":
            return 2;
        case "TAGS":
            return 3;
        default:
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PersonCard)) {
            return false;
        }

        // state check
        PersonCard card = (PersonCard) other;
        return id.getText().equals(card.id.getText())
                && person.equals(card.person);
    }
}
