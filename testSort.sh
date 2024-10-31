#!/bin/bash

#Assumes all files are compiled
test() {
    local testNumber="$1" #first input is number of Output(n).txt
    local testSort="$2" #second input is the compiled sort algorithm you want to test
    local inputfile="Output${testNumber}.txt"

    echo "input file: ${inputfile}"
}
test "$@"