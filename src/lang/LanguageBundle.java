package lang;

import java.util.ResourceBundle;

public class LanguageBundle{

    private final ILanguageBundle languageBundle;

    public LanguageBundle(ILanguageBundle language){
        this.languageBundle = language;
    }

    public ResourceBundle bundle(){
        return languageBundle.getLanguageBundle();
    }
}
