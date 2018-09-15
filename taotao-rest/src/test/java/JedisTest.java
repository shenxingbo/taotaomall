import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;

public class JedisTest {
    @Test
    public void testJedisSingle() {
        Jedis jedis = new Jedis("192.168.56.7",6379);
        jedis.set("key1", "jedis test");
        String string =  jedis.get("key1");
        System.out.println(string);
        jedis.close();
    }


    //我们使用jedis连接池
    @Test
    public void testJedisPool() {
        JedisPool jedisPool = new JedisPool("192.168.56.7",6379);
        Jedis jedis = jedisPool.getResource();
        String string =  jedis.get("key1");
        System.out.println(string);
        jedis.close();
        jedisPool.close();
    }

    @Test
    public void testJedisCluster()  {
        HashSet<HostAndPort> nodes = new HashSet<HostAndPort>();
        nodes.add(new HostAndPort("192.168.56.7", 7001));
        nodes.add(new HostAndPort("192.168.56.7", 7002));
        nodes.add(new HostAndPort("192.168.56.7", 7003));
        nodes.add(new HostAndPort("192.168.56.7", 7004));
        nodes.add(new HostAndPort("192.168.56.7", 7005));
        nodes.add(new HostAndPort("192.168.56.7", 7006));
        JedisCluster cluster = new JedisCluster(nodes);
        cluster.set("key1", "1000");
        String str = cluster.get("key1");
        System.out.println(str);
        cluster.close();
    }

    @Test
    public void testSpringJedisSingle() {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-jedis.xml");
        JedisPool jedisPool = (JedisPool) applicationContext.getBean("redisClient");
        Jedis jedis = jedisPool.getResource();
        String string = jedis.get("key1");
        System.out.println(string);
        jedis.close();
        jedisPool.close();
    }

    @Test
    public void testSpringJedisCluster() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-jedis.xml");
        JedisCluster jedisCluster = (JedisCluster) applicationContext.getBean("redisClient");
        String string = jedisCluster.get("key1");
        System.out.println(string);
        jedisCluster.close();
    }
    @Test
    public void queryDocument()  throws Exception{
        SolrServer solrServer = new HttpSolrServer("http://192.168.56.8:8080/solr");
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setStart(20);
        query.setStart(50);
        QueryResponse response = solrServer.query(query);
        SolrDocumentList documentList  = response.getResults();
        System.out.println(documentList.getNumFound());
        for (SolrDocument document : documentList) {
            System.out.println(document.get("id"));
        }

    }
}
