package lab11;

import ro.ulbs.proiectaresoftware.students.Student;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


import lab10.StudentExportStrategy;
import ro.ulbs.proiectaresoftware.students.Student;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class StudentiInConsola implements StudentExportStrategy {
    @Override public void export(List<Student> studenti, Path path) throws IOException {
        for (Student s : studenti) {
            System.out.println(format(s));
        }
    }

    private String format(Student s) {
        return s.getNumarMatricol() + ", " +
                s.getNume() + ", " +
                s.getPrenume() + ", " +
                s.getFormatieDeStudiu() + ", " +
                s.getNota();
    }
}