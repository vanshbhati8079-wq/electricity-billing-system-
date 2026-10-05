import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class new_customer extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JTextField t1, t2, t3, t4, t5, t6, t7;
    JButton b1, b2;
    JPanel p1, p2;

    new_customer() {
        super("Add Customer");

        // Frame settings
        setLocation(300, 150);
        setSize(800, 500);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245)); // Light background

        // Left Image Panel
        p1 = new JPanel();
        p1.setBackground(Color.WHITE);
        p1.setLayout(new BorderLayout());
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("images/hicon1.jpg"));
        Image i3 = ic1.getImage().getScaledInstance(180, 400, Image.SCALE_SMOOTH);
        ImageIcon ic2 = new ImageIcon(i3);
        l8 = new JLabel(ic2);
        l8.setHorizontalAlignment(SwingConstants.CENTER);
        p1.add(l8, BorderLayout.CENTER);
        add(p1, BorderLayout.WEST);

        // Right Form Panel
        p2 = new JPanel();
        p2.setLayout(new GridBagLayout());
        p2.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Font labelFont = new Font("Tahoma", Font.BOLD, 14);
        Font textFont = new Font("Tahoma", Font.PLAIN, 14);

        l1 = new JLabel("Name");
        l2 = new JLabel("Meter No");
        l3 = new JLabel("Address");
        l4 = new JLabel("State");
        l5 = new JLabel("City");
        l6 = new JLabel("Email");
        l7 = new JLabel("Phone Number");

        JLabel[] labels = {l1, l2, l3, l4, l5, l6, l7};
        for (JLabel label : labels) {
            label.setFont(labelFont);
        }

        t1 = new JTextField(20);
        t2 = new JTextField(20);
        t3 = new JTextField(20);
        t4 = new JTextField(20);
        t5 = new JTextField(20);
        t6 = new JTextField(20);
        t7 = new JTextField(20);

        JTextField[] fields = {t1, t2, t3, t4, t5, t6, t7};
        for (JTextField field : fields) {
            field.setFont(textFont);
            field.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY)); // Underline style
        }

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0; gbc.gridy = 0; p2.add(l1, gbc);
        gbc.gridy++; p2.add(l2, gbc);
        gbc.gridy++; p2.add(l3, gbc);
        gbc.gridy++; p2.add(l4, gbc);
        gbc.gridy++; p2.add(l5, gbc);
        gbc.gridy++; p2.add(l6, gbc);
        gbc.gridy++; p2.add(l7, gbc);

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 0; p2.add(t1, gbc);
        gbc.gridy++; p2.add(t2, gbc);
        gbc.gridy++; p2.add(t3, gbc);
        gbc.gridy++; p2.add(t4, gbc);
        gbc.gridy++; p2.add(t5, gbc);
        gbc.gridy++; p2.add(t6, gbc);
        gbc.gridy++; p2.add(t7, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        b1 = new JButton("Submit");
        b2 = new JButton("Cancel");

        b1.setBackground(new Color(33, 150, 243));
        b1.setForeground(Color.WHITE);
        b2.setBackground(new Color(220, 53, 69));
        b2.setForeground(Color.WHITE);

        b1.setFocusPainted(false);
        b2.setFocusPainted(false);

        b1.setFont(new Font("Tahoma", Font.BOLD, 14));
        b2.setFont(new Font("Tahoma", Font.BOLD, 14));

        buttonPanel.add(b1);
        buttonPanel.add(b2);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        p2.add(buttonPanel, gbc);

        add(p2, BorderLayout.CENTER);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            String a = t1.getText();
            String c = t2.getText();
            String d = t3.getText();
            String e = t4.getText();
            String f = t5.getText();
            String g = t6.getText();
            String h = t7.getText();

            String q1 = "insert into emp values('" + a + "','" + c + "','" + d + "','" + e + "','" + f + "','" + g + "','" + h + "')";

            try {
                conn c1 = new conn();
                c1.s.executeUpdate(q1);
                JOptionPane.showMessageDialog(null, "New customer Created Successfully");
                this.setVisible(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (ae.getSource() == b2) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new new_customer().setVisible(true);
    }
}
