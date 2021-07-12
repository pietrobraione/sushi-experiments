package dll_hard;

import sushi.Main;
import sushi.Options;

public class RunDllHard {
	public static void main(String[] args) {
		final DllHardConfigurator config = new DllHardConfigurator();
		final Options o = new Options();
		config.configure(o);
		final Main m = new Main(o);
		m.start();
	}
}
