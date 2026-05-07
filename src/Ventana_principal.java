import javax.swing.*;

public class Ventana_principal extends JFrame {



        public Ventana_principal() {
            setTitle("Dino Game");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);

            Ventana_principal panel = new Ventana_principal();
            add(panel);

            pack();
            setLocationRelativeTo(null);
            setVisible(true);

        }

}
