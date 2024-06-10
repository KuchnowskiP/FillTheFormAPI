package pl.edu.pwr.pkuchnowski.filltheformapi.authorization;

/**
 * Interface for authorizing the user.
 */

public interface Authorizer {
    /**
     * Authorizes the user.
     */
    void authorize();
}
