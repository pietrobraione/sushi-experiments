# SUSHI-experiments

## About

This repository contains the source code for various SUSHI experiment subjects: data structures (AVL tree, doubly linked 
list, node caching linked list, and treemap), GanttProject, and TSAFE.

## Checking out the project

This repository contains an Eclipse project that is configured to work together with the Eclipse projects for 
[SUSHI](https://github.com/pietrobraione/sushi). Install SUSHI in an empty Eclipse workspace by following the instructions in the README file in the SUSHI repository. Then, import this repository in the same workspace: On the Eclipse main menu select File > Import..., in the dialog that pops up select Projects from Git, insert the URI of this repository, and when asked for a wizard for importing projects answer Import Existing Eclipse Projects.

## Launching the experiments

The project contains a set of Eclipse launch configurations that can be used to launch the experiments:

* Sushi_AvlTree_accurate.launch for the AvlTree experiment with accurate invariants;
* Sushi_AvlTree_partial.launch for the AvlTree experiment with partial invariants;
* Sushi_DllHard.launch for the doubly linked list example;
* Sushi_GanttProject_&lt;accurate|partial&gt;.launch for the GanttProject experiment with accurate/partial invariants;
* Sushi_NodeCachingLinkedList_&lt;accurate|partial&gt;.launch for the NodeCachingLinkedList experiment with accurate/partial invariants;
* Sushi_TreeMap_&lt;accurate|partial&gt;.launch for the TreeMap experiment with accurate/partial invariants;
* Sushi_Tsafe_TS_R_3_&lt;accurate|partial&gt;.launch for the TSAFE experiment with accurate/partial invariants.

You will also find an Eclipse launch configuration Sushi_help.launch, that launches SUSHI without parameters 
so it prints a help screen on the console.

## Gathering the results

All the temporary files generated by SUSHI, including a dump of the console output of SUSHI (dump.txt), will reside 
in a subdirectory of the sushi-out/ folder that will have as name the date and time when SUSHI was launched. The 
generated tests will be put in the sushi-test/ source folder (note that multiple runs of SUSHI might overwrite 
the tests generated by previous runs).
  