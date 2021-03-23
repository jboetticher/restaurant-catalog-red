run: compile
	java FrontEndDeveloper

compile:
	javac SortedCollectionInterface.java RestaurantInterface.java Restaurant.java DataReaderInterface.java DataReader.java BackendInterface.java Backend.java FrontEndDeveloperInterface.java FrontEndDeveloper.java

test: testData testBackend testFrontend

testFrontend:
	@echo "FIXME: *make testFrontend* should compile (when needed) and run all your team's tests for this application"

testBackend:
	@echo "FIXME: *make testFrontend* should compile (when needed) and run all your team's tests for this application"

testData:
	@echo "FIXME: *make testFrontend* should compile (when needed) and run all your team's tests for this application"

clean:
	$(RM) *.class
