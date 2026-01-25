public class Tool {

    /**
     * Calculates the mean of a Series object.
     *
     * @param d the Series object to calculate the mean from
     */
    public static Integer mean(Series d) throws NullPointerException, IllegalArgumentException, ArithmeticException{
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
            Integer val = d.iloc(i);
            if (val != null) {
                sum += val;
                count++;
            }
        }
        if (count == 0) {
            // 如果 Series 元素全是 null
            throw new ArithmeticException("mean (Series d): d can't be empty");
        }
        // 5. 计算平均值
        return sum / count;
    }

    /**
     * Finds the maximum value in a Series object.
     *
     * @param d the Series object to find the maximum value from
     */
    public static Integer max(Series d) throws NullPointerException, IllegalArgumentException, ArithmeticException{
        // 1. 检查输入是否为 null
        if (d == null) {
            throw new NullPointerException("max (Series d): d can't be null");
        }

        // 2. 检查 Series 是否为空
        if (d.getLength() == 0) {
            throw new IllegalArgumentException("max (Series d): d can't be an empty Series");
        }

        Integer maxVal = null;

        for (int i = 0; i < d.getLength(); i++) {
            try {
                Integer val = d.iloc(i);
                if (maxVal == null || val > maxVal) {
                    maxVal = val;
                }
            } catch (NullPointerException e) {
                // 跳过 null
            }
        }

        if (maxVal == null) {
            // 全是 null 的情况，抛出 IllegalArgumentException
            throw new ArithmeticException("max (Series d): d can't be empty");
        }
        return maxVal;
    }
}
