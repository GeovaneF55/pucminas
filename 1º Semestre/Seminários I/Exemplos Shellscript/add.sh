SUM=$(($1+$2))
echo 'SUM:' $SUM

SUM=$(expr $1 + $2)
echo 'SUM:' $SUM
