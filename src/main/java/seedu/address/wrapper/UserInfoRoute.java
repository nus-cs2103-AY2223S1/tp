package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import org.json.JSONException;
import org.json.JSONObject;

import kong.unirest.UnirestInstance;
import seedu.address.wrapper.exceptions.ResponseParseException;

/**
 * Class representing routes taken to get user information
 */
public final class UserInfoRoute {
    //@@author arnav-ag

    public static final String BASE_GITHUB_URL = "https://api.github.com";
    private static final String SERVER_DOWN_STATUS_MESSAGE = "Unable to get information from API.";

    private static final String GET_USER_BASE_PATH = "/users/";
    private final String path;
    private final String username;

    private UserInfoRoute(String path, String username) {
        requireAllNonNull(path, username);
        this.path = path;
        this.username = username;
    }

    public static UserInfoRoute getUserInfoRoute(String username) {
        requireAllNonNull(username);
        return new UserInfoRoute(GET_USER_BASE_PATH + username, username);
    }

    /**
     * @param unirest Unirest instance to be used to carry out requests
     * @return Request class pertaining to user information from GitHub
     */
    public UserInfoRequest createRequest(UnirestInstance unirest) {
        requireAllNonNull(unirest);

        return new UserInfoRequest(unirest, BASE_GITHUB_URL + this.path);
    }

    public UserAvatarRequest createAvatarRequest(UnirestInstance unirest, String url) {
        requireAllNonNull(unirest);

        return new UserAvatarRequest(unirest, url);
    }

    /**
     * Class representing request needed to retrieve user information from GitHub
     */
    public static class UserInfoRequest {

        private final UnirestInstance unirest;
        private final String url;

        UserInfoRequest(UnirestInstance unirest, String url) {
            requireAllNonNull(unirest, url);
            this.unirest = unirest;
            this.url = url;
        }

        public JSONObject getJson() {
            var response = this.unirest.get(this.url).asString().getBody();

            try {
                return new JSONObject(response);
            } catch (JSONException e) {
                throw new ResponseParseException("Unable to parse API result.");
            }
        }

        @Override
        public boolean equals(Object other) {
            return other == this
                    || (other instanceof UserInfoRequest)
                    && url.equals(((UserInfoRequest) other).url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url);
        }

    }

    /**
     * Class representing request needed to retrieve user information from GitHub
     */
    public static class UserAvatarRequest {

        private final UnirestInstance unirest;
        private final String url;

        UserAvatarRequest(UnirestInstance unirest, String url) {
            requireAllNonNull(unirest, url);
            this.unirest = unirest;
            this.url = url;
        }

        public byte[] getAvatarImage() {
            return this.unirest.get(this.url).asBytes().getBody();

        }

        @Override
        public boolean equals(Object other) {
            return other == this
                    || (other instanceof UserInfoRequest)
                    && url.equals(((UserInfoRequest) other).url);
        }

        @Override
        public int hashCode() {
            return Objects.hash(url);
        }

    }

}
