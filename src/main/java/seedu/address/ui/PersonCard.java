package seedu.address.ui;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagType;
import seedu.address.model.tag.UniqueTagList;

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
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private GridPane tagPane;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        // update tags
        //        person.getTags().keySet().stream()
        //                .sorted(Comparator.comparing(tagType -> tagType.getTagTypeName()))
        //                .forEach(tagType ->
        //                {
        //                    Region newline = new Region();
        //                    newline.setPrefSize(Double.MAX_VALUE, 0.0);
        //                    person.getTags().get(tagType).toStream().forEach(tag -> tags.getChildren()
        //                        .add(new Label(tag.tagName)));
        //                    tags.getChildren().add(newline);
        //                });
        ObservableMap<TagType, UniqueTagList> tagTypeMap = person.getTags();
        List<TagType> tagTypeList = tagTypeMap.keySet().stream()
                .sorted(Comparator.comparing(tagType -> tagType.getTagTypeName())).collect(Collectors.toList());
        for (int i = 0; i < tagTypeList.size(); i++) {
            tagPane.add(new Label(tagTypeList.get(i).getTagTypeName()), 0, i);
            int idx = 1;
            for (Tag tag: tagTypeMap.get(tagTypeList.get(i))) {
                tagPane.add(new Label(tag.tagName), idx, i);
                idx += 1;
            }
        }
        tagPane.setHgap(5);
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
