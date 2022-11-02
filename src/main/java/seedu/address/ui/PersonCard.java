package seedu.address.ui;

import java.util.Comparator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import seedu.address.model.person.Person;
import seedu.address.model.social.Social;
import seedu.address.model.social.exceptions.SocialException;



/**
 * A UI component that displays information of a {@code Person}.
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
    private Label tutorial;
    @FXML
    private Label occupation;
    @FXML
    private FlowPane tags;
    @FXML
    private Button whatsapp;
    @FXML
    private Button telegram;
    @FXML
    private Button email2;
    @FXML
    private Button instagram;
    @FXML
    private Button preferred;
    @FXML
    private Circle cir2;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex, Stage primaryStage, CommandBox.CommandSetter commandSetter) {
        super(FXML);
        this.person = person;
        String s = person.getOccupation().getString();
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        tutorial.setText(person.getTutorial().tut);
        occupation.setText(s);
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        Social social = person.getSocial();
        whatsapp.setText("Whatsapp: " + social.getWhatsapp());
        telegram.setText("Telegram: " + social.getTelegram());
        email2.setText("Email: " + social.getEmail());
        instagram.setText("Instagram: " + social.getInstagram());
        preferred.setText("Preferred: " + social.getPreferred());

        if (displayedIndex % 2 == 0) {
            whatsapp.getStyleClass().add("button2");
            telegram.getStyleClass().add("button2");
            email2.getStyleClass().add("button2");
            instagram.getStyleClass().add("button2");
            preferred.getStyleClass().add("button2");
        } else {
            whatsapp.getStyleClass().add("button1");
            telegram.getStyleClass().add("button1");
            email2.getStyleClass().add("button1");
            instagram.getStyleClass().add("button1");
            preferred.getStyleClass().add("button1");
        }

        cir2.setStroke(Color.AQUAMARINE);
        String imageUrl = social.getImageUrl();
        Image im = new Image(String.valueOf(this.getClass().getResource("/images/profile_pic.png")));
        cir2.setFill(new ImagePattern(im));
        cir2.setEffect(new DropShadow(+25d, 0d, +2d, Color.AQUAMARINE));

        whatsapp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    social.openWhatsapp();
                } catch (SocialException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Whatsapp Link error");
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("To find out more info: Try using the Command Line to open the link selected");
                    alert.showAndWait();
                }
            }
        });

        telegram.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    social.openTelegram();
                } catch (SocialException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Telegram Link error");
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("To find out more info: Try using the Command Line to open the link selected");
                    alert.showAndWait();
                }
            }
        });

        email2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    social.openEmail();
                } catch (SocialException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Email Link error");
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("To find out more info: Try using the Command Line to open the link selected");
                    alert.showAndWait();
                }
            }
        });

        instagram.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    social.openInstagram();
                } catch (SocialException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Instagram Link error");
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("To find out more info: Try using the Command Line to open the link selected");
                    alert.showAndWait();
                }
            }
        });

        preferred.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    social.openPreferred();
                } catch (SocialException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Preferred Link error");
                    alert.setHeaderText(e.getMessage());
                    alert.setContentText("To find out more info: Try using the Command Line to open the link selected");
                    alert.showAndWait();
                }
            }
        });

        cardPane.setOnMouseClicked((click) -> commandSetter.setCommand(person.getEditString(displayedIndex)));
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
