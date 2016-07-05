#PBS -N NREVing-A1
#PBS -q UCTlong
#PBS -l nodes=1:ppn=1:series600
cd NREVanalysis
java Main false true
