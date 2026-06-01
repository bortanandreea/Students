package lab10;

import ro.ulbs.proiectaresoftware.students.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StudentiDinFisierText implements StudentImportStrategy {
    @Override public List<Student> read(Path path) throws IOException {
        List<Student> result = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length <5) {
                    continue;
                }
                int nr = Integer.parseInt(p[0].trim());
                String nume = p[1].trim();
                String prenume = p[2].trim();
                String formatiedestudiu = p[3].trim();
                double nota = Double.parseDouble(p[4].trim());
                result.add(new Student(nr, nume, prenume, formatiedestudiu, nota));
            }
        }
        return result;
    }
}