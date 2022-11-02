package seedu.address.github;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import kong.unirest.Config;
import kong.unirest.HttpRequestSummary;
import kong.unirest.HttpResponse;
import kong.unirest.Interceptor;
import kong.unirest.UnirestInstance;
import seedu.address.github.exceptions.NetworkConnectionException;
import seedu.address.github.exceptions.UserInvalidException;
import seedu.address.model.person.github.User;

/**
 * Class representing a singleton GitHub API wrapper
 */
public class GithubApi {
    private final UnirestInstance unirest;

    public GithubApi() {
        unirest = getDefaultUnirestInstance();
    }

    public static UnirestInstance getDefaultUnirestInstance() {
        Config config = new Config().connectTimeout(2000)
            .interceptor(new Interceptor() {
                @Override
                public void onResponse(HttpResponse<?> response, HttpRequestSummary request, Config config)
                        throws UserInvalidException, NetworkConnectionException {
                    if (response.getStatus() == 404) {
                        throw new UserInvalidException(
                            "User does not exist. Please provide an existing GitHub username.");
                    } else if (response.getStatus() == 403) {
                        throw new NetworkConnectionException(
                            "Unable to get user from GitHub as too many calls have been made. Please wait a while "
                                + "before trying this request again.");
                    }
                }

                @Override
                public HttpResponse<?> onFail(Exception e, HttpRequestSummary request, Config config)
                        throws UserInvalidException, NetworkConnectionException {
                    if (e instanceof NetworkConnectionException) {
                        throw new NetworkConnectionException(e.getMessage());
                    } else if (e instanceof UserInvalidException) {
                        throw new UserInvalidException(e.getMessage());
                    }
                    throw new NetworkConnectionException("Error while getting request, unable to get results.");
                }
            });
        return new UnirestInstance(config);
    }

    public User getUser(String username) throws UserInvalidException, NetworkConnectionException {
        requireAllNonNull(username);
        UserInfoWrapper userInfoWrapper = new UserInfoWrapper(username, unirest);
        UserReposWrapper userReposWrapper = new UserReposWrapper(username, unirest);

        return new User(username, userInfoWrapper, userReposWrapper);
    }
}
