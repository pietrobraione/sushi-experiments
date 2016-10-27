# SUSHI-experiments

## About

This repository contains the source code for various SUSHI experiment subjects: data structures (AVL tree, doubly linked 
list, node caching linked list, and treemap), GanttProject, and TSAFE.

## Checking out the project

This repository contains an Eclipse project that is configured to work together with the Eclipse projects for 
[SUSHI](https://github.com/pietrobraione/sushi) and [SUSHI-lib](https://github.com/pietrobraione/sushi-lib). 
Install Eclipse and the EGit plugin, then import in the same workspace the projects for SUSHI-lib and SUSHI by 
following the instructions in their respective README files. Fix the dependencies for the SUSHI project (again, 
refer to the README file). Finally, import this repository in the same workspace by following the same instructions 
as for the other projects (on the Eclipse main menu select File > Import..., in the dialog that pops up select 
Projects from Git, insert the URI of this repository, and when asked for a wizard for importing projects answer 
Import Existing Eclipse Projects).

## Launching the experiments

The project contains a set of Eclipse launch configurations that can be used to launch the experiments:

* Sushi_AvlTree.launch for the AvlTree experiment;
* Sushi_DllHard.launch for the doubly linked list example;
* Sushi_GanttProject.launch for the GanttProject experiment;
* Sushi_NodeCachingLinkedList.launch for the NodeCachingLinkedList experiment;
* Sushi_TreeMap.launch for the TreeMap experiment;
* Sushi_Tsafe_TS_R_3.launch for the TSAFE experiment.

You will also find an Eclipse launch configuration Sushi_help.launch, that launches SUSHI without parameters 
so it prints a help screen on the console.

## Gathering the results

