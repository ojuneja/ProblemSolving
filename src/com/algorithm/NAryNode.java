/**
 * 
 */
package com.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ojas Juneja
 *
 */
public class NAryNode {
	int val;
	List<NAryNode> childrens;

	NAryNode(int val) {
		this.val = val;
		this.childrens = new ArrayList<NAryNode>();
	}
}
