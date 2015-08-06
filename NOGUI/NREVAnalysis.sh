#PBS -q UCTlong
#PBS -l nodes=1:ppn=32:series600
#PBS –M <emailaddress>
#PBS –m abe (abort, begin, end – respectively)
#PBS -N NREVAnalysis-A1
java -o /home/chris.currin/testA1/output.txt