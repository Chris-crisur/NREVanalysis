/* This batch implements a model test of a 12 rate NREV model versus a standard GTR and strandGTR model. the strand GTR model constrains rates so that AG := TC, not AG := GA as in the GTR model.

Code by Wayne Delport, inspired by Darren P. Martin

wdelport@mac.com
19 February 2009

*/

/* user defined functions */
function PopulateNucleotideModelMatrix ( ModelMatrixName& ) {

	ModelMatrixName = { 4, 4 };
	modelString = "";
	modelString = 64;

	hv = 0;
	for ( h = 0; h < 4; h = h + 1 ) {
		for ( v = 0; v < 4; v = v + 1 ) {
			if ( h != v ) {
				modelString = ("ModelMatrixName["+h+"]["+v+"] := " + NREVBiasTerms [ hv ] + "t;\n");
				/* fprintf ( stdout, modelString ); */
				ExecuteCommands ( modelString );
				hv = hv + 1;
			}
		}		
	}
	return 0;
}


/* end of user defined functions */

/* ---------------------------- Model stuff -------------------------------------------- */


/* set some constraints */

INTERACTIVE = 0;

if ( !INTERACTIVE ) {
	SEQFILE = "alignment";
	TREEFILE = "tree";
	GTR = 1;
	NREV6 = 1;
}
LIKELIHOOD_FUNCTION_OUTPUT = 3;

/* end of constraints */

/* define a nucleotide bias correction matrix AG := 1 */

/* built like this incase we want to scale to codon models with nuc bias terms */
NREVBiasTerms = { { "AC*", "", "AT*", "CA*", "CG*", "CT*", "GA*", "GC*", "GT*", "TA*", "TC*", "TG*" } };
/* without * to remove constraints: see malemaLovesGoats */
ratesArray = { { "AC", "", "AT", "CA", "CG", "CT", "GA", "GC", "GT", "TA", "TC", "TG" } };

global AC = 1;
global AT = 1;
global CA = 1;
global CG = 1;
global CT = 1;
global GA = 1;
global GC = 1;
global GT = 1;
global TA = 1;
global TC = 1;
global TG = 1;
	
if ( !INTERACTIVE ) {
	DataSet 		ds = ReadDataFile(SEQFILE);
	fscanf ( TREEFILE, "String", treeString );
}
else {
	SetDialogPrompt ("Select a nucleotide alignment");
	DataSet 		ds = ReadDataFile(PROMPT_FOR_FILE);
	fprintf			(stdout, "Read an alignment with ", ds.species, " sequences and ", ds.sites, " sites\n");
	ExecuteAFile 	(HYPHY_BASE_DIRECTORY + "TemplateBatchFiles" + DIRECTORY_SEPARATOR + "queryTree.bf");
	fprintf ( stdout, "\nTest NREV12 against standard GTR:(Y/N)?");
	fscanf (stdin, "String", response);
	if ((response=="n")||(response=="N")) {
		GTR = 0;
	}
	else {
		GTR = 1;
	}
	fprintf ( stdout, "\nTest NREV12 against strand GTR (NREV6) (ie: AC:=TG):(Y/N)?");
	fscanf (stdin, "String", response);
	if ((response=="n")||(response=="N")) {
		NREV6 = 0;
	}
	else {
		NREV6 = 1;
	}
}
if ( ( GTR == 0 ) && ( NREV6 == 0 ) ) {
	fprintf ( stdout, "\nWell then what would you propose I do? Ok I'll go to the beach...\n" );
}
else {
	DataSetFilter nucFilter = CreateFilter (ds,1);
	HarvestFrequencies (nucFreq,nucFilter,1,1,1);

	if ( GTR ) {
		/* standard GTR */
		CA := AC;
		GA := 1;
		GC := CG;
		TA := AT;
		TC := CT;
		TG := GT;
	
		PopulateNucleotideModelMatrix ( "GTRMatrix" );
		Model GTRModel = ( GTRMatrix, nucFreq, 1 );
		Tree GTRTree = treeString;
		LikelihoodFunction lf_gtr = ( nucFilter, GTRTree );
		Optimize ( res_gtr, lf_gtr );
		//fprintf ( stdout, res_gtr, "\n" );
		
		//fprintf ( stdout, "\nRate Matrix\n\n" );
		hv = 0;
		for ( h = 0; h < 4; h = h + 1 ) {
			for ( v = 0; v < 4; v = v + 1 ) {
				if ( v != h ) {
					if ( hv == 1 ) {
						//fprintf ( stdout, "1.000 " );
						hv = hv + 1;
					}
					else {
						string = "";
						string*4;
						string = ratesArray [ hv ];
						//ExecuteCommands ( "fprintf ( stdout, Format (" + string + ", 0, 3 ), \" \" );" );
						hv = hv + 1;
					}
				}
				else {
					//fprintf ( stdout, "*     " );
				}
			}
			//fprintf ( stdout, "\n" );
		}
		Export ( modelstr, GTRModel );
		//fprintf ( stdout, modelstr, "\n" );
	
		/* forward loop in the spirit of Sergei's noMoreBush */
		for ( malemaLovesGoats = 0; malemaLovesGoats < Columns ( ratesArray ); malemaLovesGoats = malemaLovesGoats + 1 ) {
			string = "";
			string * 32;
			string = ("ClearConstraints(" + ratesArray[malemaLovesGoats] + ");\n");
			/* fprintf ( stdout, string ); */
			ExecuteCommands ( string );
		}
		/* END standard GTR */
	}

	if ( NREV6 ) {
		/* strand GTR */
		CA := GT;
		GA := CT;
		GC := CG;
		TA := AT;
		TC := 1;
		TG := AC;
	
		PopulateNucleotideModelMatrix ( "NREV6Matrix" );
		Model NREV6Model = ( NREV6Matrix, nucFreq, 1 );
		Tree NREV6Tree = treeString;
		LikelihoodFunction lf_NREV6 = ( nucFilter, NREV6Tree );
		Optimize ( res_NREV6, lf_NREV6 );
		
		/* fprintf ( stdout, res_NREV6, "\n" ); */

		//fprintf ( stdout, "\nRate Matrix\n\n" );
		hv = 0;
		for ( h = 0; h < 4; h = h + 1 ) {
			for ( v = 0; v < 4; v = v + 1 ) {
				if ( v != h ) {
					if ( hv == 1 ) {
						//fprintf ( stdout, "1.000 " );
						hv = hv + 1;
					}
					else {
						string = "";
						string*4;
						string = ratesArray [ hv ];
						//ExecuteCommands ( "fprintf ( stdout, Format (" + string + ", 0, 3 ), \" \" );" );
						hv = hv + 1;
					}
				}
				else {
					//fprintf ( stdout, "*     " );
				}
			}
			//fprintf ( stdout, "\n" );
		}
		
		Export ( modelstr, NREV6Model );
		//fprintf ( stdout, modelstr, "\n" );	
	
		/* forward loop in the spirit of Sergei's noMoreBush */
		for ( malemaLovesGoats = 0; malemaLovesGoats < Columns ( ratesArray ); malemaLovesGoats = malemaLovesGoats + 1 ) {
			string = "";
			string * 32;
			string = ("ClearConstraints(" + ratesArray[malemaLovesGoats] + ");\n");
			/* fprintf ( stdout, string ); */
			ExecuteCommands ( string );
		}
		/* end strand GTR */
	}

	/* 12 rate model */
	PopulateNucleotideModelMatrix ( "NREV12Matrix" );
	Model NREVModel = ( NREVMatrix, nucFreq, 1 );
	Tree NREVTree = treeString;
	LikelihoodFunction lf_nrev = ( nucFilter, NREVTree );
	Optimize ( res_nrev, lf_nrev );
	

	/* fprintf ( stdout, res_nrev, "\n" ); */ 
	
	//fprintf ( stdout, "\nRate Matrix\n\n" );
	hv = 0;
	for ( h = 0; h < 4; h = h + 1 ) {
		for ( v = 0; v < 4; v = v + 1 ) {
			if ( v != h ) {
				if ( hv == 1 ) {
					//fprintf ( stdout, "1.000 " );
					hv = hv + 1;
				}
				else {
					string = "";
					string*4;
					string = ratesArray [ hv ];
					//ExecuteCommands ( "fprintf ( stdout, Format (" + string + ", 0, 3 ), \" \" );" );
					hv = hv + 1;
				}
			}
			else {
				//fprintf ( stdout, "*     " );
			}
		}
		//fprintf ( stdout, "\n" );
	}
	
	Export ( modelstr, NREVModel );
	//fprintf ( stdout, modelstr, "\n" );



	/* likelihoods */
	fprintf ( stdout, "\nLikelihoods\n" );
	fprintf ( stdout, "----------------------\n" );
	fprintf ( stdout, "\nGTR\n" );
	fprintf ( stdout,   "----\nLog Likelihood = " );
	fprintf ( stdout, res_gtr[1][0], ";\n" );
	fprintf ( stdout, "\nNREV6\n" );
	fprintf ( stdout,   "----\nLog Likelihood = " );
	fprintf ( stdout, res_NREV6[1][0], ";\n" );
	fprintf ( stdout, "\nNREV12\n" );
	fprintf ( stdout,   "----\nLog Likelihood = " );
	fprintf ( stdout, res_nrev[1][0], ";\n" );


	/* likelihood ratio tests */
	fprintf ( stdout, "\nLikelihood ratio tests\n" );
	fprintf ( stdout, "----------------------\n" );
	if ( GTR ) {
		fprintf ( stdout, "GTR vs NREV12: LRT = ", 2*(res_nrev[1][0]-res_gtr[1][0]), "\tP = ", 1-CChi2 ( 2*(res_nrev[1][0]-res_gtr[1][0]), res_nrev[1][2]-res_gtr[1][2] ), "\n" );
	}
	if ( NREV6 ) {
		fprintf ( stdout, "NREV6 vs NREV12: LRT = ", 2*(res_nrev[1][0]-res_NREV6[1][0]), "\tP = ", 1-CChi2 ( 2*(res_nrev[1][0]-res_NREV6[1][0]), res_nrev[1][2]-res_NREV6[1][2] ), "\n" );
	}
	
	fprintf ( stdout, "\nAIC\n" );
	fprintf ( stdout, "---\n" );
	if ( ( GTR ) && ( NREV6 ) ) {
		fprintf ( stdout, "GTR (all parameters): ", 2*(res_gtr[1][1]) - 2*res_gtr[1][0], ";\n" );
		fprintf ( stdout, "GTR (rates only): ", 2*(res_gtr[1][2]) - 2*res_gtr[1][0], ";\n" );
		fprintf ( stdout, "NREV6 (all parameters): ", 2*(res_NREV6[1][1]) - 2*res_NREV6[1][0], ";\n" );
		fprintf ( stdout, "NREV6 (rates only): ", 2*(res_NREV6[1][2]) - 2*res_NREV6[1][0], ";\n" );
	}
	fprintf ( stdout, "NREV12 (all parameters): ", 2*(res_nrev[1][1]) - 2*res_nrev[1][0], ";\n" );
	fprintf ( stdout, "NREV12 (rates only): ", 2*(res_nrev[1][2]) - 2*res_nrev[1][0], ";\n" ); 
}

