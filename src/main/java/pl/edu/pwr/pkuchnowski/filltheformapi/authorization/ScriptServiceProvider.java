package pl.edu.pwr.pkuchnowski.filltheformapi.authorization;

import com.google.api.services.script.Script;

/**
 * Interface for providing the script service.
 */

public interface ScriptServiceProvider {
    /**
     * Gets the script service.
     * @return The script service.
     */
    Script getScriptService();
}
