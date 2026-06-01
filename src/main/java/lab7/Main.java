package lab7;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ro.ulbs.proiectaresoftware.students.Student;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String[] XLS_HEADERS = new String[]{
            "numarMatricol",
            "prenume",
            "nume",
            "formatieDeStudiu",
            "nota"
    };

    public static void main(String[] args) {
        try {
            Path inputPath = resolveInputFile();
            List<Student> students = readStudentsFromText(inputPath);

            Path xlsPath = Paths.get("laborator8_students.xls").toAbsolutePath().normalize();
            exportStudentsToXls(students, xlsPath);

            List<Student> fromXls = readStudentsFromXls(xlsPath);
            System.out.println("Exported students: " + students.size());
            System.out.println("Imported students: " + fromXls.size());
        } catch (IOException e) {
            System.err.println("Eroare la citirea/scrierea fisierelor: " + e.getMessage());
        }
    }

    static void exportStudentsToXls(List<Student> students, Path outputPath) throws IOException {
        try (Workbook workbook = new HSSFWorkbook(); OutputStream output = Files.newOutputStream(outputPath)) {
            Sheet sheet = workbook.createSheet("Students");
            Row header = sheet.createRow(0);
            for (int i = 0; i < XLS_HEADERS.length; i++) {
                header.createCell(i).setCellValue(XLS_HEADERS[i]);
            }

            int rowIndex = 1;
            for (Student student : students) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(student.getNumarMatricol());
                row.createCell(1).setCellValue(student.getPrenume());
                row.createCell(2).setCellValue(student.getNume());
                row.createCell(3).setCellValue(student.getFormatieDeStudiu());
                if (student.getNota() != null) {
                    row.createCell(4).setCellValue(student.getNota());
                }
            }

            for (int i = 0; i < XLS_HEADERS.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(output);
        }
    }

    static List<Student> readStudentsFromXls(Path inputPath) throws IOException {
        List<Student> students = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();

        try (InputStream input = Files.newInputStream(inputPath); Workbook workbook = new HSSFWorkbook(input)) {
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                return students;
            }

            int lastRow = sheet.getLastRowNum();
            for (int rowIndex = 1; rowIndex <= lastRow; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }

                String idText = cellText(row, 0, formatter);
                Integer numarMatricol = parseIntOrNull(idText);
                if (numarMatricol == null) {
                    continue;
                }

                String prenume = cellText(row, 1, formatter);
                String nume = cellText(row, 2, formatter);
                String formatieDeStudiu = cellText(row, 3, formatter);
                Double nota = parseDoubleOrNull(cellText(row, 4, formatter));

                students.add(new Student(numarMatricol, prenume, nume, formatieDeStudiu, nota));
            }
        }

        return students;
    }

    static List<Student> readStudentsFromText(Path filePath) throws IOException {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
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

    private static String cellText(Row row, int index, DataFormatter formatter) {
        if (row == null) {
            return "";
        }
        if (row.getCell(index) == null) {
            return "";
        }
        return formatter.formatCellValue(row.getCell(index)).trim();
    }

    private static Integer parseIntOrNull(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    private static Double parseDoubleOrNull(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}