package lab11;

import ro.ulbs.proiectaresoftware.students.Student;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StudentInFisierText implements StudentExportStrategy {
    @Override public void export(List<Student> studenti, Path path) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (Student s : studenti) {
                writer.write(format(s));
                writer.newLine();
            }
        }
    }

    private String format(Student s) {
        return s.getNumarMatricol() + "," +
                s.getNume() + "," +
                s.getPrenume() + "," +
                s.getFormatieDeStudiu() + "," +
                s.getNota();
    }
}