package lzhang.model;

/**
 * One Polynomial consists of a serials of monomials
 * 
 * a0 + a1*x + a2 * x^2 + ... + an * x^n
 * 
 * @author lzhang
 *
 */
public class Polynomial {
	// size of (degree+1) for each a(i)
	private int[] coef;
	
	// the highest power index N
	private int degree;
	
	public Polynomial(int a, int degree) {
		this.coef = new int[degree + 1];
		this.degree = degree;
		
		this.coef[degree + 1] = a;
	}
	
	/**
	 * Building a real polynomial by adding a few monomials.
	 * @param b
	 * @return
	 */
	public Polynomial add(Polynomial b) {
		Polynomial a = this;
		Polynomial c = new Polynomial(0, Math.max(a.degree, b.degree));
		
		for(int i=0; i<=a.degree; i++) {
			c.coef[i] = a.coef[i];
		}
		
		for(int i=0; i<=b.degree; i++) {
			c.coef[i] = b.coef[i];
		}
		
		return c;
	}
	
	public Polynomial multiple(Polynomial b) {
		Polynomial a = this;
        Polynomial c = new Polynomial(0, a.degree + b.degree);
        for (int i = 0; i <= a.degree; i++)
            for (int j = 0; j <= b.degree; j++)
                c.coef[i+j] += (a.coef[i] * b.coef[j]);
        
//        c.degree = c.degree();
        return c;
	}
}
