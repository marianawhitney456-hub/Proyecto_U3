import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Obstaculo {
    private Rectangle rect;

    public Obstaculo(int x, int y) {
        this.rect = new Rectangle(x, y, 50, 15);
    }

    public void mover() {
        rect.x -= 8;
    }

    public void dibujar(Graphics g) {
        g.setColor(new Color(34, 139, 34));
        g.fillOval(rect.x, rect.y, rect.width, rect.height);
    }

    public Rectangle getBounds() {
        return rect;
    }

    public boolean fueraDePantalla() {
        return rect.x + rect.width < 0;
    }
}