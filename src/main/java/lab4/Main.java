package lab4;

import ro.ulbs.proiectaresoftware.students.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static List<Student> readStudents(Path filePath) throws IOException {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length != 4) {
                    continue;
                }

                int numarMatricol = Integer.parseInt(parts[0].trim());
                String prenume = parts[1].trim();
                String nume = parts[2].trim();
                String formatieDeStudiu = parts[3].trim();
                students.add(new Student(numarMatricol, prenume, nume, formatieDeStudiu));
            }
        }
        return students;
    }

    static Path resolveInputStudentsFile() {
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

    static Path resolveGradesFile() {
        Path[] candidates = new Path[]{
                Paths.get("src", "lab4", "note_anon.txt"),
                Paths.get("lab4", "note_anon.txt"),
                Paths.get("note_anon.txt")
        };
        for (Path candidate : candidates) {
            Path normalized = candidate.toAbsolutePath().normalize();
            if (Files.exists(normalized)) {
                return normalized;
            }
        }
        return candidates[0].toAbsolutePath().normalize();
    }

    static void applyGrades(Path gradesFilePath, HashMap<Integer, Student> studentiPeMatricol) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(gradesFilePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    continue;
                }

                int numarMatricol = Integer.parseInt(parts[0].trim());
                double nota = Double.parseDouble(parts[1].trim());
                Student student = studentiPeMatricol.get(numarMatricol);
                if (student != null) {
                    student.setNota(nota);
                }
            }
        }
    }

    static void writeStudentsWithGrades(Path outputPath, List<Student> students) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Student student : students) {
            String notaText = student.getNota() == null ? "" : String.format("%.2f", student.getNota());
            lines.add(student.getNumarMatricol() + "," + student.getPrenume() + "," + student.getNume() + ","
                    + student.getFormatieDeStudiu() + "," + notaText);
        }
        Files.write(outputPath, lines);
    }

    static String buildNameKey(String prenume, String nume) {
        return (prenume == null ? "" : prenume.trim().toLowerCase()) + "|"
                + (nume == null ? "" : nume.trim().toLowerCase());
    }

    static float gasesteNota(String prenume, String nume, Map<String, Student> tineri) {
        Student student = tineri.get(buildNameKey(prenume, nume));
        if (student == null || student.getNota() == null) {
            return 0.0f;
        }
        return student.getNota().floatValue();
    }

    public static void main(String[] args) {
        try {
            Path inputPath = resolveInputStudentsFile();
            Path gradesPath = resolveGradesFile();
            Path outputPath = Paths.get("studenti_out.txt").toAbsolutePath().normalize();

            List<Student> students = readStudents(inputPath);

            HashMap<Integer, Student> studentiPeMatricol = new LinkedHashMap<>();
            for (Student student : students) {
                studentiPeMatricol.put(student.getNumarMatricol(), student);
            }

            applyGrades(gradesPath, studentiPeMatricol);

            Map<String, Student> tineri = new HashMap<>();
            for (Student student : studentiPeMatricol.values()) {
                tineri.put(buildNameKey(student.getPrenume(), student.getNume()), student);
            }

            float notaM = gasesteNota("Bianca", "Popescu", tineri);
            float notaN = gasesteNota("Ioan", "Popa", tineri);

            System.out.println("notaM (Bianca Popescu): " + notaM);
            System.out.println("notaN (Ioan Popa): " + notaN);

            System.out.println("Studenti (HashMap) dupa alocarea notelor:");
            for (Map.Entry<Integer, Student> entry : studentiPeMatricol.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }

            writeStudentsWithGrades(outputPath, new ArrayList<>(studentiPeMatricol.values()));

        } catch (IOException e) {
            System.err.println("Eroare la citirea/scrierea fisierelor: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Format numeric invalid in fisier: " + e.getMessage());
        }
    }
}