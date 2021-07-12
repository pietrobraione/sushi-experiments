package ncll;

import sushi.Main;
import sushi.Options;

public class RunNcllPartial {
	public static void main(String[] args) {
		final NcllConfiguratorPartial config = new NcllConfiguratorPartial();
		final Options o = new Options();
		config.configure(o);
		final Main m = new Main(o);
		m.start();
	}
}
