Compile:
javac -d target src/java/edu/school21/printer/app/Main.java src/java/edu/school21/printer/logic/ImageConverter.java

Run a program:
java -classpath ./target edu.school21.printer.app.Main --white=. --black=# --path="image.bmp"
--white - symbol to print for white colour
--black - symbol to print for black colour
--path - full path to the image
