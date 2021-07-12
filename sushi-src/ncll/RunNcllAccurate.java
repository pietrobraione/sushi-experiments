package ncll;

import sushi.Main;
import sushi.Options;

public class RunNcllAccurate {
	public static void main(String[] args) {
		final NcllConfiguratorAccurate config = new NcllConfiguratorAccurate();
		final Options o = new Options();
		config.configure(o);
		final Main m = new Main(o);
		m.start();
	}
}
