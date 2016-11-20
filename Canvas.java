import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Canvas {

    // TODO: Repaint method
    public static JPanel draw(final Color[][] array) {
        int x = array[0].length;
        int y = array.length;

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (int i = 0; i < y; i++) {
                    for (int j = 0; j < x; j++) {
                        synchronized(array) {
                            g.setColor(array[i][j]);
                            g.fillRect(j, i, 1, 1);
                        }
                    }
                }
            }
        };
        return panel;
    }
}
