package ro.ulbs.proiectaresoftware.students;


import java.util.ArrayList;
import java.util.List;

public class Ex2 {

    public static void main(String[] args) {

        List<Student> studenti = new ArrayList<>();

        studenti.add(new Student(101, "Ana", "Pop", "TI21/1"));
        studenti.add(new Student(120, "Alis", "Popa", "TI21/2"));
        studenti.add(new Student(110, "Ion", "Ionescu", "TI21/2"));
        studenti.add(new Student(115, "George", "Vasilescu", "TI21/3"));

        System.out.println("Lista studenti:");
        for (Student s : studenti) {
            System.out.println(s);
        }

        Student s1 = new Student(120, "Alis", "Popa", "TI21/2");
        Student s2 = new Student(112, "Maria", "Popa", "TI21/1");

        System.out.println("\nExista Alis Popa? " + studentExists(studenti, s1));
        System.out.println("Exista Maria Popa? " + studentExists(studenti, s2));
    }

    public static boolean studentExists(List<Student> lista, Student student) {

        for (Student s : lista) {

            if (s.getPrenume().equals(student.getPrenume()) &&
                    s.getNume().equals(student.getNume()) &&
                    s.getFormatieDeStudiu().equals(student.getFormatieDeStudiu())) {

                return true;
            }
        }

        return false;
    }
}
