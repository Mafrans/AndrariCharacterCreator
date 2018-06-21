import objects.ComboBoxItem;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.*;

public class StatBoxHandler {
    public Map<JComboBox, String> getComboBoxMap() {
        return comboBoxMap;
    }

    private ComboBoxItem[] stats = null;
    public StatBoxHandler(ComboBoxItem[] stats) {
        this.stats = stats;
    }
    private final Map<JComboBox, String> comboBoxMap = new HashMap<>();

    public void registerBox(JComboBox comboBox) {
        comboBoxMap.put(comboBox, "default"); // Default value
    }

    public void setBoxId(JComboBox box, String id) {
        comboBoxMap.put(box, id);
    }

    public ComboBoxItem[] getAvailableStats(JComboBox comboBox) {
        List<ComboBoxItem> statsToUse = new ArrayList<>();

        for (int i = 0; i < stats.length; i++) {
            String statId = stats[i].getId();
            boolean useValue = true;
            for (JComboBox box : comboBoxMap.keySet()) {
                String id = comboBoxMap.get(box);
                if (id.equals("default")) continue; // Skip value

                System.out.println(statId + " ?= " + id);
                if (id.equals(statId)) {
                    //System.out.println("Second equals triggers");
                    useValue = false;
                    break;
                }
            }

            if (useValue) {
                statsToUse.add(stats[i]);
            }
        }
        //System.out.println("--------------------------");
        return statsToUse.toArray(new ComboBoxItem[0]);
    }

    public ComboBoxItem[] getDefaults() {
        return stats;
    }

    public void setDefaults(ComboBoxItem[] defaults) {
        stats = defaults;
    }

    public String getLongestItem() {
        String longestItem = "";
        for(ComboBoxItem item : stats) {
            if(item.getValue().length() > longestItem.length()) {
                longestItem = item.getValue();
            }
        }
        return longestItem;
    }

    public String getLongestAvailableItem(JComboBox box) {
        String longestItem = "";
        for(ComboBoxItem item : getAvailableStats(box)) {
            if(item.getValue().length() > longestItem.length()) {
                longestItem = item.getValue();
            }
        }
        return longestItem;
    }
}
