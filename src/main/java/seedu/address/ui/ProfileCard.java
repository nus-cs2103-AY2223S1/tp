package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.profile.Profile;
import seedu.address.model.profile.Telegram;

/**
 * An UI component that displays information of a {@code Profile}.
 */
public class ProfileCard extends UiPart<Region> {

    private static final String FXML = "ProfileListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Profile profile;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private HBox phone;
    @FXML
    private HBox email;
    @FXML
    private HBox telegram;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ProfileCode} with the given {@code Profile} and index to display.
     */
    public ProfileCard(Profile profile, int displayedIndex) {
        super(FXML);
        ImageView phoneIcon = new ImageView("/images/phone_icon.png");
        phoneIcon.setFitHeight(15);
        phoneIcon.setFitWidth(15);
        Label phoneLabel = new Label(profile.getPhone().value);

        ImageView emailIcon = new ImageView("/images/email_icon.png");
        emailIcon.setFitHeight(15);
        emailIcon.setFitWidth(15);
        Label emailLabel = new Label(profile.getEmail().value);

        ImageView telegramIcon = new ImageView("/images/telegram_icon.png");
        telegramIcon.setFitHeight(15);
        telegramIcon.setFitWidth(15);

        this.profile = profile;
        id.setText(displayedIndex + ". ");
        name.setText(profile.getName().fullName);
        phone.getChildren().add(phoneIcon);
        phone.getChildren().add(phoneLabel);
        email.getChildren().add(emailIcon);
        email.getChildren().add(emailLabel);

        if (!profile.getTelegram().isEmpty()) {
            telegram.getChildren().add(telegramIcon);
            Label telegramLabel = new Label(Telegram.PREFIX + profile.getTelegram().value);
            telegram.getChildren().add(telegramLabel);
        }
        profile.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ProfileCard)) {
            return false;
        }

        // state check
        ProfileCard card = (ProfileCard) other;
        return id.getText().equals(card.id.getText())
                && profile.equals(card.profile);
    }
}
