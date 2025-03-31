package org.openjfx.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.openjfx.Dao.JSONLoadAndSave;
import org.openjfx.Model.BMICalc;

import java.io.IOException;
import java.text.DecimalFormat;

public class BmiController {
    /*
     FXML adatok betöltése
     */
    @FXML
    private TextField magassag;

    @FXML
    private TextField nev;

    @FXML
    private Text hasonlonev;

    @FXML
    private Text hasonlobmi;

    @FXML
    private TextField tomeg;

    @FXML
    private TextField bmiertek;

    @FXML
    private Rectangle rectangle1;

    @FXML
    private Rectangle rectangle11;

    @FXML
    private Rectangle rectangle111;

    @FXML
    private Rectangle rectangle1111;

    public BmiController(){
    }

    @FXML
    private void handleButtonAction() {
        /*
          Gombnyomás kezelése.
          */
        if(nev.getText().trim().isEmpty()){
            nev.setPromptText("Írd be a neved!");
        }
        if(tomeg.getText().trim().isEmpty() || !isInt(tomeg, tomeg.getText()) || Integer.parseInt(tomeg.getText()) <= 0){
            tomeg.setPromptText("Írd be a súlyod!");
            tomeg.setText("");
        }
        if(magassag.getText().trim().isEmpty() || !isInt(magassag, magassag.getText()) || Integer.parseInt(magassag.getText()) <= 0){
            magassag.setPromptText("Írd be a magasságod!");
            magassag.setText("");
        }
        if(!magassag.getText().trim().isEmpty() && !tomeg.getText().trim().isEmpty() && !nev.getText().trim().isEmpty()) {
            if (isInt(tomeg, tomeg.getText()) && isInt(magassag, magassag.getText())) {
                szamitas();
            }
        }
    }

    /**
     * Számítás meghívása, valamint mentés és betöltés a JSON-be, illetve a JSON-ből.
     */
    private void szamitas() {
        org.openjfx.Model.BMICalc kalkulator = new BMICalc(Double.parseDouble(tomeg.getText()), Double.parseDouble(magassag.getText()));
        DecimalFormat df = new DecimalFormat("#.000");
        double bmi = kalkulator.szamitBMI();
        bmiertek.setText(String.valueOf(df.format(bmi)));
        if (bmi < 18.5) {
            rectangle1.setVisible(true);
            rectangle11.setVisible(false);
            rectangle111.setVisible(false);
            rectangle1111.setVisible(false);
        } else if (bmi >= 18.5 && bmi < 25) {
            rectangle1.setVisible(false);
            rectangle11.setVisible(true);
            rectangle111.setVisible(false);
            rectangle1111.setVisible(false);
        } else if (bmi >= 25 && bmi < 30) {
            rectangle1.setVisible(false);
            rectangle11.setVisible(false);
            rectangle111.setVisible(true);
            rectangle1111.setVisible(false);
        } else if (bmi >= 30) {
            rectangle1.setVisible(false);
            rectangle11.setVisible(false);
            rectangle111.setVisible(false);
            rectangle1111.setVisible(true);
        }
        org.openjfx.Dao.JSONLoadAndSave loader = new JSONLoadAndSave();
        String[] nyertesek = loader.getInfoFromJSON(bmi);
        hasonlonev.setText(nyertesek[0]);
        hasonlobmi.setText(nyertesek[1]);
        loader.saveInfoToJSON(nev.getText(), String.valueOf(df.format(bmi)));
    }

    /**
     * Link kezelése.
     */
    @FXML
    private void handleLinkAction(){
        openWebpage();
    }

    /**
     * Az előre beírt weblap megnyitása
     */
    private static void openWebpage(){
        try{
            new ProcessBuilder("x-www-browser", "http://a.igneczitibor.hu/skyLine/adatvedelem/adatvedelem.html").start();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Eldöntjük egy adott inputról, hogy szám-e
     * @param input kiválasztott TextField
     * @param message Beírt szöveg
     * @return igaz, vagy hamis
     */
    private boolean isInt(TextField input, String message){
        try{
            Integer.parseInt(input.getText());
            return true;
        }catch(NumberFormatException e){
            System.out.println("Hiba " + message + " nem egy szám");
            return false;
        }
    }
}
