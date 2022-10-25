package seedu.address.wrapper;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Optional;

import kong.unirest.Config;
import kong.unirest.HttpRequestSummary;
import kong.unirest.HttpResponse;
import kong.unirest.Interceptor;
import kong.unirest.UnirestInstance;
import seedu.address.wrapper.exceptions.RepoNotFoundException;
import seedu.address.wrapper.exceptions.UserInvalidException;

public class GithubWrapper {
    //@@author arnav-ag
    private final static String BASE_CHECK_USER_URL = "https://api.github.com/users/";
    private final static String BASE_GITHUB_URL = "https://www.github.com/";
    final private UserInfoWrapper userInfoWrapper;
    final private UserReposWrapper userReposWrapper;

    public GithubWrapper(String username) throws UserInvalidException, RepoNotFoundException {
        requireAllNonNull(username);

        UnirestInstance unirest = getDefaultUnirestInstance();
        checkUserExists(username, unirest);

        userInfoWrapper = new UserInfoWrapper(username, unirest);
        userReposWrapper = new UserReposWrapper(username, unirest);
    }

    public static UnirestInstance getDefaultUnirestInstance() {
        Config config = new Config()
            .interceptor(new Interceptor() {
                @Override
                public void onResponse(HttpResponse<?> response, HttpRequestSummary request, Config config) {
                    if (response.getStatus() == 404) {
                        throw new UserInvalidException(
                            "User does not exist. Please provide an existing GitHub username.");
                    }
                }

                @Override
                public HttpResponse<?> onFail(Exception e, HttpRequestSummary request, Config config)
                    throws RepoNotFoundException {
                    throw new RepoNotFoundException("Error while getting request, unable to get results.");
                }
            });
        return new UnirestInstance(config);
    }

    private void checkUserExists(String username, UnirestInstance unirest)
        throws UserInvalidException, RepoNotFoundException {
        unirest.get(BASE_CHECK_USER_URL + username).asEmpty();
    }

    public Optional<String> getName() {
        return userInfoWrapper.getName();
    }

    public Optional<String> getEmail() {
        return userInfoWrapper.getEmail();
    }

    public Optional<String> getAddress() {
        return userInfoWrapper.getLocation();
    }

    public String getUsername() {
        return userInfoWrapper.getUsername();
    }

    public String getAvatarUrl() {
        return userInfoWrapper.getAvatarUrl();
    }

    public String getUrl() {
        return userInfoWrapper.getUrl();
    }

    public ArrayList<Integer> getRepoIds() {
        return userReposWrapper.getIDs();
    }

    public String getRepoName(int id) {
        return userReposWrapper.getRepoName(id);
    }

    public String getRepoUrl(int id) {
        return userReposWrapper.getRepoUrl(id);
    }
}
