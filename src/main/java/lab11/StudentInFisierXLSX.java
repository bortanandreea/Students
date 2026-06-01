package lab11;

import ro.ulbs.proiectaresoftware.students.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class StudentInFisierXLSX implements StudentExportStrategy {
    @Override public void export(List<Student> studenti, Path path) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            var sheet = workbook.createSheet("Studenti");
            int rowIndex =0;
            Row header = sheet.createRow(rowIndex++);
            header.createCell(0).setCellValue("NrMatricol");
            header.createCell(1).setCellValue("Nume");
            header.createCell(2).setCellValue("Prenume");
            header.createCell(3).setCellValue("Grupa");
            header.createCell(4).setCellValue("Medie");

            for (Student s : studenti) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(s.getNumarMatricol());
                row.createCell(1).setCellValue(s.getNume());
                row.createCell(2).setCellValue(s.getPrenume());
                row.createCell(3).setCellValue(s.getFormatieDeStudiu());
                row.createCell(4).setCellValue(s.getNota());
            }

            try (OutputStream out = Files.newOutputStream(path)) {
                workbook.write(out);
            }
        }
    }
}