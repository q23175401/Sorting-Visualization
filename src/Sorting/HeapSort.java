package Sorting;

import fx.Scenes.CanvasPainter;

public class HeapSort extends SortingVisualization{

	public HeapSort(CanvasPainter cp) {
		super(cp);
	}

	@Override
	protected void GenerateSortingMissions() {
		System.out.println("Start Heap Sort");
		datareg = CopyDatas();
		MaxHeap h = new MaxHeap(datareg);
		h.HeapSort();
		datareg = null;
	}

	private class MaxHeap{
		int []D;
		public MaxHeap(int[] datas) {
			D = datas;
			Heapify();
		}
		
		public void HeapSort() {
			for(int i=D.length-1; i>0; i--) {
				Swap(D, 0, i);
				MissionQueue.add(new mission(0, i, false));
				AdjustHeap(0, i);
			}
		}
		
		public void Heapify() {
			for(int i=datareg.length/2; i>=0; i--) {
				AdjustHeap(i, D.length);
			}
		}
		
		private int leftchildI(int r) {
			return r*2 + 1;
		}
		
		private int rightchildI(int r) {
			return r*2 + 2;
		}
		
		private int parent(int c) {
			if(c==0) return 0;
			
			return (c-1) / 2;
		}
		
		public void AdjustHeap(int root, int len) {
			int rootkey = D[root];
			int child = leftchildI(root);
			while(child<=len-1) {
				if(child<len-1 && D[child]< D[child+1]) {
					child = child + 1;
				}
				if(D[child]>rootkey) {
					D[parent(child)] = D[child];
					MissionQueue.add(new mission(parent(child), D[child], true));
					child = leftchildI(child);
				}else {
					break;
				}
			}
			D[parent(child)] = rootkey;
			MissionQueue.add(new mission(parent(child), rootkey, true));
		}
	}
}
