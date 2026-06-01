package ro.ulbs.proiectaresoftware.students;

import java.util.Objects;

public class Student {
    int numarMatricol;
    String prenume;
    String nume;
    String formatieDeStudiu;
    Double nota;

    @Override
    public String toString() {
        String notaText = nota == null ? "-" : String.format("%.2f", nota);
        return String.format("%12d %-12s %-12s %-12s nota=%s",
                numarMatricol, prenume, nume, formatieDeStudiu, notaText);
    }

    public Student(int numarMatricol, String prenume, String nume, String formatieDeStudiu) {
        this(numarMatricol, prenume, nume, formatieDeStudiu, null);
    }

    public Student(int numarMatricol, String prenume, String nume, String formatieDeStudiu, Double nota) {
        this.numarMatricol = numarMatricol;
        this.prenume = prenume;
        this.nume = nume;
        this.formatieDeStudiu = formatieDeStudiu;
        this.nota = nota;
    }

    public String getFormatieDeStudiu() {
        return formatieDeStudiu;
    }

    public int getNumarMatricol() {
        return numarMatricol;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNume() {
        return nume;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        Student other = (Student) obj;
        return numarMatricol == other.numarMatricol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numarMatricol);
    }
}