#NEXUS

BEGIN TAXA;
	DIMENSIONS NTAX = 8;
	TAXLABELS
		'B_FR_83_HXB2_ACC_K03455' 'B_US_83_RF_ACC_M17451' 'B_US_86_JRFL_ACC_U63632' 'B_US_90_WEAU160_ACC_U21135' 'D_CD_83_ELI_ACC_K03454' 'D_CD_83_NDK_ACC_M27323' 'D_CD_84_84ZR085_ACC_U88822' 'D_UG_94_94UG114_ACC_U88824';
END;

BEGIN CHARACTERS;
	DIMENSIONS NCHAR = 1320;
	FORMAT
		DATATYPE = DNA
		GAP=-
		MISSING=?
	;

MATRIX
	'B_FR_83_HXB2_ACC_K03455'     CCCATTAGCCCTATTGAGACTGTACCAGTAAAATTAAAGCCAGGAATGGATGGCCCAAAAGTTAAACAATGGCCATTGACAGAAGAAAAAATAAAAGCATTAGTAGAAATTTGTACAGAGATGGAAAAGGAAGGGAAAATTTCAAAAATTGGGCCTGAAAATCCATACAATACTCCAGTATTTGCCATAAAGAAAAAAGACAGTACTAAATGGAGAAAATTAGTAGATTTCAGAGAACTTAATAAGAGAACTCAAGACTTCTGGGAAGTTCAATTAGGAATACCACATCCCGCAGGGTTAAAAAAGAAAAAATCAGTAACAGTACTGGATGTGGGTGATGCATATTTTTCAGTTCCCTTAGATGAAGACTTCAGGAAGTATACTGCATTTACCATACCTAGTATAAACAATGAGACACCAGGGATTAGATATCAGTACAATGTGCTTCCACAGGGATGGAAAGGATCACCAGCAATATTCCAAAGTAGCATGACAAAAATCTTAGAGCCTTTTAGAAAACAAAATCCAGACATAGTTATCTATCAATACATGGATGATTTGTATGTAGGATCTGACTTAGAAATAGGGCAGCATAGAACAAAAATAGAGGAGCTGAGACAACATCTGTTGAGGTGGGGACTTACCACACCAGACAAAAAACATCAGAAAGAACCTCCATTCCTTTGGATGGGTTATGAACTCCATCCTGATAAATGGACAGTACAGCCTATAGTGCTGCCAGAAAAAGACAGCTGGACTGTCAATGACATACAGAAGTTAGTGGGGAAATTGAATTGGGCAAGTCAGATTTACCCAGGGATTAAAGTAAGGCAATTATGTAAACTCCTTAGAGGAACCAAAGCACTAACAGAAGTAATACCACTAACAGAAGAAGCAGAGCTAGAACTGGCAGAAAACAGAGAGATTCTAAAAGAACCAGTACATGGAGTGTATTATGACCCATCAAAAGACTTAATAGCAGAAATACAGAAGCAGGGGCAAGGCCAATGGACATATCAAATTTATCAAGAGCCATTTAAAAATCTGAAAACAGGAAAATATGCAAGAATGAGGGGTGCCCACACTAATGATGTAAAACAATTAACAGAGGCAGTGCAAAAAATAACCACAGAAAGCATAGTAATATGGGGAAAGACTCCTAAATTTAAACTGCCCATACAAAAGGAAACATGGGAAACATGGTGGACAGAGTATTGGCAAGCCACCTGGATTCCTGAGTGGGAGTTTGTTAATACCCCTCCCTTAGTGAAATTATGGTACCAGTTAGAGAAAGAACCCATAGTAGGAGCAGAAACCTTC
	'B_US_83_RF_ACC_M17451'       CCCATTAGTCCTATTGAAACTGTACCAGTAAAATTAAAGCCAGGAATGGATGGCCCAAAAGTTAAACAATGGCCATTGACAGAGGAAAAAATAAAAGCATTGGTAGAAATTTGTACAGAAATGGAAAAGGAAGGAAAAATTTCCAAAATTGGGCCTGAAAATCCATACAATACTCCAGTATTTGCCATAAAGAAAAAAGACAGTACTAAATGGAGAAAATTAGTAGATTTCAGAGAACTTAATAAGAGAACTCAAGACTTCTGGGAAGTTCAGTTAGGAATACCACATCCTGCAGGGTTAAAAAAGAAGAAATCAGTAACAGTATTGGATGTGGGTGATGCATATTTTTCAGTTCCCTTAGATAAAGAGTTCAGGAAGTATACTGCATTTACCATACCTAGTATAAACAATGAAACACCACGGATTAGATATCAGTACAATGTGCTTCCACAAGGGTGGAAAGGATCACCAGCAATATTCCAAAGTAGTATGACAAAAATCTTAGAGCCTTTTAAAAAACAAAATCCAGAAATAGTTATCTATCAATACATGGATGATTTGTATGTAGGATCTGATTTAGAAATAGGGCAGCATAGAATAAAAATAGAGGAACTGAGAGAACATCTGTTAAAGTGGGGGTTTACCACACCGGACAAGAAACATCAGAAAGAACCTCCATTTCTTTGGATGGGTTATGAACTCCATCCTGATAAATGGACAGTACAGCCTATAGTGCTGCCAGAAAAAGACAGCTGGACTGTCAATGACATACAGAAGTTAGTGGGAAAATTGAATTGGGCAAGTCAGATTTATGCAGGGATTAAAGTAAAGCAATTATGTAAACTCCTTAGGGGAACCAAAGCACTAACAGAAGTAGTACAACTAACAAAAGAAGCAGAGCTAGAACTGGCAGAAAATAGGGAGATTCTAAAAGAACCAGTACATGGAGTGTATTATGACCCATCAAAAGACTTAATAGCAGAAATACAGAAGCAGGGGCAAGGCCAATGGACATACCAAATTTATCAAGAGCCATTTAAAAACCTGAAAACAGGAAAGTATGCAAGAATGAGGGGTGCCCACACTAATGATGTAAAACAATTAACAGAGGCAGTACAAAAAGTAGCCACAGAAAGCATAGTAATATGGGGAAAGACTCCTAAATTTAAACTACCCATACAAAAAGAAACATGGGAGGCATGGTGGACAGAGTATTGGCAAGCCACCTGGATTCCTGAGTGGGAGTTTGTCAATACCCCTCCCTTAGTAAAATTGTGGTACCAGTTAGAAAAAGAACCCATAATAGGAGCAGAAACTTTC
	'B_US_86_JRFL_ACC_U63632'     CCCATTAGTCCTATTGAAACTGTACCAGTAAAATTAAAGCCAGGAATGGATGGCCCAAAAGTCAAACAATGGCCATTGACAGAAGAAAAAATAAAAGCATTAGTAGAAATTTGTACAGAAATGGAAAAGGAAGGGAAAATTTCAAAAATTGGGCCTGAAAATCCATACAATACTCCAGTATTTGCCATAAAGAAAAAGGACAGTACTAAATGGAGAAAATTAGTAGATTTCAGAGAACTTAATAAGAAAACTCAAGACTTCTGGGAAGTTCAATTAGGAATACCACATCCCGCAGGGTTAAAAAAGAGAAAATCAGTAACAGTACTGGATGTGGGTGATGCATATTTTTCAGTTCCCTTAGATAAAGACTTCAGGAAATATACTGCATTTACCATACCTAGTATAAACAATGAGACACCAGGGATTAGGTATCAGTACAATGTGCTTCCGCAGGGATGGAAAGGATCACCAGCAATATTCCAAAGTAGCATGACAAAAATCTTAGAGCCTTTTAGAAAACAAAATCCAGACATAATTATCTATCAATACATGGATGATTTGTATGTAGGATCTGACTTAGAGATAGGGCAGCATAGAGCAAAAATAGAGGAATTGAGACAACATCTGTTGAGGTGGGGGTTTACCACACCAGACAAAAAACATCAGAAAGAACCTCCATTCCTTTGGATGGGTTATGAACTCCATCCTGACAAATGGACAGTACAGCCTATAGTGCTGCCAGAAAAAGACAGCTGGACTGTCAATGACATACAGAAGTTAGTGGGAAAATTAAATTGGGCAAGTCAGATTTACGCAGGGATTAAAGTAAAGCAATTATGTAAACTCCTTAGGGGAACCAAAGCACTAACAGAAGTAATACCACTAACAGAAGAAGCAGAGCTAGAACTGGCAGAAAACAGGGAGATTCTAAAAGAGCCAGTACATGGAGTGTATTATGACCCATCAAAAGACTTAATAGCAGAACTACAGAAGCAGGGGCAAGGCCAATGGACATATCAAATTTATCAAGAGCCATTTAAAATTCTGAAAACAGGAAAATATGCAAGAACGAGGGGTGCCCACACTAATGATGTAAAACAATTAACAGAGGCAGTGCAAAAAATAGCCAATGAAAGCATAGTAATATGGGGAAAGATTCCTAAATTTAAATTACCCATACAAAAAGAAACATGGGAAACATGGTGGACAGAGTATTGGCAAGCCACCTGGATTCCTGAGTGGGAGTTTGTCAATACCCCTCCCTTAGTGAAATTATGGTACCAGTTAGAGAAAGAACCCATAGTAGGAGCAGAAACTTTC
	'B_US_90_WEAU160_ACC_U21135'  CCCATTAGTCCTATTGAAACTGTACCAGTAAAATTAAAGCCAGGAATGGATGGCCCAAAAGTTAAACAATGGCCATTGACAGAAGAGAAAATAAAAGCATTAGTAGAAATTTGTACAGAAATGGAAAAGGAAGGAAAAATTTCAAAAATTGGGCCTGAAAATCCATATAATACTCCAGTATTTGCCATAAAGAAAAAAGACAGTACTAAATGGAGAAAATTAGTAGATTTCAGAGAACTTAATAAGAGAACTCAAGACTTCTGGGAAGTTCAATTAGGAATACCACATCCTTCAGGGTTAAAAAAGAAAAAATCAGTAACAGTACTGGATGTGGGTGATGCATATTTTTCAGTACCCTTAGATGAAGACTTCAGGAAGTACACTGCATTTACCATACCTAGTATAAACAATGAAACACCAGGGATTAGATATCAGTACAATGTGCTTCCACAGGGATGGAAAGGATCACCAGCAATATTCCAAAGTAGCATGACAAAAATATTAGAGCCTTTTAGAAAACAAAATCCAGACATAGTTATCTATCAATACATGGATGATTTGTATGTAGGATCTGACTTAGAAATAGGGCAGCATAGAACAAAAATAGAGGAGCTGAGACAACATCTGTTGAGGTGGGGATTTACCACACCAGACAAAAAACATCAAAAAGACCCTCCATTCCTTTGGATGGGTTATGAACTCCATCCTGATAAATGGACAGTACAGCCTATAAAGCTGCCAGAAAAAGAAAGTTGGACTGTCAATGACATACAGAAGTTAGTGGGAAAATTGAATTGGGCAAGTCAGATTTACGCAGGGATTAAAGTAAAGCAACTATGTAAACTCCTTAGGGGGACCAAAGCACTAACAGAAATAATACCAATAACAGAAGAAGCAGAGCTAGAGCTGGCAGAAAACAGGGAAATTCTAAAAGAACCGGTACATGGAGTGTATTATGACCCATCAAAAGACTTAATAGCAGAGCTACAGAAGCAGGGGCAAGGCCAATGGACATATCAGATTTATCAAGAGCCATTTAAAAATCTGAAAACAGGAAAGTATGCAAGAGTGAGGGGTGCCCACACTAATGATGTAAAACAATTAACAGAGGCAGTGCAGAAAATAACCACAGAAAGCATAGTAATATGGGGAAAGACTCCTAAATTTAAACTACCCATACAAAAAGAAACATGGGAAACATGGTGGACAGAGTATTGGCAAGCCACCTGGATTCCTGAGTGGGAGTTTGTCAATACCCCTCCCTTAGTGAAATTATGGTATCAGTTAGAGAAAGAACCCATAGTAGGAGCAGAAACTTTC
	'D_CD_83_ELI_ACC_K03454'      CCAATTAGTCCTATTGAAACTGTACCAGTAAAATTAAAGCCAGGAATGGATGGCCCAAAAGTTAAACAATGGCCATTGACAGAAGAAAAAATAAAAGCATTAACAGAAATTTGTACAGATATGGAAAAGGAAGGAAAAATTTCAAGAATTGGGCCTGAAAATCCATACAATACTCCAATATTTGCCATAAAGAAAAAAGACAGTACCAAGTGGAGAAAATTAGTAGATTTCAGAGAACTTAATAAGAGAACTCAAGATTTCTGGGAAGTTCAATTAGGAATACCGCATCCTGCAGGGCTGAAAAAGAAAAAATCAGTAACAGTACTGGATGTGGGTGATGCATATTTTTCAGTTCCCTTAGATGAAGATTTTAGGAAATATACCGCCTTTACCATATCTAGTATAAACAATGAGACACCAGGGATTAGATATCAGTACAATGTGCTTCCACAGGGATGGAAAGGATCACCGGCAATATTCCAAAGTAGCATGACAAAAATCTTAGAGCCCTTTAGAAAACAAAATCCAGAAATGGTTATCTATCAATACATGGATGATTTGTATGTAGGATCTGACTTAGAAATAGGGCAGCATAGGACAAAAATAGAGAAATTAAGAGAACATCTATTGAGGTGGGGATTTACCAGACCAGATAAAAAACATCAGAAAGAACCCCCATTTCTTTGGATGGGTTATGAACTCCATCCTGATAAATGGACAGTACAGTCTATAAAACTGCCAGAAAAGGAGAGCTGGACTGTCAATGATATACAGAACTTAGTGGAGAGATTAAACTGGGCAAGCCAGATTTATCCAGGAATTAAAGTAAGACAATTATGTAAACTCCTTAGGGGAACCAAAGCACTAACAGAAGTAATACCACTAACAGAAGAAGCAGAATTAGAACTGGCAGAAAACAGGGAAATTTTAAAAGAACCAGTACATGGAGTGTATTATGACCCATCAAAAGACTTAATAGCAGAAATACAGAAACAAGGGCACGGCCAATGGACATACCAAATTTATCAAGAACCATTTAAAAATCTGAAAACAGGAAAGTATGCAAGAATGAGGGGTGCCCACACTAATGATGTAAAGCAATTAGCAGAGGCAGTGCAAAGAATATCCACAGAAAGCATAGTGATATGGGGAAGGACTCCTAAATTTAGACTACCCATACAAAAGGAAACATGGGAAACATGGTGGGCAGAGTATTGGCAAGCCACTTGGATTCCTGAGTGGGAATTTGTCAATACCCCTCCTTTAGTAAAATTATGGTACCAGTTAGAGAAGGAACCCATAATAGGAGCAGAAACTTTC
	'D_CD_83_NDK_ACC_M27323'      CCAATTAGTCCTATTGAAACTGTACCAGTAAAATTAAAGCCAGGAATGGATGGCCCAAAAGTTAAACAATGGCCATTGACAGAAGAAAAAATAAAAGCATTAACAGAAATTTGTACAGAAATGGAAAAGGAAGGAAAAATTTCAAGAATTGGGCCTGAAAATCCATATAATACTCCAATATTTGCCATAAAGAAAAAAGACAGTACCAAGTGGAGAAAATTAGTAGATTTCAGAGAACTTAATAAGAGAACTCAAGATTTCTGGGAGGTTCAATTAGGAATACCGCATCCTGCAGGGCTGAAAAAGAAAAAATCAGTAACAGTACTGGATGTGGGTGATGCATATTTCTCAGTTCCCTTAGATGAAGATTTTAGGAAATATACCGCATTTACCATACCTAGTATAAACAATGAGACACCAGGGATTAGATATCAGTACAATGTGCTCCCACAGGGATGGAAAGGATCACCGGCAATATTCCAAAGTAGCATGACAAAAATCTTAGAGCCCTTTAGAAAACAAAATCCAGAAATAGTTATCTATCAATACATGGATGATTTGTATGTAGGATCTGACTTAGAAATAGGGCAGCATAGAACAAAAATAGAGGAATTAAGAGAACATCTATTGAGGTGGGGATTTACCACACCAGATAAAAAACATCAGAAAGAACCTCCATTTCTTTGGATGGGTTATGAACTCCATCCTGATAAATGGACAGTACAGCCTATAAACCTGCCAGAAAAAGAAAGCTGGACTGTCAATGATATACAGAAGTTAGTGGGGAAATTAAACTGGGCAAGCCAGATTTATGCAGGAATTAAAGTAAAGCAATTATGTAAACTCCTTAGGGGAACCAAAGCACTAACAGAAGTAGTACCACTAACAGAAGAAGCAGAATTAGAACTGGCAGAAAACAGGGAAATTCTAAAAGAACCAGTACATGGAGTGTATTATGACCCATCAAAAGACTTAATAGCAGAACTACAGAAACAAGGGGACGGCCAATGGACATACCAAATTTATCAAGAACCATTTAAAAATCTAAAAACAGGAAAGTATGCAAGAACGAGGGGTGCCCACACTAATGATGTAAAACAATTAACAGAGGCAGTGCAAAAAATAGCCACAGAAAGCATAGTGATATGGGGAAAGACTCCTAAATTTAAACTACCCATACAAAAGGAAACATGGGAAACATGGTGGATAGAGTATTGGCAAGCCACCTGGATTCCTGAGTGGGAATTTGTCAATACCCCTCCTTTAGTAAAATTATGGTACCAGTTAGAGAAGGAACCCATAATAGGAGCAGAAACTTTC
	'D_CD_84_84ZR085_ACC_U88822'  CCAATTAGTCCTATTGAAACTGTACCAGTAAAATTAAAGCCAGGAATGGATGGCCCAAAAGTTAAACAATGGCCGTTGACAGAAGAAAAAATAAAAGCATTAACAGAAATTTGTACAGATATGGAAAAGGAAGGAAAAATTTCAAGAATTGGGCCTGAAAATCCATACAATACTCCAATATTTGCCATAAAGAAAAAAGACAGTACTAAGTGGAGAAAATTAGTAGATTTCAGAGAACTTAATAAGAGAACTCAAGACTTCTGGGAAGTTCAATTAGGGATACCACATCCTGCAGGATTAAAGAAGAAAAAGTCAATAACAGTACTGGATGTGGGCGATGCATATTTTTCAATTCCCTTATGTGAAGACTTTAGGAAGTACACTGCATTTACCATACCTAGTATAAACAATGAGACACCAGGGATTAGATATCAGTACAATGTACTTCCACAGGGATGGAAAGGATCACCAGCAATATTCCAAAGTAGCATGATAAAAATCTTAGAGCCCTTTAGAAAACAAAATCCAGAAGTAGTTATCTATCAATACATGGATGATTTGTATGTAGGATCTGATTTAGAAATAGGACAGCATAGAGCAAAAATAGAGAAATTAAGAGAACATCTGTTGAGGTGGGGGCTTACCACACCAGACAAAAAACATCAGAAAGAACCTCCATTTCTTTGGATGGGTTATGAACTCCATCCTGATAAGTGGACAGTACAGTCTATAACACTGCCAGAGAAAGAAAGCTGGACTGTCAATGATATACAGAAGTTAGTGGGAAAATTAAATTGGGCAAGCCAGATTTATCCAGGAATTAAAGTAAAGCAATTATGTAAACTCCTTAGGGGAACCAAGGCACTAACAGAGGTAATACCACTAACAGAAGAAGCAGAATTAGAACTGGCAGAAAACAGGGAGATTCTAAAGGAACCAATGCATGGAGTGTATTATGACCCATCAAAAGACTTAATAGCAGAATTACAGAAACAAGGGCAAGGTCAATGGACATATCAAATTTATCAAGAACCATTTAAAAATCTGAAAACAGGAAAGTATGCAAGAATGAGGGGTGCCCACACTAATGATGTAAAACAGTTAACAGAGGCAGTGCAAAAAATAGCCATAGAAAGCATAGTGATATGGGGAAAGACTCCTAAATTTAGACTACCCATACAAAAGGAAACATGGGAAACATGGTGGATAGACTATTGGCAAGCCACCTGGATTCCTGAGTGGGAATTTGTCAATACCCCTCCTTTAGTAAAATTATGGTACCAGTTAGAGAAGGAACCCATAATAGGAGCAGAAACTTTC
	'D_UG_94_94UG114_ACC_U88824'  CCAATTAGTCCTATTGAAACTGTACCAGTAAAATTAAAGCCAGGGATGGATGGCCCAAAAGTTAAACAATGGCCGTTGACAGAAGAAAAAATAAAAGCACTAATAGAAATTTGTTCAGAACTAGAAAAGGAAGGAAAAATTTCAAAAATTGGGCCTGAAAACCCATACAATACTCCAATATTTGCCATAAAGAAAAAAGACAGTACTAAGTGGAGAAAATTAGTAGATTTCAGAGAACTTAATAAGAGAACTCAAGACTTTTGGGAAGTTCAACTAGGAATACCACATCCTGCAGGGCTAAAAAAGAAAAAATCAGTAACAGTACTGGATGTGGGTGACGCATATTTTTCAGTTCCCTTACATGAAGACTTTAGAAAATATACCGCATTCACCATACCTAGTACAAACAATGAGACACCAGGAATTAGATATCAGTACAATGTGCTTCCACAAGGATGGAAAGGATCACCAGCAATATTCCAAAGTAGCATGACAAAAATCTTAGAACCTTTTAGAAAACAAAATCCAGAAATGATTATCTATCAATACATGGATGATTTGTATGTAGGATCTGACTTAGAAATAGGGCAGCATAGAATAAAAATAGAGGAATTAAGGGGACACCTCTTGAAGTGGGGATTTACCACACCAGACAAAAAGTATCAGAAAGAACCCCCATTTCTTTGGATGGGTTATGAACTCCATCCTGATAAGTGGACAGTACAGCCTATACATCTGCCAGAAAAGGAAAGCTGGACTGTCAATGATATACAGAAGTTAGTGGGAAAATTAAATTGGGCAAGCCAGATTTATCCAGGAATTAAAGTAAGACAATTATGCAAATGCCTTAGGGGAGCCAAAGCACTGACAGAAGTAATACCACTGACAGCAGAAGCAGAATTAGAACTGGCAGAAAACAGGGAAATACTAAAAGAACCAGTACATGGAGCGTATTATGACCCATCAAAAGACTTAATAGCAGAAATACAGAAACAAGGGCAAGATCAATGGACATATCAAATATATCAAGAACAATATAAAAATCTGAAAACAGGAAAGTATGCGAAAATGAGGGGTACCCACACTAATGATGTAAAACAATTAACAGAGGCAGTGCAGAAAATAGCCCAAGAATGTATAGTAATATGGGGAAAGACTCCTAAATTTAGACTACCCATACAAAAGGAAACATGGGAAACATGGTGGACAGAGTATTGGCAGGCCACCTGGATTCCTGAGTGGGAGTATGTCAACACCCCTCCTTTAGTTAAATTATGGTATCAGTTAGAGAAGGAACCCATAGTAGGAGCAGAAACTTTC;
END;

BEGIN TREES;
	TREE tree = ((((D_CD_83_ELI_ACC_K03454,D_CD_83_NDK_ACC_M27323),D_UG_94_94UG114_ACC_U88824),D_CD_84_84ZR085_ACC_U88822),B_US_83_RF_ACC_M17451,((B_FR_83_HXB2_ACC_K03455,B_US_86_JRFL_ACC_U63632),B_US_90_WEAU160_ACC_U21135));
END;

BEGIN HYPHY;

/* test preamble */

	_testDescription 		= " fit the MG94x012232 model to an HIV-1 RT (p51) alignment with 8 sequences and 430 codons";
	_expectedLL 			= -3177.532445;
	ExecuteAFile ("../Shared/TestInstrumentation.bf");
	startTestTimer (_testDescription);

/* end test preamble */

OPTIMIZATION_PRECISION = 0.001;
VERBOSITY_LEVEL        = -1;

global AT=1;
global CT=1;
global AC=1;
global CG:=AT;
global GT:=AT;

MG94custom={61,61};
MG94custom[0][1]:=AC*nonSynRate*0.10767;
MG94custom[0][2]:=synRate*0.19375;
MG94custom[0][3]:=AT*nonSynRate*0.220455;
MG94custom[0][4]:=AC*nonSynRate*0.20142;
MG94custom[0][8]:=nonSynRate*0.150568;
MG94custom[0][12]:=AT*nonSynRate*0.261648;
MG94custom[0][16]:=AC*nonSynRate*0.189773;
MG94custom[0][32]:=nonSynRate*0.284375;
MG94custom[1][0]:=AC*nonSynRate*0.478125;
MG94custom[1][2]:=CG*nonSynRate*0.19375;
MG94custom[1][3]:=CT*synRate*0.220455;
MG94custom[1][5]:=AC*nonSynRate*0.20142;
MG94custom[1][9]:=nonSynRate*0.150568;
MG94custom[1][13]:=AT*nonSynRate*0.261648;
MG94custom[1][17]:=AC*nonSynRate*0.189773;
MG94custom[1][33]:=nonSynRate*0.284375;
MG94custom[1][48]:=AT*nonSynRate*0.176989;
MG94custom[2][0]:=synRate*0.478125;
MG94custom[2][1]:=CG*nonSynRate*0.10767;
MG94custom[2][3]:=GT*nonSynRate*0.220455;
MG94custom[2][6]:=AC*nonSynRate*0.20142;
MG94custom[2][10]:=nonSynRate*0.150568;
MG94custom[2][14]:=AT*nonSynRate*0.261648;
MG94custom[2][18]:=AC*nonSynRate*0.189773;
MG94custom[2][34]:=nonSynRate*0.284375;
MG94custom[3][0]:=AT*nonSynRate*0.478125;
MG94custom[3][1]:=CT*synRate*0.10767;
MG94custom[3][2]:=GT*nonSynRate*0.19375;
MG94custom[3][7]:=AC*nonSynRate*0.20142;
MG94custom[3][11]:=nonSynRate*0.150568;
MG94custom[3][15]:=AT*nonSynRate*0.261648;
MG94custom[3][19]:=AC*nonSynRate*0.189773;
MG94custom[3][35]:=nonSynRate*0.284375;
MG94custom[3][49]:=AT*nonSynRate*0.176989;
MG94custom[4][0]:=AC*nonSynRate*0.386364;
MG94custom[4][5]:=AC*synRate*0.10767;
MG94custom[4][6]:=synRate*0.19375;
MG94custom[4][7]:=AT*synRate*0.220455;
MG94custom[4][8]:=CG*nonSynRate*0.150568;
MG94custom[4][12]:=CT*nonSynRate*0.261648;
MG94custom[4][20]:=AC*nonSynRate*0.189773;
MG94custom[4][36]:=nonSynRate*0.284375;
MG94custom[4][50]:=AT*nonSynRate*0.176989;
MG94custom[5][1]:=AC*nonSynRate*0.386364;
MG94custom[5][4]:=AC*synRate*0.478125;
MG94custom[5][6]:=CG*synRate*0.19375;
MG94custom[5][7]:=CT*synRate*0.220455;
MG94custom[5][9]:=CG*nonSynRate*0.150568;
MG94custom[5][13]:=CT*nonSynRate*0.261648;
MG94custom[5][21]:=AC*nonSynRate*0.189773;
MG94custom[5][37]:=nonSynRate*0.284375;
MG94custom[5][51]:=AT*nonSynRate*0.176989;
MG94custom[6][2]:=AC*nonSynRate*0.386364;
MG94custom[6][4]:=synRate*0.478125;
MG94custom[6][5]:=CG*synRate*0.10767;
MG94custom[6][7]:=GT*synRate*0.220455;
MG94custom[6][10]:=CG*nonSynRate*0.150568;
MG94custom[6][14]:=CT*nonSynRate*0.261648;
MG94custom[6][22]:=AC*nonSynRate*0.189773;
MG94custom[6][38]:=nonSynRate*0.284375;
MG94custom[6][52]:=AT*nonSynRate*0.176989;
MG94custom[7][3]:=AC*nonSynRate*0.386364;
MG94custom[7][4]:=AT*synRate*0.478125;
MG94custom[7][5]:=CT*synRate*0.10767;
MG94custom[7][6]:=GT*synRate*0.19375;
MG94custom[7][11]:=CG*nonSynRate*0.150568;
MG94custom[7][15]:=CT*nonSynRate*0.261648;
MG94custom[7][23]:=AC*nonSynRate*0.189773;
MG94custom[7][39]:=nonSynRate*0.284375;
MG94custom[7][53]:=AT*nonSynRate*0.176989;
MG94custom[8][0]:=nonSynRate*0.386364;
MG94custom[8][4]:=CG*nonSynRate*0.20142;
MG94custom[8][9]:=AC*nonSynRate*0.10767;
MG94custom[8][10]:=synRate*0.19375;
MG94custom[8][11]:=AT*nonSynRate*0.220455;
MG94custom[8][12]:=GT*nonSynRate*0.261648;
MG94custom[8][24]:=AC*synRate*0.189773;
MG94custom[8][40]:=nonSynRate*0.284375;
MG94custom[9][1]:=nonSynRate*0.386364;
MG94custom[9][5]:=CG*nonSynRate*0.20142;
MG94custom[9][8]:=AC*nonSynRate*0.478125;
MG94custom[9][10]:=CG*nonSynRate*0.19375;
MG94custom[9][11]:=CT*synRate*0.220455;
MG94custom[9][13]:=GT*nonSynRate*0.261648;
MG94custom[9][25]:=AC*nonSynRate*0.189773;
MG94custom[9][41]:=nonSynRate*0.284375;
MG94custom[9][54]:=AT*nonSynRate*0.176989;
MG94custom[10][2]:=nonSynRate*0.386364;
MG94custom[10][6]:=CG*nonSynRate*0.20142;
MG94custom[10][8]:=synRate*0.478125;
MG94custom[10][9]:=CG*nonSynRate*0.10767;
MG94custom[10][11]:=GT*nonSynRate*0.220455;
MG94custom[10][14]:=GT*nonSynRate*0.261648;
MG94custom[10][26]:=AC*synRate*0.189773;
MG94custom[10][42]:=nonSynRate*0.284375;
MG94custom[10][55]:=AT*nonSynRate*0.176989;
MG94custom[11][3]:=nonSynRate*0.386364;
MG94custom[11][7]:=CG*nonSynRate*0.20142;
MG94custom[11][8]:=AT*nonSynRate*0.478125;
MG94custom[11][9]:=CT*synRate*0.10767;
MG94custom[11][10]:=GT*nonSynRate*0.19375;
MG94custom[11][15]:=GT*nonSynRate*0.261648;
MG94custom[11][27]:=AC*nonSynRate*0.189773;
MG94custom[11][43]:=nonSynRate*0.284375;
MG94custom[11][56]:=AT*nonSynRate*0.176989;
MG94custom[12][0]:=AT*nonSynRate*0.386364;
MG94custom[12][4]:=CT*nonSynRate*0.20142;
MG94custom[12][8]:=GT*nonSynRate*0.150568;
MG94custom[12][13]:=AC*synRate*0.10767;
MG94custom[12][14]:=nonSynRate*0.19375;
MG94custom[12][15]:=AT*synRate*0.220455;
MG94custom[12][28]:=AC*nonSynRate*0.189773;
MG94custom[12][44]:=nonSynRate*0.284375;
MG94custom[12][57]:=AT*nonSynRate*0.176989;
MG94custom[13][1]:=AT*nonSynRate*0.386364;
MG94custom[13][5]:=CT*nonSynRate*0.20142;
MG94custom[13][9]:=GT*nonSynRate*0.150568;
MG94custom[13][12]:=AC*synRate*0.478125;
MG94custom[13][14]:=CG*nonSynRate*0.19375;
MG94custom[13][15]:=CT*synRate*0.220455;
MG94custom[13][29]:=AC*nonSynRate*0.189773;
MG94custom[13][45]:=nonSynRate*0.284375;
MG94custom[13][58]:=AT*nonSynRate*0.176989;
MG94custom[14][2]:=AT*nonSynRate*0.386364;
MG94custom[14][6]:=CT*nonSynRate*0.20142;
MG94custom[14][10]:=GT*nonSynRate*0.150568;
MG94custom[14][12]:=nonSynRate*0.478125;
MG94custom[14][13]:=CG*nonSynRate*0.10767;
MG94custom[14][15]:=GT*nonSynRate*0.220455;
MG94custom[14][30]:=AC*nonSynRate*0.189773;
MG94custom[14][46]:=nonSynRate*0.284375;
MG94custom[14][59]:=AT*nonSynRate*0.176989;
MG94custom[15][3]:=AT*nonSynRate*0.386364;
MG94custom[15][7]:=CT*nonSynRate*0.20142;
MG94custom[15][11]:=GT*nonSynRate*0.150568;
MG94custom[15][12]:=AT*synRate*0.478125;
MG94custom[15][13]:=CT*synRate*0.10767;
MG94custom[15][14]:=GT*nonSynRate*0.19375;
MG94custom[15][31]:=AC*nonSynRate*0.189773;
MG94custom[15][47]:=nonSynRate*0.284375;
MG94custom[15][60]:=AT*nonSynRate*0.176989;
MG94custom[16][0]:=AC*nonSynRate*0.348864;
MG94custom[16][17]:=AC*nonSynRate*0.10767;
MG94custom[16][18]:=synRate*0.19375;
MG94custom[16][19]:=AT*nonSynRate*0.220455;
MG94custom[16][20]:=AC*nonSynRate*0.20142;
MG94custom[16][24]:=nonSynRate*0.150568;
MG94custom[16][28]:=AT*nonSynRate*0.261648;
MG94custom[16][32]:=CG*nonSynRate*0.284375;
MG94custom[17][1]:=AC*nonSynRate*0.348864;
MG94custom[17][16]:=AC*nonSynRate*0.478125;
MG94custom[17][18]:=CG*nonSynRate*0.19375;
MG94custom[17][19]:=CT*synRate*0.220455;
MG94custom[17][21]:=AC*nonSynRate*0.20142;
MG94custom[17][25]:=nonSynRate*0.150568;
MG94custom[17][29]:=AT*nonSynRate*0.261648;
MG94custom[17][33]:=CG*nonSynRate*0.284375;
MG94custom[17][48]:=CT*nonSynRate*0.176989;
MG94custom[18][2]:=AC*nonSynRate*0.348864;
MG94custom[18][16]:=synRate*0.478125;
MG94custom[18][17]:=CG*nonSynRate*0.10767;
MG94custom[18][19]:=GT*nonSynRate*0.220455;
MG94custom[18][22]:=AC*nonSynRate*0.20142;
MG94custom[18][26]:=nonSynRate*0.150568;
MG94custom[18][30]:=AT*nonSynRate*0.261648;
MG94custom[18][34]:=CG*nonSynRate*0.284375;
MG94custom[19][3]:=AC*nonSynRate*0.348864;
MG94custom[19][16]:=AT*nonSynRate*0.478125;
MG94custom[19][17]:=CT*synRate*0.10767;
MG94custom[19][18]:=GT*nonSynRate*0.19375;
MG94custom[19][23]:=AC*nonSynRate*0.20142;
MG94custom[19][27]:=nonSynRate*0.150568;
MG94custom[19][31]:=AT*nonSynRate*0.261648;
MG94custom[19][35]:=CG*nonSynRate*0.284375;
MG94custom[19][49]:=CT*nonSynRate*0.176989;
MG94custom[20][4]:=AC*nonSynRate*0.348864;
MG94custom[20][16]:=AC*nonSynRate*0.386364;
MG94custom[20][21]:=AC*synRate*0.10767;
MG94custom[20][22]:=synRate*0.19375;
MG94custom[20][23]:=AT*synRate*0.220455;
MG94custom[20][24]:=CG*nonSynRate*0.150568;
MG94custom[20][28]:=CT*nonSynRate*0.261648;
MG94custom[20][36]:=CG*nonSynRate*0.284375;
MG94custom[20][50]:=CT*nonSynRate*0.176989;
MG94custom[21][5]:=AC*nonSynRate*0.348864;
MG94custom[21][17]:=AC*nonSynRate*0.386364;
MG94custom[21][20]:=AC*synRate*0.478125;
MG94custom[21][22]:=CG*synRate*0.19375;
MG94custom[21][23]:=CT*synRate*0.220455;
MG94custom[21][25]:=CG*nonSynRate*0.150568;
MG94custom[21][29]:=CT*nonSynRate*0.261648;
MG94custom[21][37]:=CG*nonSynRate*0.284375;
MG94custom[21][51]:=CT*nonSynRate*0.176989;
MG94custom[22][6]:=AC*nonSynRate*0.348864;
MG94custom[22][18]:=AC*nonSynRate*0.386364;
MG94custom[22][20]:=synRate*0.478125;
MG94custom[22][21]:=CG*synRate*0.10767;
MG94custom[22][23]:=GT*synRate*0.220455;
MG94custom[22][26]:=CG*nonSynRate*0.150568;
MG94custom[22][30]:=CT*nonSynRate*0.261648;
MG94custom[22][38]:=CG*nonSynRate*0.284375;
MG94custom[22][52]:=CT*nonSynRate*0.176989;
MG94custom[23][7]:=AC*nonSynRate*0.348864;
MG94custom[23][19]:=AC*nonSynRate*0.386364;
MG94custom[23][20]:=AT*synRate*0.478125;
MG94custom[23][21]:=CT*synRate*0.10767;
MG94custom[23][22]:=GT*synRate*0.19375;
MG94custom[23][27]:=CG*nonSynRate*0.150568;
MG94custom[23][31]:=CT*nonSynRate*0.261648;
MG94custom[23][39]:=CG*nonSynRate*0.284375;
MG94custom[23][53]:=CT*nonSynRate*0.176989;
MG94custom[24][8]:=AC*synRate*0.348864;
MG94custom[24][16]:=nonSynRate*0.386364;
MG94custom[24][20]:=CG*nonSynRate*0.20142;
MG94custom[24][25]:=AC*synRate*0.10767;
MG94custom[24][26]:=synRate*0.19375;
MG94custom[24][27]:=AT*synRate*0.220455;
MG94custom[24][28]:=GT*nonSynRate*0.261648;
MG94custom[24][40]:=CG*nonSynRate*0.284375;
MG94custom[25][9]:=AC*nonSynRate*0.348864;
MG94custom[25][17]:=nonSynRate*0.386364;
MG94custom[25][21]:=CG*nonSynRate*0.20142;
MG94custom[25][24]:=AC*synRate*0.478125;
MG94custom[25][26]:=CG*synRate*0.19375;
MG94custom[25][27]:=CT*synRate*0.220455;
MG94custom[25][29]:=GT*nonSynRate*0.261648;
MG94custom[25][41]:=CG*nonSynRate*0.284375;
MG94custom[25][54]:=CT*nonSynRate*0.176989;
MG94custom[26][10]:=AC*synRate*0.348864;
MG94custom[26][18]:=nonSynRate*0.386364;
MG94custom[26][22]:=CG*nonSynRate*0.20142;
MG94custom[26][24]:=synRate*0.478125;
MG94custom[26][25]:=CG*synRate*0.10767;
MG94custom[26][27]:=GT*synRate*0.220455;
MG94custom[26][30]:=GT*nonSynRate*0.261648;
MG94custom[26][42]:=CG*nonSynRate*0.284375;
MG94custom[26][55]:=CT*nonSynRate*0.176989;
MG94custom[27][11]:=AC*nonSynRate*0.348864;
MG94custom[27][19]:=nonSynRate*0.386364;
MG94custom[27][23]:=CG*nonSynRate*0.20142;
MG94custom[27][24]:=AT*synRate*0.478125;
MG94custom[27][25]:=CT*synRate*0.10767;
MG94custom[27][26]:=GT*synRate*0.19375;
MG94custom[27][31]:=GT*nonSynRate*0.261648;
MG94custom[27][43]:=CG*nonSynRate*0.284375;
MG94custom[27][56]:=CT*nonSynRate*0.176989;
MG94custom[28][12]:=AC*nonSynRate*0.348864;
MG94custom[28][16]:=AT*nonSynRate*0.386364;
MG94custom[28][20]:=CT*nonSynRate*0.20142;
MG94custom[28][24]:=GT*nonSynRate*0.150568;
MG94custom[28][29]:=AC*synRate*0.10767;
MG94custom[28][30]:=synRate*0.19375;
MG94custom[28][31]:=AT*synRate*0.220455;
MG94custom[28][44]:=CG*nonSynRate*0.284375;
MG94custom[28][57]:=CT*synRate*0.176989;
MG94custom[29][13]:=AC*nonSynRate*0.348864;
MG94custom[29][17]:=AT*nonSynRate*0.386364;
MG94custom[29][21]:=CT*nonSynRate*0.20142;
MG94custom[29][25]:=GT*nonSynRate*0.150568;
MG94custom[29][28]:=AC*synRate*0.478125;
MG94custom[29][30]:=CG*synRate*0.19375;
MG94custom[29][31]:=CT*synRate*0.220455;
MG94custom[29][45]:=CG*nonSynRate*0.284375;
MG94custom[29][58]:=CT*nonSynRate*0.176989;
MG94custom[30][14]:=AC*nonSynRate*0.348864;
MG94custom[30][18]:=AT*nonSynRate*0.386364;
MG94custom[30][22]:=CT*nonSynRate*0.20142;
MG94custom[30][26]:=GT*nonSynRate*0.150568;
MG94custom[30][28]:=synRate*0.478125;
MG94custom[30][29]:=CG*synRate*0.10767;
MG94custom[30][31]:=GT*synRate*0.220455;
MG94custom[30][46]:=CG*nonSynRate*0.284375;
MG94custom[30][59]:=CT*synRate*0.176989;
MG94custom[31][15]:=AC*nonSynRate*0.348864;
MG94custom[31][19]:=AT*nonSynRate*0.386364;
MG94custom[31][23]:=CT*nonSynRate*0.20142;
MG94custom[31][27]:=GT*nonSynRate*0.150568;
MG94custom[31][28]:=AT*synRate*0.478125;
MG94custom[31][29]:=CT*synRate*0.10767;
MG94custom[31][30]:=GT*synRate*0.19375;
MG94custom[31][47]:=CG*nonSynRate*0.284375;
MG94custom[31][60]:=CT*nonSynRate*0.176989;
MG94custom[32][0]:=nonSynRate*0.348864;
MG94custom[32][16]:=CG*nonSynRate*0.189773;
MG94custom[32][33]:=AC*nonSynRate*0.10767;
MG94custom[32][34]:=synRate*0.19375;
MG94custom[32][35]:=AT*nonSynRate*0.220455;
MG94custom[32][36]:=AC*nonSynRate*0.20142;
MG94custom[32][40]:=nonSynRate*0.150568;
MG94custom[32][44]:=AT*nonSynRate*0.261648;
MG94custom[33][1]:=nonSynRate*0.348864;
MG94custom[33][17]:=CG*nonSynRate*0.189773;
MG94custom[33][32]:=AC*nonSynRate*0.478125;
MG94custom[33][34]:=CG*nonSynRate*0.19375;
MG94custom[33][35]:=CT*synRate*0.220455;
MG94custom[33][37]:=AC*nonSynRate*0.20142;
MG94custom[33][41]:=nonSynRate*0.150568;
MG94custom[33][45]:=AT*nonSynRate*0.261648;
MG94custom[33][48]:=GT*nonSynRate*0.176989;
MG94custom[34][2]:=nonSynRate*0.348864;
MG94custom[34][18]:=CG*nonSynRate*0.189773;
MG94custom[34][32]:=synRate*0.478125;
MG94custom[34][33]:=CG*nonSynRate*0.10767;
MG94custom[34][35]:=GT*nonSynRate*0.220455;
MG94custom[34][38]:=AC*nonSynRate*0.20142;
MG94custom[34][42]:=nonSynRate*0.150568;
MG94custom[34][46]:=AT*nonSynRate*0.261648;
MG94custom[35][3]:=nonSynRate*0.348864;
MG94custom[35][19]:=CG*nonSynRate*0.189773;
MG94custom[35][32]:=AT*nonSynRate*0.478125;
MG94custom[35][33]:=CT*synRate*0.10767;
MG94custom[35][34]:=GT*nonSynRate*0.19375;
MG94custom[35][39]:=AC*nonSynRate*0.20142;
MG94custom[35][43]:=nonSynRate*0.150568;
MG94custom[35][47]:=AT*nonSynRate*0.261648;
MG94custom[35][49]:=GT*nonSynRate*0.176989;
MG94custom[36][4]:=nonSynRate*0.348864;
MG94custom[36][20]:=CG*nonSynRate*0.189773;
MG94custom[36][32]:=AC*nonSynRate*0.386364;
MG94custom[36][37]:=AC*synRate*0.10767;
MG94custom[36][38]:=synRate*0.19375;
MG94custom[36][39]:=AT*synRate*0.220455;
MG94custom[36][40]:=CG*nonSynRate*0.150568;
MG94custom[36][44]:=CT*nonSynRate*0.261648;
MG94custom[36][50]:=GT*nonSynRate*0.176989;
MG94custom[37][5]:=nonSynRate*0.348864;
MG94custom[37][21]:=CG*nonSynRate*0.189773;
MG94custom[37][33]:=AC*nonSynRate*0.386364;
MG94custom[37][36]:=AC*synRate*0.478125;
MG94custom[37][38]:=CG*synRate*0.19375;
MG94custom[37][39]:=CT*synRate*0.220455;
MG94custom[37][41]:=CG*nonSynRate*0.150568;
MG94custom[37][45]:=CT*nonSynRate*0.261648;
MG94custom[37][51]:=GT*nonSynRate*0.176989;
MG94custom[38][6]:=nonSynRate*0.348864;
MG94custom[38][22]:=CG*nonSynRate*0.189773;
MG94custom[38][34]:=AC*nonSynRate*0.386364;
MG94custom[38][36]:=synRate*0.478125;
MG94custom[38][37]:=CG*synRate*0.10767;
MG94custom[38][39]:=GT*synRate*0.220455;
MG94custom[38][42]:=CG*nonSynRate*0.150568;
MG94custom[38][46]:=CT*nonSynRate*0.261648;
MG94custom[38][52]:=GT*nonSynRate*0.176989;
MG94custom[39][7]:=nonSynRate*0.348864;
MG94custom[39][23]:=CG*nonSynRate*0.189773;
MG94custom[39][35]:=AC*nonSynRate*0.386364;
MG94custom[39][36]:=AT*synRate*0.478125;
MG94custom[39][37]:=CT*synRate*0.10767;
MG94custom[39][38]:=GT*synRate*0.19375;
MG94custom[39][43]:=CG*nonSynRate*0.150568;
MG94custom[39][47]:=CT*nonSynRate*0.261648;
MG94custom[39][53]:=GT*nonSynRate*0.176989;
MG94custom[40][8]:=nonSynRate*0.348864;
MG94custom[40][24]:=CG*nonSynRate*0.189773;
MG94custom[40][32]:=nonSynRate*0.386364;
MG94custom[40][36]:=CG*nonSynRate*0.20142;
MG94custom[40][41]:=AC*synRate*0.10767;
MG94custom[40][42]:=synRate*0.19375;
MG94custom[40][43]:=AT*synRate*0.220455;
MG94custom[40][44]:=GT*nonSynRate*0.261648;
MG94custom[41][9]:=nonSynRate*0.348864;
MG94custom[41][25]:=CG*nonSynRate*0.189773;
MG94custom[41][33]:=nonSynRate*0.386364;
MG94custom[41][37]:=CG*nonSynRate*0.20142;
MG94custom[41][40]:=AC*synRate*0.478125;
MG94custom[41][42]:=CG*synRate*0.19375;
MG94custom[41][43]:=CT*synRate*0.220455;
MG94custom[41][45]:=GT*nonSynRate*0.261648;
MG94custom[41][54]:=GT*nonSynRate*0.176989;
MG94custom[42][10]:=nonSynRate*0.348864;
MG94custom[42][26]:=CG*nonSynRate*0.189773;
MG94custom[42][34]:=nonSynRate*0.386364;
MG94custom[42][38]:=CG*nonSynRate*0.20142;
MG94custom[42][40]:=synRate*0.478125;
MG94custom[42][41]:=CG*synRate*0.10767;
MG94custom[42][43]:=GT*synRate*0.220455;
MG94custom[42][46]:=GT*nonSynRate*0.261648;
MG94custom[42][55]:=GT*nonSynRate*0.176989;
MG94custom[43][11]:=nonSynRate*0.348864;
MG94custom[43][27]:=CG*nonSynRate*0.189773;
MG94custom[43][35]:=nonSynRate*0.386364;
MG94custom[43][39]:=CG*nonSynRate*0.20142;
MG94custom[43][40]:=AT*synRate*0.478125;
MG94custom[43][41]:=CT*synRate*0.10767;
MG94custom[43][42]:=GT*synRate*0.19375;
MG94custom[43][47]:=GT*nonSynRate*0.261648;
MG94custom[43][56]:=GT*nonSynRate*0.176989;
MG94custom[44][12]:=nonSynRate*0.348864;
MG94custom[44][28]:=CG*nonSynRate*0.189773;
MG94custom[44][32]:=AT*nonSynRate*0.386364;
MG94custom[44][36]:=CT*nonSynRate*0.20142;
MG94custom[44][40]:=GT*nonSynRate*0.150568;
MG94custom[44][45]:=AC*synRate*0.10767;
MG94custom[44][46]:=synRate*0.19375;
MG94custom[44][47]:=AT*synRate*0.220455;
MG94custom[44][57]:=GT*nonSynRate*0.176989;
MG94custom[45][13]:=nonSynRate*0.348864;
MG94custom[45][29]:=CG*nonSynRate*0.189773;
MG94custom[45][33]:=AT*nonSynRate*0.386364;
MG94custom[45][37]:=CT*nonSynRate*0.20142;
MG94custom[45][41]:=GT*nonSynRate*0.150568;
MG94custom[45][44]:=AC*synRate*0.478125;
MG94custom[45][46]:=CG*synRate*0.19375;
MG94custom[45][47]:=CT*synRate*0.220455;
MG94custom[45][58]:=GT*nonSynRate*0.176989;
MG94custom[46][14]:=nonSynRate*0.348864;
MG94custom[46][30]:=CG*nonSynRate*0.189773;
MG94custom[46][34]:=AT*nonSynRate*0.386364;
MG94custom[46][38]:=CT*nonSynRate*0.20142;
MG94custom[46][42]:=GT*nonSynRate*0.150568;
MG94custom[46][44]:=synRate*0.478125;
MG94custom[46][45]:=CG*synRate*0.10767;
MG94custom[46][47]:=GT*synRate*0.220455;
MG94custom[46][59]:=GT*nonSynRate*0.176989;
MG94custom[47][15]:=nonSynRate*0.348864;
MG94custom[47][31]:=CG*nonSynRate*0.189773;
MG94custom[47][35]:=AT*nonSynRate*0.386364;
MG94custom[47][39]:=CT*nonSynRate*0.20142;
MG94custom[47][43]:=GT*nonSynRate*0.150568;
MG94custom[47][44]:=AT*synRate*0.478125;
MG94custom[47][45]:=CT*synRate*0.10767;
MG94custom[47][46]:=GT*synRate*0.19375;
MG94custom[47][60]:=GT*nonSynRate*0.176989;
MG94custom[48][1]:=AT*nonSynRate*0.348864;
MG94custom[48][17]:=CT*nonSynRate*0.189773;
MG94custom[48][33]:=GT*nonSynRate*0.284375;
MG94custom[48][49]:=CT*synRate*0.220455;
MG94custom[48][51]:=AC*nonSynRate*0.20142;
MG94custom[48][54]:=nonSynRate*0.150568;
MG94custom[48][58]:=AT*nonSynRate*0.261648;
MG94custom[49][3]:=AT*nonSynRate*0.348864;
MG94custom[49][19]:=CT*nonSynRate*0.189773;
MG94custom[49][35]:=GT*nonSynRate*0.284375;
MG94custom[49][48]:=CT*synRate*0.10767;
MG94custom[49][53]:=AC*nonSynRate*0.20142;
MG94custom[49][56]:=nonSynRate*0.150568;
MG94custom[49][60]:=AT*nonSynRate*0.261648;
MG94custom[50][4]:=AT*nonSynRate*0.348864;
MG94custom[50][20]:=CT*nonSynRate*0.189773;
MG94custom[50][36]:=GT*nonSynRate*0.284375;
MG94custom[50][51]:=AC*synRate*0.10767;
MG94custom[50][52]:=synRate*0.19375;
MG94custom[50][53]:=AT*synRate*0.220455;
MG94custom[50][57]:=CT*nonSynRate*0.261648;
MG94custom[51][5]:=AT*nonSynRate*0.348864;
MG94custom[51][21]:=CT*nonSynRate*0.189773;
MG94custom[51][37]:=GT*nonSynRate*0.284375;
MG94custom[51][48]:=AC*nonSynRate*0.386364;
MG94custom[51][50]:=AC*synRate*0.478125;
MG94custom[51][52]:=CG*synRate*0.19375;
MG94custom[51][53]:=CT*synRate*0.220455;
MG94custom[51][54]:=CG*nonSynRate*0.150568;
MG94custom[51][58]:=CT*nonSynRate*0.261648;
MG94custom[52][6]:=AT*nonSynRate*0.348864;
MG94custom[52][22]:=CT*nonSynRate*0.189773;
MG94custom[52][38]:=GT*nonSynRate*0.284375;
MG94custom[52][50]:=synRate*0.478125;
MG94custom[52][51]:=CG*synRate*0.10767;
MG94custom[52][53]:=GT*synRate*0.220455;
MG94custom[52][55]:=CG*nonSynRate*0.150568;
MG94custom[52][59]:=CT*nonSynRate*0.261648;
MG94custom[53][7]:=AT*nonSynRate*0.348864;
MG94custom[53][23]:=CT*nonSynRate*0.189773;
MG94custom[53][39]:=GT*nonSynRate*0.284375;
MG94custom[53][49]:=AC*nonSynRate*0.386364;
MG94custom[53][50]:=AT*synRate*0.478125;
MG94custom[53][51]:=CT*synRate*0.10767;
MG94custom[53][52]:=GT*synRate*0.19375;
MG94custom[53][56]:=CG*nonSynRate*0.150568;
MG94custom[53][60]:=CT*nonSynRate*0.261648;
MG94custom[54][9]:=AT*nonSynRate*0.348864;
MG94custom[54][25]:=CT*nonSynRate*0.189773;
MG94custom[54][41]:=GT*nonSynRate*0.284375;
MG94custom[54][48]:=nonSynRate*0.386364;
MG94custom[54][51]:=CG*nonSynRate*0.20142;
MG94custom[54][55]:=CG*nonSynRate*0.19375;
MG94custom[54][56]:=CT*synRate*0.220455;
MG94custom[54][58]:=GT*nonSynRate*0.261648;
MG94custom[55][10]:=AT*nonSynRate*0.348864;
MG94custom[55][26]:=CT*nonSynRate*0.189773;
MG94custom[55][42]:=GT*nonSynRate*0.284375;
MG94custom[55][52]:=CG*nonSynRate*0.20142;
MG94custom[55][54]:=CG*nonSynRate*0.10767;
MG94custom[55][56]:=GT*nonSynRate*0.220455;
MG94custom[55][59]:=GT*nonSynRate*0.261648;
MG94custom[56][11]:=AT*nonSynRate*0.348864;
MG94custom[56][27]:=CT*nonSynRate*0.189773;
MG94custom[56][43]:=GT*nonSynRate*0.284375;
MG94custom[56][49]:=nonSynRate*0.386364;
MG94custom[56][53]:=CG*nonSynRate*0.20142;
MG94custom[56][54]:=CT*synRate*0.10767;
MG94custom[56][55]:=GT*nonSynRate*0.19375;
MG94custom[56][60]:=GT*nonSynRate*0.261648;
MG94custom[57][12]:=AT*nonSynRate*0.348864;
MG94custom[57][28]:=CT*synRate*0.189773;
MG94custom[57][44]:=GT*nonSynRate*0.284375;
MG94custom[57][50]:=CT*nonSynRate*0.20142;
MG94custom[57][58]:=AC*nonSynRate*0.10767;
MG94custom[57][59]:=synRate*0.19375;
MG94custom[57][60]:=AT*nonSynRate*0.220455;
MG94custom[58][13]:=AT*nonSynRate*0.348864;
MG94custom[58][29]:=CT*nonSynRate*0.189773;
MG94custom[58][45]:=GT*nonSynRate*0.284375;
MG94custom[58][48]:=AT*nonSynRate*0.386364;
MG94custom[58][51]:=CT*nonSynRate*0.20142;
MG94custom[58][54]:=GT*nonSynRate*0.150568;
MG94custom[58][57]:=AC*nonSynRate*0.478125;
MG94custom[58][59]:=CG*nonSynRate*0.19375;
MG94custom[58][60]:=CT*synRate*0.220455;
MG94custom[59][14]:=AT*nonSynRate*0.348864;
MG94custom[59][30]:=CT*synRate*0.189773;
MG94custom[59][46]:=GT*nonSynRate*0.284375;
MG94custom[59][52]:=CT*nonSynRate*0.20142;
MG94custom[59][55]:=GT*nonSynRate*0.150568;
MG94custom[59][57]:=synRate*0.478125;
MG94custom[59][58]:=CG*nonSynRate*0.10767;
MG94custom[59][60]:=GT*nonSynRate*0.220455;
MG94custom[60][15]:=AT*nonSynRate*0.348864;
MG94custom[60][31]:=CT*nonSynRate*0.189773;
MG94custom[60][47]:=GT*nonSynRate*0.284375;
MG94custom[60][49]:=AT*nonSynRate*0.386364;
MG94custom[60][53]:=CT*nonSynRate*0.20142;
MG94custom[60][56]:=GT*nonSynRate*0.150568;
MG94custom[60][57]:=AT*nonSynRate*0.478125;
MG94custom[60][58]:=CT*synRate*0.10767;
MG94custom[60][59]:=GT*nonSynRate*0.19375;

vectorOfFrequencies={
{   0.0684634387476}
{   0.0154174945249}
{   0.0277433542637}
{   0.0315672183411}
{   0.0356916015236}
{  0.00803750266039}
{   0.0144632633625}
{   0.0164567336793}
{   0.0266806048061}
{  0.00600828830748}
{   0.0108117483528}
{   0.0123019306771}
{    0.046363843446}
{   0.0104408179834}
{   0.0187879627036}
{   0.0213775059501}
{   0.0372423266151}
{  0.00838671526271}
{   0.0150916617656}
{   0.0171717441791}
{   0.0194153011545}
{  0.00437219200093}
{  0.00786763837634}
{  0.00895203428158}
{   0.0145135537544}
{   0.0032683522715}
{  0.00588130936454}
{  0.00669192971684}
{   0.0252207226563}
{  0.00567953290953}
{   0.0102201621222}
{   0.0116288061683}
{   0.0558077379368}
{   0.0125675179311}
{   0.0226149003404}
{   0.0257319100647}
{   0.0290938869097}
{  0.00655174280379}
{   0.0117896796627}
{   0.0134146501734}
{   0.0217486037548}
{  0.00489763566432}
{  0.00881315969148}
{   0.0100278767164}
{   0.0377933284116}
{  0.00851079706952}
{   0.0153149435393}
{     0.01742580086}
{  0.00782174192914}
{   0.0160149650053}
{   0.0181073841606}
{  0.00407765810865}
{  0.00733763279709}
{  0.00834897807998}
{  0.00304817884003}
{  0.00548511337442}
{  0.00624112606825}
{   0.0235217218785}
{  0.00529692964466}
{  0.00953167814686}
{   0.0108454285073}
}
;

Model MG94customModel=(MG94custom,vectorOfFrequencies,0);

UseModel (MG94customModel);
Tree givenTree=(((D_CD_83_ELI_ACC_K03454,D_CD_83_NDK_ACC_M27323)Node3,D_UG_94_94UG114_ACC_U88824)Node2,D_CD_84_84ZR085_ACC_U88822,(B_US_83_RF_ACC_M17451,((B_FR_83_HXB2_ACC_K03455,B_US_86_JRFL_ACC_U63632)Node11,B_US_90_WEAU160_ACC_U21135)Node10)Node8);

DataSet ds = ReadDataFile(USE_NEXUS_FILE_DATA);
DataSetFilter filteredData = CreateFilter(ds,3,"0-1319","4,5,7,6,1,0,2,3","TAA,TAG,TGA");

PARAMETER_GROUPING = {"0" : {{"AC","AT","CT"}}};
LikelihoodFunction lf = (filteredData,givenTree);
OPTIMIZATION_PRECISION     = 0.001;
VERBOSITY_LEVEL	           = 1;
OPTIMIZATION_METHOD		   = 4;
USE_ADAPTIVE_VARIABLE_STEP = 1;

Optimize 			(res,lf);

/* test epilogue */
	timeMatrix = endTestTimer 				  (_testDescription);
	if (logTestResult    (Abs (res[1][0] - _expectedLL) < 2*OPTIMIZATION_PRECISION))
	{
		return timeMatrix;
	}
	return 0;
/* end test epilogue */


END;
