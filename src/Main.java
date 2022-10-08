import BancaElectronica.*;

import java.util.Comparator;

import static BancaElectronica.Utilities.convertStringToDate;

public class Main {
    public static void main(String[] args) {

    ServicioClientes sc = new Banco();



    Cliente cliente_2 = new Cliente(2, "antonio");
    Cliente cliente_3 = new Cliente(3, "gustavo");
    Cliente cliente_5 = new Cliente(5, "jesus");
    Cliente cliente_4 = new Cliente(4, "enrique");
    Cliente cliente_6 = new Cliente(6, "abraham");
    Cliente cliente_1 = new Cliente(1, "juan");

    cliente_2.setDomicilio( new Domicilio());
    cliente_3.setDomicilio( new Domicilio());
    cliente_5.setDomicilio( new Domicilio());
    cliente_4.setDomicilio( new Domicilio());
    cliente_6.setDomicilio( new Domicilio());
    cliente_1.setDomicilio( new Domicilio());

    Cuenta cuenta_1 = new CuentaDeAhorro(1, 11000, 0.05);
    Cuenta cuenta_2 = new CuentaDeAhorro(2, 12000, 0.05);
    Cuenta cuenta_3 = new CuentaDeAhorro(3, 13000, 0.05);
    Cuenta cuenta_4 = new CuentaDeAhorro(4, 14000, 0.05);
    Cuenta cuenta_5 = new CuentaDeCheque(1, 3000, 1000);
    Cuenta cuenta_6 = new CuentaDeCheque(2, 4000, 500);

    cuenta_1.setFechaApertura(convertStringToDate("01-01-2020"));
    cuenta_2.setFechaApertura(convertStringToDate("02-07-2021"));
    cuenta_3.setFechaApertura(convertStringToDate("03-08-2022"));
    cuenta_4.setFechaApertura(convertStringToDate("04-09-2020"));
    cuenta_5.setFechaApertura(convertStringToDate("05-10-2021"));
    cuenta_6.setFechaApertura(convertStringToDate("06-11-2018"));

    cliente_1.agregarCuenta(cuenta_1);
    cliente_1.agregarCuenta(cuenta_2);
    cliente_1.agregarCuenta(cuenta_3);
    cliente_2.agregarCuenta(cuenta_4);
    cliente_2.agregarCuenta(cuenta_5);
    cliente_3.agregarCuenta(cuenta_6);



    sc.agregarCliente(cliente_1);
    sc.agregarCliente(cliente_2);
    sc.agregarCliente(cliente_3);
    sc.agregarCliente(cliente_4);
    sc.agregarCliente(cliente_5);
    sc.agregarCliente(cliente_6);


    /* get clientes  using stream and sorted by numero*/
    sc.obtenerClientes().stream().sorted().forEach(System.out::println);
    /* buscar cliente por RFC */
    /*Cliente c = sc.buscarClientePorRFC("RFC 3");
    // print c if not null
    if (c != null) {
        System.out.println(c.getNombre());
    }
    */
    System.out.println("------   consulta los clientes cuyo nombre inicie con a  o con j y retorne unicamente los telefonos y ordenados z to a ---------");
    /* servicio clientes */
    /* consulta los clientes cuyo nombre inicie con a  o con j y retorne unicamente los telefonos y ordenados z to a*/
    sc.obtenerClientes().stream().filter(c -> c.getNombre().startsWith("a") || c.getNombre().startsWith("j")).map(c -> c.getTelefono()).sorted(Comparator.reverseOrder()).forEach(System.out::println);
    //sc.obtenerClientes().stream().filter(c -> c.getNombre().startsWith("a") || c.getNombre().startsWith("j")).forEach(c -> System.out.println(c.getNombre() + " " + c.getTelefono()));

        /*servicio cuentas*/
    /* consultar domicilios de los clientes cuyas cuentas con fecha de creacion no supere x tiempo*/
    System.out.println("------   consultar domicilios de los clientes cuyas cuentas con fecha de creacion no supere x tiempo ---------");
    //sc.obtenerClientes().stream().filter(c -> c.getCuentas().stream().anyMatch(cuenta -> convertStringToDate(cuenta.getFechaCreacion()).after(convertStringToDate("01/01/2010")))).map(c -> c.getDomicilio()).forEach(System.out::println);
    sc.obtenerClientes().stream().filter(c -> c.getCuentas().stream().anyMatch(cuenta -> cuenta.getFechaCreacion().isAfter(convertStringToDate("01-07-2021")))).forEach(d->System.out.println(d.getNumero() + " " + d.getNombre() + " " + d.getDomicilio().getCalle()));

        //sc.obtenerClientes().stream().map(c -> c.getDomicilio()).forEach(System.out::println);


    System.out.println("------  ordenamiento de cuentas por saldo del cliente id = 1 ---------");
    Cliente c = sc.consultarCliente(1);
    if (c != null) {
        /* get cuentas using stream and sorted by saldo */
        c.getCuentas().stream().sorted(Comparator.comparing(Cuenta::getSaldo)).forEach(System.out::println);
        //c.getCuentas().stream().sorted().forEach(System.out::println);

    }





    }
}