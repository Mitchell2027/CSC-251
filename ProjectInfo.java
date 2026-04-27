// ProjectInfo.java
// Stores basic project details entered by the user
 
public class ProjectInfo {
 
    String projectName;
    String clientName;
    String location;
    String date;
    String estimator;
 
    public ProjectInfo(String projectName, String clientName, String location,
                       String date, String estimator) {
        this.projectName = projectName;
        this.clientName  = clientName;
        this.location    = location;
        this.date        = date;
        this.estimator   = estimator;
    }
 
    public String getFormattedInfo() {
        return "=== PROJECT INFO ===\n" +
               "Project:   " + projectName + "\n" +
               "Client:    " + clientName  + "\n" +
               "Location:  " + location    + "\n" +
               "Date:      " + date        + "\n" +
               "Estimator: " + estimator   + "\n";
    }
}