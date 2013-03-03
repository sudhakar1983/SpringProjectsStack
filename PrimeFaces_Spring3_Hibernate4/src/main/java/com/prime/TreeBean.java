package com.prime;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;

@ManagedBean
@RequestScoped
public class TreeBean {
	
	private TreeNode root;

	public TreeBean() {
		root = new DefaultTreeNode("Root", null);
		TreeNode sales = new DefaultTreeNode("Sales Reports", root);
		TreeNode revenue = new DefaultTreeNode("Revenue Reports", root);
		TreeNode node2 = new DefaultTreeNode("Other Reports", root);
		
		TreeNode sales2011 = new DefaultTreeNode("Sales 2011", sales);
		TreeNode sales2012 = new DefaultTreeNode("Sales 2012", sales);

		
		TreeNode node000 = new DefaultTreeNode("March", sales2011);
		TreeNode node001 = new DefaultTreeNode("July", sales2011);
		TreeNode node010 = new DefaultTreeNode("Jan", sales2012);
		TreeNode node011 = new DefaultTreeNode("July", sales2012);
		
		
		
		TreeNode revenu2011 = new DefaultTreeNode("Revenue 2011", revenue);
		TreeNode revenu2012 = new DefaultTreeNode("Revenue 2012", revenue);
		TreeNode node100 = new DefaultTreeNode("Node 1.0.0", revenu2011);
	
	}

	public TreeNode getRoot() {
		return root;
	}
}
					
