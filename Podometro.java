/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona
 * ha dado en una semana. 
 * 
 * @author   Aritz Ciriza
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private  final char MUJER = 'M';
    private  final double ZANCADA_HOMBRE = 0.45;
    private  final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables; //hago aqui una aclaraci�n, entiendo esta variable como entera para no representar que dio "2 pasos y medio" por ejemplo.
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int tiempo;
    private int caminatasNoche; // entiendo esta variable como todas aquellas caminatas que empiecen a partir de las 21:00

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
     * Accesor para la marca
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
        if (queSexo == HOMBRE) {
            longitudZancada = Math.ceil(ZANCADA_HOMBRE * altura);
        }
        else {
            longitudZancada =Math.floor(ZANCADA_MUJER * altura);
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
        int duracionCaminata = (horaFin  - horaInicio);
        switch (dia){
            case 1 : 
            case 2 : 
            case 3 : 
            case 4 : 
            case 5 : totalPasosLaborables += pasos;
                break;
            case SABADO : totalPasosSabado += pasos;
                break;

            default: case DOMINGO : totalPasosDomingo += pasos;
                break;
        }
        if (horaInicio >= 2100) {
            caminatasNoche ++;
        }
        tiempo += duracionCaminata;
        totalDistanciaSemana = (double)totalPasosLaborables /100 * longitudZancada;
        totalDistanciaFinSemana = (double)(totalPasosSabado/100 + totalPasosDomingo/100) * longitudZancada;
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
        System.out.println("Longitud zancada: " + longitudZancada/100 + " mtos");
    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        int horas = tiempo /60;
        int minutos = tiempo - horas * 100;
        System.out.println("Estad�sticas");
        System.out.println("*****************");
        System.out.println("Distancia recorrida toda la semana: " + totalDistanciaSemana/1000 + " km");
        System.out.println("Distancia recorrida fin de semana: "+ totalDistanciaFinSemana/1000 + " km");
        System.out.println("N�pasos dias laborables: " + totalPasosLaborables);
        System.out.println("N�pasos SABADO: " + totalPasosSabado);
        System.out.println("N�pasos DOMINGO: " + totalPasosDomingo);
        System.out.println("");
        System.out.println("N�caminatas realizadas a partir de las 21h: " + caminatasNoche);
        System.out.println("");
        System.out.println("Tiempo total caminando en la semana: " + horas + "H" + " y" + minutos + "m" );
        System.out.println("D�a/s con m�s pasos caminados" );
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String diaMayor = "";
        if (totalPasosLaborables > totalPasosSabado && totalPasosLaborables > totalPasosDomingo) {
            diaMayor = "Laborables";
        }
        else if(totalPasosSabado > totalPasosDomingo && totalPasosSabado >totalPasosLaborables) {
            diaMayor = "Sabado";
        }
        else if (totalPasosDomingo > totalPasosSabado && totalPasosDomingo >totalPasosLaborables) {
            diaMayor = "Domingo";
        }

        if ( totalPasosLaborables == totalPasosSabado) {
            diaMayor = "Laborables y Sabados";
        }
        else if (totalPasosSabado == totalPasosDomingo) {
            diaMayor = "Sabados y Domingos";
        }
        else if (totalPasosLaborables == totalPasosDomingo) {
            diaMayor = "Domingos y Laborables";
        }
        return diaMayor;
    } 

    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
     *  
     */    
    public void reset() {
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
}
