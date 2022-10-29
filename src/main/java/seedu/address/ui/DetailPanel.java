package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import seedu.address.model.person.Person;
import seedu.address.model.person.github.User;
import seedu.address.model.person.github.Repo;

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
    private Label addressLabel;

    @FXML
    private HBox contactBoxContainer;

    @FXML
    private ListView<Repo> githubRepoListView;

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
        Image placeholder = new Image(this.getClass().getResourceAsStream("/images/user_placeholder.png"));
        profileImageContainer.setFill(new ImagePattern(placeholder));

        setLabelVisibility(roleLabel, person.getRole().isPresent());
        person.getRole().ifPresent(r -> roleLabel.setText(r.toString()));

        setLabelVisibility(timezoneLabel, person.getTimezone().isPresent());
        person.getTimezone().ifPresent(t -> timezoneLabel.setText(t.toString()));

        setLabelVisibility(addressLabel, person.getAddress().isPresent());
        person.getAddress().ifPresent(a -> addressLabel.setText(a.toString()));

        if (person.getGithubUser().isPresent()) {
            User githubUser = person.getGithubUser().get();
            githubRepoListView.setItems(FXCollections.observableList(githubUser.getRepoList()));
            githubRepoListView.setCellFactory(listView -> new GithubRepoListViewCell());
        }

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

    private void setLabelVisibility(Label label, boolean visible) {
        // Remove node from tree so it doesn't occupy the space.
        // @see https://stackoverflow.com/a/28559958
        label.setManaged(visible);
        label.setVisible(visible);
    }

    @Override
    public MainPanelName getPanelName() {
        return MainPanelName.Detail;
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Repo} using a {@code GithubRepoCard}.
     */
    class GithubRepoListViewCell extends ListCell<Repo> {

        GithubRepoListViewCell() {
            super();
            prefWidthProperty().bind(githubRepoListView.widthProperty().subtract(20));
            setMaxWidth(USE_PREF_SIZE);
        }

        @Override
        protected void updateItem(Repo repo, boolean empty) {
            super.updateItem(repo, empty);

            if (empty || repo == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new GithubRepoCard(repo).getRoot());
            }
        }
    }
}
