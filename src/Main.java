
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Rana Runner POO");
        PanelJuego panel = new PanelJuego();

        ventana.add(panel);
        ventana.pack();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}