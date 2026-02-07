public class Series <T>{

     /** Row names of the series. */
     private String[] rowNames;
 
     /** The Integer array that contains the list of data that constitutes a Series object. */
     private T[] data;
 
     /**
      * Constructs a new Series object.
      *
      * @param _rowNames an array of row names
      * @param _data an array of Integer data
      */
     public Series(String[] _rowNames, T[] _data) {
         // 1. 检查 _data 是否为 null
         if(_data == null)
         {
             throw new NullPointerException(
                     "Series(String[] _index, T[] _data): _data can't be null. Terminating the program");
         }
         // 2. 拷贝 data 数组，不能用 new
         this.data = (T[]) new Object[_data.length];
         System.arraycopy(_data, 0, this.data, 0, _data.length);
         // 3. 处理 _rowNames
         try {
             // 检查长度是否匹配，如果在这里 _rowNames 是 null，
             // 系统试图读取它的 .length 属性，Java 自动抛出 NullPointerException。
             if (_rowNames.length != _data.length) {
                 throw new IllegalArgumentException("Series(String[] _index, T[] _data): the length of _index and _data must be the same");
             }
             // 初始化 _rowNames 数组
             this.rowNames = new String[_rowNames.length];
             // 检查 rowNames 是否含有 null
             for (int i = 0; i < _rowNames.length; i++) {
                 if (_rowNames[i] == null || _rowNames[i].isEmpty()) {
                     throw new IllegalArgumentException(
                             "Series(String[] _index, T[] _data): _rowNames is not valid"
                     );
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
     public void append(String rn, T d) {
         // 1. 计算新长度
         int oldLength = this.data.length;
         int newLength = oldLength + 1;

         // 2. 创建新数组
         String[] newRowNames = new String[newLength];
         T[] newData = (T[]) new Object[newLength];

         // 3. 搬运旧数据
         System.arraycopy(this.rowNames, 0, newRowNames, 0, oldLength);
         System.arraycopy(this.data, 0, newData, 0, oldLength);

         newRowNames[oldLength] = rn;
         newData[oldLength] = d;

         this.rowNames = newRowNames;
         this.data = newData;
     }
 
     /**
      * Retrieves a data value given a row name.
      *
      * @param rn the row name to search for
      */
     public T loc(String rn) throws NullPointerException, IllegalArgumentException{
          if(rn == null)
          {
               throw new NullPointerException("loc(String rn): rn can't be null");
          }
          // 2. 检查字符串是否为空
          if(rn.isEmpty())
          {
               throw new IllegalArgumentException("loc(String rn): rn can't be an empty string");
          }
          // 3. 遍历 rowNames 进行查找
          for(int i = 0;i < this.rowNames.length;i++)
          {
               if(this.rowNames[i].equals(rn))
               {
                    return data[i];
               }
          }
          // 4. 没找到返回 null
          return null;
     }
 
     /**
      * Retrieves multiple data values given an array of row names.
      *
      * @param rn an array of row names to search for
      */
     public T[] loc(String[] rn) throws NullPointerException, IllegalArgumentException{
          // 1. 检查输入是否为 null
          if(rn == null)
          {
               throw new NullPointerException("loc(String[] rn): rn[] can't be null");
          }
          // 2. 检查数组是否为空
          if(rn.length == 0)
          {
               throw new IllegalArgumentException("loc(String[] rn): rn[] can't be an empty array");
          }
          // 3. 创建存放数组
          T[] results = (T[]) new Object[rn.length];
          for(int i = 0;i < results.length;i++)
          {
               // 调用单个 loc 的方法
               results[i] = loc(rn[i]);
          }
          return results;
     }
 
     /**
      * Retrieves a data value based on its integer index.
      *
      * @param ind the index of the data to retrieve
      */
     public Integer iloc(int ind) {
         try {
             // 直接访问数组，如果越界自动抛出异常
             return data[ind];
         } catch (ArrayIndexOutOfBoundsException e) {
             System.out.println("the index " + ind + " is not valid.. returning null");
             return null;
         }
     }
 
     /**
      * Removes a pair of rowname-data from the Series, given a row name.
      *
      * @param rn the row name of the pair to be removed
      */
     public boolean drop(String rn) throws NullPointerException, IllegalArgumentException{
         // 1. 检查 rn 是否为 null
         if (rn == null)
         {
             throw new NullPointerException("drop(String rn): rn can't be null");
         }
         // 2. 检查字符串是否为空
         if(rn.isEmpty())
         {
             throw new IllegalArgumentException("drop(String rn): rn can't be an empty String");
         }
         // 3. 寻找要删除的 index
         int indexToRemove = -1;
         for(int i = 0;i < this.rowNames.length;i++)
         {
             if(this.rowNames[i].equals(rn)) {
                 indexToRemove = i;
                 break; // 找到了
             }
         }
         // 4. 没找到返回 false
         if(indexToRemove == -1)
         {
             return false;
         }
         // 5. 找到了换新数组
         int newLength = rowNames.length - 1;
         String[] newRowNames = new String[newLength];
         Integer[] newData = new Integer[newLength];
         // 第一段：复制 [0] 到 [indexToRemove - 1] ，注意这里长度是 indexToRemove
         if(indexToRemove > 0)
         {
             System.arraycopy(this.rowNames, 0, newRowNames, 0, indexToRemove);
             System.arraycopy(this.data, 0, newData, 0, indexToRemove);
         }
         // 第二段：复制 [indexToRemove + 1] 到最后
         // 这里的长度计算是：总长度 - 前段数组的长度 indexToRemove - 1
         int elementsAfter = this.rowNames.length - indexToRemove - 1;
         if(elementsAfter > 0)
         {
             System.arraycopy(this.rowNames, indexToRemove + 1 , newRowNames, indexToRemove, elementsAfter);
             System.arraycopy(this.data, indexToRemove + 1 , newData, indexToRemove, elementsAfter);
         }
         this.rowNames = newRowNames;
         this.data = newData;
         return true;
     }
 
     /**
      * Replace any data value that is null with value.
      *
      * @param value the new value to replace null values
      */
     public void fillNull(Integer value) throws IllegalArgumentException{
         // 1. 检查输入是否为 null
         if(value == null)
         {
             throw new IllegalArgumentException("fillNull(T value): value can't be null");
         }
         // 2. 进行遍历替换
         for(int i = 0;i < data.length;i++)
         {
             if(data[i] == null)
             {
                data[i] = value;
             }
         }
     }
 
     /**
      * Replace any data value that is null with the mean of the Series.
      *
      */
     public void fillNullWithMean() throws IllegalArgumentException{
         Integer meanVal = null;
         try{
             // 1. 尝试计算平均值
             meanVal = Tool.mean(this);
         } catch (Exception e) {
             // 2. 出现任何问题平均值为 null
             meanVal = null;
         }
         this.fillNull(meanVal); // meanVal 为 null 时，会抛 fillNull(T value)
     }

    public static void main(String[] args) {
        String[] names = {"a", "b", "c"};
        Integer[] values = {10, 20, 30};

        Series s = new Series(names, values);

        System.out.println(s);
    }

 }