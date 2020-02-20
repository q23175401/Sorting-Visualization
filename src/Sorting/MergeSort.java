package Sorting;


import fx.Scenes.CanvasPainter;

public class MergeSort extends SortingVisualization{

	public MergeSort(CanvasPainter cp) {
		super(cp);
	}
	
	private int[] reg;
	
	@Override
	protected void GenerateSortingMissions() {
		System.out.println("Start Merge Sort");
		datareg = CopyDatas();
		reg = new int[datareg.length];
		GetMergeSortMissionRec(0, datareg.length-1);
	}
	
	private void GetMergeSortMissionRec(int from, int to) {
		int distance = to - from;
		if(distance <= 0) return;
		if (distance == 1) {
			if(datareg[from] > datareg[to]) {
				MissionQueue.add(new mission(from, to, false));
				Swap(datareg, from, to);
			}
			return;
		} 
		
		int middle = (from + to) / 2;
		GetMergeSortMissionRec(from, middle);
		GetMergeSortMissionRec(middle + 1, to);

		MergeArray(from, to);
	}
	
	private void MergeArray(int from, int to) {
		int middle = (from + to) / 2;
		
		int i = from, j =  middle+1, k=from;
		while(i<=middle && j <= to) {
			reg[k++] = datareg[i]<datareg[j] ? datareg[i++] : datareg[j++];
		}
		while(i<=middle) {
			reg[k++] = datareg[i++];
		}
		while(j<=to) {
			reg[k++] = datareg[j++];
		}
		
		k=from;
		while(k<=to) {
			MissionQueue.add(new mission(k, reg[k], true));
			datareg[k] = reg[k++];
		}
	}
	
}
