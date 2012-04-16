/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package juegos;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rodrigo.salado
 */
public class PegGameTest {

    private static char x = 'x'; //La unica razon de esto es estetica.

    @Test
    public void testGetTableroConPosicionInicial_1() {
        char[][] tableroDeEntrada = new char[][]{
            {'x', ' ', 'x'},
            {' ', 'x', ' '},
            {'x', ' ', 'x'}
        };
        float[][] tableroConLaPosibilidadInicial = new float[][]{
            {x, 1, x},
            {0, x, 0},
            {x, 0, x}
        };
        PegGame juego = new PegGame(tableroDeEntrada);
        int posicionInicial = 1;
        assertArrayEquals(juego.getTableroInicialDeProbabilidades(posicionInicial), tableroConLaPosibilidadInicial);
    }

    @Test
    public void testGetTableroConPosicionInicial_2() {
        char[][] tableroDeEntrada = new char[][]{
            {'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', 'x', ' '},
            {'x', ' ', 'x', ' ', 'x'}
        };
        float[][] tableroConLaPosibilidadInicial = new float[][]{
            {x, 0, x, 1, x},
            {0, x, 0, x, 0},
            {x, 0, x, 0, x}
        };
        PegGame juego = new PegGame(tableroDeEntrada);
        int posicionInicial = 2;
        assertArrayEquals(juego.getTableroInicialDeProbabilidades(posicionInicial), tableroConLaPosibilidadInicial);
    }

    @Test
    public void testGetTableroDePosibilidades_3() {
        char[][] tableroDeEntrada = new char[][]{
            {'x', ' ', 'x'},
            {' ', 'x', ' '},
            {'x', ' ', 'x'}
        };
        float[][] tableroDePosibilidadesEsperado = new float[][]{
            {x, 1, x},
            {0.5f, x, 0.5f},
            {x, 1, x}
        };
        PegGame juego = new PegGame(tableroDeEntrada);
        int posicionInicial = 1;
        float[][] tableroConPosicionInicial = juego.getTableroInicialDeProbabilidades(posicionInicial);
        assertArrayEquals(juego.getTableroDeProbabilidades(tableroConPosicionInicial), tableroDePosibilidadesEsperado);
    }

    @Test
    public void testGetTableroDePosibilidades_4() {
        char[][] tableroDeEntrada = new char[][]{
            {'x', ' ', 'x'},
            {' ', ' ', ' '},
            {'x', ' ', 'x'}
        };
        float[][] tableroDePosibilidadesEsperado = new float[][]{
            {x, 1, x},
            {0, 1, 0},
            {x, 1, x}
        };
        PegGame juego = new PegGame(tableroDeEntrada);
        int posicionInicial = 1;
        float[][] tableroConPosicionInicial = juego.getTableroInicialDeProbabilidades(posicionInicial);
        assertArrayEquals(juego.getTableroDeProbabilidades(tableroConPosicionInicial), tableroDePosibilidadesEsperado);
    }

    @Test
    public void testGetTableroDePosibilidades_5() {
        char[][] tableroDeEntrada = new char[][]{
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', ' ', ' ', 'x', ' ', 'x', ' '},
            {'x', ' ', ' ', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', 'x', ' ', ' ', ' ', 'x', ' '},
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'}
        };
        float[][] tableroDePosibilidadesEsperado = new float[][]{
            {x, 1, x, 0, x, 0, x, 0, x},
            {0.5f, x, 0.5f, 0, 0, x, 0, x, 0},
            {x, 0.5f, 0.5f, 0, x, 0, x, 0, x},
            {0.25f, x, 0.75f, x, 0, 0, 0, x, 0},
            {x, 0.625f, x, 0.375f, x, 0, x, 0, x}
        };
        PegGame juego = new PegGame(tableroDeEntrada);
        int posicionInicial = 1;
        float[][] tableroConPosicionInicial = juego.getTableroInicialDeProbabilidades(posicionInicial);
        assertArrayEquals(juego.getTableroDeProbabilidades(tableroConPosicionInicial), tableroDePosibilidadesEsperado);
    }

    @Test
    public void testGetTableroDePosibilidades_6() {
        char[][] tableroDeEntrada = new char[][]{
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', ' ', ' ', 'x', ' ', 'x', ' '},
            {'x', ' ', ' ', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', 'x', ' ', ' ', ' ', 'x', ' '},
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'}
        };
        float[][] tableroDePosibilidadesEsperado = new float[][]{
            {x, 0.0f, x, 1.0f, x, 0.0f, x, 0.0f, x,},
            {0.0f, x, 0.0f, 1.0f, 0.0f, x, 0.0f, x, 0.0f,},
            {x, 0.0f, 0.0f, 1.0f, x, 0.0f, x, 0.0f, x,},
            {0.0f, x, 0.5f, x, 0.5f, 0.0f, 0.0f, x, 0.0f,},
            {x, 0.25f, x, 0.5f, x, 0.25f, x, 0.0f, x}
        };
        PegGame juego = new PegGame(tableroDeEntrada);
        int posicionInicial = 2;
        float[][] tableroConPosicionInicial = juego.getTableroInicialDeProbabilidades(posicionInicial);
        assertArrayEquals(juego.getTableroDeProbabilidades(tableroConPosicionInicial), tableroDePosibilidadesEsperado);
    }

    @Test
    public void testGetTableroDePosibilidades_7() {
        char[][] tableroDeEntrada = new char[][]{
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', ' ', ' ', 'x', ' ', 'x', ' '},
            {'x', ' ', ' ', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', 'x', ' ', ' ', ' ', 'x', ' '},
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'}
        };
        float[][] tableroDePosibilidadesEsperado = new float[][]{
            {x, 0.0f, x, 0.0f, x, 1.0f, x, 0.0f, x,},
            {0.0f, x, 0.0f, 0.0f, 0.5f, x, 0.5f, x, 0.0f,},
            {x, 0.0f, 0.0f, 0.25f, x, 0.5f, x, 0.25f, x,},
            {0.0f, x, 0.125f, x, 0.125f, 0.5f, 0.125f, x, 0.125f,},
            {x, 0.0625f, x, 0.125f, x, 0.625f, x, 0.1875f, x}
        };
        PegGame juego = new PegGame(tableroDeEntrada);
        int posicionInicial = 3;
        float[][] tableroConPosicionInicial = juego.getTableroInicialDeProbabilidades(posicionInicial);
        assertArrayEquals(juego.getTableroDeProbabilidades(tableroConPosicionInicial), tableroDePosibilidadesEsperado);
    }

    @Test
    public void testGetTableroDePosibilidades_8() {
        char[][] tableroDeEntrada = new char[][]{
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', ' ', ' ', 'x', ' ', 'x', ' '},
            {'x', ' ', ' ', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', 'x', ' ', ' ', ' ', 'x', ' '},
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'}
        };
        float[][] tableroDePosibilidadesEsperado = new float[][]{
            {   x, 0.0f,    x, 0.0f,    x,    0.0f,      x, 1.0f,       x,},
            {0.0f,    x, 0.0f, 0.0f, 0.0f,       x,   0.5f,    x,    0.5f,},
            {   x, 0.0f, 0.0f, 0.0f,    x,   0.25f,      x, 0.75f,      x,},
            {0.0f,    x, 0.0f,    x, 0.0f,   0.25f, 0.375f,     x, 0.375f,},
            {   x, 0.0f,    x, 0.0f,    x, 0.4375f,      x, 0.5625f, x}
        };
        PegGame juego = new PegGame(tableroDeEntrada);
        int posicionInicial = 4;
        float[][] tableroConPosicionInicial = juego.getTableroInicialDeProbabilidades(posicionInicial);
        assertArrayEquals(juego.getTableroDeProbabilidades(tableroConPosicionInicial), tableroDePosibilidadesEsperado);
    }
    
    @Test
    public void testGetMensajeDeColumnaConMasPosibilidades_9() {
        char[][] tableroDeEntrada = new char[][]{
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', ' ', ' ', 'x', ' ', 'x', ' '},
            {'x', ' ', ' ', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', 'x', ' ', ' ', ' ', 'x', ' '},
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'}
        };
         PegGame juego = new PegGame(tableroDeEntrada);
        int columnaDeSalida = 1;
        assertEquals(juego.getMensajeDeColumnaConMasPosibilidades(columnaDeSalida), "La mejor columna es la 1, con una probabilidad de: 62.5%.\n");
    }
    
    @Test
    public void testGetMensajeDeColumnaConMasPosibilidades_10() {
        char[][] tableroDeEntrada = new char[][]{
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', ' ', ' ', 'x', ' ', 'x', ' '},
            {'x', ' ', ' ', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', 'x', ' ', ' ', ' ', 'x', ' '},
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'}
        };
         PegGame juego = new PegGame(tableroDeEntrada);
        int columnaDeSalida = 2;
        assertEquals(juego.getMensajeDeColumnaConMasPosibilidades(columnaDeSalida), "La mejor columna es la 2, con una probabilidad de: 50.0%.\n");
    }
    
    @Test
    public void testGetMensajeDeColumnaConMasPosibilidades_11() {
        char[][] tableroDeEntrada = new char[][]{
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', ' ', ' ', 'x', ' ', 'x', ' '},
            {'x', ' ', ' ', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', 'x', ' ', ' ', ' ', 'x', ' '},
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'}
        };
         PegGame juego = new PegGame(tableroDeEntrada);
        int columnaDeSalida = 3;
        assertEquals(juego.getMensajeDeColumnaConMasPosibilidades(columnaDeSalida), "La mejor columna es la 3, con una probabilidad de: 62.5%.\n");
    }
    
    @Test
    public void testGetMensajeDeColumnaConMasPosibilidades_12() {
        char[][] tableroDeEntrada = new char[][]{
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', ' ', ' ', 'x', ' ', 'x', ' '},
            {'x', ' ', ' ', ' ', 'x', ' ', 'x', ' ', 'x'},
            {' ', 'x', ' ', 'x', ' ', ' ', ' ', 'x', ' '},
            {'x', ' ', 'x', ' ', 'x', ' ', 'x', ' ', 'x'}
        };
         PegGame juego = new PegGame(tableroDeEntrada);
        int columnaDeSalida = 4;
        assertEquals(juego.getMensajeDeColumnaConMasPosibilidades(columnaDeSalida), "La mejor columna es la 3, con una probabilidad de: 18.75%.\n");
    }
}
