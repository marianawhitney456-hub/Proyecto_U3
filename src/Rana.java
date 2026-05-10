import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Rana {
    private int x = 100, y = 300;
    private int velocidadY = 0;
    private final int GRAVEDAD = 1;
    private final int SUELO_Y = 320;
    private boolean enSuelo = true;

    public void saltar() {
        if (enSuelo) {
            velocidadY = -16;
            enSuelo = false;
        }
    }

    public void actualizar() {
        y += velocidadY;
        if (y < SUELO_Y) {
            velocidadY += GRAVEDAD;
            enSuelo = false;
        } else {
            y = SUELO_Y;
            velocidadY = 0;
            enSuelo = true;
        }
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, 30, 30);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 30, 30);
    }

    public void reset() {
        y = SUELO_Y;
        velocidadY = 0;
    }
}