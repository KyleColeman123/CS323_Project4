import java.io.FileWriter;
import java.io.IOException;

public class treeNode{
			String chStr;
			int frequency;
			String code;
			treeNode left;
			treeNode right;
			treeNode next;
			
			treeNode (String c, int f, String co, treeNode l, treeNode r, treeNode n){
				chStr = c;
				frequency = f;
				code = co;
				left = l;
				right = r;
				next = n;
			}
			treeNode (){
				chStr = "dummy";
				frequency = 0;
				code = " ";
				left = null;
				right = null;
				next = null;
			}
			
			public void printNode(treeNode T, FileWriter DebugFile) {
					try {
						if (!T.isLeaf() && T.left != null && T.right != null)
							DebugFile.write("chStr:"+T.chStr + " freq:" + T.frequency + " code:" + T.code 
									+" leftStr:" + T.left.chStr +" rightStr:" + T.right.chStr);
						else if (!T.isLeaf() && T.right != null)
							DebugFile.write("chStr:"+T.chStr + " freq:" + T.frequency + " code:" + T.code 
									+" leftStr:" + T.right.chStr);
						else if (!T.isLeaf() && T.left != null)
							DebugFile.write("chStr:"+T.chStr + " freq:" + T.frequency + " code:" + T.code 
									+" leftStr:" + T.left.chStr);
						else
							DebugFile.write("chStr:"+T.chStr + " freq:" + T.frequency + " code:" + T.code);
						DebugFile.write("\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
			
			public boolean isLeaf() {
				if (this.left == null && this.right == null) return true;
				return false;
			}
			
		}
