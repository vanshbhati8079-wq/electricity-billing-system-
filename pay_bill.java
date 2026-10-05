import javax.swing.*; 
import java.awt.*;
import java.net.URI;

public class pay_bill extends JFrame {

    public pay_bill() {
        setTitle("Pay Electricity Bill");
        setSize(400, 200);
        setLocationRelativeTo(null); // center on screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Click the button below to pay your bill online.");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);

        JButton openButton = new JButton("Go to Paytm");
        openButton.addActionListener(e -> openWebPage("https://paytm.com/electricity-bill-payment"));
        add(openButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to open browser.");
            ex.printStackTrace();
        }
    }
}
