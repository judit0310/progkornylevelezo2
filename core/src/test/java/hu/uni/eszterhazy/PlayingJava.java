package hu.uni.eszterhazy;

import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class PlayingJava {

    @Test
    public void test() throws IOException {
        System.out.println("Szia");
        File file = new File("kiscica.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        System.out.println(file.getAbsolutePath());

        File target = new File("target");
        if (target.isDirectory()) {
            File[] files = target.listFiles();
            for (File f : files) {
                System.out.println(f.getName());
            }
        }

        FileWriter writer = new FileWriter(file);
        writer.write("Hello\n");
        writer.write("Cica Baba\n");
        writer.close();


        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            System.out.println(sc.next());
        }

        String string = "2019.12.25";
        System.out.println(string.matches("[1-9]\\d{3}.\\d{2}.\\d{2}"));

        LocalDate most = LocalDate.now();
        System.out.println(most);
        LocalDate egy = LocalDate.parse("2019-02-28");
        System.out.println(egy.isLeapYear());
        System.out.println(LocalDate.of(2017, 11, 4));
    }
}
