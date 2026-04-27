// FenceEstimate.java
// Calculates chain-link fencing cost based on perimeter and price per foot
 
public class FenceEstimate {
 
    double perimeter;
    double pricePerFoot;
 
    double totalCost;
 
    public FenceEstimate(double perimeter, double pricePerFoot) {
        this.perimeter    = perimeter;
        this.pricePerFoot = pricePerFoot;
    }
 
    public void calculate() {
        totalCost = perimeter * pricePerFoot;
    }
 
    public String getFormattedInfo() {
        return "=== FENCING ===\n" +
               String.format("Perimeter:        %.0f ft\n", perimeter) +
               String.format("Price/LF:         $%.2f\n", pricePerFoot) +
               String.format("Fence Cost:       $%.2f\n", totalCost);
    }
}
 