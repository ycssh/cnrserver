package cn.cnr.admin.base.redis;

import org.apache.shiro.cache.Cache;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

	public static void main(String[] args) {
		RedisCacheManager rcm = new RedisCacheManager();
		Cache<String, AtomicInteger> cache = rcm.getCache("passwordRetryCache");
		cache.put("ycssh", new AtomicInteger(100));
		System.out.println(cache.get("ycssh"));
	}

}
