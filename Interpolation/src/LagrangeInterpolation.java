
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Jama.Matrix;



/**
 * Given n points (x0,y0)...(xn-1,yn-1), the following methid computes
 * the polynomial factors of the n-1't degree polynomial passing through
 * the n points.
 *
 * Example: Passing in three points (2,3) (1,4) and (3,7) will produce
 * the results [2.5, -8.5, 10] which means that the points is on the
 * curve y = 2.5x² - 8.5x + 10.
 * 
 * @author <a href="mailto:info@geosoft.no">GeoSoft</a>
 */   
public class LagrangeInterpolation
{
  public static double[] findPolynomialFactors (double[] x, double[] y)
    throws RuntimeException
  {
    int n = x.length;

    double[][] data = new double[n][n];
    double[]   rhs  = new double[n];
    
    for (int i = 0; i < n; i++) {
      double v = 1;
      for (int j = 0; j < n; j++) {
        data[i][n-j-1] = v;
        v *= x[i];
      }

      rhs[i] = y[i];
    }

    // Solve m * s = b
    
    Matrix m = new Matrix (data);
    Matrix b = new Matrix (rhs, n);

    Matrix s = m.solve (b);

    return s.getRowPackedCopy();
  }


  public static void main (String args[])
  {
//    double x[] = {41.9, 52, 60.0, 70, 75, 80, 90, 110, 120, 135, 145.7, 150, 165, 180};
//    double y[] = {6.75, 7.51, 7.78, 7.95, 7.99, 8.0, 7.99, 7.82, 7.64, 6.83, 5.96,5.66, 4.88, 4.63};

	  double xwind[]= {6, 8, 10, 12, 14, 16, 20};
	  double yvelup[]= {5.70, 6.75, 7.17, 7.34, 7.45, 7.51, 7.57};
	  double yangledown[]= {138.7, 146.4, 150.1, 158.1, 174.6, 178.8, 178};
	  double yveldown[]= {5.19, 5.86, 6.71, 7.19, 7.49, 8.02, 9.05};
	  double yangleappdown[]= {80, 100.5, 111.8, 131.9, 168.5, 177.6, 176.5};
    double f[] = LagrangeInterpolation.findPolynomialFactors (xwind, yvelup);
    List <Double > coeffs = new ArrayList <Double >();
    for (int i = 0; i < f.length; i++) {
    //  System.out.println (f[i]);
    	coeffs.add(new Double(f[i]));
    }
   
	  System.out.println( polyEval(coeffs, 7));	   
	  System.out.println( polyEval(coeffs, 9));	   
	  System.out.println( polyEval(coeffs, 11));	   
	  System.out.println( polyEval(coeffs, 13));	   
	  System.out.println( polyEval(coeffs, 15));	   
	  System.out.println( polyEval(coeffs, 18));	   
	  double f2[] = LagrangeInterpolation.findPolynomialFactors (xwind, yangleappdown);
	    List <Double > coeffs2 = new ArrayList <Double >();
	    for (int i = 0; i < f.length; i++) {
	    //  System.out.println (f[i]);
	    	coeffs2.add(new Double(f2[i]));
	    }
	    double f3[] = LagrangeInterpolation.findPolynomialFactors (xwind, yveldown);
	    List <Double > coeffs3 = new ArrayList <Double >();
	    for (int i = 0; i < f.length; i++) {
	    //  System.out.println (f[i]);
	    	coeffs3.add(new Double(f3[i]));
	    }
	    System.out.println( polyEval(coeffs2, 7)+" "+polyEval(coeffs3, 7));	   
		  System.out.println( polyEval(coeffs2, 9)+" "+polyEval(coeffs3, 9));	   
		  System.out.println( polyEval(coeffs2, 11)+" "+polyEval(coeffs3, 11));	   
		  System.out.println( polyEval(coeffs2, 13)+" "+polyEval(coeffs3, 13));	   
		  System.out.println( polyEval(coeffs2, 15)+" "+polyEval(coeffs3, 15));	   
		  System.out.println( polyEval(coeffs2, 18)+" "+polyEval(coeffs3, 18));	  
   
    
    
  }
  
  
  public static double polyEval(List <Double > coefficients, double x) {
     // Collections .reverse(coefficients);
      Double  accumulator = coefficients.get(0);
      for (int i = 1; i < coefficients.size(); i++) {
          accumulator = (accumulator * x) + (Double ) coefficients.get(i);
      }
     // System.out.println("in eval "+ accumulator.intValue());
     
      
      return accumulator;
  }
  
}
