package cn.test.test1.UF.quick_find;

public class Test {
	
	private static int NUM_SIZE = 10;
	
	public static void main(String[] args) {
		int[] test = new int[] {0,9,3,4,5,8,7,2,2,1,5,7,0,3,4,2};
		//testUF(test);
		//testUF_union(test);
		//testWeightedQuickUnionUF(test);
		
		int[] test2 = new int[] {4,3,3,8,6,5,9,4,2,1,8,9,5,0,7,2,6,1,1,0,6,7};
		testUF_union(test2);
		
		int[] test3 = new int[] {0,1,2,3,4,5,6,7,0,2,4,6,0,4};
		testWeightedQuickUnionUF(test3);
	}
	
	public static void testUF(int[] test) {
		UF uf = new UF(NUM_SIZE);
		for(int i = 0;i < test.length/2; i++) {
			uf.union(test[i*2], test[i*2+1]);
		}
		System.out.println(uf.getIdCount());
	}
	public static void testWeightedQuickUnionUF(int[] test) {
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(NUM_SIZE);
		for(int i = 0;i < test.length/2; i++) {
			uf.union(test[i*2], test[i*2+1]);
		}
		System.out.println(uf.getIdCount());
	}
	public static void testUF_union(int[] test) {
		UF_union uf = new UF_union(NUM_SIZE);
		for(int i = 0;i < test.length/2; i++) {
			uf.union(test[i*2], test[i*2+1]);
		}
		System.out.println(uf.getIdCount());
	}
}
