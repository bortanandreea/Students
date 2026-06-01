package lab10;

import ro.ulbs.proiectaresoftware.students.Student;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StudentiDinFisierXLSX implements StudentImportStrategy {
    @Override public List<Student> read(Path path) throws IOException {
        List<Student> result = new ArrayList<>();
        try (InputStream in = Files.newInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(in)) {
            var sheet = workbook.getSheetAt(0);
            boolean first = true;
            for (Row row : sheet) {
                if (first) {
                    first = false;
                    continue;
                }
                int nr = (int) row.getCell(0).getNumericCellValue();
                String nume = row.getCell(1).getStringCellValue();
                String prenume = row.getCell(2).getStringCellValue();
                String grupa = row.getCell(3).getStringCellValue();
                double medie = row.getCell(4).getNumericCellValue();
                result.add(new Student(nr, nume, prenume, grupa, medie));
            }
        }
        return result;
    }
}