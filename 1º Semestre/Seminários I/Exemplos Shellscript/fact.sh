FACT=1
for i in $(seq 1 $1)
do
  FACT=$(($FACT*$i))
done
echo $FACT
