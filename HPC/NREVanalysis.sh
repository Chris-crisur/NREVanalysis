#PBS –N NREVing-A1
#PBS –q UCTlong
#PBS –l nodes=1:ppn=1:series600
#PBS –M chris.crisur@gmail.com
#PBS –m ae
cd NREVanalysis
java Main false true
