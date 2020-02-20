package fx.Scenes;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class CanvasPainter {
    private Canvas CV;
    private GraphicsContext GC;
    CanvasPainter(Canvas cv) {
		cv.widthProperty().addListener(e->DrawData());  // redraw when resizing
		cv.heightProperty().addListener(e->DrawData());
		
    	CV = cv;  
    	GC = CV.getGraphicsContext2D();
    	getCanvasProperty();
    }
    
    private double Width;
    private double Height;
    private double MiddlePoint;
    private double LineWidth;
    
    private void getCanvasProperty() {
    	Width = CV.getWidth();
    	Height = CV.getHeight();
    	MiddlePoint = Height / 2;
    }
    
    private int [] Datas;
    public void DrawData() {
    	if(Datas == null) return;
    	getCanvasProperty();
    	LineWidth = Width / Datas.length;
    	GC.setLineWidth(LineWidth);
    	
    	GC.clearRect(0, 0, Width, Height);
    	
    	for(int i = 0; i<Datas.length; i++) {
    		DrawALine(i*LineWidth, Datas[i]);
    	}
    }
    
    public void DrawData(int[] datas) { // Draw the image of a given integer array
    	if(datas==null) return;
    	Datas = datas;
    	
    	DrawData();
    }
    
    private Color GetDataColor(int d) { 		   // Get this data's color by the value
    	return Color.rgb(d/255/255%255, d/255%255, d%255);
    }
     
    private void DrawALine(double x, int Value) {  // Draw a line with the x given and the value of a data
    	double dataHeight = (double)Value / (255*255) * MiddlePoint;
	    LinearGradient lg = new LinearGradient(0, 0, 1, 1, true,
               CycleMethod.REFLECT,
               new Stop(0.0, GetDataColor(Value)),
               new Stop(1.0, Color.BLACK));
    	GC.setStroke(lg);
    	GC.strokeLine(x, MiddlePoint - dataHeight, x, MiddlePoint + dataHeight);
    }
    
}
