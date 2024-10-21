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
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    @FXML
    private AnchorPane ServerPage;

    @FXML
    private Button btnSend;

    @FXML
    private TextArea textArea;

    @FXML
    private Text topic;

    @FXML
    private TextField typetext;

    ServerSocket serverSocket;

    Socket socket;

    DataInputStream dataInputStream;

    DataOutputStream dataOutputStream;

    String message = "";

    public void initialize() {

        new  Thread(()-> {
            try {
                serverSocket = new ServerSocket(3000);
                textArea.appendText("Server Started\n");
                socket = serverSocket.accept();
                textArea.appendText("Server Connected\n");
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                while (!typetext.getText().equals("bye")) {
                    message = dataInputStream.readUTF();
                    textArea.appendText("\nClient:" + message);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    void SendOnAction(ActionEvent event) {
        try {
            dataOutputStream.writeUTF(typetext.getText().trim());
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
