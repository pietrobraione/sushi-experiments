package avl;

import sushi.Main;
import sushi.Options;

public class RunAvlAccurate {
	public static void main(String[] args) {
		final AvlConfiguratorAccurate config = new AvlConfiguratorAccurate();
		final Options o = new Options();
		config.configure(o);
		final Main m = new Main(o);
		m.start();
	}
}
