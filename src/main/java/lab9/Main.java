package lab9;

import ro.ulbs.proiectaresoftware.students.Student;

import java.util.Arrays;
import java.util.List;

public class Main {
    List<Student> studenticuNote = Arrays.asList(
            new Student(1025, "Ioan", "Popa", "TI21/1",8.7),
            new Student(1024, "Alis", "Popa", "TI21/2",10.0),
            new Student(1026, "Mihai", "Vecerdea", "TI22/1",8.9),
            new Student(1029, "Eugen", "Uritescu", "TI22/2",10.0),
            new Student(1029, "Anamaria", "Prodan", "TI21/1",8.9),
            new Student(1029, "Bianca", "Popescu", "TI21/1",10.0),
            new Student(1029, "Maria", "Oprea", "TI21/1",4.9)
    );

    public static void main(String[] args) {
        Main m = new Main();

        System.out.println("a) Nota 10:");
        m.studenticuNote.stream()
                .filter(s -> s.getNota() ==10.0)
                .forEach(System.out::println);

        System.out.println("b) Nota sub 5:");
        m.studenticuNote.stream()
                .filter(s -> s.getNota() <5.0)
                .forEach(System.out::println);

        System.out.println("c) Map nota <4 la4:");
        m.studenticuNote.stream()
                .map(s -> s.getNota() <4.0 ? new Student(s.getNumarMatricol(), s.getNume(), s.getPrenume(), s.getFormatieDeStudiu(),4.0)
                        : s)
                .forEach(System.out::println);

        double suma = m.studenticuNote.stream()
                .mapToDouble(Student::getNota)
                .reduce(0.0, Double::sum);
        System.out.println("d) Suma notelor: " + suma);

        double media = m.studenticuNote.isEmpty()
                ?0.0 : suma / m.studenticuNote.size();
        System.out.println("e) Media: " + media);
    }
}