import objects.ComboBoxItem;
import objects.Occupation;
import races.*;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.event.*;


public class MainPage {


    JPanel mainpanel;
    private JComboBox RaceBox;
    private JComboBox CultureBonusBox;
    private JTextField lengthTextArea;
    private JTextField weightTextArea;
    private JComboBox statBox0;
    private JComboBox statBox1;
    private JComboBox statBox2;
    private JComboBox statBox3;
    private JComboBox statBox4;
    private JComboBox statBox5;
    private JComboBox statBox6;
    private JLabel fysikLabel;
    private JLabel listLabel;
    private JLabel smidighetLabel;
    private JLabel perceptionLabel;
    private JLabel styrkaLabel;
    private JLabel kommunicationLabel;
    private JComboBox occupationBox;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JLabel förmågaLabel;
    private JLabel specialFörmågorLabel;
    private JPanel StatsPanel;
    private JTextField namnTextField;
    private JTextField titelTextField;
    private JTextField ageTextField;
    private JTextField genderTextField;
    private JButton loadFileButton;
    private JButton saveToFileButton;
    private JButton generateSheetButton;
    private JComboBox[] statBoxes = {statBox0, statBox1, statBox2, statBox3, statBox4, statBox5, statBox6};
    private Race[] races = {new Eldari(), new Kandra(), new Riddare(), new Uldinari()};
    private Occupation[] occupations = Occupation.values();
    public MainPage() {
        final StatBoxHandler statBoxHandler = new StatBoxHandler();
        for (int i = 0;i < statBoxes.length;i++) {
            final int currentIndex = i;
            statBoxHandler.registerBox(statBoxes[i]);

            DefaultComboBoxModel model = new DefaultComboBoxModel(new ComboBoxItem[] {new ComboBoxItem("beforeInitial", "   ")});
            statBoxes[i].setModel(model);

            statBoxes[i].addPopupMenuListener(new PopupMenuListener() {
                @Override
                public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                    //System.out.println("popup menu visible");

                    statBoxes[currentIndex].setSelectedIndex(0);
                    ComboBoxItem[] boxValues = statBoxHandler.getAvailableStats(statBoxes[currentIndex]);
                    statBoxes[currentIndex].setModel(new DefaultComboBoxModel(boxValues));

                    //System.out.println(Arrays.toString(boxValues));
                }

                @Override
                public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                    //System.out.println("popup menu invisible");
                }

                @Override
                public void popupMenuCanceled(PopupMenuEvent e) {
                    // Do nothing
                }
            });
            statBoxes[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //System.out.println(e.getActionCommand());
                    statBoxHandler.setBoxId(statBoxes[currentIndex], ((ComboBoxItem)statBoxes[currentIndex].getSelectedItem()).getId());
                }
            });
        }

        RaceBox.setModel(new DefaultComboBoxModel(races));
        occupationBox.setModel(new DefaultComboBoxModel(occupations));

    }
}
