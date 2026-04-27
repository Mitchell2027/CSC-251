// LaborEstimate.java
// Calculates total labor cost based on crew size, hours, and rate
 
public class LaborEstimate {
 
    int    employees;
    double hoursPerEmployee;
    double hourlyRate;
 
    double totalHours;
    double laborCost;
 
    public LaborEstimate(int employees, double hoursPerEmployee, double hourlyRate) {
        this.employees        = employees;
        this.hoursPerEmployee = hoursPerEmployee;
        this.hourlyRate       = hourlyRate;
    }
 
    public void calculate() {
        totalHours = employees * hoursPerEmployee;
        laborCost  = totalHours * hourlyRate;
    }
 
    public String getFormattedInfo() {
        return "=== LABOR ===\n" +
               String.format("Employees:        %d\n", employees) +
               String.format("Hours/Employee:   %.2f\n", hoursPerEmployee) +
               String.format("Total Hours:      %.2f\n", totalHours) +
               String.format("Labor Cost:       $%.2f\n", laborCost);
    }
}
 