package xyz.ganbug.flashsale.service.impl;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import xyz.ganbug.flashsale.service.PurchaseService;

import java.util.Arrays;
import java.util.Objects;

@Service
public class RedisPurchaseServiceImpl implements PurchaseService {
    private final StringRedisTemplate redisTemplate;

    private final String script =
            "redis.call('sadd', KEYS[1], ARGV[2])\n" +
                    "local productPurchaseList = KEYS[2]..ARGV[2]\n" +
                    "local userId = ARGV[1]\n" +
                    "local product = 'product_'..ARGV[2]\n" +
                    "local quantity = tonumber(ARGV[3])\n" +
                    "local stock = tonumber(redis.call('hget', product, 'stock'))\n" +
                    "local price = tonumber(redis.call('hget', product, 'price'))\n" +
                    "local purchase_date = ARGV[4]\n" +
                    "if stock < quantity then return 0 end\n" +
                    "stock = stock - quantity\n" +
                    "redis.call('hset', product, 'stock', tostring(stock))\n" +
                    "local sum = price * quantity\n" +
                    "local purchaseRecord = userId..','..quantity..','..sum..','..price..','..purchase_date\n" +
                    "redis.call('rpush', productPurchaseList, purchaseRecord)\n" +
                    "return 1";

    private final RedisScript<Long> scriptObj = new DefaultRedisScript<>(script, Long.class);

    private static final String PURCHASE_PRODUCT_LIST = "purchase_list_";

    private static final String PRODUCT_SCHEDULE_SET = "product_schedule_set";

    public RedisPurchaseServiceImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean purchase(Long userId, Long productId, int quantity) {
        Long result = redisTemplate.execute(scriptObj, Arrays.asList(PRODUCT_SCHEDULE_SET, PURCHASE_PRODUCT_LIST), userId + "",
                productId + "", quantity + "", System.currentTimeMillis() + "");
        Objects.requireNonNull(result);
        return result == 1;
    }
}
