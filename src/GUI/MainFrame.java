package GUI;

import com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme;
import lang.LanguageBundle;
import lang.languages.EnglishLanguage;
import lang.languages.GermanLanguage;
import lang.languages.PolishLanguage;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This class handles creation of {@link JFrame} object and filling its content. This class holds main() method where custom look and feel is set up.
 * If exception happens while setting up custom LaF, then default Swing LaF will be used.
 */
public class MainFrame extends JFrame {

    private static final int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4;
    private static final int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
    private static ResourceBundle language;

    /**
     * Default constructor which initializes object and sets every setting.
     */
    MainFrame(){
        setLanguage();
        setTitle(language.getString("title"));
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Sets proper {@link ResourceBundle} object which texts are used in every component texts or error messages.
     */
    private void setLanguage() {
        var locale = Locale.getDefault().getCountry();
        switch (locale) {
            case "PL" -> language = new LanguageBundle(new PolishLanguage()).bundle();
            case "DE" -> language = new LanguageBundle(new GermanLanguage()).bundle();
            default -> language = new LanguageBundle(new EnglishLanguage()).bundle();
        }
    }

    /**
     * Returns Resourcebundle object.
     * @return ResourceBundle object
     */
    public static ResourceBundle getLanguage(){
        return language;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                //loading custom Look and Feel
                FlatVuesionIJTheme.setup();
                new MainFrame().add(new MainPanel());
            } catch( Exception ex ) {
                //custom LaF failed, then launch with standard LaF
                new MainFrame().add(new MainPanel());
            }
        });
    }
}
