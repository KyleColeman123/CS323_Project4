import java.io.FileWriter;

public class LLlist{
			int length = 0;
			treeNode listHead;

			
			LLlist (){
				listHead = new treeNode("dummy", 0, " ", null, null, null);	
			}
			
			public void insertNewNode(treeNode T) {
				treeNode tmp = listHead;

				if (listHead.next == null) tmp.next = T;
				else {
					while (tmp.next != null) {
						if (tmp.next.frequency > T.frequency) {
							treeNode tmp2 = tmp.next;
							tmp.next = T;
							T.next =tmp2;
							length++;
							return;
						}
						tmp = tmp.next;
					}
				}
				tmp.next=T;
				length++;
			}
			
			public void printList(FileWriter file) {
				treeNode tmp = listHead;
				while (tmp != null) {
					tmp.printNode(tmp, file);
					tmp = tmp.next;
				}
			}
		}