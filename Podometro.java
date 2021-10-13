/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona
 * ha dado en una semana. 
 * 
 * @author   Aritz Ciriza
 */
public class Podometro {
    private char HOMBRE = 'H';
    private char MUJER = 'M';
    private double ZANCADA_HOMBRE = 0.45;
    private double ZANCADA_MUJER = 0.41;
    private int SABADO = 6;
    private int DOMINGO = 7;
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables; //hago aqui una aclaraci�n, entiendo esta variable como entera para no representar que dio "2 pasos y medio" por ejemplo.
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private double tiempo;
    private int caminatasNoche;

    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        sexo = 'M';
        altura = 0;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;
        if (sexo == 'H') {
            longitudZancada = Math.ceil(altura * 0.45);
        }
        else {
            longitudZancada = Math.floor(altura * 0.41);
        }
    }

    /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFin � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        int duracionCaminata = (horaFin * 60) - (horaInicio * 60);
        switch (dia){
            case 1 : totalPasosLaborables += pasos;
                break;
            case 2 : totalPasosLaborables += pasos;
                break;
            case 3 : totalPasosLaborables += pasos;
                break;
            case 4 : totalPasosLaborables += pasos;
                break;
            case 5 : totalPasosLaborables += pasos;
                break;
            case 6 : totalPasosSabado += pasos;

            default: case 7 : totalPasosDomingo += pasos;
        }
        duracionCaminata += tiempo;
    }

    /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println("Configuraci�n del pod�metro");
        System.out.println("***************************");
        System.out.println("Altura: " + altura / 100 + " mtos");
        System.out.println("Sexo: " + sexo);
        System.out.println("Longitud zancada: " + longitudZancada + " mtos");
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        System.out.println("Estad�sticas");
        System.out.println("*****************");
        System.out.println("Distancia recorrida toda la semana: " + totalDistanciaSemana + " km");
        System.out.println("Distancia recorrida fin de semana: "+ totalDistanciaFinSemana + " km");
        System.out.println("N�pasos dias laborables: " + totalPasosLaborables);
        System.out.println("N�pasos SABADO: " + totalPasosSabado);
        System.out.println("N�pasos DOMINGO: " + totalPasosDomingo);
        System.out.println("");
        System.out.println("N�caminatas realizadas a partir de las 21h: " + caminatasNoche);
        System.out.println("");
        System.out.println("Tiempo total caminando en la semana " + tiempo);
        System.out.println("D�a/s con m�s pasos caminados" +);
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
    if (totalPasosLaborables > totalPasosSabado && totalPasosLaborables > totalPasosDomingo) {
        return "Laborables";
    }
    else if(totalPasosSabado > totalPasosDomingo && totalPasosSabado >totalPasosLaborables) {
        return "Sabado";
    }
    else if (totalPasosDomingo > totalPasosSabado && totalPasosDomingo >totalPasosLaborables) {
        return "Domingo";
    }
    if ( totalPasosLaborables == totalPasosSabado) {
        return "Laborables y Sabados";
    }
    else if (totalPasosSabado == totalPasosDomingo) {
        return "Sabados y Domingos";
    }
    else if (totalPasosLaborables == totalPasosDomingo) {
        return "Domingos y Laborables";
    }
    }

    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
     *  
     */    
    public void reset() {

    }
}
