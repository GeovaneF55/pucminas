
MAX=$1
for VAR in $*; do
   if [ $VAR -gt $MAX ]; then
      MAX=$VAR
   fi
done
echo $MAX
