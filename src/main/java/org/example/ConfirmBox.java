package org.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    private static boolean answer;

    public static Boolean display(String title, String message) {

        // Defines a modal window that blocks events from
        // being delivered to any other application window
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);

        // Message parameter added to label
        Label label1 = new Label(message);

        // Yes Button
        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        // No Button
        Button noButton = new Button("No");
        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        // Adding buttons to HBox layout and center positioning them
        HBox hBox = new HBox();
        hBox.getChildren().addAll(yesButton, noButton);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);

        // Adding label and buttons to VBox layout
        VBox vBox = new VBox(10);
        vBox.getChildren().addAll(label1, hBox);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

}
