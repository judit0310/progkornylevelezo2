package hu.uni.eszterhazy.model;

import hu.uni.eszterhazy.exceptions.InvalidChip;
import hu.uni.eszterhazy.exceptions.InvalidDatum;
import hu.uni.eszterhazy.exceptions.InvalidKor;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class KutyaTest {

    @Test(expected = InvalidChip.class)
    public void testInvalidChip() throws InvalidChip {
        Kutya kutya = new Kutya();
        kutya.setChip("kiscica");
    }

    @Test
    public void testValidChip() throws InvalidChip {
        Kutya kutya = new Kutya();
        kutya.setChip("1234567891234565");
    }

    @Test(expected =InvalidDatum.class)
    public void testValidOltas() throws InvalidKor, InvalidDatum {
        Kutya kutya = new Kutya();
        kutya.setKor(5);
        kutya.setOltas_ideje(LocalDate.of(1992, 2, 14));
    }
}