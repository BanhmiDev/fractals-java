import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class FractalsGUI extends JFrame implements MouseListener, MouseWheelListener, MouseMotionListener {

    // TODO: Finish GUI
    private JPanel canvas;
    private JScrollPane canvasPane;
    private int mouseStartX, mouseStartY;
    private double canvasScale = 1;

    private Color[] gradient;
    private FractalUnit fractalUnit;

    private FractalsGUI(String[] args) {
        super("Fractals");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        int x = 600;
        int y = 600;
        gradient = Gradient.generate(Color.WHITE, Color.BLACK, 500);
        fractalUnit = new FractalUnit(gradient, x, y, -1.5, 1, -1.5, 1);

        // Real calculation
        Color[][] array = null;
        try {
            array = fractalUnit.generateMandelbrotSet();
        } catch (Exception e) {
            // ...
        }
        canvas = Canvas.draw(array);
        canvas.setPreferredSize(new Dimension(600, 600));
        canvas.addMouseListener(this);
        canvas.addMouseWheelListener(this);
        canvas.addMouseMotionListener(this);

        JPanel listPane = new JPanel();
        canvasPane = new JScrollPane(canvas);
        JPanel pane = new JPanel(new GridBagLayout());

        JButton button;
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1.0;
        c.weightx = 1.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        pane.add(canvasPane, c);

        c.gridwidth = 1;
        button = new JButton("Mandelbrot");
        c.gridx = 0;
        c.gridy = 1;
        pane.add(button, c);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvasScale = 1;
                Dimension newSize = new Dimension((int)(600 * canvasScale), (int)(600 * canvasScale));
                canvas.setSize(newSize);
                canvas.setPreferredSize(newSize);
            }
        });

        button = new JButton("Apply");
        c.gridx = 2;
        c.gridy = 1;
        pane.add(button, c);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCanvas();
            }
        });

        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        JLabel jLabel = new JLabel("...", SwingConstants.CENTER);
        jLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        pane.add(jLabel, c);

        getContentPane().add(pane);

        pack();
        setVisible(true);
    }

    public void updateCanvas() {
        canvas.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseStartX = e.getX();
        mouseStartY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        canvasScale -= e.getPreciseWheelRotation() / 5;
        if (canvasScale < 1) {
            canvasScale = 1;
        }
        Dimension newSize = new Dimension((int)(600 * canvasScale), (int)(600 * canvasScale));
        canvas.setSize(newSize);
        canvas.setPreferredSize(newSize);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Rectangle view = canvasPane.getViewport().getViewRect();
        view.x += mouseStartX - e.getX();
        view.y += mouseStartY - e.getY();
        canvas.scrollRectToVisible(view);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    public static void main(String[] args) {
        new FractalsGUI(args);
    }
}
