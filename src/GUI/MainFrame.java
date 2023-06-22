package GUI;

import com.formdev.flatlaf.IntelliJTheme;
import com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme;
import lang.LanguageBundle;
import lang.languages.EnglishLanguage;
import lang.languages.GermanLanguage;
import lang.languages.PolishLanguage;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * This class handles creation of {@link JFrame} object and filling its content. This class holds main() method where custom look and feel is set up.
 * If exception happens while setting up custom LaF, then default Swing LaF will be used.
 */
public class MainFrame extends JFrame {

    private static final int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 4;
    private static final int WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 4;
    private static ResourceBundle language;
    private static JPanel mainPanel;
    private static JFrame frame;

    /**
     * Default constructor which initializes object and sets every setting.
     */
    MainFrame(){
        setLanguage();
        setTitle(language.getString("title"));
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        mainPanel = new MainPanel();
        add(mainPanel);
        setJMenuBar(new Menu());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame = this;
        setVisible(true);
    }

    public static void updateTheme(int index){
        try {
            Themes themes = new Themes();
            Class<IntelliJTheme.ThemeLaf> c = (Class<IntelliJTheme.ThemeLaf>) Class.forName(themes.allThemes().get(index));
            Preferences root = Preferences.userRoot();
            Preferences node = root.node("passwordGenerator");
            node.put("laf", themes.allThemes().get(index));
            Method method = c.getDeclaredMethod("setup", null);
            method.invoke(null);
            UIManager.setLookAndFeel(c.getName());
            SwingUtilities.updateComponentTreeUI(frame.getRootPane());

        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | UnsupportedLookAndFeelException | InstantiationException exception) {
            exception.printStackTrace();
        }
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
                Preferences root = Preferences.userRoot();
                Preferences node = root.node("passwordGenerator");
                String className = node.get("laf", "empty");
                if (!className.equals("empty")){
                    Class<IntelliJTheme.ThemeLaf> c = (Class<IntelliJTheme.ThemeLaf>) Class.forName(className);
                    Method method = c.getDeclaredMethod("setup", null);
                    method.invoke(null);
                } else {
                    FlatVuesionIJTheme.setup();
                }
                new MainFrame();
            } catch( Exception ex ) {
                //custom LaF failed, then launch with standard LaF
                new MainFrame();
            }
        });
    }
}
