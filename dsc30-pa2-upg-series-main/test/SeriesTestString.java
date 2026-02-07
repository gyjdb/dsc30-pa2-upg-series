import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class SeriesTestString {

    private Series<String> s;

    @BeforeEach
    public void setup() {
        s = new Series<>(
                new String[]{"a", "b", "c"},
                new String[]{"x", null, "z"}
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
        // String.valueOf("x") -> "x", String.valueOf(null) -> "null"
        assertArrayEquals(new String[]{"x", "null", "z"}, s.getData());
    }

    @Test
    public void appendTest() {
        int oldLen = s.getLength();
        s.append("d", "hello");

        assertEquals(oldLen + 1, s.getLength());
        assertEquals("hello", s.loc("d"));
    }

    @Test
    public void locTest() {
        // 正常：存在
        assertEquals("x", s.loc("a"));
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
        String[] out = s.loc(new String[]{"c", "zzz", "a"});
        assertArrayEquals(new String[]{"z", null, "x"}, out);

        // 异常：null
        assertThrows(NullPointerException.class, () -> s.loc((String[]) null));
        // 异常：空数组
        assertThrows(IllegalArgumentException.class, () -> s.loc(new String[]{}));
    }

    @Test
    public void ilocTest() {
        // 正常
        assertEquals("x", s.iloc(0));
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
        s.fillNull("mid");
        assertArrayEquals(new String[]{"x", "mid", "z"}, s.getData());

        // 异常：value 为 null
        assertThrows(IllegalArgumentException.class, () -> s.fillNull(null));
    }
}