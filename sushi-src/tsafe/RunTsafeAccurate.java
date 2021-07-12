package tsafe;

import sushi.Main;
import sushi.Options;

public class RunTsafeAccurate {
	public static void main(String[] args) {
		final TsafeConfiguratorAccurate config = new TsafeConfiguratorAccurate();
		final Options o = new Options();
		config.configure(o);
		final Main m = new Main(o);
		m.start();
	}
}
