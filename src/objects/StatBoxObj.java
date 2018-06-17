package objects;

import java.util.ArrayList;
import java.util.Arrays;

public class StatBoxObj {
    private String[] initialOptions = {" ","+2","+2","+1","+0","+0","-1","-2"};
    public ArrayList<String> options = new ArrayList<>(Arrays.asList(initialOptions));
    public String currentSelected = null;
    public void removeOption(String toBeRemoved) {
        options.remove(toBeRemoved);
    }
    public void addOption(String toBeAdded) {
        options.add(toBeAdded);
    }



}
