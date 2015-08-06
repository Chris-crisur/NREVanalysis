/* This is an example HY-PHY Batch File.   It reads a 4 taxa dataset "data/hiv.nuc", performs   an HKY85 ML analysis on the data using the tree from the file.   Having finished that, the code simulates 100 replicates of the data   using MLE of the parameters, conducts an HKY ML analysis on each of the    replicates and then tabulates the distribution of resulting ln-likelihoods.         Sergei L. Kosakovsky Pond and Spencer V. Muse    December 1999. *//* 1. Read in the data and store the result in a DataSet variable.*/DataSet 		nucleotideSequences = ReadDataFile ("data/hiv.nuc");   /* 2. Filter the data, specifying that all of the data is to be used	  and that it is to be treated as nucleotides.*/	  DataSetFilter	filteredData = CreateFilter (nucleotideSequences,1);/* 3. Collect observed nucleotide frequencies from the filtered data. observedFreqs will	  store the vector of frequencies. */HarvestFrequencies (observedFreqs, filteredData, 1, 1, 1);/* 4. Define the KHY substitution matrix. '*' is defined to be -(sum of off-diag row elements) */HKY85RateMatrix = 		{{*,trvs,trst,trvs}		 {trvs,*,trvs,trst}		 {trst,trvs,*,trvs}		 {trvs,trst,trvs,*}};		 /*5.  Define the HKY85 model, by combining the substitution matrix with the vector of observed (equilibrium)	  frequencies. */	  Model HKY85	 = (HKY85RateMatrix, observedFreqs);/*6.  Now we can define the tree variable, using the tree string read from the data file,	  and, by default, assigning the last defined model (HKY85) to all tree branches. */	  Tree	givenTree = DATAFILE_TREE;/*7.  Since all the likelihood function ingredients (data, tree, equilibrium frequencies)	  have been defined we are ready to construct the likelihood function. */	  LikelihoodFunction  theLnLik = (filteredData, givenTree);/*8.  Maximize the likelihood function, storing parameter values in the matrix paramValues */Optimize (paramValues, theLnLik);/*9.  Print the tree with optimal branch lengths to the console. */fprintf  (stdout, "\n----ORIGINAL DATA----\n",theLnLik,"\n\n----SIMULATIONS----\n\n");		 /*10. Now we set up the simulation loop.	  First, we create another copy of the tree which will	  serve as the tree for simulated data sets */	  Tree	simulatedTree = DATAFILE_TREE;/*12. By default, the random generator is reset every time the program is run.	  The value of the seed is stored in RANDOM_SEED.	  If you wish to use a particular seed, say the repeat a simulation,	  set ASSIGNED_SEED to the value that you want to use.*/fprintf (stdout, "\nUsing the seed:\n", Format(RANDOM_SEED,10,0));/*12. This is a formatting stepm which sets print width for all numbers to 10	  and prints the table header */PRINT_DIGITS = 10;fprintf (stdout, "\n|----------|----------|\n| Simul. # |  Ln lik  |\n|----------|----------|");for (simCounter = 1; simCounter<=10; simCounter = simCounter+1){	/*13. Simulate a data set of the same size as the original set using		  the MLE of all the parameters */	DataSet		simulatedData = SimulateDataSet (theLnLik);	/*14. Repeat the same steps as for the original data to obtain simulated 		  ln-likelihood */		  	DataSetFilter	filteredSimData = CreateFilter (simulatedData,1);	/*15. Collect observed nucleotide frequencies from the filtered data. observedFreqs will		  store the vector of frequencies. */	HarvestFrequencies (simFreqs, filteredSimData, 1, 1, 1);	LikelihoodFunction simLik = (filteredSimData, simulatedTree);	Optimize (simParamValues, simLik);	/* print out the log-likelihood of the simulation */	fprintf (stdout, "\n|", simCounter,"|", simParamValues[1][0],"|");}fprintf (stdout, "\n|----------|----------|");   