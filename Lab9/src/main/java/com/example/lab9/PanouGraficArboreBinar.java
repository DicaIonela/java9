package com.example.lab9;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class PanouGraficArboreBinar extends Application {
    private Stage mainStage;
    private TextArea zonaTextArea;
    private ArboreBinarString arb = null;
    private TextArea getTextArea(String textInitial) {
        TextArea ta = new TextArea();
        ta.appendText(textInitial);
        ta.setPrefWidth(610);
        return ta;
    }
    private void setOnActionButoane(Button btnCitesteArboreC,
                                    Button btnCitesteFisier, Button btnTraversareRSD,
                                    Button btnTraversareSRD, Button btnTraversareSDR,
                                    Button btnInfoAutor, TextField ca) {
        btnCitesteArboreC.setOnAction(e->{
            String text = ca.getText();
            zonaTextArea.appendText("Arborele: " + text + "\n");

        });
        btnCitesteFisier.setOnAction(e -> {
            File fisier = deschideFisier(true); // citire
            if (fisier != null) {
                arb = new ArboreBinarString(fisier);
                zonaTextArea.appendText("Arbore citit din fișierul: " + fisier.getAbsolutePath() + "\n");
            }
        });
        btnTraversareRSD.setOnAction(e -> {
            if (arb != null) {
                zonaTextArea.appendText("Traversare Preordine (RSD): \n");
                zonaTextArea.appendText(arb.traversareRSD() + "\n");
            } else {
                zonaTextArea.appendText("Arborele nu a fost creat încă.\n");
            }
        });

        // Buton "Traversare SRD"
        btnTraversareSRD.setOnAction(e -> {
            if (arb != null) {
                zonaTextArea.appendText("Traversare Inordine (SRD): \n");
                zonaTextArea.appendText(arb.traversareSRD() + "\n");
            } else {
                zonaTextArea.appendText("Arborele nu a fost creat încă.\n");
            }
        });

        // Buton "Traversare SDR"
        btnTraversareSDR.setOnAction(e -> {
            if (arb != null) {
                zonaTextArea.appendText("Traversare Postordine (SDR): \n");
                zonaTextArea.appendText(arb.traversareSDR() + "\n");
            } else {
                zonaTextArea.appendText("Arborele nu a fost creat încă.\n");
            }
        });
        btnInfoAutor.setOnAction(e -> {
            zonaTextArea.appendText("PSG @ 2023" +"\n");
        });
    }
    private VBox getButoane(TextField ca ){
        Button btnCitesteArboreC= new Button("Citeste Arbore complet");
        Button btnCitesteFisier = new Button("Citeste Fisier");
        Button btnTraversareRSD = new Button("Traversare RSD");
        Button btnTraversareSRD = new Button("Traversare SRD");
        Button btnTraversareSDR = new Button("Traversare SDR");
        Button btnInfoAutor = new Button("Info autor");
        Button butoane[] ={btnCitesteArboreC,btnCitesteFisier,btnTraversareRSD,
                btnTraversareSRD, btnTraversareSDR, btnInfoAutor};
// Setarea unei dimensiuni minime pentru toate butoanele
        for(Button b:butoane) b.setMinWidth(150);
// Asocierea actiunilor care trebuie efecuate la click pe buton
//
        setOnActionButoane(btnCitesteArboreC,btnCitesteFisier,btnTraversareRSD,
                btnTraversareSRD, btnTraversareSDR, btnInfoAutor, ca);
// Constituirea grupului (vertical) de butoane
// si returnarea referintei la acest grup
        return new VBox(5, btnCitesteArboreC,btnCitesteFisier,btnTraversareRSD,
                btnTraversareSRD, btnTraversareSDR, btnInfoAutor);
    }
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        TextField ca=new TextField();
        ca.setMinWidth(680);
        zonaTextArea = getTextArea("");
        HBox hBoxInferior = new HBox(20, getButoane(ca), zonaTextArea );
        HBox hBoxSuperior = new HBox(5, new Label("Info noduri arb.:"), ca);
        VBox panouGrafic = new VBox(10, hBoxSuperior, hBoxInferior);
        panouGrafic.setPadding(new Insets(10));
        Scene scena = new Scene(panouGrafic, 800, 250);
        stage.setScene(scena);
        stage.setTitle("Arbori binari");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    private File deschideFisier(boolean citire) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Arbori", "*.arb"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        File selectedFile = citire ? fileChooser.showOpenDialog(mainStage)
                : fileChooser.showSaveDialog(mainStage);

        if (selectedFile != null) {
            zonaTextArea.appendText("File selected: " + selectedFile.getAbsolutePath() + "\n");
        }

        return selectedFile;
    }

}
