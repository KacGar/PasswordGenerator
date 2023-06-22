package GUI;

import javax.swing.*;
import java.util.*;

/**
 * This class handles of defining {@link JMenuBar} for application.
 */
public class Menu extends JMenuBar {

    private static final ResourceBundle language = MainFrame.getLanguage();

    Menu(){
        JMenu optionsMenu = new JMenu(language.getString("optionsMenu"));

        JMenuItem changeLaFMenuItem = new JMenuItem(language.getString("changeLaFMenuItem"));
        changeLaFMenuItem.addActionListener(e -> {
            var allthemes = createComboOfThemes();
            JOptionPane.showMessageDialog(null,allthemes,language.getString("changeLaFMenuItem"),JOptionPane.PLAIN_MESSAGE);
        });
        optionsMenu.add(changeLaFMenuItem);
        add(optionsMenu);
    }

    /**
     * Returns Set collection of every theme found in LaF module.
     * @return Set<String> object with names of all themes
     */
    private static Set<String> namesOfAllThemes(){
        var list = new Themes().allThemes();
        var listForCombo = new HashSet<String>();
        for (String s : list) {
            int index = s.lastIndexOf(".") + 1;
            listForCombo.add(s.substring(index));
        }
        return listForCombo;
    }

    /**
     * Creates list of every theme available for user in form of {@link JComboBox}
     * @return JComboBox<String> object
     */
    private static JComboBox<String> createComboOfThemes(){
        JComboBox<String> themesCombo = new JComboBox<>();
        var list = namesOfAllThemes();
        for (String s : list){
            themesCombo.addItem(s);
        }
        themesCombo.addItemListener(e -> {
            MainFrame.updateTheme(themesCombo.getSelectedIndex());
        });
        return themesCombo;
    }
}
