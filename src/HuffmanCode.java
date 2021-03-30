import java.io.*;
import java.util.*;

public class HuffmanCode {
		
		static int charCountAry[] = new int[256];
		static String charCode[] = new String[256];
		
		public static int[] computeCharCounts(File inFile, int[] charCountAry) {
			char c;
			String f = "";
			for (int i=0; i<256; i++) {
				c = (char) i;
				Scanner scan = null;
				try {
					scan = new Scanner(inFile);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				while (scan.hasNextLine()) {
					f = scan.nextLine();
					for (int j=0; j<f.length(); j++) {
						if (c == f.charAt(j)) charCountAry[i]++;
					}
				}
			}
			
			Scanner scan = null;
			try {
				scan = new Scanner(inFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			while (scan.hasNextLine()) {
				f = scan.nextLine();
				charCountAry[10]++;
			}
			return charCountAry;
		}
	
		public static void printCountAry(int[] charCountAry, FileWriter DebugFile) {
			for (int i=32; i<256; i++) {
				try {
					DebugFile.write("["+(char)i + "]" + charCountAry[i] + "  \n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				DebugFile.write("["+(char)10 + "]" + charCountAry[10] + "  \n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		public static LLlist constructHuffmanLList(int[] charCountAry, FileWriter DebugFile) {
			LLlist list = new LLlist();
			for (int index=0; index<256; index++) {
				if (charCountAry[index] > 0) {
					String chr = String.valueOf(index);
					int prob = charCountAry[index];
					treeNode newNode = new treeNode(chr, prob," ", null, null, null);
					list.insertNewNode(newNode);
					list.printList(DebugFile);
					try {
						DebugFile.write("\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			list.printList(DebugFile);
			return list;
		}
		
		public static BinaryTree constructHuffmanBinTree(LLlist hList, FileWriter DebugFile) {
			treeNode listHead = hList.listHead;
			while (listHead.next.next != null) {
				treeNode newNode = new treeNode();
				newNode.frequency = listHead.next.frequency + listHead.next.next.frequency;
				newNode.chStr = listHead.next.chStr + listHead.next.next.chStr;
				newNode.left = listHead.next;
				newNode.right = listHead.next.next;
				newNode.next = null;
				
				hList.insertNewNode(newNode);
				listHead.next = listHead.next.next.next;
				hList.printList(DebugFile);
				try {
					DebugFile.write("\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			treeNode root = listHead.next;
			BinaryTree binTree = new BinaryTree(root);
			return binTree;
		}
		
		public static void constructCharCode(treeNode T, String code) {
			if (T.isLeaf()) {
				T.code = code;
				int index = Integer.parseInt(T.chStr);
				charCode[index] = code;
			}
			else {
				constructCharCode(T.left, code+"0");
				constructCharCode(T.right, code+"1");
			}
		}
	
		public static void Encode (File orgFile, FileWriter outFile, FileWriter DebugFile) {
			String f = "";
			Scanner scan = null;
			try {
				scan = new Scanner(orgFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				DebugFile.write(" \n");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			while (scan.hasNextLine()) {
				f = scan.nextLine();
				for (int j=0; j<f.length(); j++) {
					char charIn = f.charAt(j);
					int index = (int) charIn;
					String code = charCode[index];
					try {
						DebugFile.write(" "+(char)index + ":");
						DebugFile.write(code);
						outFile.write(code);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					outFile.write(charCode[10]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		public static void Decode (BinaryTree bTree, File compFile, FileWriter deCompFile) {
			treeNode Spot =  bTree.root;
			String f = "";
			Scanner scan= null;
			try {
				scan = new Scanner(compFile);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			f = scan.nextLine();

			for (int j =0; j<f.length(); j++) {
					if (Spot.isLeaf()) {
						try {
							int w = Integer.parseInt(Spot.chStr);
							deCompFile.write((char) w);
							
						} catch (IOException e) {
							e.printStackTrace();
						}
						Spot = bTree.root;
					}
					char oneBit = f.charAt(j);
					if (oneBit == '0') {
						Spot = Spot.left;
					}
					else if (oneBit == '1') {
						Spot = Spot.right;
					}
					else {
						System.out.println("Error! The compress file contains invalid character!");
						System.exit(0);
					}
			}
			
			if (!scan.hasNext() && !Spot.isLeaf()) {
				System.out.println("Error! The compress file is corrupted!");
			}
			else
				System.out.println("Done!");
			
		}
		
		public static void userInterface(BinaryTree bTree, FileWriter DebugFile) {
			String nameOrg = null;
			String nameCompress;
			String nameDeCompress;
			char yesNo;
			System.out.println("Would you like to compress a file? Enter Y or N ");
			Scanner input1 = new Scanner(System.in);
			yesNo = input1.next().charAt(0);
			while (yesNo != 'N') {
				if (yesNo == 'N') System.exit(0);
				else {
					System.out.println("Enter the name of the file you want to compress.");
					Scanner input2 = new Scanner(System.in);
					nameOrg = input2.next();
				}
				nameCompress = nameOrg + "_Compressed.txt";
				nameDeCompress = nameOrg + "_DeCompressed.txt";
				nameOrg = nameOrg + ".txt";
				File orgFile = new File(nameOrg);
				File compFile = new File(nameCompress);
				File deCompFile = new File(nameDeCompress);
				try {
					orgFile.createNewFile();
					compFile.createNewFile();
					deCompFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					FileWriter Comp = new FileWriter(nameCompress);
					FileWriter DeComp = new FileWriter(nameDeCompress);
					Encode (orgFile, Comp, DebugFile);
					Comp.close();
					Decode(bTree,compFile, DeComp);
					DeComp.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void main(String[] args) {
			String nameInFile = new String(args[2]);
			File  inFile = new File(nameInFile);
			String nameDebugFile = new String(nameInFile + "_DeBug.txt");
			File DebugFile = new File(nameDebugFile);
			try {
				DebugFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			computeCharCounts(inFile, charCountAry);
			FileWriter debug = null;
			try {
				debug = new FileWriter(nameDebugFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			printCountAry(charCountAry, debug);
			LLlist hList = constructHuffmanLList(charCountAry, debug);
			BinaryTree btree = constructHuffmanBinTree(hList, debug);
			constructCharCode(btree.root, "");
			btree.preOrderTraversal(btree.root, debug);
			btree.inOrderTraversal(btree.root, debug);
			btree.postOrderTraversal(btree.root, debug);
			userInterface(btree, debug);
			try {
				debug.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
}
