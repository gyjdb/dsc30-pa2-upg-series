public class Series {

     /** Row names of the series. */
     private String[] rowNames;
 
     /** The Integer array that contains the list of data that constitutes a Series object. */
     private Integer[] data;
 
     /**
      * Constructs a new Series object.
      *
      * @param _rowNames an array of row names
      * @param _data an array of Integer data
      */
     public Series(String[] _rowNames, Integer[] _data) {
         // TODO: Implement constructor
     }
 
     /**
      * Returns a string representation of the Series object.
      */
     public String toString() {
         // TODO: Implement toString method
         return "";
     }
 
     /**
      * Returns the length of the series object.
      */
     public int getLength() {
         // TODO: Implement getLength method
         return 0;
     }
 
     /**
      * Returns the row names of this Series object.
      */
     public String[] getRowNames() {
         // TODO: Implement getRowNames method
         return new String[0];
     }
 
     /**
      * Returns the data of this Series object as strings.
      */
     public String[] getData() {
         // TODO: Implement getData method
         return new String[0];
     }
 
     /**
      * Adds a new pair of rowName and data at the end of the Series object.
      *
      * @param rn the row name to be added
      * @param d the Integer data value to be added
      */
     public void append(String rn, Integer d) {
         // TODO: Implement append method
     }
 
     /**
      * Retrieves a data value given a row name.
      *
      * @param rn the row name to search for
      */
     public Integer loc(String rn) {
         // TODO: Implement loc method
         return null;
     }
 
     /**
      * Retrieves multiple data values given an array of row names.
      *
      * @param rn an array of row names to search for
      */
     public Integer[] loc(String[] rn) {
         // TODO: Implement loc method for multiple row names
         return new Integer[0];
     }
 
     /**
      * Retrieves a data value based on its integer index.
      *
      * @param ind the index of the data to retrieve
      */
     public Integer iloc(int ind) {
         // TODO: Implement iloc method
         return null;
     }
 
     /**
      * Removes a pair of rowname-data from the Series, given a row name.
      *
      * @param rn the row name of the pair to be removed
      */
     public boolean drop(String rn) {
         // TODO: Implement drop method
         return false;
     }
 
     /**
      * Replace any data value that is null with value.
      *
      * @param value the new value to replace null values
      */
     public void fillNull(Integer value) {
         // TODO: Implement fillNull method
     }
 
     /**
      * Replace any data value that is null with the mean of the Series.
      *
      */
     public void fillNullWithMean() {
         // TODO: Implement fillNullWithMean method
         // Handle ArithmeticException that could be raised from mean()
     }
 }