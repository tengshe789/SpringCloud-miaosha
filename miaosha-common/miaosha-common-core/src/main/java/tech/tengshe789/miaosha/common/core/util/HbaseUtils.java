package tech.tengshe789.miaosha.common.core.util;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @program: -miaosha
 * @description: HbaseUtils
 * @author: tEngSHe789
 * @create: 2019-03-19 17:58
 **/
public class HbaseUtils {
//	private static Admin admin = null;
//	private static Connection conn = null;
//	static{
//		// 创建hbase配置对象
//		Configuration conf = HBaseConfiguration.create();
//		conf.set("hbase.rootdir","hdfs://192.168.80.134:9000/hbase");
//		//使用eclipse时必须添加这个，否则无法定位
//		conf.set("hbase.zookeeper.quorum","192.168.80.134");
//		conf.set("hbase.client.scanner.timeout.period", "600000");
//		conf.set("hbase.rpc.timeout", "600000");
//		try {
//			conn = ConnectionFactory.createConnection(conf);
//			// 得到管理程序
//			admin = conn.getAdmin();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	/**
//	 * 插入数据，create "userflaginfo,"baseinfo"
//	 * create "tfidfdata,"baseinfo"
//	 */
//	public static void put(String tablename, String rowkey, String famliyname, Map<String,String> datamap) throws Exception {
//		Table table = conn.getTable(TableName.valueOf(tablename));
//		// 将字符串转换成byte[]
//		byte[] rowkeybyte = Bytes.toBytes(rowkey);
//		Put put = new Put(rowkeybyte);
//		if(datamap != null){
//			Set<Map.Entry<String,String>> set = datamap.entrySet();
//			for(Map.Entry<String,String> entry : set){
//				String key = entry.getKey();
//				Object value = entry.getValue();
//				put.addColumn(Bytes.toBytes(famliyname), Bytes.toBytes(key), Bytes.toBytes(value+""));
//			}
//		}
//		table.put(put);
//		table.close();
//		System.out.println("ok");
//	}
//
//	/**
//	 *
//	 */
//	public static String getdata(String tablename, String rowkey, String famliyname,String colum) throws Exception {
//		Table table = conn.getTable(TableName.valueOf(tablename));
//		// 将字符串转换成byte[]
//		byte[] rowkeybyte = Bytes.toBytes(rowkey);
//		Get get = new Get(rowkeybyte);
//		Result result =table.get(get);
//		byte[] resultbytes = result.getValue(famliyname.getBytes(),colum.getBytes());
//		if(resultbytes == null){
//			return null;
//		}
//
//		return new String(resultbytes);
//	}
//
//	/**
//	 *
//	 */
//	public static void putdata(String tablename, String rowkey, String famliyname,String colum,String data) throws Exception {
//		Table table = conn.getTable(TableName.valueOf(tablename));
//		Put put = new Put(rowkey.getBytes());
//		put.addColumn(famliyname.getBytes(),colum.getBytes(),data.getBytes());
//		table.put(put);
//	}


}

