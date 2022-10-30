package seedu.address.ui;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.contact.Contact;
import seedu.address.model.person.contact.ContactType;
import seedu.address.model.person.github.Repo;
import seedu.address.model.person.github.User;
import seedu.address.model.tag.Tag;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class DetailPanel extends MainPanel {

    private static final String FXML = "DetailPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DetailPanel.class);

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
    private Hyperlink githubLink;

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

        githubRepoListView.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                Repo repo = githubRepoListView.getSelectionModel().getSelectedItem();
                openUrl(repo.getRepoUrl());
            }
        });

        githubRepoListView.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {
                    Repo repo = githubRepoListView.getSelectionModel().getSelectedItem();
                    openUrl(repo.getRepoUrl());
                }
            }
        });
    }


    /**
     * Focus the list view and the first person in the view
     */
    public void focus() {
        githubRepoListView.requestFocus();
        SelectionModel<Repo> model = githubRepoListView.getSelectionModel();

        if (model.isEmpty()) {
            model.selectFirst();
        }
    }

    public void clearSelectedRepo() {
        githubRepoListView.getSelectionModel().clearSelection();
    }

    /**
     * Returns the selected person in the list
     */
    public int getSelectedRepoIndex() {
        return githubRepoListView.getSelectionModel().getSelectedIndex();
    }

    public <T extends Event> void addEventHandler(EventType<T> eventType, EventHandler<? super T> eventHandler) {
        githubRepoListView.addEventHandler(eventType, eventHandler);
    }

    public <T extends Event> void addEventFilter(EventType<T> eventType, EventHandler<? super T> eventHandler) {
        githubRepoListView.addEventFilter(eventType, eventHandler);
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

        setInformation(person);
        setTags(person.getTags());
        setContacts(person.getContacts());

        setGithubLink(person.getGithubUser());
        setGithubRepos(person.getGithubUser());
    }

    private void setInformation(Person person) {
        ArrayList<String> information = new ArrayList<>();

        person.getRole().ifPresent(r -> information.add(r.toString()));
        person.getTimezone().ifPresent(t -> information.add(t.toString()));
        person.getAddress().ifPresent(a -> information.add(a.toString()));

        informationLabel.prefWidthProperty().bind(this.getRoot().widthProperty().subtract(180));
        setVisibility(informationLabel, information.size() != 0);
        if (information.size() > 0) {
            informationLabel.setText(String.join(" • ", information));
        }
    }

    private void setTags(Set<Tag> tagSet) {
        tags.setManaged(!tagSet.isEmpty());
        tags.getChildren().clear();
        if (!tagSet.isEmpty()) {
            tagSet.stream().sorted(Comparator.comparing(tag -> tag.tagName))
                    .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        }
    }

    private void setGithubLink(Optional<User> user) {
        setVisibility(githubLink, user.isPresent());
        user.ifPresent(u -> {
            githubLink.setText("@" + u.getUsername());
            githubLink.setOnAction(e -> {
            });
        });
    }

    private void openUrl(String url) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException ex) {
                logger.severe("Error occurred when user clicked the link, " + ex.toString());
                a.setContentText("An internal error has occurred, unable to open browser.");
                a.show();
            } catch (URISyntaxException ex) {
                logger.warning("Github url is invalid " + ex.toString());
                a.setContentText("Given url is invalid, please confirm your contact information again.");
                a.show();
            }
        }
    }

    private void setGithubRepos(Optional<User> user) {
        // Hide the title when github user is empty
        setVisibility(reposTitleLabel, user.isPresent());
        githubRepoListView.setItems(FXCollections.emptyObservableList());
        if (user.isPresent()) {
            User githubUser = user.get();

            // Hide the title when repo list has no repo
            setVisibility(reposTitleLabel, githubUser.getRepoList().size() != 0);
            githubRepoListView.setItems(FXCollections.observableList(githubUser.getRepoList()));
            githubRepoListView.setCellFactory(listView -> new GithubRepoListViewCell());
        }
    }

    private void setContacts(Map<ContactType, Contact> contacts) {
        setVisibility(contactsTitleLabel, contacts.size() != 0);
        List<ContactBox> contactBoxList = new ArrayList<ContactBox>(
                contacts.values().stream().map(ContactBox::new).collect(Collectors.toList()));

        contactBoxContainer.getChildren().clear();
        contactBoxContainer.getChildren()
                .addAll(contactBoxList.stream().map(ContactBox::getRoot).collect(Collectors.toList()));
    }

    private void setVisibility(Control control, boolean visible) {
        // Remove node from tree so it doesn't occupy the space.
        // @see https://stackoverflow.com/a/28559958
        control.setManaged(visible);
        control.setVisible(visible);
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
