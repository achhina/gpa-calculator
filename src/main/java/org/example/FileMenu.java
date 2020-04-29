package org.example;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FileMenu {

    ToggleGroup gpaToggle;

    public HBox createMenu(Stage stage, TableView<Grade> table, FileMenu menu, Label cGPA) {
        // File Menu
        Menu fileMenu = new Menu("_File");

        // File Menu Item - Preferences
        Menu preferenceMenu = new Menu("Preferences");

        // Sub Menu for File Menu
        Menu subPreferenceMenu = new Menu("Dark Mode");

        // Toggle Group for Dark Mode
        ToggleGroup darkToggle = new ToggleGroup();

        // Dark Mode on Radio Menu Button
        RadioMenuItem darkOn = new RadioMenuItem("On");
        darkOn.setToggleGroup(darkToggle);
        darkOn.setOnAction(e -> {
            String filePath = getClass().getResource("Styles.css").toExternalForm();
            stage.getScene().getStylesheets().add(filePath);
        });
        darkOn.setSelected(true);

        // Dark Mode off Radio Menu Button
        RadioMenuItem darkOff = new RadioMenuItem("Off");
        darkOff.setToggleGroup(darkToggle);
        darkOff.setOnAction(e -> stage.getScene().getStylesheets().clear());

        // Adding On/Off Toggles to sub menu and adding sub
        // menu to preference menu
        subPreferenceMenu.getItems().addAll(darkOn, darkOff);
        preferenceMenu.getItems().addAll(subPreferenceMenu);

        // File Menu Item - Open
        MenuItem openMenu = new MenuItem("Open...");
        openMenu.setOnAction(e -> {
            SpreadsheetConverter.openFile(stage, table, menu);
            cGPA.setText(Converter.cGPALabel(table));
        });

        // File Menu Item - Save
        MenuItem saveMenu = new MenuItem("Save...");
        saveMenu.setOnAction(e -> {
            SpreadsheetConverter.saveFile(stage, table);
            cGPA.setText(Converter.cGPALabel(table));
        });

        // File Menu Item - Exit
        MenuItem exitMenu = new MenuItem("Exit...");
        exitMenu.setOnAction(e -> {
            Boolean answer = ConfirmBox.display("GPA Calculator", "Are you sure you want to exit the application?");
            if (answer) stage.close();
        });

        // Adding File Menu Items
        fileMenu.getItems().addAll(preferenceMenu, new SeparatorMenuItem(), openMenu, saveMenu, exitMenu);

        // GPA Scale Menu
        Menu scaleGPA = new Menu("GPA Scale to Use");
        gpaToggle = new ToggleGroup();

        // GPA Scale Menu Item - York Scale
        RadioMenuItem york = new RadioMenuItem("York");
        york.setToggleGroup(gpaToggle);
        york.setSelected(true);
        york.setOnAction(e -> {
            table.getColumns().remove(table.getColumns().size()-1);
            table.getColumns().addAll(Columns.column("york"));
            cGPA.setText(Converter.cGPALabel(table));
        });

        // GPA Scale Menu Item - Ryerson Scale
        RadioMenuItem ryerson = new RadioMenuItem("Ryerson");
        ryerson.setToggleGroup(gpaToggle);
        ryerson.setOnAction(e -> {
            table.getColumns().remove(table.getColumns().size()-1);
            table.getColumns().addAll(Columns.column("ryerson"));
            cGPA.setText(Converter.cGPALabel(table));
        });

        // GPA Scale Menu Item - UofT Scale
        RadioMenuItem uoft = new RadioMenuItem("UofT");
        uoft.setToggleGroup(gpaToggle);
        uoft.setOnAction(e -> {
            table.getColumns().remove(table.getColumns().size()-1);
            table.getColumns().addAll(Columns.column("uoft"));
            cGPA.setText(Converter.cGPALabel(table));
        });

        // Adding GPA Menu Items
        scaleGPA.getItems().addAll(york, ryerson, uoft);

        // Creating MenuBar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, scaleGPA);

        // Adding MenuBar to HBox layout
        HBox hBox = new HBox();
        hBox.getChildren().addAll(menuBar);

        return hBox;
    }

    public void manualToggle(String scale){
        // Manually toggles RadioMenuItem buttons
        switch (scale) {
            case "york":
                gpaToggle.getToggles().get(0).setSelected(true);
                break;
            case "ryerson":
                gpaToggle.getToggles().get(1).setSelected(true);
                break;
            case "uoft":
                gpaToggle.getToggles().get(2).setSelected(true);
                break;
        }
    }
}
