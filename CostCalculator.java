
public class CostCalculator {

    public double calculateTotal(ConcretePad pad, LaborEstimate labor, FenceEstimate fence, double concretePrice) {

        double materialCost = pad.adjustedVolume * concretePrice;
        double total = materialCost + labor.laborCost;

        if (fence != null) {
            total += fence.totalCost;
        }

        return total;
    }
}