package encryptAndSafety.hmacAlgorithm;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

// Hmac算法
// 小结
// Hmac算法是一种标准的基于密钥的哈希算法，可以配合MD5、SHA-1等哈希算法，计算的摘要长度和原摘要算法长度相同。
public class Main {
    public static void main(String[] args) throws Exception {
        // 在前面讲到哈希算法时，我们说，存储用户的哈希口令时，要加盐存储，目的就在于抵御彩虹表攻击。
        //
        // 我们回顾一下哈希算法：
        //
        // digest = hash(input)
        // 正是因为相同的输入会产生相同的输出，我们加盐的目的就在于，使得输入有所变化：
        //
        // digest = hash(salt + input)
        // 这个salt可以看作是一个额外的“认证码”，同样的输入，不同的认证码，会产生不同的输出。因此，要验证输出的哈希，必须同时提供“认证码”。

        // Hmac算法就是一种基于密钥的消息认证码算法，它的全称是Hash-based Message Authentication Code，是一种更安全的消息摘要算法。
        //
        // Hmac算法总是和某种哈希算法配合起来用的。例如，我们使用MD5算法，对应的就是HmacMD5算法，它相当于“加盐”的MD5：
        //
        // HmacMD5 ≈ md5(secure_random_key, input)

        // 因此，HmacMD5可以看作带有一个安全的key的MD5。使用HmacMD5而不是用MD5加salt，有如下好处：
        //
        // HmacMD5使用的key长度是64字节，更安全；
        // Hmac是标准算法，同样适用于SHA-1等其他哈希算法；
        // Hmac输出和原有的哈希算法长度一致。
        // 可见，Hmac本质上就是把key混入摘要的算法。验证此哈希时，除了原始的输入数据，还要提供key。

        // 为了保证安全，我们不会自己指定key，而是通过Java标准库的KeyGenerator生成一个安全的随机的key。下面是使用HmacMD5的代码：
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
        SecretKey key = keyGen.generateKey();
        // 打印随机生成的key:
        byte[] skey = key.getEncoded();
        System.out.println(new BigInteger(1, skey).toString(16));
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(key);
        mac.update("HelloWorld".getBytes(StandardCharsets.UTF_8));
        byte[] result = mac.doFinal();
        System.out.println(new BigInteger(1, result).toString(16));
        // 和MD5相比，使用HmacMD5的步骤是：
        //
        // 1、通过名称HmacMD5获取KeyGenerator实例；
        // 2、通过KeyGenerator创建一个SecretKey实例；
        // 3、通过名称HmacMD5获取Mac实例；
        // 4、用SecretKey初始化Mac实例；
        // 5、对Mac实例反复调用update(byte[])输入数据；
        // 6、调用Mac实例的doFinal()获取最终的哈希值。

        // 我们可以用Hmac算法取代原有的自定义的加盐算法，因此，存储用户名和口令的数据库结构如下：
        //
        // username	        secret_key (64 bytes)	    password
        // bob	            a8c06e05f92e...5e16	        7e0387872a57c85ef6dddbaa12f376de
        // alice	        e6a343693985...f4be	        c1f929ac2552642b302e739bc0cdbaac
        // tim	            f27a973dfdc0...6003	        af57651c3a8a73303515804d4af43790

        // 有了Hmac计算的哈希和SecretKey，我们想要验证怎么办？这时，SecretKey不能从KeyGenerator生成，而是从一个byte[]数组恢复：
        byte[] hkey = new byte[] { 106, 70, -110, 125, 39, -20, 52, 56, 85, 9, -19, -72, 52, -53, 52, -45, -6, 119, -63,
                30, 20, -83, -28, 77, 98, 109, -32, -76, 121, -106, 0, -74, -107, -114, -45, 104, -104, -8, 2, 121, 6,
                97, -18, -13, -63, -30, -125, -103, -80, -46, 113, -14, 68, 32, -46, 101, -116, -104, -81, -108, 122,
                89, -106, -109 };

        SecretKey key1 = new SecretKeySpec(hkey, "HmacMD5");
        Mac mac1 = Mac.getInstance("HmacMD5");
        mac1.init(key1);
        mac1.update("HelloWorld".getBytes(StandardCharsets.UTF_8));
        byte[] result1 = mac1.doFinal();
        System.out.println(Arrays.toString(result1));
        // [126, 59, 37, 63, 73, 90, 111, -96, -77, 15, 82, -74, 122, -55, -67, 54]
        // 恢复SecretKey的语句就是new SecretKeySpec(hkey, "HmacMD5")。
    }
}
