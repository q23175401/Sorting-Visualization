package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MainScene  extends Application{
	public static Stage MainStage;
	public static Scene MainScene;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage mainStage) throws Exception {
		MainStage = mainStage;
		Parent content;
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("./Scenes/MainScene.fxml"));
		try {
			content = loader.load();
		}catch(Exception e) {
			e.printStackTrace();
			content = new Label("Load Failed!");
		}
		MainScene = new Scene(content);
		MainStage.setMinWidth(800);
		MainStage.setMinHeight(800);
		MainStage.setScene(MainScene);
		MainStage.setTitle("Sorting Visualization");
		MainStage.show();
	}
}
