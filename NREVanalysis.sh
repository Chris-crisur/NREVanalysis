#PBS –N NREVing-A1
#PBS –q UCTlong
#PBS –l nodes=4:ppn=2:series600
#PBS –M chris.crisur@gmail.com
#PBS –m ae
cd NREVanalysis
mpirun -hostfile $PBS_NODEFILE java Main false true -o /home/chris.currin/testA1/output.txt
