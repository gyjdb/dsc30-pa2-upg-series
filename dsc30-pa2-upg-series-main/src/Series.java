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
         // 1. 检查 _data 是否为 null
         if(_data == null)
         {
             throw new NullPointerException("Series(String[] _index, Integer[] _data): _data can't be null. Terminating the program");
         }
         // 2. 深拷贝 data 数组
         this.data = new Integer[_data.length];
         for (int i = 0; i < _data.length; i++)
         {
            this.data[i] = _data[i];
         }
         // 3. 处理 _rowNames
         try {
             // 检查长度是否匹配
             if (_rowNames.length != _data.length) {
                 throw new IllegalArgumentException("Series(String[] _index, Integer[] _data): the length of _index and _data must be the same");
             }
             // 初始化 _rowNames 数组
             this.rowNames = new String[_rowNames.length];
             // 检查 rowNames 是否含有 null
             for (int i = 0; i < _rowNames.length; i++) {
                 if (_rowNames[i] == null) {
                     throw new IllegalArgumentException("Series(String[] _index, Integer[] _data): _rowNames is not valid");
                 }
                 this.rowNames[i] = _rowNames[i];
             }
         } catch(NullPointerException e)
             // 4. 捕获 _rowNames 为 null 的情况
             {
                 this.rowNames = new String[_data.length];
                 for (int i = 0; i < _data.length; i++) {
                     this.rowNames[i] = String.valueOf(i);
                 }
             }
         }
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