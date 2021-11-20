# !/bin/bash

echo "Running RBF for Selwood Dataset"
echo "parameters file: "$1

javac *.java
java RBF $1


rm *.class