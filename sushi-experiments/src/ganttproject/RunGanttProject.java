package ganttproject;

import java.io.FileNotFoundException;
import java.io.IOException;

import jbse.apps.run.RunParameters;
import jbse.apps.run.Run;
import jbse.apps.run.RunParameters.DecisionProcedureType;
import jbse.apps.run.RunParameters.InteractionMode;
import jbse.apps.run.RunParameters.StateFormatMode;
import jbse.apps.run.RunParameters.StepShowMode;
import jbse.apps.settings.ParseException;
import jbse.apps.settings.SettingsReader;

public class RunGanttProject {
	//Customize them.
	private static final String z3Path            = "/opt/local/bin/z3";

	private static final String sushiExperimentsHome = "./";
	private static final String jbseHome             = "/Users/pietro/git/jbse/";

	//private static final String jbseClassPath    = sushiExperimentsHome + "lib/jbse-lib.jar";
	private static final String jbseClassPath    = jbseHome + "bin/";
	private static final String jreClassPath     = jbseHome + "data/jre/rt.jar";
	private static final String targetClassPath  = sushiExperimentsHome + "bin/";
	private static final String guavaClassPath   = sushiExperimentsHome + "lib/ganttproject-guava.jar";
	private static final String jbseSourcePath   = jbseHome + "src/";
	private static final String jreSourcePath    = jbseHome + "data/jre/src.zip";
	private static final String targetSourcePath = sushiExperimentsHome + "src/";

	private static final String[] classPath  = { jbseClassPath, jreClassPath, targetClassPath, guavaClassPath };
	private static final String[] sourcePath = { jbseSourcePath, jreSourcePath, targetSourcePath };

	private static final String targetMethodClass       = "ganttproject/Driver"; 
	private static final String targetMethodParamsSig   = "()Z"; 
	private static final String targetMethodName        = "entry"; 
	private static final String guidanceMethodClass     = "ganttproject/Driver"; 
	private static final String guidanceMethodParamsSig = "()Z"; 
	private static final String guidanceMethodName      = "test"; 

	private static final String outFile                 = sushiExperimentsHome + "out/gantt.txt";

	public static void main(String[] args)	{
		final RunParameters p = new RunParameters();
		set(p);
		final Run r = new Run(p);
		r.run();

	}
	
	private static void set(RunParameters p) {
		try {
			new SettingsReader(sushiExperimentsHome + "settings/linked_list.jbse").fillRunParameters(p);
			new SettingsReader(sushiExperimentsHome + "settings/ganttproject.jbse").fillRunParameters(p);
		} catch (FileNotFoundException e) {
			System.err.println("Error: settings file not found.");
			System.exit(1);
		} catch (ParseException e) {
			System.err.println("Error: settings file syntactically ill-formed.");
			System.err.println(e);
			System.exit(2);
		} catch (IOException e) {
			System.err.println("Error while closing settings file.");
			System.exit(3);
		}
		p.addClasspath(classPath);
		p.addSourcePath(sourcePath);
		p.setMethodSignature(targetMethodClass, targetMethodParamsSig, targetMethodName);
		//p.setMethodSignature(guidanceMethodClass, guidanceMethodParamsSig, guidanceMethodName);
		p.setOutputFileName(outFile);
		p.setDecisionProcedureType(DecisionProcedureType.Z3);
		p.setExternalDecisionProcedurePath(z3Path);
		p.setStateFormatMode(StateFormatMode.TRACE);
		p.setStepShowMode(StepShowMode.ALL);
		p.setShowInfo(true);
		p.setShowWarnings(true);
		p.setShowContradictory(false);
		p.setShowOutOfScope(false);
		p.setHeapScope("ganttproject/Node", 3);
		p.setHeapScope("ganttproject/NodeData", 5);
		p.setHeapScope("ganttproject/GraphData", 2);
		p.setHeapScope("ganttproject/ExplicitDependencyImpl", 1);
		p.setHeapScope("ganttproject/ImplicitInheritedDependency", 1);
		p.setHeapScope("ganttproject/ImplicitSubSuperTaskDependency", 1);
		p.setDepthScope(74); //43 = minimum for having return 1; 74 = stabilization, bigger scope same result
		//p.setIdentifierSubregion(".1.1.1.2.2.3.1.1.1.1.1.1.2.1.1.3.2.1.1.1");
		//p.setGuided(guidanceMethodClass, guidanceMethodParamsSig, guidanceMethodName);
	}
}
