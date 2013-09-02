/**
 * 
 */
package org.erplab.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lawrence.wang
 *
 * 2011-5-7
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String,String> m = new HashMap();
		m.put("123", "123");
		m.put("123", "1235");
		m.put("123", "12356");
		System.out.println(m.get("123"));
	}

}
