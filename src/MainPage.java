import objects.*;
import org.json.JSONObject;
import races.*;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;


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
    private ComboBoxItem[] defaultStats = new ComboBoxItem[]{
            new ComboBoxItem("default", " "),
            new ComboBoxItem("+2", "+2"),
            new ComboBoxItem("+1 0", "+1"),
            new ComboBoxItem("+1 1", "+1"),
            new ComboBoxItem("+0 0", "+0"),
            new ComboBoxItem("+0 1", "+0"),
            new ComboBoxItem("-1", "-1"),
            new ComboBoxItem("-2", "-2"),
    };
    private StatBoxHandler statBoxHandler = new StatBoxHandler(defaultStats);
    private StatBoxHandler specialAbilityBoxHandler = new StatBoxHandler(new ComboBoxItem[0]);

    public MainPage() {
        final MainPage mainPage = this;
        final JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Character Sheet", "sheet", "sht"));
        saveToFileButton.addActionListener(e -> {
            //Handle save button action.
            fileChooser.setDialogTitle("Save Character Sheet");
            fileChooser.setApproveButtonText("Save Sheet");

            int returnVal = fileChooser.showSaveDialog(mainPage.mainpanel);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if(!file.getName().endsWith(".sheet")) {
                    file = new File(file.getParent(), file.getName() + ".sheet");
                }

                EUtil.getLogger().info("Saving file " + file.getName() + ".");

                try {
                    EUtil.createFile(file, generateSaveData().toString());
                }
                catch (IOException ex) {
                    EUtil.getLogger().severe("Could not save file!");
                    ex.printStackTrace();
                }
            }
            else {
                EUtil.getLogger().info("Save command cancelled by user.");
            }
        });

        loadFileButton.addActionListener(e -> {
            fileChooser.setDialogTitle("Open Character Sheet");
            int returnVal = fileChooser.showOpenDialog(mainPage.mainpanel);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                EUtil.getLogger().info("Loading file " + file.getName() + ".");

                String jsonString = null;
                try {
                    jsonString = EUtil.readTextFile(file);
                }
                catch (IOException e1) {
                    EUtil.getLogger().severe("IO Error while opening file!");
                    e1.printStackTrace();
                    return;
                }

                SaveFileObj saveFileObj = null;
                try {
                    saveFileObj = SaveFileObj.fromJSON(new JSONObject(jsonString));
                }
                catch (ClassNotFoundException | IllegalAccessException | InstantiationException e1) {
                    EUtil.getLogger().info("File " + file.getName() + "does not contain a valid Character Sheet.");
                    e1.printStackTrace();
                    return;
                }

                namnTextField.setText(saveFileObj.getName());
                titelTextField.setText(saveFileObj.getTitle());
                ageTextField.setText(saveFileObj.getAge());
                genderTextField.setText(saveFileObj.getGender());
                occupationBox.setSelectedItem(saveFileObj.getOccupation());
                raceBox.setSelectedItem(saveFileObj.getRace());
                cultureBonusBox.setSelectedItem(saveFileObj.getCultureBonus());
                abilityComboBox.setSelectedItem(saveFileObj.getAbility());

                Object[] specialAbilities = Occupation.getAllAbilities();
                List<ComboBoxItem> specialAbilityItems = new ArrayList<>();
                specialAbilityItems.add(new ComboBoxItem("default", "   "));
                for(int i = 0; i < specialAbilities.length; i++) {
                    specialAbilityItems.add(new ComboBoxItem(String.valueOf(i),((SpecialAbility)specialAbilities[i]).getAbility()));
                }

                specialAbilityBoxHandler = new StatBoxHandler(specialAbilityItems.toArray(new ComboBoxItem[0]));
                if(saveFileObj.getSpecialAbilities() != null) {
                    for(int i = 0; i < saveFileObj.getSpecialAbilities().length; i++) {
                        JComboBox box = specialAbilityBoxes[i];
                        ComboBoxItem item = saveFileObj.getSpecialAbilities()[i];

                        ComboBoxItem comboBoxItem = null;
                        for(ComboBoxItem defaultItem : specialAbilityItems.toArray(new ComboBoxItem[0])) {
                            if(defaultItem.getId().equals(item.getId())) {
                                comboBoxItem = defaultItem;
                                break;
                            }
                        }

                        System.out.println(box + "\n- " + item.getId() + ": " + item.getValue());
                        box.setModel(new DefaultComboBoxModel(specialAbilityBoxHandler.getAvailableStats(box)));

                        box.setSelectedItem(comboBoxItem);
                        specialAbilityBoxHandler.setBoxId(box, item.getId());
                    }
                }

                statBoxHandler = new StatBoxHandler(defaultStats);
                for(CharacterStat stat : saveFileObj.getStats().keySet()) {
                    int index = 0;
                    switch(stat) {
                        case KOMMUNIKATION: index = 0; break;
                        case FYSIK: index = 1; break;
                        case LIST: index = 2; break;
                        case SMIDIGHET: index = 3; break;
                        case PERCEPTION: index = 4; break;
                        case STYRKA: index = 5; break;
                        case VILJESTYRKA: index = 6; break;
                    }
                    String id = saveFileObj.getStats().get(stat);

                    JComboBox comboBox = statBoxes[index];
                    comboBox.setModel(new DefaultComboBoxModel(statBoxHandler.getAvailableStats(comboBox)));

                    ComboBoxItem comboBoxItem = null;
                    for(ComboBoxItem defaultItem : defaultStats) {
                        if(defaultItem.getId().equals(id)) {
                            comboBoxItem = defaultItem;
                            break;
                        }
                    }


                    if (comboBoxItem != null) {
                        comboBox.setSelectedItem(comboBoxItem);
                        statBoxHandler.setBoxId(comboBox, comboBoxItem.getId());
                    }
                }

                lengthTextArea.setText(saveFileObj.getHeight());
                weightTextArea.setText(saveFileObj.getWeight());
            }
            else {
                EUtil.getLogger().info("Load command cancelled by user.");
            }
        });



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
                    statBoxes[currentIndex].setSelectedIndex(0);
                    ComboBoxItem[] boxValues = statBoxHandler.getAvailableStats(statBoxes[currentIndex]);
                    statBoxes[currentIndex].setModel(new DefaultComboBoxModel(boxValues));
                }

                @Override
                public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}

                @Override
                public void popupMenuCanceled(PopupMenuEvent e) {}
            });
            statBoxes[i].addActionListener(e -> {
                statBoxHandler.setBoxId(statBoxes[currentIndex], ((ComboBoxItem)statBoxes[currentIndex].getSelectedItem()).getId());
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
        Map<CharacterStat, String> statMap = new HashMap<>();
        for(int i = 0; i < statBoxes.length; i++) {
            JComboBox statBox = statBoxes[i];

            CharacterStat characterStat = null;
            switch (i) {
                case 0: characterStat = CharacterStat.KOMMUNIKATION; break;
                case 1: characterStat = CharacterStat.FYSIK; break;
                case 2: characterStat = CharacterStat.LIST; break;
                case 3: characterStat = CharacterStat.SMIDIGHET; break;
                case 4: characterStat = CharacterStat.PERCEPTION; break;
                case 5: characterStat = CharacterStat.STYRKA; break;
                case 6: characterStat = CharacterStat.VILJESTYRKA; break;
            }

            String id = ((ComboBoxItem)statBox.getSelectedItem()).getId();

            statMap.put(characterStat, id);
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
