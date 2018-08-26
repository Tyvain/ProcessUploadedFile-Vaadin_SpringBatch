package nc.unc.importparcoursup.batch.tasks;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.FileSystemResource;

public class ArchiveFileTask implements Tasklet {

    private File fileToArchive;
    private FileSystemResource archiveDirectory;

    public ArchiveFileTask(File fileToArchive, FileSystemResource archiveDirectory) {
	this.fileToArchive = fileToArchive;
	this.archiveDirectory = archiveDirectory;
    }

    /**
     * copy file to archive directory with timestamp
     */
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {	
	String myTimestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	String destinationName = FilenameUtils.removeExtension(fileToArchive.getName())  + "_" + myTimestamp + ".csv";
	File destination = new File(archiveDirectory.getFile(), destinationName);
	FileUtils.copyFile(fileToArchive, destination);
	return RepeatStatus.FINISHED;
    }

}
