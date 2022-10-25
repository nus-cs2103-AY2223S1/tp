//@@author arnav-ag
package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import org.json.JSONObject;

import kong.unirest.UnirestInstance;
import seedu.address.storage.Storage;

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
    private final UserInfoRoute.UserInfoRequest getUserInfoUserInfoRequest;
    private JSONObject userJson;

    /**
     * @param username GitHub username of corresponding user to initialize class with
     * @param unirest  Unirest instance to carry out all further requests
     * @param storage  Storage instance to manage user avatar downloads
     */
    public UserInfoWrapper(String username, UnirestInstance unirest, Storage storage) {
        requireAllNonNull(username);

        UserInfoRoute getUserInfoUserInfoRoute = UserInfoRoute.getUserInfoRoute(username, storage);
        getUserInfoUserInfoRequest = getUserInfoUserInfoRoute.createRequest(unirest);
    }

    private void getUserJson() {
        this.userJson = this.getUserInfoUserInfoRequest.getJson();
    }

    public Optional<String> getName() {
        if (userJson == null) {
            getUserJson();
        }

        return Optional.of(userJson.optString(NAME_KEY, null));
    }

    public String getUsername() {
        if (userJson == null) {
            getUserJson();
        }
        return userJson.getString(USERNAME_KEY);
    }

    public Optional<String> getEmail() {
        if (userJson == null) {
            getUserJson();
        }

        return Optional.of(userJson.optString(EMAIL_KEY, null));
    }

    public Optional<String> getLocation() {
        if (userJson == null) {
            getUserJson();
        }

        return Optional.of(userJson.optString(LOCATION_KEY, null));
    }

    public String getAvatarUrl() {
        if (userJson == null) {
            getUserJson();
        }
        return userJson.getString(IMAGE_LOCATION_KEY);
    }

    public String getUrl() {
        if (userJson == null) {
            getUserJson();
        }
        return userJson.getString(URL_KEY);
    }
}
