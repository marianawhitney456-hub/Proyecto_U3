import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Rana {

    private int x = 100, y = 270;

    private int velocidadY = 0;

    private final int GRAVEDAD = 1;

    private final int SUELO_Y = 270;

    // Número de saltos
    private int saltos = 0;

    // Imagen rana
    private Image spriteRana;

    public Rana() {

        try {

            spriteRana = new ImageIcon(
                    getClass().getResource("/proyecto_u3/Sprites/rana.png")
            ).getImage();

        } catch (Exception e) {

            System.out.println("No se pudo cargar rana.png");

            e.printStackTrace();

            spriteRana = null;
        }
    }

    // Salto y doble salto
    public void saltar() {

        // Máximo 2 saltos
        if (saltos < 2) {

            velocidadY = -16;

            saltos++;
        }
    }

    public void actualizar() {

        y += velocidadY;

        if (y < SUELO_Y) {

            velocidadY += GRAVEDAD;

        } else {

            y = SUELO_Y;
            velocidadY = 0;

            saltos = 0;
        }
    }

    public void dibujar(Graphics g) {

        if (spriteRana != null) {

            g.drawImage(spriteRana,x,y,50,50,null);

        } else {

            g.fillRect(x, y, 50, 50);
        }
    }

    public Rectangle getBounds() {

        return new Rectangle(x, y, 50, 50);
    }
    public int getY() {

    return y;
}

    public void reset() {

        y = SUELO_Y;

        velocidadY = 0;

        saltos = 0;
    }
}
