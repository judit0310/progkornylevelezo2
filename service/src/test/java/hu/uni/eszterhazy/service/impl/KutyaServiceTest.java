package hu.uni.eszterhazy.service.impl;

import hu.uni.eszterhazy.dao.IKutyaDAO;
import hu.uni.eszterhazy.model.Fajta;
import hu.uni.eszterhazy.model.Ivar;
import hu.uni.eszterhazy.model.Kutya;
import hu.uni.eszterhazy.service.KutyaNotFound;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class KutyaServiceTest {
    KutyaService service;
        IKutyaDAO dao= Mockito.mock(IKutyaDAO.class);

    @Before
    public void setUp() throws Exception, KutyaNotFound {
        Kutya kutya = new Kutya("Buksi", "1234567891234567",
                Fajta.BEAGLE, Ivar.EGYÉB,
                true, LocalDate.now(),3,"kiss Béla");
        Collection<Kutya> k = new ArrayList<>();
        k.add(kutya);
        Mockito.when(dao.readAllKutya()).thenReturn(k);
        Mockito.when(dao.readKutyaById(kutya.getChip())).thenReturn(kutya);
        Mockito.when(dao.readKutyaById("kiscica")).thenThrow(KutyaNotFound.class);
        service=new KutyaService(dao);
    }

    @Test
    public void name() {
        assertEquals(1, service.getAllKutya().size());
    }

    @Test(expected = KutyaNotFound.class )
    public void testKutyaNotFound() throws KutyaNotFound {
        service.getKutyaByChip("kiscica");
    }

    @Test
    public void testIvaros() {
        assertEquals(0, service.getAllIvarosKutya().size());
    }


}