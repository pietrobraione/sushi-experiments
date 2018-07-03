package ganttproject.settings;

import static common.Settings.*;

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

public class GanttprojectParametersAccurate extends ParametersModifier {
	@Override
	public void modify(Options p) {
		//Local configurations
		p.setEvosuitePath(EVOSUITE_MOSA_PATH);
		p.setSushiLibPath(SUSHI_LIB_PATH);
		p.setZ3Path(Z3_PATH);

		//Target 
		p.setClassesPath(BIN_PATH, JBSE_PATH, GUAVA_PATH);
		p.setJREPath(JRE_PATH);
		p.setTargetClass("ganttproject/DependencyGraph");

		//Analysis params 
		p.setEvosuiteBudget(3600);
		p.setJBSEBudget(3600);
		p.setCoverage(Coverage.BRANCHES);
		p.setLogLevel(Level.DEBUG);
		p.setPhases(1, 2, 3, 4, 5, 6); /*1=JBSE-traces, 2-merge, 3=Minimize, 4=JBSE-sushiPC, 5-Javac, 6-EvoSuite*/
		
		//Tmp out directories
		p.setOutDirectory(OUT_PATH);
		p.setTmpDirectoryBase(TMP_BASE_PATH);
		
		//Parallelism
		p.setRedundanceEvosuite(1);
		p.setParallelismEvosuite(2);
		
		//Timeout
		p.setGlobalBudget(7200);
		
		p.setUseMOSA(true);
	}

	@Override
	public void modify(JBSEParameters p) 
	throws FileNotFoundException, ParseException, IOException {
		loadHEXFile(SETTINGS_PATH + "linked_list.jbse", p);
		loadHEXFile(SETTINGS_PATH + "ganttproject_accurate.jbse", p);
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
		//p.add("-Delite=5");
	}
}
