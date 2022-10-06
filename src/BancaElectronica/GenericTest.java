package BancaElectronica;

public class GenericTest <T> {
    private T dato;

    public GenericTest(T dato) {
        this.dato = dato;
    }

    public void add(T dato) {
        this.dato = dato;
    }
    public T getDato() {
        return dato;
    }
    public static void main(String[] args) {
        GenericTest<Integer> obj1= new GenericTest<Integer>(23);

        /*Usando inferencia Java 7 en adelante*/
        GenericTest<String> obj2= new GenericTest<>("hola");
        GenericTest<Character> obj3= new GenericTest<>('a');

        /*Imprimiendo tipos de dato de cada objeto*/
        System.out.println("-*-*-*tipos de dato-*-*-*");
        System.out.println("obj1.dato: "+obj1.getDato().getClass());
        System.out.println("obj2.dato: "+obj2.getDato().getClass());
        System.out.println("obj3.dato: "+obj3.getDato().getClass());
    }
}
