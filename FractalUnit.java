import java.awt.Color;

public class FractalUnit {

    public static double MAX_LENGTH = 5.0;
    public static final int THREAD_AMOUNT = 10;

    private Color[] gradient;
    private double x, y, xStart, yStart, stepY, stepX;
    private Color[][] resultArray;
    
    public FractalUnit(Color[] gradient, int x, int y, double xStart, double xEnd, double yStart, double yEnd) {
        this.gradient = gradient;
        this.x = x;
        this.y = y;
        this.xStart = xStart;
        this.yStart = yStart;
        this.stepX = Math.abs(xStart-xEnd)/x;
        this.stepY = Math.abs(yStart-yEnd)/y;
        this.resultArray = new Color[y][x];
    }

    public int compute(Complex start, Complex step, int maxIterations) {
        Complex current = start;
        Complex next = null;

        int iterations = 0;
        double currentLength = current.abs();
       
        while (currentLength < MAX_LENGTH && iterations < maxIterations) {
            next = current.pow(2).add(step);
            current = next;
            currentLength = current.abs(); 
            iterations++;
        }
        return iterations;
    }

    public Color[][] generateMandelbrotSet() throws Exception {
        Thread[] threads = new Thread[THREAD_AMOUNT];
        int work = (int)x/THREAD_AMOUNT;
        int start = 0; 
        
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MandelThread(start, start + work);
            threads[i].start();
            start += work;
        }
        threads[threads.length-1] = new MandelThread(start, (int)x);
        threads[threads.length-1].start();

        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
        }

        return resultArray;
    }

    class MandelThread extends Thread {
        private int start;
        private int end;

        public MandelThread(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public void run() {
            for (int i = 0; i < y; i++) {
                for (int j = start; j < end; j++) {
                    int iterations = compute(new Complex(0, 0), new Complex(xStart + j * stepX, yStart + i * stepY), gradient.length-1);
                    resultArray[i][j] = gradient[iterations];
                }
            }
        }
    }
}

