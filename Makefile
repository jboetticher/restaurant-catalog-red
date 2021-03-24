run: compile
	java FrontEndDeveloper

compile: RedBlackTree.class Restaurant.class DataReader.class Backend.class FrontEndDeveloper.class

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
	java -jar junit5.jar -cp . --scan-classpath

testFrontend: compile
	# Jaan didn't make his tests in junit, so it can't be compiled with junit.

testBackend: compile BackEndDeveloperTests.java
	javac -cp .:junit5.jar BackEndDeveloperTests.java

testData: compile DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java

clean:
	$(RM) *.class
