package fx.Scenes;

import java.net.URL;
import java.util.ResourceBundle;

import Sorting.*;
import Sorting.RandomValueGenerator;
import Sorting.SortingVisualization;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainSceneController implements Initializable{
	@FXML
	public Canvas MainCanvas;
	@FXML
	public AnchorPane AP;
	
	@FXML
	public VBox VB;
	
	private CanvasPainter CP;
	private RandomValueGenerator RVG = new RandomValueGenerator();
	private int[] CurrentDatas;
	
	private BoubleSort BS;
	private InsertionSort IS;
	private MergeSort MS;
	private QuickSort QS;
	private HeapSort HS;
	private RadixSort RS;
	private CountingSort CS;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		AP.prefWidthProperty().bind(VB.widthProperty());
		AP.prefHeightProperty().bind(VB.heightProperty().subtract(30));
		MainCanvas.widthProperty().bind(AP.widthProperty());
		MainCanvas.heightProperty().bind(AP.heightProperty());

		CP = new CanvasPainter(MainCanvas);
		SetRandom();
		
		BS = new BoubleSort(CP);
		IS = new InsertionSort(CP);
		MS = new MergeSort(CP);
		QS = new QuickSort(CP);
		HS = new HeapSort(CP);
		RS = new RadixSort(CP);
		CS = new CountingSort(CP);
	}
    
	private static final int MIN_NUMBER = 5000;
	private static final int MAX_NUMBER = 255 * 255;
	private static final int NUM_DATAS = 300;
	public void SetRandom() {
		CurrentDatas = RVG.GenerateRandom(NUM_DATAS, MIN_NUMBER, MAX_NUMBER);
		CP.DrawData(CurrentDatas);
		AllStop(null);
	}
	
	public void AllStop(SortingVisualization SV) {
		if(SortingVisualization.IsFinished || (SV!=null && SV.HasTask())) return;
		
		BS.DataClearAndStop();
		IS.DataClearAndStop();
		MS.DataClearAndStop();
		QS.DataClearAndStop();
		HS.DataClearAndStop();
		RS.DataClearAndStop();
		CS.DataClearAndStop();
	}
	
	public void CountingSort() {
		AllStop(CS);
		CS.Sort(CurrentDatas);
	}
	
	public void RadixSort() {
		AllStop(RS);
		RS.Sort(CurrentDatas);
	}
	
	public void HeapSort() {
		AllStop(HS);
		HS.Sort(CurrentDatas);
	}
	
	public void InsertionSort() {
		AllStop(IS);
		IS.Sort(CurrentDatas);
	}
	
	public void QuickSort() {
		AllStop(QS);
		QS.Sort(CurrentDatas);
	}
	
	public void MergeSort() {
		AllStop(MS);
		MS.Sort(CurrentDatas);
	}
	
	public void BoubleSort() {
		AllStop(BS);
		BS.Sort(CurrentDatas);
	}
	

}
