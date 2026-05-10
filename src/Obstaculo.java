import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Obstaculo {
    private Rectangle rect;


    private static Image spriteHoja = null;

    public Obstaculo(int x, int y) {

        this.rect = new Rectangle(x, y, 60, 20);


        if (spriteHoja == null) {
            try {
                spriteHoja = new ImageIcon(getClass().getResource("Sprites/hoja.png")).getImage();
            } catch (Exception e) {
                System.out.println("No se pudo cargar hoja.png, usando respaldo.");
                spriteHoja = null;
            }
        }
    }

    public void mover() {
        rect.x -= 8;
    }

    public void dibujar(Graphics g) {
        if (spriteHoja != null) {
            // Dibujamos la hoja usando el rectángulo como guía
            g.drawImage(spriteHoja, rect.x, rect.y, rect.width, rect.height, null);
        } else {
            // Respaldo original (óvalo verde oscuro)
            g.setColor(new Color(34, 139, 34));
            g.fillOval(rect.x, rect.y, rect.width, rect.height);
        }
    }

    public Rectangle getBounds() {
        return rect;
    }

    public boolean fueraDePantalla() {
        return rect.x + rect.width < 0;
    }
}