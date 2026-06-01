package lab11;

import ro.ulbs.proiectaresoftware.students.Student;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(); // Add test students if needed
        Path textPath = Path.of("students.txt");
        Path xlsxPath = Path.of("students.xlsx");

        StudentExportStrategy textExport = new Decorator(new StudentInFisierText());
        StudentExportStrategy xlsxExport = new Decorator(new StudentInFisierXLSX());

        try {
            System.out.println("Exporting to TXT...");
            textExport.export(students, textPath);

            System.out.println("Exporting to XLSX...");
            xlsxExport.export(students, xlsxPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}