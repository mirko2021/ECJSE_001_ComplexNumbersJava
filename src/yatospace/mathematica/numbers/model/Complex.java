package yatospace.mathematica.numbers.model;

import java.io.Serializable;

import yatospace.mathematica.numbers.util.Quadrant;

/**
 * Complex nubmer representation. 
 * @author VM
 * @version 1.0
 */
public class Complex implements Serializable, Cloneable{
	private static final long serialVersionUID = 9011940168215785034L;
	private double real = 0.0d; 
	private double imag = 0.0d;
	
	public Complex() {}
	
	public Complex(double real) {
		this.real = real; 
	}
	
	public Complex(double real, double imag) {
		this.real = real;
		this.imag = imag;
	}
	
	public double getReal() {
		return real;
	}
	
	public void setReal(double real) {
		this.real = real;
	}
	
	public double getImag() {
		return imag;
	}
	
	public void setImag(double imag) {
		this.imag = imag;
	}
	
	public void plus(Complex other) {
		if(other==null) other = new Complex();
		real += other.real; 
		imag += other.imag; 
	}
	
	public void minus(Complex other) {
		if(other==null) other = new Complex();
		real -= other.real; 
		imag -= other.imag; 
	}
	
	public void multipy(Complex other) {
		if(other==null) other = new Complex();
		double realRes = real * other.real - imag * other.imag;
		double imagRes = imag = real * other.imag + imag * other.real; 
		real = realRes; 
		imag = imagRes; 
	}
	
	public void divide(Complex other) {
		if(other==null) other = new Complex();
		double resReal = real * other.real + imag * other.imag;
		double resImag = imag * other.real - real * other.imag;
		resReal /= other.real * other.real + other.imag * other.imag; 
		resImag /= other.real * other.real + other.imag * other.imag;
		real = resReal;
		imag = resImag; 
	}
	
	public double module() {
		return Math.sqrt(real*real+imag*imag); 
	}
	
	public double argument() {
		if(real==0.0d) throw new NullPointerException("Argument of the Real zero."); 
		double effImag = Math.abs(imag); 
		double effReal = Math.abs(real);
		return Math.atan(effImag/effReal)*(180d/Math.PI);
	}
	
	public Quadrant quadrant() {
		if(real>0 && imag>0) return Quadrant.I; 
		if(real<0 && imag>0) return Quadrant.II; 
		if(real<0 && imag<0) return Quadrant.III; 
		if(real>0 && imag<0) return Quadrant.IV; 
		return Quadrant.O;
	}
	
	public double phase() {
		if(real==0 && imag==0) throw new NullPointerException("Phase for Zero.");
		if (imag == 0 && real > 0) return 0; 
		if (imag == 0 && real < 0) return 180;
		if (imag > 0 && real == 0) return 90;
		if (imag < 0 && real == 0) return 270;
		if (quadrant() == Quadrant.I)  return argument(); 
		if (quadrant() == Quadrant.II) return 180 - argument();
		if (quadrant() == Quadrant.III) return 180 + argument(); 
		if (quadrant() == Quadrant.IV)  return 360 - argument();
		throw new RuntimeException();
	}
	
	@Override 
	public Complex clone() {
		Complex result = new Complex();
		result.real = real; 
		result.imag = imag; 
		return result; 
	}
	
	@Override 
	public boolean equals(Object object) {
		if(object instanceof Complex) {
			Complex complex = (Complex) object; 
			if(real!=complex.real) return false; 
			if(imag!=complex.imag) return false; 
			return true; 
		}
		return false; 
	}
	
	@Override 
	public String toString() {
		if (real == 0.0 && imag == 0.0) return "0";
		else if (imag == 0.0) return ""+real;
		else if (real == 0.0) {
			if (imag < 0.0) return "-i" + (-imag);
			else return "i" + imag;
		}
		else if (imag < 0) return "" +real + "-i" + (-imag);
		else  return "" + real + "+i" + imag;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode(); 
	}
}
