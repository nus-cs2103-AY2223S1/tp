package seedu.clinkedin.ui;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.commons.util.ImageUtil;
import seedu.clinkedin.logic.commands.ViewCommand;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.logic.parser.exceptions.ParseException;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.Rating;
import seedu.clinkedin.model.tag.Tag;
import seedu.clinkedin.model.tag.TagType;
import seedu.clinkedin.model.tag.UniqueTagList;

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

    public final MainWindow mainWindow;

    public final Index index;

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
    @FXML
    private Label note;
    @FXML
    private Label status;
    @FXML
    private Label rating;

    @FXML
    private FlowPane links;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, MainWindow mainWindow) {
        super(FXML);
        this.person = person;
        this.index = Index.fromOneBased(displayedIndex);
        this.mainWindow = mainWindow;
        cardPane.addEventHandler(MouseEvent.MOUSE_PRESSED, viewPerson());
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        ObservableMap<TagType, UniqueTagList> tagTypeMap = person.getTags();
        List<TagType> tagTypeList = tagTypeMap.keySet().stream()
                .sorted(Comparator.comparing(tagType -> tagType.getTagTypeName())).collect(Collectors.toList());
        for (int i = 0; i < tagTypeList.size(); i++) {
            Label title = new Label(tagTypeList.get(i).getTagTypeName());
            title.setStyle("-fx-background-color: #004999; -fx-text-fill: white;");
            tagPane.add((title), 0, i);
            int idx = 1;
            for (Tag tag: tagTypeMap.get(tagTypeList.get(i))) {
                tagPane.add(new Label(tag.tagName), idx, i);
                idx += 1;
            }
        }
        tagPane.setHgap(5);
        tagPane.setVgap(5);

        status.setText(person.getStatus().status);
        // note.setText(person.getNote().value.length() > 0 ? "Notes: " + person.getNote().value : "");
        rating.setText(person.getRating().equals(new Rating("0")) ? "" : "Rating: " + person.getRating().toString());
        person.getLinks().stream().sorted(Comparator.comparing(link -> link.platform))
                .forEach(link -> {
                    Button button = new Button();
                    button.setOnAction(openBrowser(link.link.toString()));
                    Image image = ImageUtil.getSocialIcon(link.platform);
                    if (image == null) {
                        button.setText(link.platform + " : " + link.link);
                    } else {
                        ImageView platformIcon = new ImageView(image);
                        platformIcon.setFitHeight(20);
                        platformIcon.setFitWidth(20);
                        button.setGraphic(platformIcon);
                    }
                    links.getChildren().add(button);
                });
    }

    private EventHandler<ActionEvent> openBrowser(final String url) {
        Application a = new Application() {
            @Override
            public void start(Stage primaryStage) {
            }
        };
        EventHandler<ActionEvent> e = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HostServices services = a.getHostServices();
                services.showDocument(url);
            }
        };
        return e;
    }

    private EventHandler<MouseEvent> viewPerson() {
        return new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent clickEvent) {
                try {
                    mainWindow.executeCommand(new ViewCommand(index));
                } catch (ParseException | CommandException e) {
                    e.printStackTrace();
                }
            };
        };
    };

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
        return index.equals(card.index)
                && person.equals(card.person);
    }
}
