package BancaElectronica;

import java.util.ArrayList;

public interface ServicioCuentas
{
    public boolean agregarCuenta(Cuenta cuenta);
    public boolean cancelarCuenta(Cuenta cuenta);
    public void abonarCuenta(int numeroCuenta, double monto);
    public void retirarCuenta(int numeroCuenta, double monto);
    public ArrayList<Cuenta> obtenerCuentas();
}
