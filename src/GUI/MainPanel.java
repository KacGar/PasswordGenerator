package GUI;

import password.AlphabetBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * This class handles creation of every component which will be displayed in application.
 */
public class MainPanel extends JPanel {

    private static final ResourceBundle language = MainFrame.getLanguage();
    private static ArrayList<JComboBox<String>> boxes = new ArrayList<>();

    /**
     * Constructor which initializes every component and sets basically everything.
     */
    public MainPanel(){
        var headerString = language.getString("header");
        var headerLabel = new JLabel(headerString);
        headerLabel.setFocusable(false);
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headerLabel.setFont(headerLabel.getFont().deriveFont(20f));

        var lettersComboBox = new JComboBox<String>();
        lettersComboBox.addItem(language.getString("bigAndSmallBox"));
        lettersComboBox.addItem(language.getString("upperCaseBox"));
        lettersComboBox.addItem(language.getString("lowerCaseBox"));
        lettersComboBox.setSelectedIndex(0);

        var numsAndSpecialCharsComboBox = new JComboBox<String>();
        numsAndSpecialCharsComboBox.addItem(language.getString("noNumsAndSpecialBox"));
        numsAndSpecialCharsComboBox.addItem(language.getString("numbersBox"));
        numsAndSpecialCharsComboBox.addItem(language.getString("specialCharsBox"));
        numsAndSpecialCharsComboBox.addItem(language.getString("numsAndSpecialBox"));
        numsAndSpecialCharsComboBox.setSelectedIndex(3);

        var boxWrapper = new JPanel();
        boxWrapper.setLayout(new BorderLayout());
            var panel = new JPanel();
            panel.setLayout(new FlowLayout());
            panel.add(lettersComboBox);
            panel.add(numsAndSpecialCharsComboBox);
        boxWrapper.add(panel,BorderLayout.CENTER);

        var label = new JLabel(language.getString("labelValue"));
        label.setFont(label.getFont().deriveFont(20f));

        var passwordLengthValueText = new JLabel("16");
        passwordLengthValueText.setFont(passwordLengthValueText.getFont().deriveFont(20f));

        var passwordLengthSlider = new JSlider(JSlider.HORIZONTAL,
                                                8,24,16);
        passwordLengthSlider.setMajorTickSpacing(2);
        passwordLengthSlider.setMinorTickSpacing(1);
        passwordLengthSlider.setPaintTicks(true);
        passwordLengthSlider.setPaintLabels(true);
        passwordLengthSlider.addChangeListener(e -> passwordLengthValueText.setText(
                String.valueOf(passwordLengthSlider.getValue())));

        var textAndSliderWrapper = new JPanel();
        textAndSliderWrapper.setLayout(new GridLayout(0,1));
            var panel2 = new JPanel();
            panel2.setLayout(new FlowLayout());
            panel2.add(label);
            panel2.add(passwordLengthValueText);
        textAndSliderWrapper.add(panel2);
        textAndSliderWrapper.add(passwordLengthSlider);

        var generateBtn = new JButton(language.getString("generateBtn"));
        generateBtn.setFocusable(false);
        generateBtn.setFont(generateBtn.getFont().deriveFont(16f));
        generateBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        generateBtn.setVerticalTextPosition(SwingConstants.CENTER);
        generateBtn.addActionListener(e -> {
            AlphabetBuilder alphabetBuilder = new AlphabetBuilder();
            alphabetBuilder.letterSize(lettersComboBox.getSelectedIndex());
            alphabetBuilder.addNumsAndSpecialChars(numsAndSpecialCharsComboBox.getSelectedIndex());
            alphabetBuilder.passwordLength(passwordLengthSlider.getValue());
            var generatedPassword = alphabetBuilder.build().getPassword();
            var textField = new JTextField(generatedPassword.length());
            textField.setEditable(true);
            textField.setText(generatedPassword);
            JOptionPane.showMessageDialog(this,textField,language.getString("dialogTitle"),JOptionPane.WARNING_MESSAGE);
        });

        var btnWrapper = new JPanel();
        btnWrapper.setLayout(new BorderLayout());
        btnWrapper.add(generateBtn, BorderLayout.CENTER);

        //adding components to panel
        setLayout(new GridBagLayout());
        var gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.3;
        gc.fill = GridBagConstraints.BOTH;
        add(headerLabel,gc);
        gc.gridy += 1;
        gc.weighty = 0.2;
        add(boxWrapper,gc);
        gc.gridy += 1;
        gc.weighty = 0.3;
        add(textAndSliderWrapper,gc);
        gc.gridy += 1;
        gc.fill = GridBagConstraints.BOTH;
        add(btnWrapper,gc);
    }
}
