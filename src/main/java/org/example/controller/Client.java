package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    @FXML
    private Button btnSend;

    @FXML
    private AnchorPane clientPage;

    @FXML
    private TextArea textArea;

    @FXML
    private Text topic;

    @FXML
    private TextField typetext;

    Socket socket;

    DataInputStream dataInputStream;

    DataOutputStream dataOutputStream;

    String message = "";

    @FXML
    void SendOnAction(ActionEvent event) {
        try {
            dataOutputStream.writeUTF(typetext.getText().trim());
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize() {
        new  Thread(()-> {
            try {
                socket = new Socket("localhost", 3000);
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(message);
                while (!textArea.getText().equals("exit")) {
                    message = dataInputStream.readUTF();
                    textArea.appendText("\nServer:" + message);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

}