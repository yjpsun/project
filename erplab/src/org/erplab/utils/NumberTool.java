/**
 * wooiioog@gmail.com
 * May 9, 20114:01:43 PM
 */
package org.erplab.utils;

/**
 * @author wanggang
 *
 */
public class NumberTool {

	public static Float round1Point(Float input){
		if(input == null)
			return 0f;
		return (float)(Math.round(input*10))/10;
	}
}
