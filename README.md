# Team-Git-Good-CSCI3501-Sorting-Competition

## Data Generation

### Prompts for Output files

`java DataGenerator Outputfile number-of-lines rate-parameter`

#### For Outputs Files 1-6

* Output1.txt 100000 .1
* Output2.txt 100000 .001
* Output3.txt 500000 .1
* Output4.txt 500000 .001
* Output5.txt 1000000 .1
* Output6.txt 1000000 .001

## Testing the sorting algorithm

* Compile the sorting competition file using `javac sortingfile`
* Run the compiled code using `java sortingfile input-filename output-filename`

### Group0 as Example

#### Compile the Java File

`javac Group0`

#### Run the file

`java Group0 output.txt sortedoutput.txt`

## Automatic Testing

The repository includes a script for testing a sort and whether or not it sorted correctly!

In order to use it use
`./testSort.sh test-number sortingfile`