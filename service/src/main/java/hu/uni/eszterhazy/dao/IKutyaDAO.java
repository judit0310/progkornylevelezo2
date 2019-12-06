package hu.uni.eszterhazy.dao;

import hu.uni.eszterhazy.model.Kutya;
import hu.uni.eszterhazy.service.KutyaEzzelAChippelMarVan;
import hu.uni.eszterhazy.service.KutyaNotFound;

import java.util.Collection;

public interface IKutyaDAO {

    public Collection<Kutya> readAllKutya();

    public void addKutya(Kutya kutya) throws KutyaEzzelAChippelMarVan;

    Kutya readKutyaById(String chip) throws KutyaNotFound;
}
