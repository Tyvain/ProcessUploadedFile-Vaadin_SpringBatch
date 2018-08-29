package nc.unc.importparcoursup.view;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;

public class UploadComponent extends Upload {
    private File myFile;

    public UploadComponent(String filePath) {
	super();
	MemoryBuffer buffer = new MemoryBuffer();
	setReceiver(buffer);
	addSucceededListener(event ->
	    {
		try {
		    myFile = new File(filePath);
		    FileUtils.copyInputStreamToFile(buffer.getInputStream(), myFile);
		} catch (IOException e) {
		    throw new RuntimeException(e);
		}	
	    });
    }

    public File getFile() {
	return myFile;
    }

    
    
}