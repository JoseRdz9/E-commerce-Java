import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RelojContadorGUI extends JFrame {
    private JLabel relojLabel;
    private JLabel contadorLabel;
    private JTextField incrementoTextField;
    private JButton aplicarIncrementoButton;
    private Timer relojTimer;
    private Timer contadorTimer;
    private int contador;
    private int incremento;

    public RelojContadorGUI() {
        // Configuración de la ventana
        setTitle("Reloj y Contador");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));  // 4 filas: reloj, contador, campo de texto y botón

        // Inicialización del contador e incremento
        contador = 0;
        incremento = 1;  // Inicia incrementando de 1 en 1

        // Componente para mostrar el reloj
        relojLabel = new JLabel("Hora: ", JLabel.CENTER);
        relojLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(relojLabel);

        // Componente para mostrar el contador
        contadorLabel = new JLabel("Contador: " + contador, JLabel.CENTER);
        contadorLabel.setFont(new Font("Serif", Font.BOLD, 20));
        add(contadorLabel);

        // Campo de texto para capturar el nuevo valor de incremento
        incrementoTextField = new JTextField("1", 5);
        add(incrementoTextField);

        // Botón para aplicar el nuevo incremento
        aplicarIncrementoButton = new JButton("Aplicar Incremento");
        aplicarIncrementoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarNuevoIncremento();
            }
        });
        add(aplicarIncrementoButton);

        // Crear el temporizador para actualizar el reloj cada segundo
        relojTimer = new Timer(1000, e -> actualizarReloj());
        relojTimer.start();  // Iniciar el temporizador del reloj

        // Crear un temporizador para actualizar el contador cada segundo
        contadorTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarContador();
            }
        });
        contadorTimer.start();  // Iniciar el temporizador del contador

        // Mostrar la ventana
        setVisible(true);
    }

    // Método para actualizar el texto del reloj
    private void actualizarReloj() {
        // Obtener la hora actual
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String horaActual = formatoHora.format(new Date());

        // Actualizar la etiqueta del reloj
        relojLabel.setText("Hora: " + horaActual);
    }

    // Método para actualizar el valor del contador
    private void actualizarContador() {
        contador += incremento;
        contadorLabel.setText("Contador: " + contador);
    }

    // Método para aplicar un nuevo incremento
    private void aplicarNuevoIncremento() {
        try {
            incremento = Integer.parseInt(incrementoTextField.getText());
        } catch (NumberFormatException e) {
            // Si el valor ingresado no es un número, se reinicia el incremento a 1
            incremento = 1;
            incrementoTextField.setText("1");
        }
        // Reiniciar el contador a cero
        contador = 0;
        contadorLabel.setText("Contador: " + contador);
    }

    // Método principal
    public static void main(String[] args) {
        SwingUtilities.invokeLater(RelojContadorGUI::new);
    }
}