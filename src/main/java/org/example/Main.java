package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root2 = FXMLLoader.load(getClass().getResource("/Server.fxml"));
        Stage stage2 = new Stage();
        stage2.setTitle("Server");
        stage2.setScene(new Scene(root2));
        stage2.show();

        Parent root1 = FXMLLoader.load(getClass().getResource("/Client.fxml"));
        Stage stage1 = new Stage();
        stage1.setTitle("Client");
        stage1.setScene(new Scene(root1));
        stage1.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

