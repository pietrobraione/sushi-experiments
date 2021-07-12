package avl;

import sushi.Main;
import sushi.Options;

public class RunAvlPartial {
	public static void main(String[] args) {
		final AvlConfiguratorPartial config = new AvlConfiguratorPartial();
		final Options o = new Options();
		config.configure(o);
		final Main m = new Main(o);
		m.start();
	}
}
