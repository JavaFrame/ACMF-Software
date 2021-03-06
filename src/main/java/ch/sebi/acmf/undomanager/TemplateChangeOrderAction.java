package ch.sebi.acmf.undomanager;

import ch.sebi.acmf.data.Song;
import ch.sebi.acmf.data.Template;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Sebastian on 19.11.2017.
 */
public class TemplateChangeOrderAction extends Action {
	private ObservableList<Template> oldOrder, newOrder;

	public TemplateChangeOrderAction(Song song, ObservableList<Template> oldOrder) {
		super(song);
		this.oldOrder = FXCollections.observableArrayList(oldOrder);
	}

	@Override
	public void undo() {
		newOrder = FXCollections.observableArrayList(getSong().getTemplates());
		getSong().templatesProperty().setAll(oldOrder);
		getUndoManager().getAcmfMainController().getSong_listview().getSelectionModel().select(getSong());
	}

	@Override
	public void redo() {
		getSong().templatesProperty().set(newOrder);
		getUndoManager().getAcmfMainController().getSong_listview().getSelectionModel().select(getSong());
	}
}
