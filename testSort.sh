#!/bin/bash

# Assumes all files are compiled and data is prepared
test() {
    local testNumber="$1" #first input is number of Output(n).txt
    local testSort="$2" #second input is the compiled sort algorithm you want to test
    local inputfile="Output${testNumber}.txt"
    local sortedoutput="Sorted${testNumber}.txt"
    local correctoutput="Sorted${testNumber}Correct.txt"
    local test="test.txt"
    touch "${sortedoutput}"
    touch "${correctoutput}"

    echo "input file: ${inputfile}"
    echo "${test}"

    # Run Group0 for correct Output
    echo "Group 0"
    java Group0 "${inputfile}" "${correctoutput}" # >> Group0Time.txt

    # Run input sort
    echo "$2"
    java "$2" "${inputfile}" "${sortedoutput}" # >> TestedSortTime.txt


    # diff ${sortedoutput} ${correctoutput}  >> diff.txt
    $(diff ${sortedoutput} ${correctoutput})
        if [ $? -eq 0 ]
            then 
                echo "Files are equal!"
            else
                echo "Files are different!"
            fi
}
test "$@"