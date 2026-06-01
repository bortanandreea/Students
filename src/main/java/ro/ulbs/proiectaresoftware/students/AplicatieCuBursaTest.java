package ro.ulbs.proiectaresoftware.students;
import java.util.ArrayList;
import java.util.List;

public class AplicatieCuBursaTest {

    public static void main(String[] args) {
        testSorteaza();
        System.out.println("Testul pentru sorteaza() a trecut cu succes.");
    }

    static void testSorteaza() {
        AplicatieCuBursa aplicatie = new AplicatieCuBursa();

        List<StudentBursier> lista = new ArrayList<>();
        lista.add(new StudentBursier(1, "Mara", "Ionescu", "TI131/2", 9.10, 700.00));
        lista.add(new StudentBursier(2, "Ana", "Ionescu", "TI131/1", 9.10, 600.00));
        lista.add(new StudentBursier(3, "Ana", "Ionescu", "TI131/1", 9.10, 500.00));
        lista.add(new StudentBursier(4, "Ana", "Ionescu", "TI131/1", 8.50, 900.00));
        lista.add(new StudentBursier(5, "Vlad", "Georgescu", "ISM141/1", 9.90, 800.00));

        List<StudentBursier> sortata = aplicatie.sorteaza(lista);

        assertEquals("ISM141/1", sortata.get(0).getFormatieDeStudiu(), "Ordinea pe formatieDeStudiu este gresita");
        assertEquals("TI131/1", sortata.get(1).getFormatieDeStudiu(), "Ordinea pe formatieDeStudiu este gresita");
        assertEquals("Ionescu", sortata.get(1).getNume(), "Ordinea pe nume este gresita");
        assertEquals("Ana", sortata.get(1).getPrenume(), "Ordinea pe prenume este gresita");

        assertDoubleEquals(8.50, sortata.get(1).getNota(), "Ordinea pe nota este gresita");
        assertDoubleEquals(9.10, sortata.get(2).getNota(), "Ordinea pe nota este gresita");
        assertDoubleEquals(500.00, sortata.get(2).getCuantumBursa(), "Ordinea pe cuantumBursa este gresita");
        assertDoubleEquals(600.00, sortata.get(3).getCuantumBursa(), "Ordinea pe cuantumBursa este gresita");

        assertEquals("TI131/2", sortata.get(4).getFormatieDeStudiu(), "Ultimul element ar trebui sa fie din TI131/2");
    }

    static void assertEquals(String expected, String actual, String message) {
        if (!expected.equals(actual)) {
            throw new AssertionError(message + ": expected='" + expected + "', actual='" + actual + "'");
        }
    }

    static void assertDoubleEquals(double expected, Double actual, String message) {
        if (actual == null || Math.abs(expected - actual) > 0.000001) {
            throw new AssertionError(message + ": expected=" + expected + ", actual=" + actual);
        }
    }
}