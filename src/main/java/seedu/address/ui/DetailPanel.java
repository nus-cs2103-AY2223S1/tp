package seedu.address.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.github.Repo;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class DetailPanel extends MainPanel {

    private static final String FXML = "DetailPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(GithubRepoCard.class);

    @FXML
    private Circle profileImageContainer;

    @FXML
    private ImageView profileImageView;

    @FXML
    private Label contactsTitleLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label informationLabel;

    @FXML
    private Hyperlink githubLabel;

    @FXML
    private HBox contactBoxContainer;

    @FXML
    private Label reposTitleLabel;

    @FXML
    private ListView<Repo> githubRepoListView;

    @FXML
    private FlowPane tags;

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

        AtomicReference<Image> avatarImage =
            new AtomicReference<>(new Image(
                Objects.requireNonNull(
                    this.getClass().getResourceAsStream("/images/user_placeholder.png")
                )));

        person.getGithubUser().ifPresent(u -> {
            if (u.getAvatarImageFilePath().isPresent()) {
                File avatarImageFile = u.getAvatarImageFilePath().get().toFile();
                avatarImage.set(new Image(avatarImageFile.toURI().toString()));
            }
        });

        profileImageContainer.setFill(new ImagePattern(avatarImage.get()));

        // Information
        ArrayList<String> information = new ArrayList<>();

        person.getRole().ifPresent(r -> information.add(r.toString()));
        person.getTimezone().ifPresent(t -> information.add(t.toString()));
        person.getAddress().ifPresent(a -> information.add(a.toString()));

        setLabelVisibility(informationLabel, information.size() != 0);
        if (information.size() > 0) {
            informationLabel.setText(String.join(" • ", information));
        }

        // Tags
        tags.setManaged(!person.getTags().isEmpty());
        tags.getChildren().clear();
        if (!person.getTags().isEmpty()) {
            person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        }

        // Github
        setHyperlinkVisibility(githubLabel, person.getGithubUser().isPresent());
        person.getGithubUser().ifPresent(u -> {
            githubLabel.setText("@" + u.getUsername());

            Alert a = new Alert(Alert.AlertType.ERROR);
            githubLabel.setOnAction(e -> {
                if (Desktop.isDesktopSupported()) {
                    try {
                        Desktop.getDesktop().browse(new URI(u.getUrl()));
                    } catch (IOException ex) {
                        logger.severe("Error occurred when user clicked the link, " + ex);
                        a.setContentText("An internal error has occurred, unable to open browser.");
                        a.show();
                    } catch (URISyntaxException ex) {
                        logger.warning("Github user's link is invalid." + ex);
                        a.setContentText("Github user's link is invalid, unable to open in browser.");
                        a.show();
                    }
                }
            });
        });

        // Hide the title when github user is empty
        setLabelVisibility(reposTitleLabel, person.getGithubUser().isPresent());

        githubRepoListView.setItems(FXCollections.emptyObservableList());
        person.getGithubUser().ifPresent(u -> {
            // Hide the title when repo list has no repo
            setLabelVisibility(reposTitleLabel, u.getRepoList().size() != 0);
            githubRepoListView.setItems(FXCollections.observableList(u.getRepoList()));
            githubRepoListView.setCellFactory(listView -> new GithubRepoListViewCell());
        });

        // Contacts
        setLabelVisibility(contactsTitleLabel, person.getContacts().size() != 0);
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

    private void setHyperlinkVisibility(Hyperlink hyperlink, boolean visible) {
        // Remove node from tree so it doesn't occupy the space.
        // @see https://stackoverflow.com/a/28559958
        hyperlink.setManaged(visible);
        hyperlink.setVisible(visible);
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
