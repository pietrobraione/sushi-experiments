package treemap;

import sushi.Main;
import sushi.Options;

public class RunTreemapPartial {
	public static void main(String[] args) {
		final TreemapConfiguratorPartial config = new TreemapConfiguratorPartial();
		final Options o = new Options();
		config.configure(o);
		final Main m = new Main(o);
		m.start();
	}
}
