package common;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Settings {
	public static final Path GIT_REPO_ROOT      = Paths.get("/Users", "pietro", "git");
	public static final Path BIN_PATH           = GIT_REPO_ROOT.resolve(Paths.get("sushi-experiments", "bin"));
	public static final Path JBSE_PATH          = GIT_REPO_ROOT.resolve(Paths.get("sushi", "jbse", "target", "classes"));
	public static final Path GUAVA_PATH         = GIT_REPO_ROOT.resolve(Paths.get("sushi-experiments", "lib", "ganttproject-guava.jar"));
	public static final Path TMP_BASE_PATH      = GIT_REPO_ROOT.resolve(Paths.get("sushi-experiments", "sushi-out"));
	public static final Path EVOSUITE_PATH      = GIT_REPO_ROOT.resolve(Paths.get("sushi", "evosuite", "evosuite-shaded-1.0.3.jar"));
	public static final Path EVOSUITE_MOSA_PATH = GIT_REPO_ROOT.resolve(Paths.get("sushi", "evosuite", "evosuite-shaded-1.0.6-SNAPSHOT.jar"));
	public static final Path SUSHI_LIB_PATH     = GIT_REPO_ROOT.resolve(Paths.get("sushi", "runtime", "target", "classes"));
	public static final Path Z3_PATH            = Paths.get("/opt", "local", "bin", "z3");
	//public static final Path Z3_PATH            = Paths.get("/Users", "denaro", "Desktop", "RTools", "Z3", "z3-4.3.2.d548c51a984e-x64-osx-10.8.5", "bin", "z3");
	public static final Path OUT_PATH           = GIT_REPO_ROOT.resolve(Paths.get("sushi-experiments", "sushi-test"));
	public static final String SETTINGS_PATH    = GIT_REPO_ROOT.resolve(Paths.get("sushi-experiments", "settings")).toString() + "/";
}
