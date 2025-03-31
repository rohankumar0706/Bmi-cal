package org.openjfx.Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjfx.Model.BMICalc;

import static org.junit.jupiter.api.Assertions.*;

class BMICalcTest {
    private BMICalc undertest;

    @BeforeEach
    void setup(){
        undertest = new BMICalc(68, 170);
    }

    @AfterEach
    void tearDown(){
        undertest = null;
    }

    @Test
    void tesztBMIOsztas(){
        assertEquals(23.529, undertest.szamitBMI());
    }

    @Test
    void getMagassag() {
        double m = undertest.getMagassag();
        assertEquals(170, m);
    }

    @Test
    void setMagassag() {
        undertest.setMagassag(175);
        assertEquals(175,undertest.getMagassag());
    }

    @Test
    void getSuly() {
        double s = undertest.getSuly();
        assertEquals(68, s);
    }

    @Test
    void setSuly() {
        undertest.setSuly(55);
        assertEquals(55,undertest.getSuly());
    }

}