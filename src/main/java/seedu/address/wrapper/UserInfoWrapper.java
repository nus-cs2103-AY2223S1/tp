//@@author arnav-ag
package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

import org.json.JSONObject;

import kong.unirest.UnirestInstance;

/**
 * Class representing a wrapper over the requests and routes needed to get user information from GitHub
 */
public class UserInfoWrapper {
    //@@author arnav-ag

    private static final String NAME_KEY = "name";
    private static final String URL_KEY = "html_url";
    private static final String USERNAME_KEY = "login";
    private static final String EMAIL_KEY = "email";
    private static final String LOCATION_KEY = "location";
    private static final String IMAGE_LOCATION_KEY = "avatar_url";
    private final UserInfoRoute userInfoRoute;
    private final UserInfoRoute.UserInfoRequest userInfoRequest;
    private final UnirestInstance unirest;
    private final Path defaultImagePath;
    private JSONObject userJson;

    /**
     * @param username GitHub username of corresponding user to initialize class with
     * @param unirest  Unirest instance to carry out all further requests
     */
    public UserInfoWrapper(String username, UnirestInstance unirest) {
        requireAllNonNull(username, unirest);

        this.unirest = unirest;
        userInfoRoute = UserInfoRoute.getUserInfoRoute(username);

        userInfoRequest = userInfoRoute.createRequest(unirest);

        getUserJson();

        defaultImagePath = Paths.get("/images", getUsername());
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
     * @throws IOException Exception thrown when trying to save image to file
     */
    public void downloadAvatar() throws IOException {
        UserInfoRoute.UserAvatarRequest userAvatarRequest = userInfoRoute.createAvatarRequest(unirest, getAvatarUrl());
        byte[] image = userAvatarRequest.getAvatarImage();
        Files.write(defaultImagePath, image);
    }

    public String getUrl() {
        return userJson.getString(URL_KEY);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof UserInfoWrapper)
                && userInfoRequest.equals(((UserInfoWrapper) other).userInfoRequest)
                && userJson.equals(((UserInfoWrapper) other).userJson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userInfoRequest, userJson);
    }
}
