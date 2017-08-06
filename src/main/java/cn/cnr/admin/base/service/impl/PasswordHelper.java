package cn.cnr.admin.base.service.impl;

import cn.cnr.admin.base.model.User;
import cn.cnr.admin.base.realm.AES;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PasswordHelper {

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    @Value("${password.algorithmName}")
    private String algorithmName = "md5";
    @Value("${password.hashIterations}")
    private int hashIterations = 2;

    public void setRandomNumberGenerator(RandomNumberGenerator randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public void setHashIterations(int hashIterations) {
        this.hashIterations = hashIterations;
    }

    public void encryptPassword(User user) {
    	if(user.getSalt()==null)
    		user.setSalt(randomNumberGenerator.nextBytes().toHex());
    	try {
			user.setPassword(AES.Encrypt(user.getPassword(), user.getCredentialsSalt().substring(user.getCredentialsSalt().length()-16)));
		} catch (Exception e) {
			e.printStackTrace();
		}
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();

        user.setPassword(newPassword);
    }

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("admin");
        user.setSalt("5fce761774f95b0f3756b426ca949815");
        user.setPassword("nls123456");
        try {
            user.setPassword(AES.Encrypt(user.getPassword(), user.getCredentialsSalt().substring(user.getCredentialsSalt().length()-16)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String newPassword = new SimpleHash(
                "md5",
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                2).toHex();
        System.out.println(newPassword);
    }
}
