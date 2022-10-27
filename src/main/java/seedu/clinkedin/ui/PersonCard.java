package seedu.clinkedin.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    private HBox ratingBox;
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

        note.setText(person.getNote().value.length() > 0 ? "Notes: " + person.getNote().value : "");

        // pure text "Rating: " with color
        int ratingNumber = person.getRating().value;
        rating.setText(person.getRating().equals(new Rating("0")) ? "" : "Rating: " + person.getRating()
                .toString());
        rating.setFont(new Font(4));
        rating.setPadding(new Insets(1));

        CornerRadii corn = new CornerRadii(2.5);
        if (ratingNumber <= 3 && ratingNumber >= 1) {
            Color red = Color.rgb(235, 50, 50);
            Background redBg = new Background(new BackgroundFill(red, corn, Insets.EMPTY));
            rating.setBackground(redBg);
        } else if (ratingNumber <= 6) {
            Color yellow = Color.rgb(236, 180, 16);
            Background yellowBg = new Background(new BackgroundFill(yellow, corn, Insets.EMPTY));
            rating.setBackground(yellowBg);
        } else {
            Color green = Color.rgb(16, 185, 67);
            Background greenBg = new Background(new BackgroundFill(green, corn, Insets.EMPTY));
            rating.setBackground(greenBg);
        }

        try {
            // stars as rating
            int displayRating = person.getRating().value;
            // Image fullStar = ImageUtil.getStar("fullstar");
            Image fullStar = new Image(new FileInputStream("/images/rating/fullstar.png"));
            ImageView fullStarView = new ImageView();
            fullStarView.setImage(fullStar);
            fullStarView.setFitWidth(15);
            // Image halfStar = ImageUtil.getStar("halfstar");
            Image halfStar = new Image(new FileInputStream("/images/rating/halfstar.png"));
            ImageView halfStarView = new ImageView();
            halfStarView.setImage(halfStar);
            halfStarView.setFitWidth(15);
            // Image emptyStar = ImageUtil.getStar("emptystar");
            Image emptyStar = new Image(new FileInputStream("/images/rating/emptystar.png"));
            ImageView emptyStarView = new ImageView();
            emptyStarView.setImage(emptyStar);
            emptyStarView.setFitWidth(15);

            int stars = 5;
            while (stars != 0) {
                stars--;
                if (displayRating == 0) {
                    ratingBox.getChildren().add(emptyStarView);
                } else if (displayRating == 1) {
                    ratingBox.getChildren().add(halfStarView);
                    displayRating = 0;
                } else {
                    ratingBox.getChildren().add(fullStarView);
                    displayRating -= 2;
                }
                System.out.println(displayRating);
            }
        } catch (FileNotFoundException e) {
            e.toString();
        }

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
