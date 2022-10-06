package BancaElectronica;

public class CuentaDeCheque extends Cuenta {

    private double costoManejoMensual;

    public CuentaDeCheque(int numero, double saldo, double costoManejoMensual) {
        super(numero, saldo);
        this.costoManejoMensual = costoManejoMensual;
    }

    public CuentaDeCheque(int numero, double saldo, double costoManejoMensual, String fechaApertura) {
        super(numero, saldo, fechaApertura);
        this.costoManejoMensual = costoManejoMensual;
    }

    public double getCostoManejoMensual() {
        return costoManejoMensual;
    }

    /* toString() */
    @Override
    public String toString() {

        return "CuentaDeCheque{numero=" + getNumero() + ", "  + "saldo=" + getSaldo() + '}';
    }

    @Override
    public int compareTo(Cuenta o) {
        /* comporar por saldo */
        return o.getSaldo() > getSaldo() ? 1 : -1;
    }
}
