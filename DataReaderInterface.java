// --== CS400 File Header Information ==--
// Name: <Kenneth Diao>
// Email: <kdiao2@wisc.edu>
// Team: <Red>
// Group: <KF>
// TA: <Keren Chen>
// Lecturer: <Gary Dahl>
// Notes to Grader: none

import java.util.List;
import java.util.zip.DataFormatException;
import java.io.IOException;
import java.io.Reader;

public interface DataReaderInterface {
		
	public List<RestaurantInterface> readDataSet(Reader inputFileReader) throws IOException, DataFormatException;

}
