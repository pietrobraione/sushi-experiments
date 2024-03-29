package treemap;

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

public class TreemapConfiguratorPartial implements OptionsConfigurator {
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
		p.setTargetClass("treemap/TreeMap");
		
		//Analysis params 
		p.setEvosuiteBudget(360);
		p.setJBSEBudget(3600);
		p.setMinimizerBudget(300);
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("treemap/TreeMap(?!.*HEXTriggers.*$).*");
		p.setHeapScope("treemap/TreeMap$Entry", 5); 				
		p.setDepthScope(500);
		p.setCountScope(6000);
		p.setHEXFiles(SETTINGS_PATH.resolve("tree_map_partial.jbse"));
		
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
