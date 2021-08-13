package yatospace.mathematica.numbers.test;

import java.util.Scanner;

import yatospace.mathematica.numbers.model.Complex;

/**
 * Complex number test program. 
 * @author VM
 * @version 1.0
 */
public class ComplexProgram { 	
	
	public static Complex loadComplex(String text) {
		if(text==null) return null; 
		if(text.length()==0) return null; 
		if(text.endsWith("+")) return null; 
		if(text.endsWith("-")) return null; 
		if(text.endsWith("i")) return null;
		int countI = 0; 
		int countPlus = 0; 
		int countMinus = 0;
		int countDots = 0; 
		
		for(int i=0; i<text.length(); i++) {
			if(text.charAt(i)=='+') countPlus++; 
			else if(text.charAt(i)=='-') countMinus++;
			else if(text.charAt(i)=='.') countDots++;
			else if(text.charAt(i)=='i') countI++;
			else if(!Character.isDigit(text.charAt(i)))
				return null; 
		}
		
		if(countI>1) return null; 
		if(countPlus+countMinus>2) return null; 
		if(countDots>2) return null; 
		
		if(text.contains("++")) return null; 
		if(text.contains("--")) return null; 
		if(text.contains("+-")) return null; 
		if(text.contains("-+")) return null; 
		if(text.contains("i+")) return null; 
		if(text.contains("i-")) return null; 
		
		if(countPlus+countMinus==2) {
			if(!text.startsWith("+") && !text.startsWith("-")) return null; 
			if(!text.contains("+i") && !text.contains("-i")) return null;
			if(text.startsWith("+i")) return null; 
			if(text.startsWith("-i")) return null;
			String signReal = text.substring(0,1); 
			String signImag = ""; 
			text = text.substring(1); 
			if(text.contains("+")) signImag = "+";
			if(text.contains("-")) signImag = "-";
			String[] array = text.split("["+signImag+"]");
			if(array.length!=2) return null; 
			if(!array[1].startsWith("i")) return null; 
			array[1] = array[1].substring(1);
			double sr = 1.0; 
			double si = 1.0; 
			if(signReal.contentEquals("+")) sr = 1.0; 
			if(signImag.contentEquals("+")) si = 1.0; 
			if(signReal.contentEquals("-")) sr = -1.0; 
			if(signImag.contentEquals("-")) si = -1.0; 
			return new Complex(sr*Double.parseDouble(array[0]), si*Double.parseDouble(array[1]));
		}else if(countPlus==1) {
			if(text.startsWith("+")) {
				if(text.startsWith("+i")) {
					text = text.substring(2); 
					return new Complex(0.0, Double.parseDouble(text));
				}else {
					text = text.substring(1); 
					return new Complex(Double.parseDouble(text));
				}
			}else {
				if(!text.contains("+i")) return null; 
				String[] array = text.split("[+]"); 
				if(array.length!=2) return null;
				if(!array[1].startsWith("i")) return null; 
				array[1] = array[1].substring(1);
				return new Complex(Double.parseDouble(array[0]), Double.parseDouble(array[1]));
			}
		}else if(countMinus==1) {
			if(text.startsWith("-")) {
				if(text.startsWith("-i")) {
					text = text.substring(2); 
					return new Complex(0.0, -Double.parseDouble(text));
				}else {
					text = text.substring(1); 
					return new Complex(-Double.parseDouble(text));
				}
			}else {
				if(!text.contains("-i")) return null; 
				String[] array = text.split("[-]"); 
				if(array.length!=2) return null;
				if(!array[1].startsWith("i")) return null; 
				array[1] = array[1].substring(1);
				return new Complex(Double.parseDouble(array[0]), -Double.parseDouble(array[1]));
			}
		}else if(countI==1) {
			if(!text.startsWith("i")) return null;
			text = text.substring(1); 
			return new Complex(0.0d, Double.parseDouble(text));
		}else {
			return new Complex(Double.parseDouble(text));
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String ... args) {
		try {
			Scanner scanner = new Scanner(System.in);
			
			System.out.print("Unesi prvi broj : "); 
			String text = scanner.nextLine();
			Complex a = loadComplex(text);
			System.out.println("Uneseni broj je : "+a);
			
			System.out.print("Unesi drugi broj : "); 
			text = scanner.nextLine();
			Complex b = loadComplex(text);
			System.out.println("Uneseni broj je : "+b);
			
			if(a==null) throw new NullPointerException();
			if(b==null) throw new NullPointerException();
			
			Complex c = a.clone(); 
			Complex d = a.clone(); 
			Complex e = a.clone();
			Complex f = a.clone();
			
			boolean z = b.module()==0;
			
			c.plus(b);
			d.minus(b);
			e.multipy(b);
			if(!z) f.divide(b);
			
			System.out.println("Vrijednost zbira : "+c);
			System.out.println("Vrijednost razlike : "+d);
			System.out.println("Vrijednost proizvoda : "+e);
			if(z) System.out.println("Vrijednost kolicnika : nedefinisana");
			if(!z) System.out.println("Vrijednost kolicnika : "+f);
			
			System.out.println("Moduo prvog broja je : "+a.module());
			System.out.println("Moduo drugog broja je : "+b.module());
			System.out.println("Moduo zbira : "+c.module());
			System.out.println("Moduo razlike : "+d.module());
			System.out.println("Moduo proizvoda : "+e.module());
			if(z) System.out.println("Moduo kolicnika : nedefinisana");
			if(!z) System.out.println("Modu kolicnika : "+f.module());
			
			if(a.module()==0) System.out.println("Faza prvog broja je : nedefinisana");
			else System.out.println("Faza prvog broja je : "+a.phase());
			if(b.module()==0) System.out.println("Faza drugog broja je : nedefinisana");
			else System.out.println("Faza drugog broja je : "+b.phase());
			if(c.module()==0) System.out.println("Faza zbira : nedefinisana");
			else System.out.println("Faza zbira : "+c.phase());
			if(d.module()==0) System.out.println("Faza razlike : nedefinisana");
			else System.out.println("Faza razlike : "+d.phase());
			if(e.module()==0) System.out.println("Faza proizvoda : nedefinisana");
			else System.out.println("Faza proizvoda : "+e.phase());
			if(z) System.out.println("Faza kolicnika : nedefinisana");
			if(!z) 
				if(f.module()==0) System.out.println("Faza kolicnika : nedefinisana");
				else              System.out.println("Faza kolicnika : "+f.phase());
		}catch(Exception ex) {
			System.out.println("Dogodila se greska.");
		}
	}
}
