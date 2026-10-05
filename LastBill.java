import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class LastBill extends JFrame implements ActionListener {
    JLabel headingLabel;
    JTextArea billTextArea;
    JButton generateBtn;
    Choice meterChoice;
    JPanel topPanel;

    LastBill() {
        super("Last Bill Details");

        setSize(600, 800);
        setLocation(400, 50);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        // Heading
        headingLabel = new JLabel("Generate Bill");
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Meter No dropdown
        meterChoice = new Choice();
        for (int i = 1001; i <= 1010; i++) {
            meterChoice.add("" + i);
        }

        // Top Panel
        topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        topPanel.setBackground(new Color(255, 255, 255));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(headingLabel);
        topPanel.add(meterChoice);

        add(topPanel, BorderLayout.NORTH);

        // Text Area
        billTextArea = new JTextArea();
        billTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        billTextArea.setEditable(false);
        billTextArea.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(billTextArea);
        add(scrollPane, BorderLayout.CENTER);

        // Generate Button
        generateBtn = new JButton("Generate Bill");
        generateBtn.setBackground(new Color(33, 150, 243));
        generateBtn.setForeground(Color.WHITE);
        generateBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        generateBtn.setFocusPainted(false);
        generateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(255, 255, 255));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(generateBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        generateBtn.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            conn c = new conn();
            billTextArea.setText(""); // Clear previous

            ResultSet rs = c.s.executeQuery("select * from emp where meter_number = " + meterChoice.getSelectedItem());

            if (rs.next()) {
                billTextArea.append("\n    Customer Name: " + rs.getString("name"));
                billTextArea.append("\n    Meter Number : " + rs.getString("meter_number"));
                billTextArea.append("\n    Address      : " + rs.getString("address"));
                billTextArea.append("\n    State        : " + rs.getString("state"));
                billTextArea.append("\n    City         : " + rs.getString("city"));
                billTextArea.append("\n    Email        : " + rs.getString("email"));
                billTextArea.append("\n    Phone Number : " + rs.getString("phone"));
                billTextArea.append("\n-------------------------------------------------------------\n");
            } else {
                billTextArea.append("No Customer Found.\n");
            }

            billTextArea.append("\nDetails of the Last Bills:\n\n");

            rs = c.s.executeQuery("select * from bill where MeterNumber = " + meterChoice.getSelectedItem());

            boolean billFound = false;
            while (rs.next()) {
                billTextArea.append("  " + rs.getString("month") + "  -  ₹" + rs.getString("amount") + "\n");
                billFound = true;
            }

            if (!billFound) {
                billTextArea.append("\nNo previous bills found.\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data!");
        }
    }

    public static void main(String[] args) {
        new LastBill().setVisible(true);
    }
}
