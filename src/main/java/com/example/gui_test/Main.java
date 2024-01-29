package com.example.gui_test;
import com.google.gson.Gson;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



 public class Main extends Application {

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            primaryStage.setTitle("Consulta CNPJ");

            Label label = new Label("Digite o CNPJ a ser pesquisado:");
            TextField cnpjTextField = new TextField();
            Button searchButton = new Button("Consultar");

            TextArea resultArea = new TextArea();
            resultArea.setEditable(false);
            resultArea.setPrefHeight(300);

            searchButton.setOnAction(e -> {
                String cnpj = cnpjTextField.getText().replaceAll("[^0-9]", "");
                if (!cnpj.isEmpty()) {
                    try {
                        String result = consultarCnpj(cnpj);
                        resultArea.setText(result);
                    } catch (IOException | InterruptedException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    resultArea.setText("Por favor, digite um CNPJ válido.");
                }
            });

            VBox layout = new VBox(10);
            layout.getChildren().addAll(label, cnpjTextField, searchButton, resultArea);

            Scene scene = new Scene(layout, 700, 400);
            primaryStage.setScene(scene);

            primaryStage.show();
        }

        private String consultarCnpj(String cnpj) throws IOException, InterruptedException {
            String endpoint = "https://api.gtech.site/companies/" + cnpj + "/is_blocked";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            Gson gson = new Gson();
            Cnpj cnpjResult = gson.fromJson(json, Cnpj.class);

            if (cnpjResult != null) {
                return cnpjResult.toString();
            } else {
                return "CNPJ não encontrado!";
            }
        }
    }
