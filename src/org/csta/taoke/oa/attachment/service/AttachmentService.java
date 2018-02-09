package org.csta.taoke.oa.attachment.service;

import java.util.List;

import org.csta.taoke.oa.attachment.entity.Attachment;

public interface AttachmentService {
	public List<Attachment> getAttachmentList(Attachment attachment);
	public List<Attachment> getAttachmentTrashList(Attachment attachment);
	public List<Attachment> getAttachment(Attachment attachment);
	public void insertAttachment(Attachment attachment);
	public void updateAttachment(Attachment attachment);
	public void removeAttachment(Attachment attachment);
	public void redoAttachment(Attachment attachment);
	public void deleteAttachment(Attachment attachment);
}
