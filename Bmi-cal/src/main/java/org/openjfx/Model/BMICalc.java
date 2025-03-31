package org.openjfx.Model;

public class BMICalc {
    private double magassag;
    private double suly;

    /**
     * Magasság lekérdezése
     * @return magasság
     */
    public double getMagassag() {
        return magassag;
    }

    /**
     * Magasság beállítása
     * @param magassag kívánt magasság
     */
    public void setMagassag(double magassag) {
        this.magassag = magassag;
    }

    /**
     * Súly lekérdezése
     * @return súly
     */
    public double getSuly() {
        return suly;
    }

    /**
     * Súly beállítása
     * @param suly kívánt súly
     */
    public void setSuly(double suly) {
        this.suly = suly;
    }

    /**
     * BMI Kalkulátor konstruktorja
     * @param suly súly
     * @param magassag magasság
     */
    public BMICalc(double suly, double magassag) {
        this.magassag = magassag;
        this.suly = suly;
    }

    /**
     * BMI kiszámítása
     * @return BMI érték
     */
    public double szamitBMI(){
        double bmiS;
        if(this.magassag > 0 && this.suly > 0) {
            bmiS = this.suly / ((this.magassag / 100) * (this.magassag / 100));
            return bmiS;
        }else{
            System.err.println("A magasságnak, vagy a súlynak nagyobbnak kell lennie 0-nál!");
            return 0;
        }
    }
}
