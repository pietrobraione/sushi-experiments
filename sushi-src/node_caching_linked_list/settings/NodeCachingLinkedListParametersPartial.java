package node_caching_linked_list.settings;

import static common.Settings.BIN_PATH;
import static common.Settings.EVOSUITE_PATH;
import static common.Settings.JAVA8_HOME;
import static common.Settings.JBSE_PATH;
import static common.Settings.OUT_PATH;
import static common.Settings.SETTINGS_PATH;
import static common.Settings.SUSHI_LIB_PATH;
import static common.Settings.TMP_BASE_PATH;
import static common.Settings.Z3_PATH;

import java.io.IOException;
import java.util.List;

import sushi.configure.Coverage;
import sushi.configure.JBSEParameters;
import sushi.configure.MergerParameters;
import sushi.configure.Options;
import sushi.configure.ParametersModifier;
import sushi.configure.ParseException;
import sushi.logging.Level;

public class NodeCachingLinkedListParametersPartial extends ParametersModifier {
	@Override
	public void modify(Options p) {
		//Local configurations
		p.setJava8Path(JAVA8_HOME);
		p.setEvosuitePath(EVOSUITE_PATH);
		p.setSushiLibPath(SUSHI_LIB_PATH);
		p.setJBSELibraryPath(JBSE_PATH);
		p.setZ3Path(Z3_PATH);

		//Target 
		p.setClassesPath(BIN_PATH);
		p.setTargetClass("node_caching_linked_list/NodeCachingLinkedList");

		//Analysis params 
		p.setEvosuiteBudget(300);
		p.setJBSEBudget(3600);
		p.setMinimizerBudget(300);
		p.setCoverage(Coverage.BRANCHES);
		p.setLogLevel(Level.INFO);
		
		//Tmp out directories
		p.setOutDirPath(OUT_PATH);
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
		loadHEXFile(SETTINGS_PATH.resolve("node_caching_linked_list_partial.jbse"), p);
		p.setHeapScope("node_caching_linked_list/NodeCachingLinkedList$LinkedListNode", 3); 			
		p.setDepthScope(50);
		p.setCountScope(600);
	}

	@Override
	public void modify(MergerParameters p) {
		p.setBranchesToCover("node_caching_linked_list/NodeCachingLinkedList.*");
	}

	@Override
	public void modify(List<String> p) {
		p.add("-Dobject_reuse_probability=0.8");
	}
}
