package nc.unc.parcoursupui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.internal.MessageDigestUtil;
import com.vaadin.flow.router.Route;

/**
 * The main view contains a simple label element and a template element.
 */
@HtmlImport("@vaadin/vaadin-upload/vaadin-upload.js")
@Route
public class MainView extends VerticalLayout {

    public MainView(@Autowired StudentRepository studentRepo) {

	add(new Label("Nombre de lignes importées: " + studentRepo.count()));

	Div output = new Div();
	MemoryBuffer buffer = new MemoryBuffer();
	Upload upload = new Upload(buffer);

	upload.addSucceededListener(event ->
	    {
		try {
		    FileUtils.copyInputStreamToFile(buffer.getInputStream(), new File("C:/temp/wtf.txt"));
		} catch (IOException e) {
		    throw new RuntimeException(e);
		}
		showOutput(event.getFileName(), new Div(), output);
	    });
	add(upload);
    }

    private void showOutput(String text, Component content, HasComponents outputContainer) {
	HtmlComponent p = new HtmlComponent(Tag.P);
	p.getElement()
		.setText(text);
	outputContainer.add(p);
	outputContainer.add(content);
    }

    private Component createComponent(String mimeType, String fileName, InputStream stream) {
	Div content = new Div();
	String text = String.format("Mime type: '%s'\nSHA-256 hash: '%s'", mimeType, MessageDigestUtil.sha256(stream.toString()));
	content.setText(text);
	return new Div();

    }
}
