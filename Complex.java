/**
 * This class provides some operations on complex numbers, used in our main program.
 */
public class Complex {

    public double x = Double.NaN;
    public double y = Double.NaN;
    
    public Complex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Complex polar(double r, double theta) {
        return new Complex(r*Math.cos(theta), r*Math.sin(theta));
    }

    public Complex add(Complex c) {
        return new Complex(x + c.x, y + c.y);
    }

    public Complex multiply(double a) {
        return new Complex(x*a, y*a);
    }
    
    public double abs() {
        return Math.sqrt(x*x + y*y);
    }

    public double norm() {
        return x*x + y*y;
    }

    public Complex log() {
        return new Complex(Math.log(norm())/2.0, Math.atan2(y, x));
    }

    public Complex exp() {
        return polar(Math.exp(x), y);
    }

    public Complex pow(double a) {
        return log().multiply(a).exp();
    }
}
