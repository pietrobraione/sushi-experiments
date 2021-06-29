package common;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Settings {
	//Settings for Docker
	public static final Path GIT_REPO_ROOT  = Paths.get("/root");
	public static final Path Z3_PATH        = Paths.get("/usr", "bin", "z3");
	public static final Path JAVA8_HOME     = Paths.get("/usr", "lib", "jvm", "java-8-openjdk-amd64");
	
	//Pietro's settings
	//public static final Path GIT_REPO_ROOT  = Paths.get("/Users", "pietro", "git");
	//public static final Path Z3_PATH        = Paths.get("/opt", "local", "bin", "z3");
	//public static final Path JAVA8_HOME     = Paths.get("/Library", "Java", "JavaVirtualMachines", "openjdk8", "Contents", "Home");
	
	//these are ok for everyone
	public static final Path JBSE_PATH      = GIT_REPO_ROOT.resolve(Paths.get("sushi", "jbse", "build", "classes", "java", "main"));
	public static final Path SUSHI_LIB_PATH = GIT_REPO_ROOT.resolve(Paths.get("sushi", "runtime", "build", "classes", "java", "main"));
	public static final Path EVOSUITE_PATH  = GIT_REPO_ROOT.resolve(Paths.get("sushi", "evosuite", "evosuite-shaded-1.0.6-SNAPSHOT.jar"));
	public static final Path BIN_PATH       = GIT_REPO_ROOT.resolve(Paths.get("sushi-experiments", "bin"));
	public static final Path GUAVA_PATH     = GIT_REPO_ROOT.resolve(Paths.get("sushi-experiments", "lib", "ganttproject-guava.jar"));
	public static final Path TMP_BASE_PATH  = GIT_REPO_ROOT.resolve(Paths.get("sushi-experiments", "sushi-out"));
	public static final Path OUT_PATH       = GIT_REPO_ROOT.resolve(Paths.get("sushi-experiments", "sushi-test"));
	public static final Path SETTINGS_PATH  = GIT_REPO_ROOT.resolve(Paths.get("sushi-experiments", "settings"));
}
