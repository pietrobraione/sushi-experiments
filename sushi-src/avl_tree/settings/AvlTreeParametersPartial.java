package avl_tree.settings;

import static common.Settings.*;

import java.io.IOException;
import java.util.List;

import sushi.configure.Coverage;
import sushi.configure.JBSEParameters;
import sushi.configure.MergerParameters;
import sushi.configure.Options;
import sushi.configure.ParametersModifier;
import sushi.configure.ParseException;
import sushi.logging.Level;

public class AvlTreeParametersPartial extends ParametersModifier {
	@Override
	public void modify(Options p) {
		//Local configurations
		p.setJava8Path(JAVA8_HOME);
		p.setEvosuitePath(EVOSUITE_PATH);
		p.setUseMOSA(false);
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
		p.setLogLevel(Level.INFO);

		//Tmp out directories
		p.setOutDirectory(OUT_PATH);
		p.setTmpDirectoryBase(TMP_BASE_PATH);
		
		//Parallelism
		p.setRedundanceEvosuite(1);
		p.setParallelismEvosuite(20);
		
		//Timeout
		p.setGlobalBudget(7200);
	}
	
	@Override
	public void modify(JBSEParameters p) 
	throws ParseException, IOException {
		loadHEXFile(SETTINGS_PATH.resolve("avl_tree_partial.jbse"), p);
		p.setHeapScope("avl_tree/AvlNode", 5);
	}							 

	@Override
	public void modify(MergerParameters p) {
		p.setBranchesToCover("avl_tree/AvlTree.*");
	}

	@Override
	public void modify(List<String> p) {
		p.add("-Dobject_reuse_probability=0.8");
	}
}
