package tsafe;

import sushi.Main;
import sushi.Options;

public class RunTsafePartial {
	public static void main(String[] args) {
		final TsafeConfiguratorPartial config = new TsafeConfiguratorPartial();
		final Options o = new Options();
		config.configure(o);
		final Main m = new Main(o);
		m.start();
	}
}
