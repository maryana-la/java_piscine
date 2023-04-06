Compile:
javac -d target src/java/edu.school21.printer/app/Main.java src/java/edu.school21.printer/logic/ImageConverter.java
cp -r src/resources target/resources
jar cmvf src/manifest.txt target/images-to-char.jar -C target ./edu

Run:
java -jar target/images-to-char.jar --white=. --black=e --path=target/resources/small.bmp
