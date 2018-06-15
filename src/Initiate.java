import javax.swing.*;

public class Initiate {
    public static void main(String[] args) {
        new Initiate();
    }
    private Initiate() {
        MainPage mainPage = new MainPage();
       SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame jFrame = new JFrame("Andrari Character sheet creator");
                jFrame.setContentPane(mainPage.mainpanel);
                jFrame.setSize(600,500);
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.pack();
                jFrame.setVisible(true);
                mainPage.giveValue();

            }
        });
    }
}
