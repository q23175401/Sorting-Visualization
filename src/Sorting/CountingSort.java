package Sorting;

import fx.Scenes.CanvasPainter;

public class CountingSort extends SortingVisualization {

	public CountingSort(CanvasPainter cp) {
		super(cp);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void GenerateSortingMissions() {
		datareg = CopyDatas();
		CSort();
		datareg = null;
	}
	
	private static final int COUNTING_RANGE = 90000;
	private int[] CountingArray = new int[COUNTING_RANGE];
	private void CSort() {
		for(int i=0; i<CountingArray.length; i++) {
			CountingArray[i] = 0;
		}
		
		for(int i=0; i<datareg.length; i++) {
			CountingArray[datareg[i]]++;
		}
		
		for(int i=1; i<CountingArray.length; i++) {
			CountingArray[i] += CountingArray[i-1];
		}
		
		int valueI = 0;
		for(int i=0; i<datareg.length; i++) {
			valueI = --CountingArray[Datas[i]];
			MissionQueue.add(new mission(valueI, Datas[i], true));
			datareg[valueI] = Datas[i];
		}
	}

}