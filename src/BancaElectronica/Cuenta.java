package BancaElectronica;

import java.time.Instant;

public abstract class  Cuenta implements Comparable<Cuenta> {
    private int numero;
    private String fechaApertura;
    private double saldo;
    private String fechaCancelacion;

    public Cuenta(int numero, double saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public String getFechaCreacion() {
        return getFechaApertura() ;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(String fechaCancelacion) {
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
