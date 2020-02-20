package Sorting;
import java.util.Random;

public class RandomValueGenerator {
	public int[] GenerateRandom(int num, int from, int to) {
		Random rd = new Random();
		int[] datas = new int[num];
		for (int i=0; i<num; i++) {
			datas[i] = rd.nextInt(to-from) + from;
		}
		return datas;
	}
}
