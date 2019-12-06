package hu.uni.eszterhazy.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hu.uni.eszterhazy.dao.IKutyaDAO;
import hu.uni.eszterhazy.model.Kutya;
import hu.uni.eszterhazy.service.KutyaEzzelAChippelMarVan;
import hu.uni.eszterhazy.service.KutyaNotFound;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;


public class KutyaDAO implements IKutyaDAO {
    private Logger logger = Logger.getLogger(this.getClass());
    File jsonfile;
    ObjectMapper mapper;

    public KutyaDAO(String jsonfilepath) throws IOException {
        jsonfile = new File(jsonfilepath);
        if (!jsonfile.exists()) {
            jsonfile.createNewFile();
            FileWriter writer = new FileWriter(jsonfile);
            writer.write("[]");
            writer.close();
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        logger.info("DAO letrehozva, a fajl neve:"+jsonfilepath);
    }

    public Collection<Kutya> readAllKutya() {
        Collection<Kutya> result = new ArrayList<>();
        TypeReference type = new TypeReference<ArrayList<Kutya>>() {
        };
        try {
            result = mapper.readValue(jsonfile, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Az adatbazis le lett kerve, összesen "+result.size()+" kutya van benne");
        return result;
    }

    public void addKutya(Kutya kutya) throws KutyaEzzelAChippelMarVan {
        try {
            readKutyaById(kutya.getChip());
            logger.warn("A kert "+kutya.getChip()+" chippel mar szerepel kutya az adatbazisban");
            throw new KutyaEzzelAChippelMarVan();
        } catch (KutyaNotFound kutyaNotFound) {
            Collection<Kutya> kutyusok = readAllKutya();
            kutyusok.add(kutya);
            try {
                mapper.writeValue(jsonfile, kutyusok);
                logger.info("Uj kutya kerult felvitelre "+kutya.getChip()+"azonositoval, így már összesen "+kutyusok.size()+" kutya van az adatbázisban");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Kutya readKutyaById(String chip) throws KutyaNotFound {
        Collection<Kutya> kutyak = readAllKutya();
        for (Kutya k : kutyak) {
            if(k.getChip().equalsIgnoreCase(chip)){
                return k;
            }
        }
        logger.warn("A kert "+chip+" chippel nem szerepel kutya az adatbazisban");
        throw new KutyaNotFound();
    }


    void clearFile() throws IOException {
        mapper.writeValue(jsonfile, new ArrayList<>());
    }
}
