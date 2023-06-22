package lang.languages;

import lang.ILanguageBundle;
import java.util.ResourceBundle;

/**
 * This class handles retrieving polish language file as resource bundle. To get resource bundle, use object method.
 */
public class PolishLanguage implements ILanguageBundle {

    public PolishLanguage(){}

    private final String baseName = "lang.languages.GUILang_pl_PL";
    private final ResourceBundle polishLanguage = ResourceBundle.getBundle(baseName);

    /**
     * Returns a polish properties file as {@link ResourceBundle} object.
     * @return ResourceBundle object
     */
    @Override
    public ResourceBundle getLanguageBundle() {
        return ResourceBundle.getBundle(polishLanguage.getBaseBundleName());
    }
}
