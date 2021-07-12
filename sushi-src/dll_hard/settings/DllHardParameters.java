package dll_hard.settings;

import static common.Settings.BIN_PATH;
import static common.Settings.EVOSUITE_PATH;
import static common.Settings.JAVA8_HOME;
import static common.Settings.JBSE_PATH;
import static common.Settings.OUT_PATH;
import static common.Settings.SETTINGS_PATH;
import static common.Settings.SUSHI_LIB_PATH;
import static common.Settings.TMP_BASE_PATH;
import static common.Settings.Z3_PATH;

import sushi.Coverage;
import sushi.Options;
import sushi.OptionsConfigurator;
import sushi.Level;

public class DllHardParameters implements OptionsConfigurator {
	@Override
	public void configure(Options p) {
		//Local configurations
		p.setJava8Path(JAVA8_HOME);
		p.setEvosuitePath(EVOSUITE_PATH);
		p.setSushiLibPath(SUSHI_LIB_PATH);
		p.setJBSELibraryPath(JBSE_PATH);
		p.setZ3Path(Z3_PATH);

		//Target
		p.setClassesPath(BIN_PATH);
		p.setTargetMethod("dll_hard/Main", "(Lcommon/LinkedList;Ljava/lang/Object;)V", "sample");

		//Analysis params 
		p.setEvosuiteBudget(2400);
		p.setJBSEBudget(3600);
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("dll_hard/Main:\\(Lcommon/LinkedList;Ljava/lang/Object;\\)V:sample:.*");
		p.setHEXFiles(SETTINGS_PATH.resolve("linked_list.jbse"), SETTINGS_PATH.resolve("dll_hard.jbse"));
		
		//Phases
		p.setPhases(1, 2, 3, 4, 5, 6); /*1=JBSE-traces, 2-merge, 3=Minimize, 4=JBSE-sushiPC, 5-Javac, 6-EvoSuite*/

		//Tmp out directories
		p.setOutDirPath(OUT_PATH);
		p.setTmpDirectoryBase(TMP_BASE_PATH);
		
		//Redundance and parallelism
		p.setRedundanceEvosuite(1);
		p.setParallelismEvosuite(2);
		
		//Logging
		p.setLogLevel(Level.DEBUG);
		
		//Evosuite
		p.setAdditionalEvosuiteArgs("-Dobject_reuse_probability=0.8");

		//Timeout
		p.setGlobalBudget(7200);
	}
}
