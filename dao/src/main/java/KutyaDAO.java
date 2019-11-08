import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hu.uni.eszterhazy.model.Kutya;

import javax.management.openmbean.ArrayType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class KutyaDAO {
    File jsonfile;
    ObjectMapper mapper;
    public KutyaDAO(String jsonfilepath) throws IOException {
        jsonfile = new File(jsonfilepath);
        if(!jsonfile.exists()){
            jsonfile.createNewFile();
            FileWriter writer = new FileWriter(jsonfile);
            writer.write("[]");
            writer.close();
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public Collection<Kutya> readAllKutya() throws IOException {
        Collection<Kutya> result = new ArrayList<>();
        TypeReference type= new TypeReference<ArrayType<Kutya>>(){};
        result = mapper.readValue(jsonfile, type);
        return result;
    }
}
