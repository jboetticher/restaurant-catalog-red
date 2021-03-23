run: compile
	java FrontEndDeveloper

compile:
	javac SortedCollectionInterface.java RedBlackTree.java RestaurantInterface.java Restaurant.java DataReaderInterface.java DataReader.java BackendInterface.java Backend.java FrontEndDeveloper.java

test: testData testBackend testFrontend

testFrontend: compile
	@echo "FIXME: *make testFrontend* should compile (when needed) and run all your team's tests for this application"

testBackend: compile
	@echo "FIXME: *make testFrontend* should compile (when needed) and run all your team's tests for this application"

testData: compile
	@echo "FIXME: *make testFrontend* should compile (when needed) and run all your team's tests for this application"

clean:
	$(RM) *.class
