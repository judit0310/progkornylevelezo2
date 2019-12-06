package hu.uni.eszterhazy.dao.impl;

import hu.uni.eszterhazy.model.Fajta;
import hu.uni.eszterhazy.model.Ivar;
import hu.uni.eszterhazy.model.Kutya;
import hu.uni.eszterhazy.service.KutyaEzzelAChippelMarVan;
import hu.uni.eszterhazy.service.KutyaNotFound;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class KutyaDAOTest {
    KutyaDAO dao;
    @Before
    public void setUp() throws IOException {
        dao = new KutyaDAO("proba.json");
        dao.clearFile();

    }

    @Test
    public void readReturnEmpty() {
        assertEquals(0, dao.readAllKutya().size());
    }

    @Test
    public void testAddedValidKutya() throws KutyaEzzelAChippelMarVan {
        int originalSize = dao.readAllKutya().size();
        Kutya kutya = new Kutya("Buksi", "1234567891234567",
                Fajta.BEAGLE, Ivar.EGYÉB,
                true, LocalDate.now(),3,"kiss Béla");
        dao.addKutya(kutya);
        assertEquals(originalSize+1, dao.readAllKutya().size());
    }

    @Test(expected = KutyaEzzelAChippelMarVan.class)
    public void testDuplicatedKutya() throws IOException, KutyaEzzelAChippelMarVan {
        Kutya kutya = new Kutya("Buksi", "1234567891234567",
                Fajta.BEAGLE, Ivar.EGYÉB,
                true, LocalDate.now(),3,"kiss Béla");
        dao.addKutya(kutya);
        dao.addKutya(kutya);
    }

    @Test
    public void testReadByIdReturnSameObject() throws KutyaEzzelAChippelMarVan, KutyaNotFound {
        Kutya kutya = new Kutya("Buksi", "1234567891234567",
                Fajta.BEAGLE, Ivar.EGYÉB,
                true, LocalDate.now(),3,"kiss Béla");
        dao.addKutya(kutya);
        assertEquals(kutya, dao.readKutyaById(kutya.getChip()));
    }
}