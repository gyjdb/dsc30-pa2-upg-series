public class Tool {

    /**
     * Calculates the mean of a Series object.
     *
     * @param d the Series object to calculate the mean from
     */
    public static Integer mean(Series d) {
        // 1. 检查输入是否为 null
        if (d == null) {
            throw new NullPointerException("mean (Series d): d can't be null");
        }

        // 2. 检查 Series 是否为空
        if (d.getLength() == 0) {
            throw new IllegalArgumentException("mean (Series d): d can't be an empty Series");
        }

        int sum = 0;
        int count = 0;

        for (int i = 0; i < d.getLength(); i++) {
            try {
                // 3. 累加求和
                int val = d.iloc(i);
                sum += val;
                count++;
            } catch (NullPointerException e) {
                // 4. 捕获 NPE 并跳过该值
            }
        }

        // 5. 计算平均值
        // 如果 count 为 0，这里会自动抛出 ArithmeticException
        return sum / count;
    }

    /**
     * Finds the maximum value in a Series object.
     *
     * @param d the Series object to find the maximum value from
     */
    public static Integer max(Series d) {
        // 1. 检查输入是否为 null
        if (d == null) {
            throw new NullPointerException("max (Series d): d can't be null");
        }

        // 2. 检查 Series 是否为空
        if (d.getLength() == 0) {
            throw new IllegalArgumentException("max (Series d): d can't be an empty Series");
        }

        Integer maxVal = null;
        boolean hasValidData = false;

        for (int i = 0; i < d.getLength(); i++) {
            try {
                // 3. 获取数据
                // 同样利用拆箱触发 NPE 检查
                int val = d.iloc(i);

                // 4. 更新最大值
                if (maxVal == null || val > maxVal) {
                    maxVal = val;
                }
                hasValidData = true;
            } catch (NullPointerException e) {
                // 5. 捕获 NPE 并跳过
            }
        }

        // 6. 如果全是 null，显式抛出 ArithmeticException
        if (!hasValidData) {
            throw new ArithmeticException();
        }

        return maxVal;
    }
}
