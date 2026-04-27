// ConcretePad.java
// Calculates concrete volume and material cost for a pad
 
public class ConcretePad {

    double length;
    double width;
    double thicknessInches;
    double pricePerCY;
 
    double area;
    double rawVolume;
    double adjustedVolume;
    double materialCost;
 
    public ConcretePad(double length, double width, double thicknessInches, double pricePerCY) {
        this.length          = length;
        this.width           = width;
        this.thicknessInches = thicknessInches;
        this.pricePerCY      = pricePerCY;
    }
 
    public void calculate() {
        double thicknessFt = thicknessInches / 12.0;
        area           = length * width;
        rawVolume      = (area * thicknessFt) / 27.0;
        adjustedVolume = rawVolume * 1.08;   // 8% waste
        materialCost   = adjustedVolume * pricePerCY;
    }
 
    public String getFormattedInfo() {
        return "=== CONCRETE PAD ===\n" +
               String.format("Dimensions:       %.0f ft x %.0f ft\n", length, width) +
               String.format("Thickness:        %.1f inches\n", thicknessInches) +
               String.format("Area:             %.2f sq ft\n", area) +
               String.format("Raw Volume:       %.2f CY\n", rawVolume) +
               String.format("Adjusted Volume:  %.2f CY (8%% waste)\n", adjustedVolume) +
               String.format("Material Cost:    $%.2f\n", materialCost);
    }

    private void applyPadPreset() {

    String selected = (String) padPresetBox.getSelectedItem();

    if (selected == null) return;

    switch (selected) {

        case "Small Residential Pad (20x20)":
            fLength.setText("20");
            fWidth.setText("20");
            break;

        case "Garage / Workshop Pad (20x40)":
            fLength.setText("20");
            fWidth.setText("40");
            break;

        case "Small Warehouse (50x100)":
            fLength.setText("50");
            fWidth.setText("100");
            break;

        case "Mid Warehouse (100x150)":
            fLength.setText("100");
            fWidth.setText("150");
            break;

        case "Large Warehouse (150x200)":
            fLength.setText("150");
            fWidth.setText("200");
            break;

        case "Loading Apron (60x80)":
            fLength.setText("60");
            fWidth.setText("80");
            break;

        case "Parking Lot Section (100x200)":
            fLength.setText("100");
            fWidth.setText("200");
            break;

        case "Equipment Pad (10x10)":
            fLength.setText("10");
            fWidth.setText("10");
            break;

        case "Dumpster Pad (12x20)":
            fLength.setText("12");
            fWidth.setText("20");
            break;

        case "RV/Boat Slab (14x40)":
            fLength.setText("14");
            fWidth.setText("40");
            break;

        case "Basketball Court (50x84)":
            fLength.setText("50");
            fWidth.setText("84");
            break;

        case "Custom":
            fLength.setText("");
            fWidth.setText("");
            break;
    }
}
}