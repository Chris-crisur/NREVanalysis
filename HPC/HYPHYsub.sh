#PBS –N NREVing-HYPHYsub
#PBS –q UCTlong
#PBS –l nodes=4:ppn=4:series600
/opt/exp_soft/hyphyMPI/bin/HYPHYMPI /home/chris.currin/NREVanalysis/HYPHY/TEMP/aaviadenovirus269Y.fas${PBS_ARRAYID}/SchmodelTest.bf