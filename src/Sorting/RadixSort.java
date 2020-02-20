package Sorting;

import java.util.LinkedList;
import java.util.Queue;

import fx.Scenes.CanvasPainter;

public class RadixSort extends SortingVisualization {

	public RadixSort(CanvasPainter cp) {
		super(cp);
		for(int i=0; i<Buckets.length; i++) {
			Buckets[i] = new LinkedList<Integer>();
		}
	}

	@Override
	protected void GenerateSortingMissions() {
		System.out.println("Start Radix Sort");
		datareg = CopyDatas();
		RSort();
		datareg = null;
	}
	
	private static final int NUM_BUCKETS = 10; 
	private static final int NUM_DIGITS = 8;
	@SuppressWarnings("unchecked")
	private Queue<Integer> [] Buckets = new Queue[NUM_BUCKETS];
	
	private void RSort() {
		for(int i=0; i<NUM_DIGITS; i++) {
			PutToBuckets(i);
			PutBackToArray();
		}
	}
	private void PutBackToArray() {
		int k = 0;
		for(int i=0; i<NUM_BUCKETS; i++) {
			while(!Buckets[i].isEmpty()) {
				int value = Buckets[i].poll();
				MissionQueue.add(new mission(k, value, true));
				datareg[k++] = value;  
			}
		}
	}
	
	private void PutToBuckets(int offset) {
		int data = -1;
		for(int i=0; i<datareg.length; i++) {
			data = datareg[i];
			for(int of=0; of<offset; of++) {
				data/=10;
			}
			Buckets[data%10].add(datareg[i]);
		}
	}

}
