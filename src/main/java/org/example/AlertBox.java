package org.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title, String message) {
        Stage window = new Stage();

        // Defines a modal window that blocks events from
        // being delivered to any other application window
        window.initModality(Modality.APPLICATION_MODAL);

        // Set Title & Width
        window.setTitle(title);
        window.setMinWidth(300);

        // Message from parameter being passed to label
        Label label1 = new Label(message);

        // Exit button
        Button button1 = new Button("Exit");
        button1.setOnAction(e -> window.close());

        // Creating VBox layout
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(label1, button1);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        // Setting layout and waiting for response
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
    }

}
