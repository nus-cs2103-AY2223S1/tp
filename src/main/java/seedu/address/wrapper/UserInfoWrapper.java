//@@author arnav-ag
package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import org.json.JSONObject;

import kong.unirest.UnirestInstance;
import seedu.address.storage.Storage;

public class UserInfoWrapper {
    //@@author arnav-ag

    private final UserInfoRoute.UserInfoRequest getUserInfoUserInfoRequest;
    private JSONObject userJson;
    private final String NAME_KEY = "name";
    private final String URL_KEY = "url";
    private final String USERNAME_KEY = "login";
    private final String EMAIL_KEY = "email";
    private final String LOCATION_KEY = "location";
    private final String IMAGE_LOCATION_KEY = "avatar_url";

    public UserInfoWrapper(String user, UnirestInstance unirest, Storage storage) {
        requireAllNonNull(user);

        UserInfoRoute getUserInfoUserInfoRoute = UserInfoRoute.getUserInfoRoute(user, storage);
        getUserInfoUserInfoRequest = getUserInfoUserInfoRoute.createRequest(unirest);
    }

    private void getUserJson() {
        this.userJson = this.getUserInfoUserInfoRequest.getJSON();
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
