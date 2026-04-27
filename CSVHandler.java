// CSVHandler.java
// Saves completed estimates to a CSV file
 
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
 
public class CSVHandler {
 
    public static void saveEstimate(String filePath, String projectName, String clientName,
                                    String date, double area, double adjustedVolume,
                                    double materialCost, double laborCost,
                                    double fenceCost, double grandTotal) {
 
        File file = new File(filePath);
        boolean needsHeader = !file.exists() || file.length() == 0;
 
        try (FileWriter fw = new FileWriter(file, true)) {
            if (needsHeader) {
                fw.write("project_name,client_name,date,area_sqft," +
                         "concrete_cy,material_cost,labor_cost,fence_cost,grand_total\n");
            }
            fw.write(String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f,%.2f,%.2f\n",
                     projectName, clientName, date,
                     area, adjustedVolume, materialCost, laborCost, fenceCost, grandTotal));
 
        } catch (IOException e) {
            System.out.println("Error saving estimate: " + e.getMessage());
        }
    }
}