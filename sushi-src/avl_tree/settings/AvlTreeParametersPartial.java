package avl_tree.settings;

import static common.Settings.*;

import sushi.Coverage;
import sushi.Options;
import sushi.OptionsConfigurator;
import sushi.Level;

public class AvlTreeParametersPartial implements OptionsConfigurator {
	@Override
	public void configure(Options p) {
		//Local configurations
		p.setJava8Path(JAVA8_HOME);
		p.setEvosuitePath(EVOSUITE_PATH);
		p.setEvosuiteNoDependency(true);
		p.setSushiLibPath(SUSHI_LIB_PATH);
		p.setJBSELibraryPath(JBSE_PATH);
		p.setZ3Path(Z3_PATH);
		
		//Target 
		p.setClassesPath(BIN_PATH);
		p.setTargetClass("avl_tree/AvlTree");

		//Analysis params 
		p.setEvosuiteBudget(60);
		p.setJBSEBudget(3600);
		p.setMinimizerBudget(300);
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("avl_tree/AvlTree.*");
		p.setHeapScope("avl_tree/AvlNode", 5);
		p.setHEXFiles(SETTINGS_PATH.resolve("avl_tree_partial.jbse"));

		//Tmp out directories
		p.setOutDirPath(OUT_PATH);
		p.setTmpDirectoryBase(TMP_BASE_PATH);
		
		//Redundance and parallelism
		p.setRedundanceEvosuite(1);
		p.setParallelismEvosuite(20);
		
		//Evosuite
		p.setAdditionalEvosuiteArgs("-Dobject_reuse_probability=0.8");

		//Logging
		p.setLogLevel(Level.INFO);
		
		//Timeout
		p.setGlobalBudget(7200);
	}
}
