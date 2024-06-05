package pl.edu.pwr.pkuchnowski.FillTheFormAPI.Authorization;

import com.google.api.services.script.Script;

public interface IScriptServiceProvider {
    Script getScriptService();
}
