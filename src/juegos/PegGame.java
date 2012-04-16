/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juegos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodrigo.salado
 */
public class PegGame {

    private char[][] tableroConLosCaracteresIniciales;
    private int numeroDeFilas;
    private int numeroDeColumnas;
    private static char pija = 'x';

    public PegGame(char[][] tableroDeEntrada) {
        this.tableroConLosCaracteresIniciales = tableroDeEntrada;
        this.numeroDeFilas = tableroDeEntrada.length;
        this.numeroDeColumnas = tableroDeEntrada[0].length;
    }

    public float[][] getTableroInicialDeProbabilidades(int columnaDeEntradaDeLaBola) {
        float[][] tableroInicialDeProbabilidades = new float[numeroDeFilas][numeroDeColumnas];
        int columnaRealDeEntradaDeLaBola = getValorRealDeLaColumna(columnaDeEntradaDeLaBola);
        float probabilidadInicial = 1; //Una probabilidad de 100%
        for (int posicionDeLaFila = 0; posicionDeLaFila < numeroDeFilas; posicionDeLaFila++) {
            for (int posicionDeLaColumna = 0; posicionDeLaColumna < numeroDeColumnas; posicionDeLaColumna++) {
                inicializaValoresDelTableroDeProbabilidades(tableroInicialDeProbabilidades, posicionDeLaFila, posicionDeLaColumna);
            }
        }
        setProbabilidadInicialEnLaColumnaDeEntrada(tableroInicialDeProbabilidades, columnaRealDeEntradaDeLaBola, probabilidadInicial);
        return tableroInicialDeProbabilidades;
    }

    public float[][] getTableroDeProbabilidades(float[][] tableroInicialDeProbabilidades) {
        float[][] tableroDeProbabilidades = copiaTableroDeProbabilidades(tableroInicialDeProbabilidades);

        for (int posicionDeLaFila = 0; posicionDeLaFila < numeroDeFilas; posicionDeLaFila++) {
            for (int posicionDeLaColumna = 0; esValidaLaFilaParaElTableroDeProbabilidades(posicionDeLaColumna, posicionDeLaFila); posicionDeLaColumna++) {

                float probabilidadEnCurso = getValorDelTableroDeProbabilidades(tableroDeProbabilidades, posicionDeLaFila, posicionDeLaColumna);
                if (esPijaOProbabilidadCero(probabilidadEnCurso)) {
                    continue;
                }
                Float valorInferiorIzquierdo = getValorInferiorIzquierdo(posicionDeLaColumna, tableroDeProbabilidades, posicionDeLaFila);
                Float valorInferiorInmediato = getValorInferiorInmediato(tableroDeProbabilidades, posicionDeLaFila, posicionDeLaColumna);
                Float valorInferiorDerecho = getValorInferiorDerecho(posicionDeLaColumna, tableroDeProbabilidades, posicionDeLaFila);

                if (valorInferiorInmediato != pija) {
                    float valor = probabilidadEnCurso + valorInferiorInmediato;
                    setValorAlTableroDeProbabilidades(tableroDeProbabilidades, posicionDeLaFila + 1, posicionDeLaColumna, valor);
                    continue;
                }
                if (esValidoElValorInferiorIzquierdo(valorInferiorIzquierdo, posicionDeLaColumna, valorInferiorDerecho)) {
                    float valor = (probabilidadEnCurso / 2) + valorInferiorIzquierdo;
                    setValorAlTableroDeProbabilidades(tableroDeProbabilidades, posicionDeLaFila + 1, posicionDeLaColumna - 1, valor);
                } else if (valorInferiorDerecho != null) {
                    float valor = probabilidadEnCurso + valorInferiorDerecho;
                    setValorAlTableroDeProbabilidades(tableroDeProbabilidades, posicionDeLaFila + 1, posicionDeLaColumna + 1, valor);
                    continue;
                }
                if (esValidoElValorInferiorDerecho(valorInferiorDerecho, posicionDeLaColumna, valorInferiorIzquierdo)) {
                    float valor = (probabilidadEnCurso / 2) + valorInferiorDerecho;
                    setValorAlTableroDeProbabilidades(tableroDeProbabilidades, posicionDeLaFila + 1, posicionDeLaColumna + 1, valor);
                } else if (valorInferiorIzquierdo != null) {
                    float valor = probabilidadEnCurso + valorInferiorIzquierdo;
                    setValorAlTableroDeProbabilidades(tableroDeProbabilidades, posicionDeLaFila + 1, posicionDeLaColumna - 1, valor);
                    continue;
                }
            }
        }
        pintaTableroDeProbabilidades(tableroDeProbabilidades);
        return tableroDeProbabilidades;
    }

    public String getMensajeDeColumnaConMasPosibilidades(int numeroDeColumnaInicial) {
        List<Resultados> listaDeResultados = new ArrayList<Resultados>();
        int valorRealDeLaColumnaDeSalida = getValorRealDeLaColumna(numeroDeColumnaInicial);
        for (int numeroDeColumna = 1; numeroDeColumna < numeroDeColumnas / 2; numeroDeColumna++) {
            float[][] tableroInicialDeProbabilidades = getTableroInicialDeProbabilidades(numeroDeColumna);
            float[][] tableroDeProbabilidades = getTableroDeProbabilidades(tableroInicialDeProbabilidades);
            float[] ultimaFilaDelTableroDeProbabilidades = tableroDeProbabilidades[numeroDeFilas - 1];
            float probabilidad = ultimaFilaDelTableroDeProbabilidades[valorRealDeLaColumnaDeSalida];
            if (probabilidad == 0) {
                continue;
            }
            listaDeResultados.add(new Resultados(numeroDeColumna, probabilidad));
        }
        String salidaDeResultados = "";
        float probabilidad = -1;
        for (Resultados resultado : listaDeResultados) {
            probabilidad = resultado.getProbabilidad() > probabilidad ? resultado.getProbabilidad() : probabilidad;
        }
        for (Resultados resultado : listaDeResultados) {
            if (resultado.getProbabilidad() == probabilidad) {
                salidaDeResultados += "La mejor columna es la "
                        + resultado.getColumna()
                        + ", con una probabilidad de: " + probabilidad * 100 + "%.\n";
            }
        }
        return salidaDeResultados;
    }

    private int getValorRealDeLaColumna(int columnaDeEntradaDeLaBola) {
        return (columnaDeEntradaDeLaBola * 2) - 1;
    }

    private boolean esValidoElValorInferiorDerecho(Float valorInferiorDerecho, int posicionDeLaColumna, Float valorInferiorIzquierdo) {
        return valorInferiorDerecho != null && posicionDeLaColumna < numeroDeColumnas - 1 && valorInferiorIzquierdo != null;
    }

    private boolean esValidoElValorInferiorIzquierdo(Float valorInferiorIzquierdo, int posicionDeLaColumna, Float valorInferiorDerecho) {
        return valorInferiorIzquierdo != null && posicionDeLaColumna != 0 && valorInferiorDerecho != null;
    }

    private boolean esPijaOProbabilidadCero(float probabilidadEnCurso) {
        return probabilidadEnCurso == pija || probabilidadEnCurso == 0;
    }

    private boolean esValidaLaFilaParaElTableroDeProbabilidades(int posicionDeLaColumna, int posicionDeLaFila) {
        return posicionDeLaColumna < numeroDeColumnas && posicionDeLaFila < numeroDeFilas - 1;
    }

    private float getValorDelTableroDeProbabilidades(float[][] tableroDeProbabilidades, int posicionDeLaFila, int posicionDeLaColumna) {
        return tableroDeProbabilidades[posicionDeLaFila][posicionDeLaColumna];
    }

    private Float getValorInferiorDerecho(int posicionDeLaColumna, float[][] tableroDeProbabilidades, int posicionDeLaFila) {
        return posicionDeLaColumna < numeroDeColumnas - 1 ? tableroDeProbabilidades[posicionDeLaFila + 1][posicionDeLaColumna + 1] : null;
    }

    private float getValorInferiorInmediato(float[][] tableroDeProbabilidades, int posicionDeLaFila, int posicionDeLaColumna) {
        return tableroDeProbabilidades[posicionDeLaFila + 1][posicionDeLaColumna];
    }

    private Float getValorInferiorIzquierdo(int posicionDeLaColumna, float[][] tableroDeProbabilidades, int posicionDeLaFila) {
        return posicionDeLaColumna != 0 ? tableroDeProbabilidades[posicionDeLaFila + 1][posicionDeLaColumna - 1] : null;
    }

    private void setProbabilidadInicialEnLaColumnaDeEntrada(float[][] tableroInicialDeProbabilidades, int columnaRealDeEntradaDeLaBola, float probabilidadInicial) {
        tableroInicialDeProbabilidades[0][columnaRealDeEntradaDeLaBola] = probabilidadInicial;
    }

    private void inicializaValoresDelTableroDeProbabilidades(float[][] tableroInicialDeProbabilidades, int posicionDeLaFila, int posicionDeLaColumna) {
        tableroInicialDeProbabilidades[posicionDeLaFila][posicionDeLaColumna] = tableroConLosCaracteresIniciales[posicionDeLaFila][posicionDeLaColumna] == pija ? pija : 0;
    }

    private void setValorAlTableroDeProbabilidades(float[][] tableroDeProbabilidades, int posicionDeLaFila, int posicionDeLaColumna, float valor) {
        tableroDeProbabilidades[posicionDeLaFila][posicionDeLaColumna] = valor;
    }

    private void pintaTableroDeProbabilidades(float[][] tablero) {
        for (int fila = 0; fila < numeroDeFilas; fila++) {
            for (int columna = 0; columna < numeroDeColumnas; columna++) {
                float valor = tablero[fila][columna];
                System.out.print("[" + (valor == pija ? " x " : valor) + "]");
            }
            System.out.println();
        }
        System.out.println("________");
    }

    private float[][] copiaTableroDeProbabilidades(float[][] tableroDeEntrada) {
        float[][] tableroDeSalida = new float[numeroDeFilas][numeroDeColumnas];
        for (int posicionDeLaFila = 0; posicionDeLaFila < numeroDeFilas; posicionDeLaFila++) {
            System.arraycopy(tableroDeEntrada[posicionDeLaFila], 0, tableroDeSalida[posicionDeLaFila], 0, numeroDeColumnas);
        }
        return tableroDeSalida;
    }
}

class Resultados {

    private int columna;
    private float probabilidad;

    public Resultados(int columna, float probabilidad) {
        this.columna = columna;
        this.probabilidad = probabilidad;
    }

    public int getColumna() {
        return columna;
    }

    public float getProbabilidad() {
        return probabilidad;
    }
}