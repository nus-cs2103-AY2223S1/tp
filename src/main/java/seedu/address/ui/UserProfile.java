package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.user.ExistingUser;
import seedu.address.model.person.user.User;

/**
 * Class to render user profile on UI
 */
public class UserProfile extends UiPart<Region> {

    private static final String FXML = "UserProfile.fxml";
    private String githubUrl;

    private User user;

    @FXML
    private Button copyButton;
    @FXML
    private HBox userProfilePane;
    @FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label github;
    @FXML
    private Label userLabel;
    @FXML
    private Label currModuleDescription;
    @FXML
    private Label prevModuleDescription;
    @FXML
    private Label planModuleDescription;
    @FXML
    private FlowPane currModulesTags;
    @FXML
    private FlowPane prevModulesTags;
    @FXML
    private FlowPane planModulesTags;

    /**
     * creates a new UserProfile
     *
     * @param user current user
     */
    public UserProfile(User user) {
        super(FXML);
        this.user = user;
        if (user instanceof ExistingUser) {
            userLabel.setText("User:");
            name.setText(user.getName().fullName);
            phone.setText(user.getPhone().value);
            address.setText(user.getAddress().value);
            email.setText(user.getEmail().value);
            github.setText("Github: " + user.getGithub().value);
            currModuleDescription.setText("Current Modules: ");
            prevModuleDescription.setText("Previous Modules: ");
            planModuleDescription.setText("Planned Modules: ");
            user.getCurrModules().stream()
                    .sorted(Comparator.comparing(mod -> mod.moduleName))
                    .forEach(mod -> currModulesTags.getChildren().add(new Label(mod.moduleName)));
            user.getPrevModules().stream()
                    .sorted(Comparator.comparing(mod -> mod.moduleName))
                    .forEach(mod -> prevModulesTags.getChildren().add(new Label(mod.moduleName)));
            user.getPlanModules().stream()
                    .sorted(Comparator.comparing(mod -> mod.moduleName))
                    .forEach(mod -> planModulesTags.getChildren().add(new Label(mod.moduleName)));
            githubUrl = "https://github.com/" + user.getGithub().value;
        } else {
            userLabel.setText("User details have not been added.");
            githubUrl = "";
        }
    }

    /**
     * Updates the current user.
     *
     * @param user new current user
     */
    public void update(User user) {
        this.user = user;
        if (user instanceof ExistingUser) {
            userLabel.setText("User: ");
            name.setText(user.getName().fullName);
            phone.setText(user.getPhone().value);
            address.setText(user.getAddress().value);
            email.setText(user.getEmail().value);
            github.setText("Github: " + user.getGithub().value);
            currModuleDescription.setText("Current Modules: ");
            prevModuleDescription.setText("Previous Modules: ");
            planModuleDescription.setText("Planned Modules: ");
            currModulesTags.getChildren().clear();
            planModulesTags.getChildren().clear();
            prevModulesTags.getChildren().clear();
            user.getCurrModules().stream()
                    .sorted(Comparator.comparing(mod -> mod.moduleName))
                    .forEach(mod -> currModulesTags.getChildren().add(new Label(mod.moduleName)));
            user.getPrevModules().stream()
                    .sorted(Comparator.comparing(mod -> mod.moduleName))
                    .forEach(mod -> prevModulesTags.getChildren().add(new Label(mod.moduleName)));
            user.getPlanModules().stream()
                    .sorted(Comparator.comparing(mod -> mod.moduleName))
                    .forEach(mod -> planModulesTags.getChildren().add(new Label(mod.moduleName)));
            githubUrl = "https://github.com/" + user.getGithub().value;
        } else {
            userLabel.setText("User details have not been added.");
            name.setText("");
            phone.setText("");
            address.setText("");
            email.setText("");
            github.setText("");
            currModuleDescription.setText("");
            prevModuleDescription.setText("");
            planModuleDescription.setText("");
            currModulesTags.getChildren().clear();
            planModulesTags.getChildren().clear();
            prevModulesTags.getChildren().clear();
            githubUrl = "";
        }
    }

    /**
     * Updates the current user when a new semester begins.
     *
     * @param user new current user
     */
    public void updatePrevMods(User user) {
        this.user = user;
        userLabel.setText("User: ");
        name.setText(user.getName().fullName);
        phone.setText(user.getPhone().value);
        address.setText(user.getAddress().value);
        email.setText(user.getEmail().value);
        github.setText("Github: " + user.getGithub().value);
        currModuleDescription.setText("Current Modules: ");
        prevModuleDescription.setText("Previous Modules: ");
        planModuleDescription.setText("Planned Modules: ");
        currModulesTags.getChildren().clear();
        prevModulesTags.getChildren().clear();
        planModulesTags.getChildren().clear();
        user.getCurrModules().stream()
                .sorted(Comparator.comparing(mod -> mod.moduleName))
                .forEach(mod -> currModulesTags.getChildren().add(new Label(mod.moduleName)));
        user.getPrevModules().stream()
                .sorted(Comparator.comparing(mod -> mod.moduleName))
                .forEach(mod -> prevModulesTags.getChildren().add(new Label(mod.moduleName)));
        user.getPlanModules().stream()
                .sorted(Comparator.comparing(mod -> mod.moduleName))
                .forEach(mod -> planModulesTags.getChildren().add(new Label(mod.moduleName)));
    }

    /**
     * Copies the URL to the github profile to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(githubUrl);
        clipboard.setContent(url);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserProfile)) {
            return false;
        }

        // state check
        UserProfile profile = (UserProfile) other;
        return user.equals(profile.user);
    }
}
