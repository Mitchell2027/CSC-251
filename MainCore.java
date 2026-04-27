// Mariah Mitchell
// CSC 251
// 4-26-2026
// Concrete Pad and Chain-Link Esitmator Application

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
public class MainCore extends JFrame {
    // Colors
    private static final Color BG_COLOR = new Color(245, 245, 245);   // light gray
    private static final Color PANEL_COLOR = Color.WHITE;
    private static final Color ACCENT_COLOR = new Color(70, 130, 180); // steel blue
    // Pad Presets
    private final String[] PAD_PRESETS = {
    "Custom",
    "Small Residential Pad (20x20)",
    "Garage / Workshop Pad (20x40)",
    "Small Warehouse (50x100)",
    "Mid Warehouse (100x150)",
    "Large Warehouse (150x200)",
    "Loading Apron (60x80)",
    "Parking Lot Section (100x200)",
    "Equipment Pad (10x10)",
    "Dumpster Pad (12x20)",
    "RV/Boat Slab (14x40)",
    "Basketball Court (50x84)"
};
    // ── Input Fields ──
    // Project Info
    JTextField fProjectName, fClientName, fLocation, fDate, fEstimator;
    // Concrete Pad
    JTextField fLength, fWidth, fThickness, fConcretePrice;
    // Labor
    JTextField fEmployees, fHours, fRate;
    // Fencing
    JCheckBox  chkFence;
    JTextField fPerimeter, fFencePrice;
    JPanel     fencePanel;

    JComboBox<String> padPresetBox;
 
    // ── Entry Point ──
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainCore::new);
    }
 
    
    // ── Constructor ───
    public MainCore() {
        setTitle("Concrete & Fence Estimator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(480, 700);
        setLocationRelativeTo(null);
        setResizable(false);
 
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(BorderFactory.createEmptyBorder(16, 20, 16, 20));
        root.setBackground(BG_COLOR);
        // Project Info Section
        root.add(buildSection("Project Info",
            row("Project Name:",  fProjectName  = field()),
            row("Client Name:",   fClientName   = field()),
            row("Location:",      fLocation     = field()),
            row("Date:",          fDate         = field()),
            row("Estimator:",     fEstimator    = field())
        ));
 
        root.add(Box.createVerticalStrut(10));

         // PAD PRESET DROPDOWN
        padPresetBox = new JComboBox<>(PAD_PRESETS);
        padPresetBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        padPresetBox.setAlignmentX(LEFT_ALIGNMENT);
        padPresetBox.setBackground(Color.WHITE);
        padPresetBox.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        padPresetBox.addActionListener(e -> applyPadPreset());
        root.add(new JLabel("Pad Preset:"));
        root.add(padPresetBox);
        root.add(Box.createVerticalStrut(10));

        // Concrete Pad Section
        root.add(buildSection("Concrete Pad",
            row("Length (ft):",        fLength        = field()),
            row("Width (ft):",         fWidth         = field()),
            row("Thickness (in):",     fThickness     = field()),
            row("Price per CY ($):",   fConcretePrice = field())
        ));
 
        root.add(Box.createVerticalStrut(10));
        // Labor Section
        root.add(buildSection("Labor",
            row("Employees:",          fEmployees = field()),
            row("Hours/Employee:",     fHours     = field()),
            row("Hourly Rate ($):",    fRate      = field())
        ));
 
        root.add(Box.createVerticalStrut(10));
 
        // Fencing toggle
        chkFence = new JCheckBox("Include Chain-Link Fencing");
        chkFence.setFont(new Font("SansSerif", Font.BOLD, 13));
        chkFence.setAlignmentX(LEFT_ALIGNMENT);
        chkFence.addActionListener(e -> fencePanel.setVisible(chkFence.isSelected()));
 
        fencePanel = buildSection("Fencing",
            row("Perimeter (ft):",     fPerimeter  = field()),
            row("Price per LF ($):",   fFencePrice = field())
        );
        fencePanel.setVisible(false);
 
        root.add(chkFence);
        root.add(Box.createVerticalStrut(6));
        root.add(fencePanel);
        root.add(Box.createVerticalStrut(14));
 
        // Buttons

        JComboBox<String> padPresetBox;
        JButton btnCalc  = new JButton("Calculate Estimate");
        JButton btnReset = new JButton("Reset");
 
        btnCalc.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnReset.setFont(new Font("SansSerif", Font.BOLD, 14));
 
        btnCalc.addActionListener(e -> calculate());
        btnReset.addActionListener(e -> reset());
        btnCalc.setBackground(ACCENT_COLOR);
        btnCalc.setForeground(Color.WHITE);
        btnCalc.setFocusPainted(false);

        btnReset.setBackground(Color.DARK_GRAY);
        btnReset.setForeground(Color.WHITE);
        btnReset.setFocusPainted(false);
 
        JPanel btnPanel = new JPanel(new GridLayout(1, 2, 8, 0));
        btnPanel.setAlignmentX(LEFT_ALIGNMENT);
        btnPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnPanel.add(btnCalc);
        btnPanel.add(btnReset);
        root.add(btnPanel);
 
        JScrollPane scroll = new JScrollPane(root);
        scroll.setBorder(null);
        scroll.setBackground(BG_COLOR);
        scroll.getViewport().setBackground(BG_COLOR);
        add(scroll);
        setVisible(true);
    }
     //  PRESET LOGIC
    private void applyPadPreset() {

        String selected = (String) padPresetBox.getSelectedItem();

        if (selected == null) return;

        switch (selected) {
            case "Small Residential Pad (20x20)": fLength.setText("20"); fWidth.setText("20"); break;
            case "Garage / Workshop Pad (20x40)": fLength.setText("20"); fWidth.setText("40"); break;
            case "Small Warehouse (50x100)": fLength.setText("50"); fWidth.setText("100"); break;
            case "Mid Warehouse (100x150)": fLength.setText("100"); fWidth.setText("150"); break;
            case "Large Warehouse (150x200)": fLength.setText("150"); fWidth.setText("200"); break;
            case "Loading Apron (60x80)": fLength.setText("60"); fWidth.setText("80"); break;
            case "Parking Lot Section (100x200)": fLength.setText("100"); fWidth.setText("200"); break;
            case "Equipment Pad (10x10)": fLength.setText("10"); fWidth.setText("10"); break;
            case "Dumpster Pad (12x20)": fLength.setText("12"); fWidth.setText("20"); break;
            case "RV/Boat Slab (14x40)": fLength.setText("14"); fWidth.setText("40"); break;
            case "Basketball Court (50x84)": fLength.setText("50"); fWidth.setText("84"); break;
            case "Custom": fLength.setText(""); fWidth.setText(""); break;
        }
    }
    // ── Calculate ─
    private void calculate() {
        try {
            // Project Info
            String projectName = require(fProjectName, "Project Name");
            String clientName  = require(fClientName,  "Client Name");
            String location    = fLocation.getText().trim();
            String date        = require(fDate,        "Date");
            String estimator   = require(fEstimator,   "Estimator");
 
            ProjectInfo project = new ProjectInfo(projectName, clientName, location, date, estimator);
 
            // Concrete Pad
            double length        = positiveDouble(fLength,        "Length");
            double width         = positiveDouble(fWidth,         "Width");
            double thickness     = positiveDouble(fThickness,     "Thickness");
            double concretePrice = positiveDouble(fConcretePrice, "Concrete Price");
 
            ConcretePad pad = new ConcretePad(length, width, thickness, concretePrice);
            pad.calculate();
 
            // Labor
            int    employees = positiveInt(fEmployees, "Employees");
            double hours     = positiveDouble(fHours,  "Hours");
            double rate      = positiveDouble(fRate,   "Rate");
 
            LaborEstimate labor = new LaborEstimate(employees, hours, rate);
            labor.calculate();
 
            // Fencing
            FenceEstimate fence = null;
            if (chkFence.isSelected()) {
                double perimeter  = positiveDouble(fPerimeter,  "Perimeter");
                double fencePrice = positiveDouble(fFencePrice, "Fence Price");
                fence = new FenceEstimate(perimeter, fencePrice);
                fence.calculate();
            }
 
            // Grand Total
            double fenceCost  = fence != null ? fence.totalCost : 0;
            double grandTotal = pad.materialCost + labor.laborCost + fenceCost;
 
            // Build Summary
            StringBuilder sb = new StringBuilder();
            sb.append(project.getFormattedInfo()).append("\n");
            sb.append(pad.getFormattedInfo()).append("\n");
            sb.append(labor.getFormattedInfo()).append("\n");
            if (fence != null) sb.append(fence.getFormattedInfo()).append("\n");
            sb.append("=== TOTAL ===\n");
            sb.append(String.format("Grand Total:      $%.2f\n", grandTotal));
 
            JOptionPane.showMessageDialog(this, sb.toString(), "Estimate Summary",
                                          JOptionPane.INFORMATION_MESSAGE);
 
            // Save to CSV
            CSVHandler.saveEstimate("estimates.csv",
                projectName, clientName, date,
                pad.area, pad.adjustedVolume,
                pad.materialCost, labor.laborCost, fenceCost, grandTotal);
 
        } catch (AppException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }
 
    // ── Reset ─────────────────────────────────────────────────────────────────
    private void reset() {
        fProjectName.setText("");
        fClientName.setText("");
        fLocation.setText("");
        fDate.setText("");
        fEstimator.setText("");
        fLength.setText("");
        fWidth.setText("");
        fThickness.setText("");
        fConcretePrice.setText("");
        fEmployees.setText("");
        fHours.setText("");
        fRate.setText("");
        fPerimeter.setText("");
        fFencePrice.setText("");
        chkFence.setSelected(false);
        fencePanel.setVisible(false);
    }
 
    // ── Validation Helpers ────────────────────────────────────────────────────
    private String require(JTextField f, String name) throws AppException {
        String val = f.getText().trim();
        if (val.isEmpty()) throw new AppException(name + " cannot be empty.");
        return val;
    }
 
    private double positiveDouble(JTextField f, String name) throws AppException {
        try {
            double val = Double.parseDouble(f.getText().trim());
            if (val < 0) throw new AppException(name + " must be a positive number.");
            return val;
        } catch (NumberFormatException e) {
            throw new AppException(name + " must be a valid number.");
        }
    }
 
    private int positiveInt(JTextField f, String name) throws AppException {
        try {
            int val = Integer.parseInt(f.getText().trim());
            if (val < 0) throw new AppException(name + " must be a positive whole number.");
            return val;
        } catch (NumberFormatException e) {
            throw new AppException(name + " must be a valid whole number.");
        }
    }
 
    private static class AppException extends Exception {
        AppException(String msg) { super(msg); }
    }
 
    // ── UI Helpers ────────────────────────────────────────────────────────────
    private JTextField field() {
        JTextField f = new JTextField(20);
        f.setFont(new Font("SansSerif", Font.PLAIN, 13));
        return f;
    }
 
    private JPanel row(String label, JTextField field) {
        JPanel p = new JPanel(new BorderLayout(8, 0));
        p.setAlignmentX(LEFT_ALIGNMENT);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        p.setBackground(PANEL_COLOR);

 
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lbl.setPreferredSize(new Dimension(150, 26));
        lbl.setForeground(new Color(60, 60, 60));
        
        field.setBackground(Color.WHITE);
        field.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
 
        p.add(lbl,   BorderLayout.WEST);
        p.add(field, BorderLayout.CENTER);
        return p;
    }
 
    private JPanel buildSection(String title, JPanel... rows) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), title,
            javax.swing.border.TitledBorder.LEFT,
            javax.swing.border.TitledBorder.TOP,
            new Font("SansSerif", Font.BOLD, 13)
        ));
        p.setAlignmentX(LEFT_ALIGNMENT);
 
        for (JPanel row : rows) {
            p.add(row);
            p.add(Box.createVerticalStrut(6));
        }
        return p;
    }
}