//@@author arnav-ag
package seedu.address.github;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import org.json.JSONObject;

import kong.unirest.UnirestInstance;
import seedu.address.github.exceptions.FileSaveFailException;
import seedu.address.model.UserPrefs;

/**
 * Class representing a wrapper over the requests and routes needed to get user information from GitHub
 */
public class UserInfoWrapper {
    private static final String NAME_KEY = "name";
    private static final String URL_KEY = "html_url";
    private static final String USERNAME_KEY = "login";
    private static final String EMAIL_KEY = "email";
    private static final String LOCATION_KEY = "location";
    private static final String IMAGE_LOCATION_KEY = "avatar_url";
    private final Path avatarImageFileDirectory;
    private final UserInfoRoute.UserInfoRequest userInfoRequest;
    private final UserInfoRoute.UserAvatarRequest userAvatarRequest;

    private final String avatarImageFileName;
    private JSONObject userJson;

    /**
     * @param username GitHub username of corresponding user to initialize class with
     * @param unirest  Unirest instance to carry out all further requests
     */
    public UserInfoWrapper(String username, UnirestInstance unirest) {
        requireAllNonNull(username, unirest);

        UserInfoRoute userInfoRoute = UserInfoRoute.getUserInfoRoute(username);

        userInfoRequest = userInfoRoute.createRequest(unirest);
        getUserJson();

        userAvatarRequest = userInfoRoute.createAvatarRequest(unirest, getAvatarUrl());
        avatarImageFileName = getUsername() + ".png";

        UserPrefs userPrefs = new UserPrefs();
        avatarImageFileDirectory =
            Paths.get(userPrefs.getAddressBookFilePath().getParent().toString(), "images");
    }

    private void getUserJson() {
        this.userJson = this.userInfoRequest.getJson();
    }

    public Optional<String> getName() {
        return Optional.ofNullable(userJson.optString(NAME_KEY, null));
    }

    public String getUsername() {
        return userJson.getString(USERNAME_KEY);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(userJson.optString(EMAIL_KEY, null));
    }

    public Optional<String> getLocation() {
        return Optional.ofNullable(userJson.optString(LOCATION_KEY, null));
    }

    public String getAvatarUrl() {
        return userJson.getString(IMAGE_LOCATION_KEY);
    }

    /**
     * Downloads avatar to file specified in class above.
     */
    public void downloadAvatar() throws FileSaveFailException {
        byte[] image = userAvatarRequest.getAvatarImage();
        try {
            avatarImageFileDirectory.toFile().mkdirs();
            Path avatarImageFilePath = Paths.get(avatarImageFileDirectory.toString(), avatarImageFileName);

            if (avatarImageFilePath.toFile().exists() && !avatarImageFilePath.toFile().isFile()) {
                if (!avatarImageFilePath.toFile().delete()) {
                    throw new FileSaveFailException(
                            "Directory exists with same name as file to be saved and could not be deleted. Please "
                                    + "delete directory to save this user's avatar.");
                }
            }

            Files.write(avatarImageFilePath, image);
        } catch (Exception e) {
            throw new FileSaveFailException("Unable to save user avatar to local storage.");
        }
    }

    public Path getAvatarImageFilePath() {
        return Paths.get(avatarImageFileDirectory.toString(), avatarImageFileName);
    }

    public String getUrl() {
        return userJson.getString(URL_KEY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof UserInfoWrapper)
            && userInfoRequest.equals(((UserInfoWrapper) other).userInfoRequest)
            && userAvatarRequest.equals(((UserInfoWrapper) other).userAvatarRequest)
            && userJson.equals(((UserInfoWrapper) other).userJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userAvatarRequest, userJson);
    }
}
