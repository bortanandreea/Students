package lab10;

import ro.ulbs.proiectaresoftware.students.Student;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface StudentExportStrategy {
    void export(List<Student> studenti, Path path) throws IOException;
}