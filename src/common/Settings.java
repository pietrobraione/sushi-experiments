package common;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Settings {
	public static final Path BIN_PATH       = Paths.get("..", "sushi-experiments", "bin");
	public static final Path JBSE_PATH      = Paths.get("..", "sushi-experiments", "lib", "jbse-lib.jar");
	//public static final Path JBSE_PATH      = Paths.get("/Users", "denaro", "git", "jbse-new", "bin");
	public static final Path TMP_BASE_PATH  = Paths.get("..", "sushi-experiments", "out");
	public static final Path EVOSUITE_PATH  = Paths.get(".", "lib", "evosuite-shaded-1.0.3.jar");
	public static final Path SUSHI_LIB_PATH = Paths.get("..", "sushi-lib", "bin");
	public static final Path Z3_PATH        = Paths.get("/opt", "local", "bin", "z3");
	//public static final Path Z3_PATH        = Paths.get("/Users", "denaro", "Desktop", "RTools", "Z3", "z3-4.3.2.d548c51a984e-x64-osx-10.8.5", "bin", "z3");
	public static final Path OUT_PATH       = Paths.get("..", "sushi-experiments", "test");
}
