package BancaElectronica;


import java.time.LocalDate;

public abstract class  Cuenta implements Comparable<Cuenta> {
    private int numero;
    private LocalDate fechaApertura;
    private double saldo;
    private LocalDate fechaCancelacion;

    public Cuenta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public Cuenta(int numero, double saldo, LocalDate fechaApertura) {
        this.numero = numero;
        this.saldo = saldo;
        this.fechaApertura = fechaApertura;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public LocalDate getFechaCreacion() {
        return getFechaApertura() ;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LocalDate getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(LocalDate fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    /* toString() */
    @Override
    public String toString() {
        return "Cuenta{" + "numero=" + numero + ", saldo=" + saldo  + '}';
    }

    public void abonar(double monto) {
        setSaldo(getSaldo() + monto);
    }

    public void retirar(double monto) {
        setSaldo(getSaldo() - monto);
    }


}
