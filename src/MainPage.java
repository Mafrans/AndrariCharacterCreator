import objects.StatBoxObj;

import javax.swing.*;
import java.awt.event.ContainerAdapter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;


public class MainPage {


    JPanel mainpanel;
    private JTextArea namnTextArea;
    private JTextArea titelGradTextArea;
    private JCheckBox kommunicationCheckBox;
    private JCheckBox fysikCheckBox;
    private JCheckBox listCheckBox;
    private JCheckBox smidighetCheckBox;
    private JCheckBox perceptionCheckBox;
    private JCheckBox styrkaCheckBox;
    private JCheckBox viljestyrkaCheckBox;
    private JComboBox RaceBox;
    private JComboBox CultureBonusBox;
    private JTextField lengthTextArea;
    private JTextField weightTextArea;
    private JButton saveToFileButton;
    private JButton generateSheetButton;
    private JButton loadFileButton;
    private JTextArea genderTextArea;
    private JTextArea occupationTextArea;
    private JTextArea ageTextArea;
    private JComboBox statBox0;
    private JComboBox statBox1;
    private JComboBox statBox2;
    private JComboBox statBox3;
    private JComboBox statBox4;
    private JComboBox statBox5;
    private JComboBox statBox6;

    private StatBoxObj[] statBoxObjs = new StatBoxObj[7];
    public MainPage() {


    }
    public void giveValue() {
        for (int i = 0; i < statBoxObjs.length; i++) {
            statBoxObjs[i].setInitialOptions();
        }
        JComboBox[] statBoxes = {statBox0, statBox1, statBox2, statBox3, statBox4, statBox5, statBox6};
        for (int i = 0; i < statBoxes.length; i++) {
            for (int j = 0; j < statBoxObjs[i].options.size(); j++) {
                statBoxes[i].addItem(statBoxObjs[i].options.get(j));
            }

        }
    }


}
