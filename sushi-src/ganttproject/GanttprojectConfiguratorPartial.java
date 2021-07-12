package ganttproject;

import static common.Settings.BIN_PATH;
import static common.Settings.EVOSUITE_PATH;
import static common.Settings.GUAVA_PATH;
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

public class GanttprojectConfiguratorPartial implements OptionsConfigurator {
	@Override
	public void configure(Options p) {
		//Local configurations
		p.setJava8Path(JAVA8_HOME);
		p.setEvosuitePath(EVOSUITE_PATH);
		p.setSushiLibPath(SUSHI_LIB_PATH);
		p.setJBSELibraryPath(JBSE_PATH);
		p.setZ3Path(Z3_PATH);

		//Target 
		p.setClassesPath(BIN_PATH, GUAVA_PATH);
		p.setTargetClass("ganttproject/DependencyGraph");

		//Analysis params 
		p.setEvosuiteBudget(120);
		p.setJBSEBudget(3600);
		p.setMinimizerBudget(300);
		p.setCoverage(Coverage.BRANCHES);
		p.setBranchesToCover("ganttproject/DependencyGraph.*");
		p.setHeapScope("ganttproject/Node", 3);
		p.setHeapScope("ganttproject/NodeData", 5);
		p.setHeapScope("ganttproject/GraphData", 2);
		p.setHeapScope("ganttproject/ExplicitDependencyImpl", 1);
		p.setHeapScope("ganttproject/ImplicitInheritedDependency", 1);
		p.setHeapScope("ganttproject/ImplicitSubSuperTaskDependency", 1);
		//in the ISSTA 2017 paper it was p.setDepthScope(55);
		//here we use a better alternative: we fix a heap scope (that is
		//more stable w.r.t. changes in the standard library) and we put 
		//the minimum heap scope that excludes only the diverging trace
		//(i.e., if we increment the depth scope we do not obtain more traces)
		p.setHeapScope("common/LinkedList$Entry", 6);
		p.setHeapScope("ganttproject/DependencyEdge", 5);
		p.setDepthScope(250);
		p.setHEXFiles(SETTINGS_PATH.resolve("linked_list.jbse"), SETTINGS_PATH.resolve("ganttproject_partial.jbse"));
		
		//Tmp out directories
		p.setOutDirPath(OUT_PATH);
		p.setTmpDirectoryBase(TMP_BASE_PATH);
		
		//Redundance and parallelism
		p.setRedundanceEvosuite(1);
		p.setParallelismEvosuite(20);
		
		//Evosuite
		p.setAdditionalEvosuiteArgs("-Dobject_reuse_probability=0.8 -Delite=5");

		//Logging
		p.setLogLevel(Level.INFO);
		
		//Timeout
		p.setGlobalBudget(7200);
	}
}
