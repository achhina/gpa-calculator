package org.example;

import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class Columns {

    public static TableColumn<Grade, String> column(String col) {
        // Course name column
        TableColumn<Grade, String> courseColumn = new TableColumn<>("Course Name");
        courseColumn.setMinWidth(220);
        courseColumn.setCellValueFactory(new PropertyValueFactory<>("course"));

        // Weight column
        TableColumn<Grade, String> weightColumn = new TableColumn<>("Credits");
        weightColumn.setMinWidth(110);
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));

        // Letter Grade Column
        TableColumn<Grade, String> gradeColumn = new TableColumn<>("Letter Grade");
        gradeColumn.setMinWidth(110);
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        // 4.0 scale column
        TableColumn<Grade, String> fourColumn = new TableColumn<>("4.0 Scale");
        fourColumn.setMinWidth(110);
        fourColumn.setCellValueFactory(new PropertyValueFactory<>("fourGPA"));

        // York GPA Column
        TableColumn<Grade, String> yorkColumn = new TableColumn<>("York Scale");
        yorkColumn.setMinWidth(110);
        yorkColumn.setCellValueFactory(new PropertyValueFactory<>("yorkGPA"));

        // Ryerson GPA Column
        TableColumn<Grade, String> ryersonColumn = new TableColumn<>("Ryerson Scale");
        ryersonColumn.setMinWidth(110);
        ryersonColumn.setCellValueFactory(new PropertyValueFactory<>("ryersonGPA"));

        // UofT GPA Column
        TableColumn<Grade, String> uoftColumn = new TableColumn<>("UofT Scale");
        uoftColumn.setMinWidth(110);
        uoftColumn.setCellValueFactory(new PropertyValueFactory<>("uoftGPA"));

        // Empty Column
        TableColumn<Grade, String> emptyColumn = new TableColumn<>();

        switch (col) {
            case "course":
                return courseColumn;
            case "weight":
                return weightColumn;
            case "grade":
                return gradeColumn;
            case "four":
                return fourColumn;
            case "york":
                return yorkColumn;
            case "ryerson":
                return ryersonColumn;
            case "uoft":
                return uoftColumn;
            default:
                return emptyColumn;
        }

    }

    public static String whichColumn(TableView<Grade> table) {
        // Get's current alternative scale column header from table
        String scale = table.getColumns().get(table.getColumns().size()-1).getText().toLowerCase();

        // Finds which column it is based off header
        if(scale.contains("york")) return "york";
        else if (scale.contains("ryerson")) return "ryerson";
        else if (scale.contains("uoft")) return "uoft";
        else return "";
    }

    public static String whichColumn(String scale) {
        // Overloaded method for strings to be checked for which column it is
        scale = scale.toLowerCase();

        if(scale.contains("york")) return "york";
        else if (scale.contains("ryerson")) return "ryerson";
        else if (scale.contains("uoft")) return "uoft";
        else return "";
    }

}
