package BancaElectronica;

import static BancaElectronica.Utilities.generateRandomStreet;

public class Domicilio {
    private String calle;
    private String numero;
    private String colonia;
    private String estado;
    private String codigoPostal;

    public Domicilio(){
     this.calle = generateRandomStreet();
    }

    public Domicilio(String calle, String numero, String colonia, String estado, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.colonia = colonia;
        this.estado = estado;
        this.codigoPostal = codigoPostal;
    }



    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
    /*
    toString
     */
    @Override
    public String toString() {
        return "calle=" + calle ;
    }
}
