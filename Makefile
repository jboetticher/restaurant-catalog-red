run: compile
	java FrontEndDeveloper

compile: RedBlackTree.class Restaurant.class DataReader.class Backend.class FrontEndDeveloper.class
	javac -cp .:junit5.jar

RedBlackTree.class: SortedCollectionInterface.java RedBlackTree.java
	javac SortedCollectionInterface.java RedBlackTree.java

Restaurant.class: RestaurantInterface.java Restaurant.java
	javac RestaurantInterface.java Restaurant.java

DataReader.class: DataReaderInterface.java DataReader.java
	javac DataReaderInterface.java DataReader.java

Backend.class: BackendInterface.java Backend.java
	javac BackendInterface.java Backend.java

FrontEndDeveloper.class: FrontEndDeveloper.java
	javac FrontEndDeveloper.java

test: testData testBackend testFrontend

testFrontend: compile
	java -jar junit5.jar -cp . --scan-classpath -n TestFrontend.java

testBackend: compile
	java -jar junit5.jar -cp . --scan-classpath -n TestBackend.java

testData: compile
	java -jar junit5.jar -cp . --scan-classpath -n DataWranglerTests.java

clean:
	$(RM) *.class
