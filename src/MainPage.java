import objects.*;
import org.json.JSONObject;
import races.*;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainPage {


    JPanel mainpanel;
    private JComboBox raceBox;
    private JComboBox cultureBonusBox;
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
    private JComboBox abilityComboBox;
    private JComboBox specialComboBox;
    private JComboBox specialComboBox2;
    private JComboBox specialComboBox3;
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
    private JComboBox[] specialAbilityBoxes = {specialComboBox,specialComboBox2,specialComboBox3};
    private Race[] races = {new Eldari(), new Kandra(), new Riddare(), new Uldinari()};
    private Occupation[] occupations = Occupation.values();
    private final StatBoxHandler statBoxHandler = new StatBoxHandler(new ComboBoxItem[] {
            new ComboBoxItem("default", " "),
            new ComboBoxItem("+2", "+2"),
            new ComboBoxItem("+1 0", "+1"),
            new ComboBoxItem("+1 1", "+1"),
            new ComboBoxItem("first +0", "+0"),
            new ComboBoxItem("second +0", "+0"),
            new ComboBoxItem("-1", "-1"),
            new ComboBoxItem("-2", "-2"),
    });
    private final StatBoxHandler specialAbilityBoxHandler = new StatBoxHandler(new ComboBoxItem[0]);
    public MainPage() {

        raceBox.setModel(new DefaultComboBoxModel(races));
        raceBox.addActionListener(e -> {
            for(JComboBox box : specialAbilityBoxes) {
                box.setSelectedIndex(0);
            }
            CharacterStat[] cultureBonuses = ((Race)raceBox.getSelectedItem()).getRaceBonuses();
            cultureBonusBox.setModel(new DefaultComboBoxModel(cultureBonuses));

        });
        CharacterStat[] cultureBonuses = ((Race)raceBox.getSelectedItem()).getRaceBonuses();
        cultureBonusBox.setModel(new DefaultComboBoxModel(cultureBonuses));



        occupationBox.setModel(new DefaultComboBoxModel(occupations));
        occupationBox.addActionListener(e -> {
            /*
            for(JComboBox box : specialAbilityBoxes) {
                box.setSelectedIndex(0);
            }
            */
            String[] abilities = ((Occupation)occupationBox.getSelectedItem()).getAbilities();
            abilityComboBox.setModel(new DefaultComboBoxModel(abilities));
        });

        String[] abilities = ((Occupation)occupationBox.getSelectedItem()).getAbilities();
        abilityComboBox.setModel(new DefaultComboBoxModel(abilities));



        for (int i = 0;i < statBoxes.length;i++) {
            final int currentIndex = i;
            statBoxHandler.registerBox(statBoxes[i]);

            DefaultComboBoxModel model = new DefaultComboBoxModel(new ComboBoxItem[] {new ComboBoxItem("beforeInitial", " "), new ComboBoxItem("beforeInitial", " "), new ComboBoxItem("beforeInitial", statBoxHandler.getLongestAvailableItem(statBoxes[i]))});
            statBoxes[i].setModel(model);
            statBoxes[i].setSelectedIndex(0);

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


        for (int i = 0; i < specialAbilityBoxes.length; i++) {
            final int currentIndex = i;
            final JComboBox currentBox = specialAbilityBoxes[i];
            specialAbilityBoxHandler.registerBox(specialAbilityBoxes[i]);

            Object[] specialAbilities = EUtil.combineArrays(Occupation.getDefaultAbilities(), ((Occupation)occupationBox.getSelectedItem()).getSpecialAbilities(), ((Race) raceBox.getSelectedItem()).getAvailableSpecialAbilities());
            List<ComboBoxItem> specialAbilityItems = new ArrayList<>();
            specialAbilityItems.add(new ComboBoxItem("default", "   "));
            for(int n = 0; n < specialAbilities.length; n++) {
                specialAbilityItems.add(new ComboBoxItem(String.valueOf(n),((SpecialAbility)specialAbilities[n]).getAbility()));
            }
            specialAbilityBoxHandler.setDefaults(specialAbilityItems.toArray(new ComboBoxItem[0]));

            DefaultComboBoxModel model = new DefaultComboBoxModel(new ComboBoxItem[] {new ComboBoxItem("beforeInitial"," "), new ComboBoxItem("beforeInitial", specialAbilityBoxHandler.getLongestAvailableItem(specialAbilityBoxes[i]))});
            specialAbilityBoxes[i].setModel(model);
            specialAbilityBoxes[i].setSelectedIndex(0);

            specialAbilityBoxes[i].addPopupMenuListener(new PopupMenuListener() {
                @Override
                public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                    //System.out.println("popup menu visible");
                    Object[] specialAbilities = EUtil.combineArrays(Occupation.getDefaultAbilities(), ((Occupation)occupationBox.getSelectedItem()).getSpecialAbilities(), ((Race) raceBox.getSelectedItem()).getAvailableSpecialAbilities());
                    List<ComboBoxItem> specialAbilityItems = new ArrayList<>();
                    specialAbilityItems.add(new ComboBoxItem("default", "   "));
                    for(int i = 0; i < specialAbilities.length; i++) {
                        specialAbilityItems.add(new ComboBoxItem(String.valueOf(i),((SpecialAbility)specialAbilities[i]).getAbility()));
                    }

                    specialAbilityBoxHandler.setDefaults(specialAbilityItems.toArray(new ComboBoxItem[0]));

                    currentBox.setSelectedIndex(0);
                    ComboBoxItem[] boxValues = specialAbilityBoxHandler.getAvailableStats(currentBox);
                    currentBox.setModel(new DefaultComboBoxModel(boxValues));

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

            specialAbilityBoxes[currentIndex].addActionListener(e -> {
                specialAbilityBoxHandler.setBoxId(currentBox, ((ComboBoxItem)currentBox.getSelectedItem()).getId());
            });

        }
    }

    public JSONObject generateSaveData() {
        Map<CharacterStat, Integer> statMap = new HashMap<>();
        for(int i = 0; i < statBoxes.length; i++) {
            JComboBox statBox = statBoxes[i];
            Map<JComboBox, String> comboBoxMap = statBoxHandler.getComboBoxMap();

            CharacterStat characterStat = null;
            switch (i) {
                case 0: characterStat = CharacterStat.KOMMUNIKATION;
                case 1: characterStat = CharacterStat.FYSIK;
                case 2: characterStat = CharacterStat.LIST;
                case 3: characterStat = CharacterStat.SMIDIGHET;
                case 4: characterStat = CharacterStat.PERCEPTION;
                case 5: characterStat = CharacterStat.STYRKA;
                case 6: characterStat = CharacterStat.VILJESTYRKA;
            }

            int value = Integer.parseInt(comboBoxMap.get(statBox).replaceAll("+", ""));

            statMap.put(characterStat, value);
        }


        SaveFileObj saveFile = new SaveFileObj();
        saveFile.setName(namnTextField.getText());
        saveFile.setTitle(titelTextField.getText());
        saveFile.setAge(ageTextField.getText());
        saveFile.setGender(genderTextField.getText());
        saveFile.setOccupation((Occupation) occupationBox.getSelectedItem());
        saveFile.setRace((Race) raceBox.getSelectedItem());
        saveFile.setCultureBonus((CharacterStat) cultureBonusBox.getSelectedItem());
        saveFile.setStats(statMap);
        saveFile.setHeight(lengthTextArea.getText());
        saveFile.setWeight(weightTextArea.getText());
        saveFile.setAbility(abilityComboBox.getSelectedItem().toString());
        saveFile.setSpecialAbilities(new ComboBoxItem[] {
                (ComboBoxItem) specialComboBox.getSelectedItem(),
                (ComboBoxItem) specialComboBox2.getSelectedItem(),
                (ComboBoxItem) specialComboBox3.getSelectedItem()
        });

        return saveFile.toJSON();
    }
}
