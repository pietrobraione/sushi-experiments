package treemap;

import sushi.Main;
import sushi.Options;

public class RunTreemapAccurate {
	public static void main(String[] args) {
		final TreemapConfiguratorAccurate config = new TreemapConfiguratorAccurate();
		final Options o = new Options();
		config.configure(o);
		final Main m = new Main(o);
		m.start();
	}
}
