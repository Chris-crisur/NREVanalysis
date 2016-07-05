#Introduction
NREVanalysis is used to check (and generate) the best phylogenetic tree, where GTR is favoured compared against NREV6 and NREV12 models
##Installation
At the moment, only Linux is supported

HYPHY have released a Windows binary, so it is possible to port to Windows (this was a previous limitation)

run the following in the terminal (will need sudo privileges; bash sometimes optional) 
`bash installRequiredLibs`
`bash installHyPhy`

####FastTree
Try provided binaries:  

- type in the terminal: `<full directory>/FastTree` or `<full directory>/FastTreeMP` 
(e.g. if NREVanalysis is located in Documents folder then open a terminal and type 
`~/Documents/NREVanalysis/FastTree/FastTreeMP)`
- if the response is multiple lines (i.e. shows how to use the program) then the file is compatible with the system
- the first output line should be 'Usage for FastTree version...' etc.
- if there is an error such as cannot find the command or must install the program, compile by running:
`bash installFastTree` (see file in editor for details)
- if you'd like FastTree to be slightly more accurate in generating the _initial_ tree, recompile FastTree with -DUSE_DOUBLE

finally run:
`javac *.java`

Alternative, run 
`bash install`
which does the previous steps for you, but will build FastTree explicitly

