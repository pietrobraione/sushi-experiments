package tsafe.settings;

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

import jbse.rewr.RewriterAbsSum;
import jbse.rewr.RewriterPolynomials;
import jbse.rewr.RewriterSinCos;
import jbse.rewr.RewriterSqrt;
import sushi.Coverage;
import sushi.Options;
import sushi.ParametersModifier;
import sushi.ParseException;
import sushi.execution.jbse.JBSEParameters;
import sushi.execution.merger.MergerParameters;
import sushi.Level;

public class TsafeParametersPartial extends ParametersModifier {
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
		p.setTargetClass("tsafe/TsafeTrajectorySynthesis");
		
		//Analysis params 
		p.setEvosuiteBudget(240);
		p.setJBSEBudget(3600);
		p.setMinimizerBudget(300);
		p.setCoverage(Coverage.BRANCHES);
		p.setLogLevel(Level.INFO);

		//Tmp out directories
		p.setOutDirPath(OUT_PATH);
		p.setTmpDirectoryBase(TMP_BASE_PATH);

		//Parallelism
		p.setParallelismEvosuite(20);
		
		//Timeout
		p.setGlobalBudget(7200);
	}

	@Override
	public void modify(JBSEParameters p) 
	throws ParseException, IOException {
		loadHEXFile(SETTINGS_PATH.resolve("linked_list.jbse"), p);
		loadHEXFile(SETTINGS_PATH.resolve("tsafe_partial.jbse"), p);
		p.setDoSignAnalysis(true);
		p.addRewriter(RewriterPolynomials.class, RewriterSinCos.class, RewriterSqrt.class, RewriterAbsSum.class);
		p.setHeapScope("common/LinkedList$Entry", 3);
	}

	@Override
	public void modify(MergerParameters p) {
		p.setBranchesToCover("tsafe/TsafeTrajectorySynthesis.*");
	}

	@Override
	public void modify(List<String> p) {
		p.add("-Dobject_reuse_probability=0.8");
		p.add("-Delite=5");
	}
}
