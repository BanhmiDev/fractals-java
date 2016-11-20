import java.awt.Color;

public class Gradient {

    public static Color[] generate(Color startColor, Color endColor, int n) {
        Color[] gradient = new Color[n];
        int stepWidth = gradient.length;
        gradient[0] = startColor;
        double currentRed = startColor.getRed();
        double currentGreen = startColor.getGreen();
        double currentBlue = startColor.getBlue();

        double stepRed = ((double)(endColor.getRed() - startColor.getRed())) / stepWidth;
        double stepGreen = ((double)(endColor.getGreen() - startColor.getGreen())) / stepWidth;
        double stepBlue = ((double)(endColor.getBlue() - startColor.getBlue())) / stepWidth;
        
        for (int j = 1; j < stepWidth; j++) {
            currentRed += stepRed;
            currentGreen += stepGreen;
            currentBlue += stepBlue;
            gradient[j] = new Color((int)currentRed, (int)currentGreen, (int)currentBlue);
        }
        gradient[gradient.length - 1] = endColor;
        return gradient;
    }
}
