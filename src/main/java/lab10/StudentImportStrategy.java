package lab10;

import ro.ulbs.proiectaresoftware.students.Student;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface StudentImportStrategy {
    List<Student> read(Path path) throws IOException;
}