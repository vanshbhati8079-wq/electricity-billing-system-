import java.awt.*;
import javax.swing.*;

public class welcome {
    public static void main(String args[]) {
        SplashFrame f1 = new SplashFrame();
        f1.setVisible(true);

        try {
            Thread.sleep(7000); // Wait for 7 seconds
        } catch (Exception e) {
            e.printStackTrace();
        }

        f1.setVisible(false); // Close splash window
        new login().setVisible(true); // Open login page
    }

    public static class SplashFrame extends JFrame {
        SplashFrame() {
            super("Electricity Billing System");

            setLayout(new BorderLayout());

            ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("images/splash.jpg"));
            Image i1 = c1.getImage().getScaledInstance(720, 550, Image.SCALE_DEFAULT);
            ImageIcon i2 = new ImageIcon(i1);

            JLabel l1 = new JLabel(i2);
            add(l1, BorderLayout.CENTER);

            setSize(720, 550);
            setLocationRelativeTo(null); // Center of screen
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
