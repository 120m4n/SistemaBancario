package BancaElectronica;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class CuentaDeAhorro extends Cuenta {
    private double tasaInteresMensual;
    private double saldoMinimo;

    public CuentaDeAhorro(int numero, double saldo, double tasaInteresMensual, LocalDate fechaApertura) {
        super(numero, saldo, fechaApertura);
        this.tasaInteresMensual = tasaInteresMensual;
        this.saldoMinimo = 0;
    }
    public CuentaDeAhorro(int numero, double saldo, double tasaInteresMensual) {
        super(numero, saldo);
        this.tasaInteresMensual = tasaInteresMensual;
        this.saldoMinimo = saldoMinimo;
        this.setFechaApertura(Instant.now().atZone(ZoneId.of("America/Mexico_City")).toLocalDate());
    }

    public double getTasaInteres() {
        return tasaInteresMensual;
    }

    public void setTasaInteres(double tasaInteresMensual) {
        this.tasaInteresMensual = tasaInteresMensual;
    }

    public double getSaldoMinimo() {
        return saldoMinimo;
    }

    public void setSaldoMinimo(double saldoMinimo) {
        this.saldoMinimo = saldoMinimo;
    }

    /* caltulateInterest() */
    public double calculateInterest() {
        return getSaldo() * getTasaInteres();
    }

    /* toString() */
    @Override
    public String toString() {
        return "CuentaDeAhorro{numero=" + getNumero() + ", "  +"saldo=" + getSaldo() + '}';
    }

    @Override
    public int compareTo(Cuenta o) {
        return o.getSaldo() > getSaldo() ? 1 : -1;
    }
}

