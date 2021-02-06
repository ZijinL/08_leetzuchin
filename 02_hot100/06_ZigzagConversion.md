# 0006 Z字形变换

https://leetcode-cn.com/problems/zigzag-conversion/

将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：

```
P   A   H   N
A P L S I I G
Y   I   R
```


之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。

请你实现这个将字符串进行指定行数变换的函数：

```java
String convert(string s, int numRows);
```

## 数组模拟

按照题意，用一个`StringBuilder`组成的数组存储各行的值，挨个字符读取，然后放在指定位置；注意到了端点需要换向，最后统一输出；

时间复杂度：$O(n)$ ；遍历长度为`n`的字符串

空间复杂度：$O(n)$ ；额外需要大小为`n`的空间

```java
class Solution {
    public String convert(String s, int numRows) {

        if (numRows == 1)
            return s;
        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        boolean direction = true;
        int currentline = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            rows.get(currentline).append(s.charAt(i));

            if (currentline == 0) {
                direction = true;
            } else if (currentline == numRows - 1) {
                direction = false;
            }

            if (direction) {
                currentline += 1;
            } else {
                currentline -= 1;
            }
        }

        for (int i = 1; i < numRows; i++) {
            rows.get(0).append(rows.get(i));
        }

        return rows.get(0).toString();

    }
}
```

## 数学递推

根据定义，计算得到各个字符的位置；然后从第一行开始遍历，直到最后一行；数学递推的难点在于两端的情况是特殊的需要单独讨论，而且如何表达周期性也是一个难点；

这里采用了两重循环的方法，第一层循环是竖着走，第二层循环是横着走；还需要保证斜着的也能够遍历到；

* 时间复杂度：$O(n)$ ；虽然是多重循环，但是本质上，每个字符都只访问了一次；
* 空间复杂度：$O(n)$ ；所需要的额外空间也是`n`个字符的大小。

```java
class Solution {
    public String convert(String s, int numRows) {

        if (numRows == 1)
            return s;
        StringBuilder res = new StringBuilder();

        int n = s.length();
        int circle = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; i + j < n; j += circle) {
                res.append(s.charAt(i + j));
                if (i != 0 && i != numRows - 1 && j - i + circle < n) {
                    res.append(s.charAt(j - i + circle));
                }
            }
        }
        return res.toString();
    }
}
```

