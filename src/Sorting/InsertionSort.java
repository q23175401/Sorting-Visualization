package Sorting;

import fx.Scenes.CanvasPainter;

public class InsertionSort extends SortingVisualization{

	public InsertionSort(CanvasPainter cp) {
		super(cp);
	}
	
	@Override
	protected void GenerateSortingMissions() {
		System.out.println("Start Insertion Sort");
		datareg = CopyDatas();
		int len = datareg.length;
		for(int i=1; i< len; i++) {
			for(int j=i; j>0; j--) {
				if(datareg[j-1]> datareg[j]) {
					Swap(datareg, j, j-1);
					MissionQueue.add(new mission(j, j-1, false));
				}
				else break;
			}
		}
	}
}
