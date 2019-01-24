package com.sweet.apple.util;

import com.sweet.apple.dto.QuotaResult;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author zhujialing
 * @Create 2018-09-25 下午3:31
 * @Description:
 */
@org.springframework.context.annotation.Configuration
public class HbaseTemplate {

    private Connection connection;

    public static Configuration getCon() {

        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum","mini-03,mini-04,mini-05");
        configuration.set("hbase.rootdir","hdfs://mini:8020/hbase");
        configuration.set("zookeeper.znode.parent","/hbase");
        return configuration;
    }

    public Connection getConnection() {

        if (null == connection) {
            synchronized (this) {
                if (null == connection) {
                    try {
                        ThreadPoolExecutor pool = new ThreadPoolExecutor(500,Integer.MAX_VALUE,1, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
                        connection = ConnectionFactory.createConnection(getCon(),pool);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }






    public Map<String,QuotaResult> findCard(Set<String> cards,Set<String> indexes){
        Map<String,String> map = new HashMap<>();
        Result result = null;
        QuotaResult quotaResult = new QuotaResult();
        Map<String,QuotaResult> resultMap = new HashMap<>();
        for(String card : cards) {
            Get get = new Get(card.getBytes());
            try {
                Table table = getConnection().getTable(TableName.valueOf("card_nature_test"));
                result = table.get(get);
                for(String index :indexes){
                    String value = Bytes.toString(result.getValue("n".getBytes(),index.getBytes()));
                    map.put(index,value);
                }
                quotaResult.setRowKey(card);
                quotaResult.setQuota(map);
                resultMap.put(card,quotaResult);

                System.out.println("=====1====" + quotaResult.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        System.out.println("=====3====" + resultMap);

        return  resultMap;
    }




    public static void main(String[] args) {

        final Table table = null;

        Runnable runnable = new Runnable() {
            @Override public void run() {
                HbaseTemplate hbaseTemplate = new HbaseTemplate();
                Set<String> i = new HashSet<>();
                i.add("CP0115");
                i.add("CP0116");
                i.add("CP0113");
                i.add("CP5376");
                i.add("CP5375");
                Set<String> s = new HashSet<>();
                s.add("00000007B88A1431470EA49F6F23D3F84FABCD25755B5C8E92AB3D40101ACF80");
                s.add("8ED3B4E5FCD2EC2FC1D0F479963DA11BDB09A5F8F5EFD9BD1A5BA97FF573BBA1");
                hbaseTemplate.findCard(s,i);
            }

        };
        Thread thread1 = new Thread(runnable);
//        Thread thread2 = new Thread(runnable);
        thread1.start();
//        thread2.start();

    }
}
