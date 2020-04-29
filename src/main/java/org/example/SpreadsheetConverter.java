package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class SpreadsheetConverter {

    public static void saveFile(Stage window, TableView<Grade> table) {

        // Initializing JavaFX FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");

        // Default file name to be saved as
        fileChooser.setInitialFileName("gpa.csv");

        // Extensions supported
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv")
        );

        // Shows OS built in file explorer to choose
        // location to save data to
        File file = fileChooser.showSaveDialog(window);

        // This is to catch if they cancel the dialog save box
        if (file != null) {
            try {
                // All columns currently displayed
                ObservableList<TableColumn<Grade, ?>> column = table.getColumns();

                // String to add to csv
                StringBuilder sb = new StringBuilder();

                // Adds column heading for csv
                for (TableColumn<Grade,?> col: column) {
                    sb.append(col.getText());
                    sb.append(",");
                }

                // Replaces last comma with newline in string builder
                sb.delete(sb.length()-1,sb.length());
                sb.append(",\n");

                // Adding cell data from each column by row in string builder
                for (byte i = 0; i < table.getItems().size(); i++) {
                    for (TableColumn<Grade, ?> col : column) {
                        sb.append(col.getCellData(i));
                        sb.append(",");
                    }

                    // Replaces last comma with newline in string builder
                    // except for the last line where it only replaces the comma
                    sb.delete(sb.length()-1,sb.length());
                    if (i < table.getItems().size()-1) sb.append(",\n");
                }

                // Writing and saving file
                PrintWriter writer = new PrintWriter(file);
                writer.println(sb);
                writer.close();

            } catch (IOException ex) {
                // Handling Exception
            }
        }

    }

    public static void openFile(Stage window, TableView<Grade> table, FileMenu menu){
        // Initializing FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");

        // Extensions supported
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files (*.csv)", "*.csv")
        );

        // Shows OS built in file explorer to choose
        // location to open data from
        File file = fileChooser.showOpenDialog(window);

        try {
            // Get file path from FileChooser earlier and open in Buffered Reader
            BufferedReader br = new BufferedReader(new FileReader(file.getAbsolutePath()));

            // Split line by comma and read first line to get column headers
            String line = br.readLine();
            String[] b = line.split(",");

            // Find out which column it is
            String column = Columns.whichColumn(b[4]);

            // Enter loop if valid scale is retrieved in column
            if (!column.equals("")) {
                // Remove the current scale column
                table.getColumns().remove(table.getColumns().size()-1);

                // Add relevant scale column and toggle menu button accordingly
                table.getColumns().addAll(Columns.column(column));
                menu.manualToggle(column);

                // To hold Grade objects from saved data
                ObservableList<Grade> grades = FXCollections.observableArrayList();

                // Move on from column header line
                line = br.readLine();

                // Run through every line of saved data
                while (line != null) {
                    // Split line by comma
                    b = line.split(",");

                    // Create and add new Grade object to ObservableList grades
                    grades.add(new Grade(b[0], Float.parseFloat(b[1]), b[2]));
                    line = br.readLine();
                }

                // Add Grade objects to table
                table.setItems(grades);
            } else {
                // Handle inappropriate data set
                AlertBox.display("GPA Calculator", "GPA Calculator could not open file because it is either not" +
                        "a supported file type or because the file has been damaged.");
            }

        } catch (IOException ex) {
            // Handling Exception
            AlertBox.display("GPA Calculator", "GPA Calculator could not open file because it is either not" +
                    "a supported file type or because the file has been damaged.");
        }
    }
}
