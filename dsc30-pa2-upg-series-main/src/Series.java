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
         System.arraycopy(_data, 0, this.data, 0, _data.length);
         // 3. 处理 _rowNames
         try {
             // 检查长度是否匹配，如果在这里 _rowNames 是 null，
             // 系统试图读取它的 .length 属性，Java 自动抛出 NullPointerException。
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
         {
             // 4. 捕获 _rowNames 为 null 的情况 ：用 "0","1","2"... 自动生成
             this.rowNames = new String[_data.length];
             for (int i = 0; i < _data.length; i++) {
                 this.rowNames[i] = String.valueOf(i);
             }
         }
     }

     /**
      * Returns a string representation of the Series object.
      */
     public String toString() {
         StringBuilder result = new StringBuilder();
         result.append("Printing Series...\n\n");
         for(int i = 0;i < data.length;i++)
         {
             result.append(rowNames[i]).append("\t").append(data[i]).append("\n");
         }
         return result.toString();
     }
 
     /**
      * Returns the length of the series object.
      */
     public int getLength() { return data.length; }
 
     /**
      * Returns the row names of this Series object.
      */
     public String[] getRowNames() {
         String[] copy = new String[rowNames.length];
         System.arraycopy(rowNames, 0, copy, 0, rowNames.length);
         return copy;
     }
 
     /**
      * Returns the data of this Series object as strings.
      */
     public String[] getData() {
         String[] copy = new String[data.length];
         for (int i = 0; i < data.length; i++)
         {
             copy[i] = String.valueOf(data[i]);
         }
         return copy;
     }
 
     /**
      * Adds a new pair of rowName and data at the end of the Series object.
      *
      * @param rn the row name to be added
      * @param d the Integer data value to be added
      */
     public void append(String rn, Integer d) {
         // 1. 计算新长度
         int oldLength = this.data.length;
         int newLength = oldLength + 1;

         // 2. 创建新数组
         String[] newRowNames = new String[newLength];
         Integer[] newData = new Integer[newLength];

         // 3. 搬运旧数据
         System.arraycopy(this.rowNames, 0, newRowNames, 0, oldLength);
         System.arraycopy(this.data, 0, newData, 0, oldLength);

         // 4. 处理新数据的 rowNames
         if (rn == null)
         {
             newRowNames[oldLength] = String.valueOf(oldLength);
         }
         else
         {
             newRowNames[oldLength] = rn;
         }

         // 5. 填入新的 data
         newData[oldLength] = d;

         // 6. 更新引用
         this.rowNames = newRowNames;
         this.data = newData;
     }
 
     /**
      * Retrieves a data value given a row name.
      *
      * @param rn the row name to search for
      */
     public Integer loc(String rn) {
         return 0;
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

    public static void main(String[] args) {
        String[] names = {"a", "b", "c"};
        Integer[] values = {10, 20, 30};

        Series s = new Series(names, values);

        System.out.println(s);
    }

 }