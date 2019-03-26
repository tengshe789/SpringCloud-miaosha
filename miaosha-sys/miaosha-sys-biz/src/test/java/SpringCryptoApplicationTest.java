import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tech.tengshe789.miaosha.sys.biz.MiaoshaSysBootstap;

/**
 * @program: -miaosha
 * @description: 加解密单元测试
 * @author: tEngSHe789
 * @create: 2019-03-26 10:58
 **/
public class SpringCryptoApplicationTest {
	@RunWith(SpringJUnit4ClassRunner.class)
	@SpringBootTest(classes = MiaoshaSysBootstap.class)
	public class PigAdminApplicationTest {
		@Autowired
		private StringEncryptor stringEncryptor;

		@Test
		public void testEnvironmentProperties() {
			System.out.println(stringEncryptor.encrypt("app"));
		}

	}
}
