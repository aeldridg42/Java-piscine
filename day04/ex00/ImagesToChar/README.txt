# deleting 'target' directory
rm -rf target

# creating 'target' directory
mkdir target

# compiling all java files and moving .class files into the 'target'
javac src/java/edu/school21/printer/*/*.java -d ./target

# launch program with args - white char, black char, path to the image
java -classpath target edu.school21.printer.app.Program . 0 ./../../ex01/ImagesToChar/src/resources/it.bmp

