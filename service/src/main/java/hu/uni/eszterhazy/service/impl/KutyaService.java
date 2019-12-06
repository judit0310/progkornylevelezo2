package hu.uni.eszterhazy.service.impl;

import hu.uni.eszterhazy.dao.IKutyaDAO;
import hu.uni.eszterhazy.model.Kutya;
import hu.uni.eszterhazy.service.KutyaEzzelAChippelMarVan;
import hu.uni.eszterhazy.service.KutyaNotFound;
import org.apache.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

public class KutyaService {

    IKutyaDAO dao;
    private Logger logger = Logger.getLogger(this.getClass());

    public KutyaService(IKutyaDAO dao) {
        this.dao = dao;
    }

    public void addKutya(Kutya kutya) throws KutyaEzzelAChippelMarVan {
        dao.addKutya(kutya);
    }

    public Collection<Kutya> getAllKutya(){
        return dao.readAllKutya();
    }


    public Collection<Kutya> getAllIvarosKutya(){
        Collection<Kutya> kutyak = dao.readAllKutya();
        Collection<Kutya> result=new ArrayList<>();
        for (Kutya k: kutyak) {
            if(!k.isIvartalanitva()){
                result.add(k);
            }

        }
        logger.info(kutyak.size()+" kutyából "+result.size()+" kutya ivaros");
        return result;
    }

    public Kutya getKutyaByChip(String chip) throws KutyaNotFound {
        return dao.readKutyaById(chip);
    }




}
