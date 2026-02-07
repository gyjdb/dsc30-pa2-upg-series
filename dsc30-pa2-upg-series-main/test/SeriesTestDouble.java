import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SeriesTestDouble {

    private Series<Double> s;

    @BeforeEach
    public void setup() {
        s = new Series<>(
                new String[]{"a", "b", "c"},
                new Double[]{1.5, null, 3.0}
        );
        System.out.println(s);
    }

    @AfterEach
    public void tearDown() {
        System.out.println("===========");
        System.out.println(s);
    }

    @Test
    public void getDataTest() {
        // 正常：转字符串
        assertArrayEquals(new String[]{"1.5", "null", "3.0"}, s.getData());
    }

    @Test
    public void appendTest() {
        int oldLen = s.getLength();
        s.append("d", 4.0);

        assertEquals(oldLen + 1, s.getLength());
        assertEquals(4.0, s.loc("d"));
    }

    @Test
    public void locTest() {
        // 正常：存在
        assertEquals(1.5, s.loc("a"));
        // 正常：不存在 -> null
        assertNull(s.loc("zzz"));

        // 异常：null
        assertThrows(NullPointerException.class, () -> s.loc((String) null));
        // 异常：空串
        assertThrows(IllegalArgumentException.class, () -> s.loc(""));
    }

    @Test
    public void locArrayTest() {
        // 正常：混合存在/不存在
        Double[] out = s.loc(new String[]{"c", "zzz", "a"});
        assertArrayEquals(new Double[]{3.0, null, 1.5}, out);

        // 异常：null
        assertThrows(NullPointerException.class, () -> s.loc((String[]) null));
        // 异常：空数组
        assertThrows(IllegalArgumentException.class, () -> s.loc(new String[]{}));
    }

    @Test
    public void ilocTest() {
        // 正常
        assertEquals(1.5, s.iloc(0));
        // 越界 -> null
        assertNull(s.iloc(999));
        assertNull(s.iloc(-1));
    }

    @Test
    public void dropTest() {
        // 删除存在的
        assertTrue(s.drop("a"));
        assertNull(s.loc("a"));

        // 删除不存在的 -> false
        assertFalse(s.drop("zzz"));

        // 异常
        assertThrows(NullPointerException.class, () -> s.drop(null));
        assertThrows(IllegalArgumentException.class, () -> s.drop(""));
    }

    @Test
    public void fillNullTest() {
        // 正常：把 null 替换成给定值
        s.fillNull(2.0);
        assertArrayEquals(new String[]{"1.5", "2.0", "3.0"}, s.getData());

        // 异常：value 为 null
        assertThrows(IllegalArgumentException.class, () -> s.fillNull(null));
    }
}