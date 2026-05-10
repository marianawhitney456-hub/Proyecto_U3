
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class PanelJuego extends JPanel implements ActionListener, KeyListener {
    private Rana rana;
    private ArrayList<Obstaculo> obstaculos;
    private Timer timer;
    private Random random;
    private int puntuacion = 0;
    private boolean juegoTerminado = false;

    public PanelJuego() {
        setPreferredSize(new Dimension(800, 400));
        setBackground(new Color(135, 206, 235));
        setFocusable(true);
        addKeyListener(this);

        rana = new Rana();
        obstaculos = new ArrayList<>();
        random = new Random();
        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar Agua
        g.setColor(new Color(30, 144, 255));
        g.fillRect(0, 330, 800, 70);

        rana.dibujar(g);

        for (Obstaculo o : obstaculos) {
            o.dibujar(g);
        }

        // UI
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Puntos: " + puntuacion, 20, 30);

        if (juegoTerminado) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("GAME OVER - Pulsa R", 250, 200);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!juegoTerminado) {
            rana.actualizar();

            // Generar obstáculos
            if (random.nextInt(100) < 3) {
                if (obstaculos.isEmpty() || obstaculos.get(obstaculos.size() - 1).getBounds().x < 600) {
                    obstaculos.add(new Obstaculo(800, 320));
                }
            }

            // Manejar obstáculos
            for (int i = 0; i < obstaculos.size(); i++) {
                Obstaculo o = obstaculos.get(i);
                o.mover();

                if (o.getBounds().intersects(rana.getBounds())) {
                    juegoTerminado = true;
                    timer.stop();
                }

                if (o.fueraDePantalla()) {
                    obstaculos.remove(i);
                    puntuacion++;
                }
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) rana.saltar();
        if (e.getKeyCode() == KeyEvent.VK_R && juegoTerminado) reiniciar();
    }

    private void reiniciar() {
        rana.reset();
        obstaculos.clear();
        puntuacion = 0;
        juegoTerminado = false;
        timer.start();
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}