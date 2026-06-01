package lab2;

import ro.ulbs.proiectaresoftware.students.Student;
import ro.ulbs.proiectaresoftware.students.StudentBursier;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    static boolean checkStudent1(Student s) {
        if (s.getNumarMatricol() ==120 && s.getNume().equals("Alis") && s.getPrenume().equals("Popa") && s.getFormatieDeStudiu().equals("TI21/1")) {
            return true;
        }
        return false;
    }

    static boolean checkStudent2(Student s) {
        if (s.getNumarMatricol() ==112 && s.getNume().equals("Maria") && s.getPrenume().equals("Popa") && s.getFormatieDeStudiu().equals("TI21/1")) {
            return true;
        }
        return false;
    }

    static ArrayList<Student> readStudents(Path filePath) throws IOException {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length ==4) {
                    int numarMatricol = Integer.parseInt(parts[0].trim());
                    String prenume = parts[1].trim();
                    String nume = parts[2].trim();
                    String formatieDeStudiu = parts[3].trim();
                    students.add(new Student(numarMatricol, prenume, nume, formatieDeStudiu));
                }
            }
        }
        return students;
    }

    static void writeStudents(Path filePath, ArrayList<Student> students) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        for (Student s : students) {
            lines.add(s.getNumarMatricol() + "," + s.getPrenume() + "," + s.getNume() + "," + s.getFormatieDeStudiu());
        }
        Files.write(filePath, lines);
    }

    static void writeBursieri(Path filePath, List<StudentBursier> bursieri) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        for (StudentBursier b : bursieri) {
            lines.add(b.toString());
        }
        Files.write(filePath, lines);
    }

    static Path resolveInputFile() {
        Path[] candidates = new Path[]{
                Paths.get("studenti_in.txt"),
                Paths.get("src", "studenti_in.txt"),
                Paths.get("..", "studenti_in.txt")
        };
        for (Path candidate : candidates) {
            Path normalized = candidate.toAbsolutePath().normalize();
            if (Files.exists(normalized)) {
                return normalized;
            }
        }
        return candidates[0].toAbsolutePath().normalize();
    }

    public static void main(String[] args) {
        try {
            Path inputPath = resolveInputFile();
            Path outputPath = Paths.get("studenti_out.txt").toAbsolutePath().normalize();

            ArrayList<Student> a = readStudents(inputPath);

            System.out.println("Toti studentii:");
            for (Student elem : a) {
                System.out.println(elem);
            }

            a.sort(Comparator.comparing(Student::getNume));
            writeStudents(outputPath, a);

            System.out.println("Fisierul de iesire a fost scris la: " + outputPath);

            boolean s1 = false;
            boolean s2 = false;
            for (Student elem : a) {
                if (checkStudent1(elem)) {
                    System.out.println("Studentul1 a fost gasit");
                    s1 = true;
                } else if (checkStudent2(elem)) {
                    System.out.println("Studentul2 a fost gasit");
                    s2 = true;
                }
            }
            if (!s1) {
                System.out.println("Studentul1 nu a fost gasit");
            }
            if (!s2) {
                System.out.println("Studentul2 nu a fost gasit");
            }

            List<StudentBursier> bursieri = new ArrayList<>();
            bursieri.add(new StudentBursier(1025, "Andrei", "Popa", "ISM141/2",8.70,725.50));
            bursieri.add(new StudentBursier(1024, "Ioan", "Mihalcea", "ISM141/1",9.80,801.10));
            bursieri.add(new StudentBursier(1026, "Anamaria", "Prodan", "TI131/1",8.90,745.50));
            bursieri.add(new StudentBursier(1029, "Bianca", "Popescu", "TI131/1",9.10,780.80));

            Path bursieriOutput = Paths.get("bursieri_out.txt").toAbsolutePath().normalize();
            writeBursieri(bursieriOutput, bursieri);

            System.out.println("Fisierul de iesire pentru bursieri a fost scris la: " + bursieriOutput);
        } catch (IOException e) {
            System.err.println("Eroare la citirea/scrierea fisierelor: " + e.getMessage());
        }
    }
}