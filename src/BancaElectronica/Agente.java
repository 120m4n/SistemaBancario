package BancaElectronica;

import java.time.LocalDate;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class Agente implements Callable<Double> {
    private Banco banco;
    private Stream<String> cuentas;

    public Agente(Banco banco, Stream<String> cuentas) {
        this.banco = banco;
        this.cuentas = cuentas;
    }

    @Override
    public Double call() throws Exception {
        final double[] total = {0};

        cuentas.forEach( linea -> {
            String[] datos = linea.replace("]", "").split(",");
            String tipoCuenta = datos[0].split("\\[")[0].trim();
            int idCuenta = Integer.parseInt(datos[0].split("\\[")[1].trim());
            double saldo = Double.parseDouble(datos[2].trim());
            double tasaInteres = Double.parseDouble(datos[3].trim());
            int idCliente = Integer.parseInt(datos[4].trim());
            LocalDate fechaApertura = Utilities.convertStringToDate(datos[1].trim());
            Cuenta cuenta = null;
            switch (tipoCuenta) {
                case "CA":
                    cuenta = new CuentaDeAhorro(idCuenta, saldo, tasaInteres, fechaApertura);
                    break;
                case "CC":
                    double costoMantenimiento = Double.parseDouble(datos[3]);
                    cuenta = new CuentaDeCheque(idCuenta, saldo, costoMantenimiento, fechaApertura);
                    break;
            }
            /* add the account to the client if exist in bank otherwise
            * create the new client */
            if (banco.consultarCliente(idCliente) != null) {
                banco.consultarCliente(idCliente).agregarCuenta(cuenta);
            } else {
                /* create cliente and add the account */
                Cliente cliente = new Cliente(idCliente, "cliente" + idCliente);
                cliente.agregarCuenta(cuenta);
                banco.agregarCliente(cliente);
            }
            total[0] += saldo;
        });

        return total[0];
    }
}
