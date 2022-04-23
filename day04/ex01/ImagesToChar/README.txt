# deleting 'target' directory
rm -rf target

# creating 'target' directory
mkdir target

# compiling all java files and moving .class files into the 'target'
javac src/java/edu/school21/printer/*/*.java -d ./target

# copying resources folder
cp -R src/resources target/.

# creating jar file int 'target' folder
jar cfm ./target/images-to-char-printer.jar src/manifest.txt -C target .
chmod u+x target/images-to-char-printer.jar

# launch program with args - white char, black char
java -classpath target edu.school21.printer.app.Program . 0
