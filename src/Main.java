import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        JFrame frame = new JFrame("App");
        frame.setContentPane(new TestGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
