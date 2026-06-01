package lab10;

import lab10.StudentiInConsola;
import lab10.StudentiInFisierText;
import lab10.StudentiInFisierXLSX;
import lab10.StudentiDinFisierText;
import lab10.StudentiDinFisierXLSX;
import ro.ulbs.proiectaresoftware.students.Student;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class AplicatieCuStrategy {

    public static void main(String[] args) throws Exception {
        List<Student> studenti = Arrays.asList(
                new Student(1025, "Ioan", "Popa", "TI21/1",8.7),
                new Student(1024, "Alis", "Popa", "TI21/2",10.0),
                new Student(1026, "Mihai", "Vecerdea", "TI22/1",8.9),
                new Student(1029, "Eugen", "Uritescu", "TI22/2",10.0),
                new Student(1029, "Anamaria", "Prodan", "TI21/1",8.9),
                new Student(1029, "Bianca", "Popescu", "TI21/1",10.0),
                new Student(1029, "Maria", "Oprea", "TI21/1",4.9)
        );

        new StudentiInConsola().export(studenti, null);

        Path txt = Path.of("studenti.txt");
        Path xlsx = Path.of("studenti.xlsx");

        new StudentiInFisierText().export(studenti, txt);
        new StudentInFisierXLSX().export(studenti, xlsx);

        List<Student> dinTxt = new StudentiDinFisierText().read(txt);
        List<Student> dinXlsx = new StudentiDinFisierXLSX().read(xlsx);

        new StudentiInConsola().export(dinTxt, null);
        new StudentiInConsola().export(dinXlsx, null);
    }
}