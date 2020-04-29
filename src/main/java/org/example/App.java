package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    Stage window;
    TableView<Grade> table;
    TextField courseInput;
    TextField weightInput;
    ComboBox<String> gradeInput;
    Label cGPA;

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Rename stage, set title & add a confirmation box on program close request
        window = primaryStage;
        window.setTitle("GPA Calculator");
        window.setOnCloseRequest(e -> {
            e.consume();
            Boolean close = ConfirmBox.display("GPA Calculator", "Are you sure you want to exit the application?");
            if (close) window.close();
        });

        // Course Input field
        courseInput = new TextField();
        courseInput.setPromptText("Course Name");
        courseInput.setMinWidth(100);

        // Weight Input field
        weightInput = new TextField();
        weightInput.setPromptText("Credits");
        weightInput.setMinWidth(100);

        // Grade Input dropdown
        gradeInput = new ComboBox<>();
        gradeInput.setPromptText("Letter Grade");
        gradeInput.getItems().addAll("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E", "F");
        gradeInput.setMinWidth(40);

        // Add & Delete Buttons
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            addButtonClick();
            // Updates Cumulative GPA
            cGPA.setText(Converter.cGPALabel(table));
        });

        Button delButton = new Button("Delete");
        delButton.setOnAction(e -> {
            delButtonClicked();
            // Updates Cumulative GPA
            cGPA.setText(Converter.cGPALabel(table));
        });

        // Initializing Table
        table = new TableView<>();
        table.setItems(getGrades());

        // Allow to select more than one row at a time
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Adding Columns
        table.getColumns().addAll(Columns.column("course"), Columns.column("weight"),
                Columns.column("grade"), Columns.column("four"), Columns.column("york"));


        // Toolbar to Add Courses
        HBox courseAddToolbar = new HBox();
        courseAddToolbar.setPadding(new Insets(10, 10, 10, 10));
        courseAddToolbar.setSpacing(20);
        courseAddToolbar.getChildren().addAll(courseInput, weightInput,  gradeInput, addButton, delButton);

        // Cumulative GPA Label
        cGPA = new Label(Converter.cGPALabel(table));

        // Toolbar/Area to display Cumulative GPA
        HBox cGPAToolbar = new HBox();
        cGPAToolbar.setPadding(new Insets(10, 10, 10, 10));
        cGPAToolbar.setSpacing(20);
        cGPAToolbar.getChildren().addAll(cGPA);
        cGPAToolbar.setAlignment(Pos.BASELINE_RIGHT);

        // File Menu
        FileMenu file = new FileMenu();
        HBox menu = file.createMenu(primaryStage, table, file, cGPA);

        // Final layout setup to display in program as VBox
        VBox vBox = new VBox();
        vBox.getChildren().addAll(menu, table, cGPAToolbar, courseAddToolbar);

        // Add layout and stylesheet to scene
        Scene scene = new Scene(vBox);
        String filePath = getClass().getResource("Styles.css").toExternalForm();
        scene.getStylesheets().add(filePath);

        // Add scene to window and display window
        window.setScene(scene);
        window.show();

    }

    // Add Button Clicked
    private void addButtonClick() {
        // New Grade object created using user input fields
        Grade grade = new Grade(courseInput.getText(), Float.parseFloat(weightInput.getText()),
                gradeInput.getValue());

        // Add Grade object to table
        table.getItems().add(grade);

        // Clear inputs from text field
        courseInput.clear();
        weightInput.clear();
    }

    // Delete Button Clicked
    private void delButtonClicked() {
        // Get's all Grade objects in table and removes currently selected object
        ObservableList<Grade> allGrades = table.getItems();
        ObservableList<Grade> gradeSelected = table.getSelectionModel().getSelectedItems();
        gradeSelected.forEach(allGrades::remove);

    }

    public ObservableList<Grade> getGrades() {
        // Sample Course list created on program initialization
        ObservableList<Grade> grades = FXCollections.observableArrayList();
        grades.add(new Grade("Sample Course 1", 3.0f, "A+"));
        grades.add(new Grade("Sample Course 2", 3.0f, "A"));
        grades.add(new Grade("Sample Course 3", 3.0f, "B+"));

        return grades;
    }

    public static void main(String[] args) {
        launch();
    }

}