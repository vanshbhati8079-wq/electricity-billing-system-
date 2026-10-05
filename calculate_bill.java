import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class calculate_bill extends JFrame implements ActionListener {
    JLabel heading, meterLabel, unitLabel, monthLabel, imageLabel;
    JTextField unitField;
    Choice meterChoice, monthChoice;
    JButton submitBtn, cancelBtn;
    JPanel formPanel;

    calculate_bill() {
        super("Calculate Bill");

        // Frame settings
        setLocation(300, 150);
        setSize(800, 500);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        // Heading
        heading = new JLabel("Calculate Electricity Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 26));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(heading, BorderLayout.NORTH);

        // Left Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(180, 300, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        imageLabel = new JLabel(i3);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.WEST);

        // Form Panel
        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);

        Font labelFont = new Font("Tahoma", Font.BOLD, 14);
        Font textFont = new Font("Tahoma", Font.PLAIN, 14);

        meterLabel = new JLabel("Meter No");
        monthLabel = new JLabel("Month");
        unitLabel = new JLabel("Units Consumed");

        JLabel[] labels = {meterLabel, monthLabel, unitLabel};
        for (JLabel label : labels) {
            label.setFont(labelFont);
        }

        meterChoice = new Choice();
        for (int i = 1001; i <= 1010; i++) {
            meterChoice.add("" + i);
        }

        monthChoice = new Choice();
        String[] months = {"January", "February", "March", "April", "May", "June", 
                           "July", "August", "September", "October", "November", "December"};
        for (String m : months) {
            monthChoice.add(m);
        }

        unitField = new JTextField(20);
        unitField.setFont(textFont);
        unitField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.GRAY));

        // Adding form components
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(meterLabel, gbc);
        gbc.gridy++; formPanel.add(monthLabel, gbc);
        gbc.gridy++; formPanel.add(unitLabel, gbc);

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 1; gbc.gridy = 0; formPanel.add(meterChoice, gbc);
        gbc.gridy++; formPanel.add(monthChoice, gbc);
        gbc.gridy++; formPanel.add(unitField, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        submitBtn = new JButton("Submit");
        cancelBtn = new JButton("Cancel");

        submitBtn.setBackground(new Color(33, 150, 243));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setFont(new Font("Tahoma", Font.BOLD, 14));

        cancelBtn.setBackground(new Color(220, 53, 69));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);
        cancelBtn.setFont(new Font("Tahoma", Font.BOLD, 14));

        buttonPanel.add(submitBtn);
        buttonPanel.add(cancelBtn);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        submitBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitBtn) {
            try {
                String meterNo = meterChoice.getSelectedItem();
                String unitsStr = unitField.getText();
                String month = monthChoice.getSelectedItem();

                int units = Integer.parseInt(unitsStr);
                int totalBill = units * 7 + 50 + 12 + 102 + 20 + 50; // Charges

                String q = "insert into bill (MeterNumber, month, units, amount) values('" + meterNo + "','" + month + "','" + unitsStr + "','" + totalBill + "')";
                conn c1 = new conn();
                c1.s.executeUpdate(q);

                JOptionPane.showMessageDialog(null, "Bill Updated Successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error Occurred! Check input.");
            }
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new calculate_bill().setVisible(true);
    }
}
