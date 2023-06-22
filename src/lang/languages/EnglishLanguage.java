package lang.languages;

import lang.ILanguageBundle;
import java.util.ResourceBundle;

/**
 * This class handles retrieving english language file as resource bundle. To get resource bundle, use object method.
 */
public class EnglishLanguage implements ILanguageBundle {

    public EnglishLanguage(){}

    private final String baseName = "lang.languages.GUILang_en";
    private final ResourceBundle englishLanguage = ResourceBundle.getBundle(baseName);

    /**
     * Returns a english properties file as {@link ResourceBundle} object.
     * @return ResourceBundle object
     */
    @Override
    public ResourceBundle getLanguageBundle() {
        return ResourceBundle.getBundle(englishLanguage.getBaseBundleName());
    }
}
