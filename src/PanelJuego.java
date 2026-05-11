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

    // Imagen de fondo
    private Image spriteAgua;

    public PanelJuego() {

        this.setPreferredSize(new Dimension(800, 400));

        this.setFocusable(true);
        this.addKeyListener(this);

        // Cargar agua.png
        try {

            spriteAgua = new ImageIcon(
                    getClass().getResource("/proyecto_u3/Sprites/agua.png")
            ).getImage();

        } catch (Exception e) {

            System.out.println("No se pudo cargar agua.png");
            e.printStackTrace();
        }

        rana = new Rana();
        
        obstaculos = new ArrayList<>();


        obstaculos.add(new Obstaculo(80, 300));

        random = new Random();

        timer = new Timer(20, this);

        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        // Dibujar fondo
        if (spriteAgua != null) {

            g.drawImage(spriteAgua,
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    null);

        } else {

            // Fondo de respaldo
            g.setColor(new Color(135, 206, 235));

            g.fillRect(0, 0, getWidth(), getHeight());
        }

        // Dibujar rana
        rana.dibujar(g);

        // Dibujar obstáculos
        for (Obstaculo o : obstaculos) {

            o.dibujar(g);
        }

        // Puntos
        g.setColor(Color.BLACK);

        g.setFont(new Font("Arial", Font.BOLD, 18));

        g.drawString("Puntos: " + puntuacion, 20, 30);

        // Game Over
        if (juegoTerminado) {

            g.setColor(Color.RED);

            g.setFont(new Font("Arial", Font.BOLD, 30));

            g.drawString("GAME OVER - Pulsa R", 220, 200);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (!juegoTerminado) {

            rana.actualizar();

            // Crear obstáculos
            if (random.nextInt(100) < 2) {

                if (obstaculos.isEmpty()
                        || obstaculos.get(obstaculos.size() - 1)
                                .getBounds().x < 600) {

                    int distancia = 70 + random.nextInt(40);

                    obstaculos.add(new Obstaculo(800 + distancia, 300));
                }
            }

            // Mover obstáculos
            boolean sobreHoja = false;

        for (int i = 0; i < obstaculos.size(); i++) {

            Obstaculo o = obstaculos.get(i);

            o.mover();

            // Si la rana toca una hoja
            if (o.getBounds().intersects(rana.getBounds())) {

             sobreHoja = true;
            }

             // Eliminar hojas fuera de pantalla
             if (o.fueraDePantalla()) {

            obstaculos.remove(i);

             puntuacion++;
         }
    }

      // Si la rana cae al agua
        if (rana.getY() >= 320 && !sobreHoja) {

            juegoTerminado = true;

             timer.stop();
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

        // Saltar
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {

            rana.saltar();
        }

        // Reiniciar
        if (e.getKeyCode() == KeyEvent.VK_R
                && juegoTerminado) {

            reiniciar();
        }
    }

    private void reiniciar() {

        rana.reset();

        obstaculos.clear();

        puntuacion = 0;

        juegoTerminado = false;

        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
