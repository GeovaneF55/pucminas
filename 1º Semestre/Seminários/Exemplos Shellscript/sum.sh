SUM=0
for VAR in $*; do
   SUM=$(($SUM+$VAR))
done
echo 'SUM:' $SUM
