package org.openjfx.Dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JSONLoadAndSave {
    public JSONLoadAndSave() {
    }

    /**
     * Információk betöltése a JSON fájlból, bejövő BMI összehasonlítása a JSON-ben található értékekkel.
     * @param bmi összehasonlítani kívánt BMI
     * @return leghasonlóbb BMI értékekkel rendelkező Neve és BMI értéke
     */
    public String[] getInfoFromJSON(double bmi) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("./src/main/resources/org/openjfx/adatok.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray lista = (JSONArray) jsonObject.get("adatok");
            String[] parts;
            String part2;
            String[] parts2;
            String part3;
            String part4;
            String finalpart;
            double bmiSTAR;
            double eredmeny;
            ArrayList<Double> numbers = new ArrayList<>();
            for (int i = 0; i < lista.toArray().length; i++) {
                parts = lista.get(i).toString().split(",",-2);
                part2 = parts[1];
                parts2 = part2.split(":",2);
                part3 = parts2[1];
                part4 = part3.replaceAll("}","");
                finalpart = part4.substring(1, part4.length()-1);
                bmiSTAR = Double.parseDouble(finalpart);
                eredmeny = bmiSTAR;// - bmi;
                numbers.add(eredmeny);
            }
            double distance = Math.abs(numbers.get(0) - bmi);
            int idx = 0;
            for (int i = 1 ; i < lista.toArray().length; i++) {
                double idistance = Math.abs(numbers.get(i) - bmi);
                if(idistance < distance){
                    idx = i;
                    distance = idistance;
                }
            }
            Object nyerosor = lista.get(idx);
            String[] spliteles = nyerosor.toString().split(",",-2);
            String nyeronev = (spliteles[0].split(":",-2)[1]);
            nyeronev = nyeronev.substring(1, nyeronev.length()-1);
            String nyerobmi = (spliteles[1].split(":",2)[1]).replaceAll("}","");
            nyerobmi = nyerobmi.substring(1, nyerobmi.length()-1);
            //hasonlonev.setText(nyeronev);
            //hasonlobmi.setText(nyerobmi);
            return new String[] {nyeronev, nyerobmi};
        } catch (FileNotFoundException e) {
            System.err.println("A fájl nem található!");
            e.printStackTrace();
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    /**
     * Adat elmentése az előre megadott JSON fájlba.
     * @param name menteni kívánt Név
     * @param bmivalue menteni kívánt BMI érték
     */
    public void saveInfoToJSON(String name, String bmivalue){
        try {
            JSONObject user = new JSONObject();
            bmivalue = bmivalue.replaceAll(",", ".");
            user.put("Name", name);
            user.put("BMI", bmivalue);
            ObjectMapper mapper = new ObjectMapper();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("./src/main/resources/org/openjfx/adatok.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray lista = (JSONArray) jsonObject.get("adatok");
            lista.add(user);
            mapper.writeValue(new File("./src/main/resources/org/openjfx/adatok.json"), jsonObject);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
