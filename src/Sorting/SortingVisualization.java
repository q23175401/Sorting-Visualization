package Sorting;

import java.util.LinkedList;
import java.util.Queue;

import fx.Scenes.CanvasPainter;
import javafx.animation.Animation.Status;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

public abstract class SortingVisualization {
	
	public static boolean IsFinished = true;
	public static double TIME_QUANTUM = 1; // 1 milliseconds
	protected boolean HasTask = false;
	protected static int[] Datas;
	protected Timeline TL = new Timeline();
	protected CanvasPainter CP;

	SortingVisualization(CanvasPainter cp){
		CP = cp;
	    TL.setCycleCount(0);
	    TL.setOnFinished(e->Finished());
		
	    TL.getKeyFrames().add(
    		new KeyFrame(
				Duration.millis(TIME_QUANTUM),
				(e)->Sorting()
			)
	    );
	}
	
	protected void Finished() {
		System.out.println("Finished");
		IsFinished  = true;
		HasTask    = false;
	}
	
	protected void Swap(int[] datas, int i, int j) {
		int temp = datas[i];
		datas[i] = datas[j];
		datas[j] = temp;
	}
	
	public void DataClearAndStop() {
		TL.stop();
		TL.setCycleCount(0);
		Datas = null;
		HasTask = false;
		IsFinished = true;
		datareg = null;
		MissionQueue.clear();
	}

	public boolean HasTask() {
		return HasTask;
	}
	
	/*
	 * Things to do every frame of TimeLine
	 * */
	protected void Sorting() {
		if(MissionQueue.isEmpty()) return;
		CompleteAMission(MissionQueue.poll());
		CP.DrawData(Datas);
	}
	protected void CompleteAMission(mission m) {
		m.CompleteMission(Datas);
	}
	
	protected abstract void GenerateSortingMissions();
	/*
	 * Setting TimeLine Cycle Count or other properties before sorting 
	 * */
	protected void SortingSetting() {
		GenerateSortingMissions();
		TL.setCycleCount(MissionQueue.size());
	}
	
	protected void PauseSorting() {
		TL.pause();
	}
	protected void ContinueSorting() {
		TL.play();
	}
	public void Sort(int[] datas) {
		if(datas==null) return;
		
		if(TL.getStatus() == Status.STOPPED) {
			DataClearAndStop();
			Datas = datas;
			SortingSetting();
			IsFinished = false;
			HasTask = true;
			TL.play();
		}else if(TL.getStatus() == Status.PAUSED) {
			ContinueSorting();
		}else {
			PauseSorting();
		}
		
	}
	
	protected int[] datareg;
	protected Queue<mission> MissionQueue = new LinkedList<mission>();
	protected class mission { // A Sorting Mission
		public mission(int f, int t, boolean isset) {
			from = f;
			to = t;
			isSet = isset;
		}
		private int from; // from or at
		private int to;   // to   or value  for different mission
		private boolean isSet = false;
		public boolean IsSetMission() {
			return isSet;
		}
		
		public void CompleteMission(int[] datas) {
			if(datas == null) return;
			if(isSet) {
				datas[from] = to;
			}else {
				int temp = datas[from];
				datas[from] = datas[to];
				datas[to] = temp;
			}
		}
	}
	
	protected int[] CopyDatas() {
		if(Datas == null) return null;
		int[] reg = new int [Datas.length];
		for(int i=0; i<Datas.length; i++)
			reg[i] = Datas[i];
		
		return reg;
	}
	
}
