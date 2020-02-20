package Sorting;

import fx.Scenes.CanvasPainter;

public class QuickSort extends SortingVisualization {

	public QuickSort(CanvasPainter cp) {
		super(cp);
	}
	

	@Override
	protected void GenerateSortingMissions() {
		System.out.println("Start Quick Sort");
		if(Datas == null) return;
		datareg = CopyDatas();
		QSGetMissionRec(0, datareg.length-1);
		datareg = null;
	}
	
	private void QSGetMissionRec(int from, int to) {
		int distance = to - from;
		if (distance <= 0) return;
		if (distance == 1) 
		{
			if(datareg[from] > datareg[to]) {
				Swap(datareg, from, to);
				MissionQueue.add(new mission(from, to, false));
			}
			
			return;
		}
		
		int pivotI = Partition(datareg, from, to);
		
		QSGetMissionRec(from, pivotI - 1);
		QSGetMissionRec(pivotI + 1, to);
	}
	
	private int Partition(int[] datas, int from, int to) {
		int pivot = datas[to];
		int smaller = from-1, larger = from;
		
		for(; larger<=to; larger++) {
			if(datas[larger] > pivot) continue;
			Swap(datas, ++smaller, larger);
			MissionQueue.add(new mission(smaller, larger, false));
		}
	
		return smaller;
	}
	
}
