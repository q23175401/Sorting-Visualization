package Sorting;

import fx.Scenes.CanvasPainter;

public class BoubleSort extends SortingVisualization{

	public BoubleSort(CanvasPainter cp) {
		super(cp);
	}
	
	@Override
	protected void GenerateSortingMissions() {
		System.out.println("Start Bouble Sort");
		datareg = CopyDatas();
		int len = datareg.length;

		for(int i=0; i<len; i++) {
			for(int j=0; j<len-i-1; j++) {
				if(datareg[j] > datareg[j+1]) {
					Swap(datareg, j, j+1);
					MissionQueue.add(new mission(j, j+1, false));
				}
			}
		}
		datareg = null;
	}
	
}
