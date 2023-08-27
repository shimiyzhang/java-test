package encryptAndSafety.bouncyCastle;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.Security;

// BouncyCastle
// 小结
// BouncyCastle是一个开源的第三方算法提供商；
//
// BouncyCastle提供了很多Java标准库没有提供的哈希算法和加密算法；
//
// 使用第三方算法前需要通过Security.addProvider()注册。
public class Main {
    public static void main(String[] args) throws Exception {
        // 我们知道，Java标准库提供了一系列常用的哈希算法。
        //
        // 但如果我们要用的某种算法，Java标准库没有提供怎么办？
        //
        // 方法一：自己写一个，难度很大；
        //
        // 方法二：找一个现成的第三方库，直接使用。
        //
        // BouncyCastle就是一个提供了很多哈希算法和加密算法的第三方库。它提供了Java标准库没有的一些算法，例如，RipeMD160哈希算法。
        //
        // 我们来看一下如何使用BouncyCastle这个第三方提供的算法。

        // 首先，我们必须把BouncyCastle提供的jar包放到classpath中。这个jar包就是bcprov-jdk18on-xxx.jar，可以从官方网站下载。
        //
        // Java标准库的java.security包提供了一种标准机制，允许第三方提供商无缝接入。我们要使用BouncyCastle提供的RipeMD160算法，需要先把BouncyCastle注册一下：
        // 注册BouncyCastle:
        Security.addProvider(new BouncyCastleProvider());
        // 按名称正常调用:
        MessageDigest md = MessageDigest.getInstance("RipeMD160");
        md.update("HelloWorld".getBytes(StandardCharsets.UTF_8));
        byte[] result = md.digest();
        System.out.println(new BigInteger(1, result).toString(16));
        // 其中，注册BouncyCastle是通过下面的语句实现的：
        // Security.addProvider(new BouncyCastleProvider());
        // 注册只需要在启动时进行一次，后续就可以使用BouncyCastle提供的所有哈希算法和加密算法。
    }
}
