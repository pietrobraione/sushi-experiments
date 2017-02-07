package ganttproject.settings;

import static common.Settings.BIN_PATH;
import static common.Settings.EVOSUITE_PATH;
import static common.Settings.GUAVA_PATH;
import static common.Settings.JBSE_PATH;
import static common.Settings.JRE_PATH;
import static common.Settings.OUT_PATH;
import static common.Settings.SETTINGS_PATH;
import static common.Settings.SUSHI_LIB_PATH;
import static common.Settings.TMP_BASE_PATH;
import static common.Settings.Z3_PATH;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import sushi.configure.Coverage;
import sushi.configure.JBSEParameters;
import sushi.configure.MergerParameters;
import sushi.configure.Options;
import sushi.configure.ParametersModifier;
import sushi.configure.ParseException;
import sushi.logging.Level;

public class GanttprojectParametersPartial extends ParametersModifier {
	@Override
	public void modify(Options p) {
		//Local configurations
		p.setEvosuitePath(EVOSUITE_PATH);
		p.setSushiLibPath(SUSHI_LIB_PATH);
		p.setZ3Path(Z3_PATH);

		//Target 
		p.setClassesPath(BIN_PATH, JBSE_PATH, GUAVA_PATH);
		p.setJREPath(JRE_PATH);
		p.setTargetClass("ganttproject/DependencyGraph");

		//Analysis params 
		p.setEvosuiteBudget(120);
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
	throws FileNotFoundException, ParseException, IOException {
		loadHEXFile(SETTINGS_PATH + "linked_list.jbse", p);
		loadHEXFile(SETTINGS_PATH + "ganttproject_partial.jbse", p);
		p.setHeapScope("ganttproject/Node", 3);
		p.setHeapScope("ganttproject/NodeData", 5);
		p.setHeapScope("ganttproject/GraphData", 2);
		p.setHeapScope("ganttproject/ExplicitDependencyImpl", 1);
		p.setHeapScope("ganttproject/ImplicitInheritedDependency", 1);
		p.setHeapScope("ganttproject/ImplicitSubSuperTaskDependency", 1);
		p.setDepthScope(55);
	}	
	
	@Override
	public void modify(MergerParameters p) {
		p.setBranchesToCover("ganttproject/DependencyGraph.*");
	}


	@Override
	public void modify(List<String> p) {
		p.add("-Dobject_reuse_probability=0.8");
		p.add("-Delite=5");
	}
}
