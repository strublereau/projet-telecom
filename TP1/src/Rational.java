public class Rational {
	// Rational(a,b),
	// Additionner 2 rationnels
	// isPositive()
	private int numerateur;
	private int denominateur;

	public Rational(int a, int b) {
		// this.numerateur = a;
		// this.denominateur = b;
		if (b < 0) {
			a = -a;
			b = -b;
		}
		int p = pgcd(Math.abs(a), Math.abs(b));
		this.numerateur = a / p;
		this.denominateur = b / p;
	}

	public int pgcd(int a, int b) {
		if ((a < 0) || (b < 0)) {
			return -1;
		}
		int r = 0;
		while (b != 0) {
			r = a % b;
			a = b;
			b = r;
		}
		return a;
	}
	
	/*public int pgcd(int a, int b) {
		if (b == 0) {
			return a;
		}
		else {
			pgcd(b,a%b);
		} 
		return -1;
	}*/

	public static boolean isPositive(Rational r) {
		return (r.numerateur >= 0 && r.denominateur > 0)
				|| (r.numerateur <= 0 && r.denominateur < 0);
		// return this.numerateur*this.denomaniteur >= 0;
	}

	public Rational add(Rational r) {
		Rational resultat = new Rational(this.numerateur * r.denominateur
				+ r.numerateur * this.denominateur, this.denominateur
				* r.denominateur);
		return resultat;
	}

	public static void main(String[] args) {
		int a = -21;
		int b = 14;
		Rational r = new Rational(a, b);
		
		System.out.println(" rational : " + r);
		System.out.println(" rational : " + isPositive(r));
	}
	
	public String toString() {
		return this.numerateur + "/" + this.denominateur;
	}
}