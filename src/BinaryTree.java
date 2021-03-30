import java.io.FileWriter;

public class BinaryTree{
			treeNode root;
			
			BinaryTree(){
				root = null;
			}
			BinaryTree(treeNode r){
				root = r;
			}
			
			public void preOrderTraversal (treeNode root, FileWriter outFile) {
				if (root.isLeaf()) root.printNode(root, outFile);
				else {
					root.printNode(root, outFile);
					preOrderTraversal(root.left, outFile);
					preOrderTraversal(root.right, outFile);
				}
			}
			
			public void inOrderTraversal (treeNode root, FileWriter outFile) {
				if (root.isLeaf()) root.printNode(root, outFile);
				else {
					inOrderTraversal(root.left, outFile);
					root.printNode(root, outFile);
					inOrderTraversal(root.right, outFile);
				}
			}
			
			public void postOrderTraversal (treeNode root, FileWriter outFile) {
				if (root.isLeaf()) root.printNode(root, outFile);
				else {
					postOrderTraversal(root.left, outFile);
					postOrderTraversal(root.right, outFile);
					root.printNode(root, outFile);
				}
			}
			
		}