package seedu.address.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import seedu.address.model.person.Person;
import seedu.address.model.person.github.User;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class DetailPanel extends MainPanel {

    private static final String FXML = "DetailPanel.fxml";

    @FXML
    private Circle profileImageContainer;

    @FXML
    private ImageView profileImageView;

    @FXML
    private Label nameLabel;

    @FXML
    private Label roleLabel;

    @FXML
    private Label timezoneLabel;

    @FXML
    private HBox contactBoxContainer;

    /**
     * Initialises the DetailPanel.
     *
     * @param person The person whose contact details are to be displayed.
     */
    public DetailPanel(SimpleObjectProperty<Person> person) {
        super(FXML);

        // Add change listener so when person change, we can get update detail directly.
        person.addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldPerson, Person newPerson) {
                updatePersonDetail(newPerson);
            }
        });
    }

    private void updatePersonDetail(Person person) {
        nameLabel.setText(person.getName().toString());

        User githubUser = person.getGithubUser().orElse(null);
        Image avatarImage = new Image(this.getClass().getResourceAsStream("/images/user_placeholder.png"));
        if (githubUser != null) {
            if (githubUser.getAvatarImageFilePath().isPresent()) {
                File avatarImageFile = githubUser.getAvatarImageFilePath().get().toFile();
                avatarImage = new Image(avatarImageFile.toURI().toString());
            }
        }
        profileImageContainer.setFill(new ImagePattern(avatarImage));

        roleLabel.setVisible(person.getRole().isPresent());
        person.getRole().ifPresent(r -> roleLabel.setText(r.toString()));

        timezoneLabel.setVisible(person.getTimezone().isPresent());
        person.getTimezone().ifPresent(t -> timezoneLabel.setText(t.toString()));

        List<ContactBox> contactBoxList = new ArrayList<ContactBox>(
            person.getContacts()
                .values().stream()
                .map(ContactBox::new)
                .collect(Collectors.toList()));

        contactBoxContainer.getChildren().clear();
        contactBoxContainer.getChildren().addAll(contactBoxList.stream()
            .map(ContactBox::getRoot)
            .collect(Collectors.toList()));
    }

    @Override
    public MainPanelName getPanelName() {
        return MainPanelName.Detail;
    }
}
