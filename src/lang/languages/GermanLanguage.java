package lang.languages;

import lang.ILanguageBundle;
import java.util.ResourceBundle;

/**
 * This class handles retrieving german language file as resource bundle. To get resource bundle, use object method.
 */
public class GermanLanguage implements ILanguageBundle {

    private final String baseName = "lang.languages.GUILang_de_DE";
    private final ResourceBundle germanLanguage = ResourceBundle.getBundle(baseName);
    public GermanLanguage(){

    }

    /**
     * Returns a german properties file as {@link ResourceBundle} object.
     * @return ResourceBundle object
     */
    @Override
    public ResourceBundle getLanguageBundle() {
        return ResourceBundle.getBundle(germanLanguage.getBaseBundleName());
    }
}
