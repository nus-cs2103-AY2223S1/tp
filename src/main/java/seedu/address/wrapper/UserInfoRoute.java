package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import org.json.JSONException;
import org.json.JSONObject;

import kong.unirest.UnirestInstance;
import seedu.address.wrapper.exceptions.JsonParseException;

public final class UserInfoRoute {
    //@@author arnav-ag

    public static final String BASE_GITHUB_URL = "https://api.github.com";
    private static final String SERVER_DOWN_STATUS_MESSAGE = "Unable to get information from API.";

    private final static String GET_USER_BASE_PATH = "/users/";
    private final String path;

    private UserInfoRoute(String path) {
        requireAllNonNull(path);
        this.path = path;
    }

    public static UserInfoRoute getUserInfoRoute(String user) {
        requireAllNonNull(user);
        return new UserInfoRoute(GET_USER_BASE_PATH + user);
    }

    public UserInfoRequest createRequest(UnirestInstance unirest) {
        requireAllNonNull(unirest);

        return new UserInfoRequest(unirest, BASE_GITHUB_URL + this.path);
    }

    public String getPath() {
        return this.path;
    }

    public static class UserInfoRequest {

        private final UnirestInstance unirest;
        private final String url;

        UserInfoRequest(UnirestInstance unirest, String url) {
            requireAllNonNull(unirest, url);
            this.unirest = unirest;
            this.url = url;
        }

        public JSONObject getJSON() {
            var response = this.unirest.get(this.url).asString().getBody();

            try {
                return new JSONObject(response);
            } catch (JSONException e) {
                throw new JsonParseException("Unable to parse API result.");
            }
        }
    }

}
