package common;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class Settings {
	//BEGIN TO PATCH
	//Settings for Docker
	public static final Path SUSHI_WORKSPACE       = Paths.get("/root", "sushi");
	public static final Path EXPERIMENTS_WORKSPACE = Paths.get("/root", "sushi-experiments");
	public static final Path Z3_PATH               = Paths.get("/usr", "bin", "z3");
	public static final Path JAVA8_HOME            = Paths.get("/usr", "lib", "jvm", "java-8-openjdk-amd64");

	//Pietro's local settings
	//public static final Path SUSHI_WORKSPACE       = Paths.get("/Users", "pietro", "git", "sushi");
	//public static final Path EXPERIMENTS_WORKSPACE = Paths.get("/Users", "pietro", "git", "sushi-experiments");
	//public static final Path Z3_PATH               = Paths.get("/usr", "local", "bin", "z3");
	//public static final Path JAVA8_HOME            = Paths.get("/Library", "Java", "JavaVirtualMachines", "temurin-8.jdk", "Contents", "Home");
	//END TO PATCH

	public static final Path JBSE_PATH      = SUSHI_WORKSPACE.resolve(Paths.get("jbse", "build", "classes", "java", "main"));
	public static final Path SUSHI_LIB_PATH = SUSHI_WORKSPACE.resolve(Paths.get("runtime", "build", "classes", "java", "main"));
	public static final Path EVOSUITE_PATH  = SUSHI_WORKSPACE.resolve(Paths.get("libs", "evosuite-shaded-1.2.1-SNAPSHOT.jar"));
	public static final Path BIN_PATH       = EXPERIMENTS_WORKSPACE.resolve(Paths.get("bin"));
	public static final Path GUAVA_PATH     = EXPERIMENTS_WORKSPACE.resolve(Paths.get("libs", "ganttproject-guava.jar"));
	public static final Path TMP_BASE_PATH  = EXPERIMENTS_WORKSPACE.resolve(Paths.get("sushi-out"));
	public static final Path OUT_PATH       = EXPERIMENTS_WORKSPACE.resolve(Paths.get("sushi-test"));
	public static final Path SETTINGS_PATH  = EXPERIMENTS_WORKSPACE.resolve(Paths.get("settings"));
}
