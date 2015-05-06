package one;

import java.math.BigDecimal;
import java.math.BigInteger;

public class trytry {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BigInteger a = new BigInteger("48");
		BigInteger mx = a.add(BigInteger.ZERO);
      //  System.out.println(sqrt5.toString());
        System.out.println(5^2);
	}
	static BigInteger  sqrt( BigInteger a )
	  {
	    BigInteger mn = BigInteger.ZERO;
	    BigInteger mx = a.add(BigInteger.ZERO);
	    while (mn.compareTo(mx) < 0)
	    {
	      BigInteger av = mn.add(mx).add(BigInteger.ONE).divide(BigInteger.valueOf(2));
	      System.out.println(av);
	      System.out.println(mn);
	      System.out.println(mx);
	      BigInteger tmp = av.multiply(av);
	      if (tmp.compareTo(a) <= 0){
	        mn = av;System.out.println("!!!!!!!!!!!!!!!!");}
	      else
	        mx = av.subtract(BigInteger.ONE);
	    }
	    return mn;
	  }
}
