1. Organize folders:
rm -rf target; rm -rf lib; mkdir lib; mkdir target
cp -r src/resources target/resources

2. Download libraries
curl -o lib/jcommander.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar
curl -o lib/JCDP-2.0.3.1.jar https://repo1.maven.org/maven2/com/diogonunes/JCDP/2.0.3.1/JCDP-2.0.3.1.jar

3. Extract libraries
jar -xf lib/jcommander.jar
jar -xf lib/JCDP-2.0.3.1.jar
mv com/ target/com; rm -rf META-INF/

4. Compile a program
javac -d target/ -cp lib/jcommander.jar:lib/JCDP-2.0.3.1.jar: src/java/edu.school21.printer/app/Main.java src/java/edu.school21.printer/logic/ImageConverter.java src/java/edu.school21.printer/logic/ArgsParsing.java
jar cmfv src/manifest.txt target/images-to-char.jar -C target ./

5. Run a program
java -jar target/images-to-char.jar --white=RED --black=BLACK --path=target/resources/image.bmp
