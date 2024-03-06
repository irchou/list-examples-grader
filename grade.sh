CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

git clone $1 student-submission 2> git-output.txt
echo 'Finished cloning'

if [[ -f student-submission/ListExamples.java ]]
then
  cp student-submission/*.java grading-area
  cp *.java grading-area
  cp -r lib grading-area
else
  echo "Missing ListExamples.java; check file naming or file location"
  echo "Score: 0"
  exit 1
fi

cd grading-area
javac -cp $CPATH *.java 

if [[ $? -ne 0 ]]
then
  echo "ListExamples.java doesn't compile; see error message above"
  echo "Score: 0"
  exit 1
fi

java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > test-output.txt
endLine=$(cat test-output.txt | tail -n 2 | head -n 1)

okCheck=$(echo $endLine | awk '{print $1}')
if [[ $okCheck == "OK" ]]
then
  echo "Score: 100"
  exit 0
fi

tests=$(echo $endLine | awk -F'[, ]' '{print $3}')
failures=$(echo $endLine | awk -F'[, ]' '{print $6}')
successes=$(( tests - failures ))

echo "Your score is $successes/$tests"

# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests
