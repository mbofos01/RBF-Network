# !/bin/bash

pip3 install matplotlib > /dev/null 2>&1
dos2unix $1 > /dev/null 2>&1
echo "Running RBF for Selwood Dataset"
echo "parameters file: "$1

javac *.java
java RBF $1


rm *.class