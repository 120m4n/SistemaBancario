package BancaElectronica;

import static java.lang.Integer.parseInt;

public class Utilities {
    /* convert string date to date */
    public static java.util.Date convertStringToDate(String date) {
        java.util.Date dateConverted = null;
        try {
            dateConverted = new java.text.SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (java.text.ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return dateConverted;
    }
    /* genereate random datestring in format dd/MM/yyyy */
    public static String generateRandomDate() {
        java.util.Random random = new java.util.Random();
        int day = random.nextInt(28) + 1;
        int month = random.nextInt(12) + 1;
        int year = random.nextInt(100) + 1900;
        return day + "/" + month + "/" + year;
    }

    /* generate random telephone number */
    public static String generateRandomTelephone() {
        java.util.Random random = new java.util.Random();
        String telephone = "";
        for (int i = 0; i < 10; i++) {
            telephone += random.nextInt(10);
        }
        return telephone;
    }

    /* generate random street name */
    public static String generateRandomStreet() {
        String[] streets = {"Calle", "Avenida", "Boulevard", "Privada", "Camino"};
        java.util.Random random = new java.util.Random();
        return streets[random.nextInt(streets.length)] + " " + generateRandomString(10);
    }

    private static String generateRandomString(int i) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        java.util.Random random = new java.util.Random();
        String randomString = "";
        for (int j = 0; j < i; j++) {
            randomString += characters.charAt(random.nextInt(characters.length()));
        }
        return randomString;
    }

    /* read Clients from CSV file */
    public static java.util.List<Cliente> readClientsFromCSV(String path) {
        java.util.List<Cliente> clientes = new java.util.ArrayList<>();
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(path))) {
            String line;
            // skip first line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Cliente cliente = new Cliente(parseInt(values[0]), values[1]);
                cliente.setTelefono(values[2]);
                cliente.setDomicilio(new Domicilio(values[3], parseInt(values[4]), values[5], values[6], parseInt(values[7])));
                clientes.add(cliente);
            }
        } catch (java.io.IOException ex) {
            System.out.println(ex.getMessage());
        }
        return clientes;
    }

    /*  read Accounts from CSV file */
    public static java.util.List<Cuenta> readAccountsFromCSV(String path) {
        java.util.List<Cuenta> cuentas = new java.util.ArrayList<>();
        try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(path))) {
            String line;
            // skip first line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Cuenta cuenta = createCuenta(values);
                /* add cuenta to cuentas if not null */
                if (cuenta != null) {
                    cuentas.add(cuenta);
                }

            }
        } catch (java.io.IOException ex) {
            System.out.println(ex.getMessage());
        }
        return cuentas;
    }

    private static Cuenta createCuenta(String[] metadata) {
        int numero = parseInt(metadata[0].trim());
        int codigoCliente = parseInt(metadata[1].trim());
        String tipo = metadata[2].trim();
        double tasadeInteres = Double.parseDouble(metadata[3].trim());
        double costoMantenimiento = Double.parseDouble(metadata[4].trim());
        double saldo = Double.parseDouble(metadata[5].trim());
        String fechaApertura = metadata[6].trim();
        /* switch tipo */
        switch (tipo) {
            case "Ahorro":
                return new CuentaDeAhorro(numero, saldo , tasadeInteres, fechaApertura);
            case "Cheque":
                return new CuentaDeCheque(numero, saldo, costoMantenimiento, fechaApertura);
            default:
                return null;
        }


    }

    public static String getRFC(String nombre, String apellidoPaterno, String apellidoMaterno, String fechaNacimiento) {
        String rfc = "";
        rfc += apellidoPaterno.substring(0, 2);
        rfc += apellidoMaterno.substring(0, 1);
        rfc += nombre.substring(0, 1);
        rfc += fechaNacimiento.substring(2, 4);
        rfc += fechaNacimiento.substring(5, 7);
        rfc += fechaNacimiento.substring(8, 10);
        return rfc;
    }
    /* Generic Method */
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    public <T> void imprimirGenerico(T obj) {
        System.out.println(obj.getClass() + " " + obj);
    }
}
