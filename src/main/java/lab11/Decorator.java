package lab11;

import ro.ulbs.proiectaresoftware.students.Student;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Decorator implements StudentExportStrategy {
    private final StudentExportStrategy wrappedStrategy;

    public Decorator(StudentExportStrategy wrappedStrategy) {
        this.wrappedStrategy = wrappedStrategy;
    }

    @Override
    public void export(List<Student> studenti, Path path) throws IOException {
        long startTime = System.currentTimeMillis();

        wrappedStrategy.export(studenti, path);

        long endTime = System.currentTimeMillis();
        System.out.println("Timp de executie export: " + (endTime - startTime) + " ms");
    }
}