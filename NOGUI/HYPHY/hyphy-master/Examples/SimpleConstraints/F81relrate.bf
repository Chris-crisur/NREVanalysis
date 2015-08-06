/* This is an example HY-PHY Batch File.   It reads in a PHYLIP nucleotide dataset data/3.seq and performs   the relative rate test on the 3-taxa tree, using F81 model.      Output is printed out as a Newick Style tree with branch lengths   representing the number of expected substitutions per branch (which   is the default setting for nucleotide models w/o rate variation).   Also, the likelihood ratio statistic is evaluated and the P-value   for the test is reported.         Sergei L. Kosakovsky Pond and Spencer V. Muse    December 1999. *//* 1. Read in the data and store the result in a DataSet variable.*/DataSet 		nucleotideSequences = ReadDataFile ("data/3.seq");   /* 2. Filter the data, specifying that all of the data is to be used	  and that it is to be treated as nucleotides. */	  DataSetFilter	filteredData = CreateFilter (nucleotideSequences,1);/* 3. Collect observed nucleotide frequencies from the filtered data. observedFreqs will	  store the vector of frequencies. */HarvestFrequencies (observedFreqs, filteredData, 1, 1, 1);/* 4. Define the F81 substitution matrix. '*' is defined to be -(sum of off-diag row elements) */F81RateMatrix = 		{{*,mu,mu,mu}		 {mu,*,mu,mu}		 {mu,mu,*,mu}		 {mu,mu,mu,*}};		 /*5.  Define the F81 models, by combining the substitution matrix with the vector of observed 	  (equilibrium) frequencies. */Model 	F81 = (F81RateMatrix, observedFreqs);/*6.  Now we can define the simple three taxa tree.	  Since there is only 1 three sequence tree, we turn on	  ALLOW_SEQUENCE_MISMATCH to tell hyphy to map the first	  sequence in the data to leaf 'a', the 2nd - to leaf 'b' 	  and the third - leaf 'c'. */ALLOW_SEQUENCE_MISMATCH = 1;	  Tree	threeTaxaTree = (a,b,c);/*7.  Since all the likelihood function ingredients (data, tree, equilibrium frequencies)	  have been defined we are ready to construct the likelihood function. */	  LikelihoodFunction  theLnLik = (filteredData, threeTaxaTree);/*8.  Maximize the likelihood function, storing parameter values in the matrix paramValues. 	  We also store the resulting ln-lik. */Optimize (paramValues, theLnLik);unconstrainedLnLik = paramValues[1][0];/*9.  Print the tree with optimal branch lengths to the console. */fprintf  (stdout, "\n0).UNCONSTRAINED MODEL:", theLnLik);/*10. We now constrain the rate of evolution to be equal along the branches leading 	  to c and b and repeat the optimization. */	  threeTaxaTree.b.mu := threeTaxaTree.c.mu;Optimize (paramValues, theLnLik);/*11. Now we compute the ln-lik ratio statistic and the P-Value, using the Chi^2 dist'n 	  with 1 degree of freedom. */	  lnlikDelta = 2 (unconstrainedLnLik-paramValues[1][0]);pValue = 1-CChi2 (lnlikDelta, 1);fprintf (stdout, "\n\n1). With the outgroup at taxon #1, the P-value is:", pValue, "\n", theLnLik);/*12. Clear the constraints on the tree and repeat the previous steps for other outgroups. */ClearConstraints (threeTaxaTree);threeTaxaTree.a.mu := threeTaxaTree.c.mu;Optimize (paramValues, theLnLik);lnlikDelta = 2 (unconstrainedLnLik-paramValues[1][0]);pValue = 1-CChi2 (lnlikDelta, 1);fprintf (stdout, "\n\n2). With the outgroup at taxon #2, the P-value is:", pValue, "\n", theLnLik);ClearConstraints (threeTaxaTree);threeTaxaTree.b.mu := threeTaxaTree.a.mu;Optimize (paramValues, theLnLik);lnlikDelta = 2 (unconstrainedLnLik-paramValues[1][0]);pValue = 1-CChi2 (lnlikDelta, 1);fprintf (stdout, "\n\n3). With the outgroup at taxon #3, the P-value is:", pValue, "\n", theLnLik);		    