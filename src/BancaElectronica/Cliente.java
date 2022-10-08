package BancaElectronica;

import java.time.LocalDate;
import java.util.ArrayList;

import static BancaElectronica.Utilities.generateRandomTelephone;

public class Cliente implements ServicioCuentas, Comparable<Cliente> {
    private int numero;
    private String nombre;
    private Domicilio domicilio;
    private String rfc;
    private String telefono;
    private ArrayList<Cuenta> cuentas;
    private LocalDate fechaNacimiento;

    public Cliente(int numero, String nombre){
        this.numero = numero;
        this.nombre = nombre;
        this.telefono = generateRandomTelephone();
        cuentas = new ArrayList<Cuenta>();
    }

    public Cliente(int numero, String nombre, Domicilio domicilio, String rfc, String telefono, LocalDate fechaNacimiento) {
        this.numero = numero;
        this.nombre = nombre;
        this.domicilio = domicilio;
        this.rfc = rfc;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        cuentas = new ArrayList<Cuenta>();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    /* toString */
    public String toString() {
        return "Cliente: " + numero + " " +  nombre + " " + telefono;
    }


    public int getId() {
        return numero;
    }

    @Override
    public boolean agregarCuenta(Cuenta cuenta) {
        return cuentas.add(cuenta);
    }

    @Override
    public boolean cancelarCuenta(Cuenta cuenta) {
        return cuentas.remove(cuenta);
    }

    @Override
    public void abonarCuenta(int numeroCuenta, double monto) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumero() == numeroCuenta) {
                cuenta.abonar(monto);
            }
        }
    }

    @Override
    public void retirarCuenta(int numeroCuenta, double monto) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumero() == numeroCuenta) {
                cuenta.retirar(monto);
            }
        }
    }

    @Override
    public ArrayList<Cuenta> obtenerCuentas() {
        return cuentas;
    }

    @Override
    /* compareTo by Numero*/
    public int compareTo(Cliente o) {
        return numero - o.numero;
    }

}
