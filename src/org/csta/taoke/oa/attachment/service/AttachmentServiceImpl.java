package org.csta.taoke.oa.attachment.service;

import java.util.List;

import org.csta.taoke.oa.attachment.dao.AttachmentMapper;
import org.csta.taoke.oa.attachment.entity.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	@Autowired
	private AttachmentMapper dao;

	@Override
	public List<Attachment> getAttachmentList(Attachment attachment) {
		return dao.getAttachmentList(attachment);
	}

	@Override
	public List<Attachment> getAttachmentTrashList(Attachment attachment) {
		return dao.getAttachmentTrashList(attachment);
	}

	@Override
	public List<Attachment> getAttachment(Attachment attachment) {
		return dao.getAttachment(attachment);
	}

	@Override
	public void insertAttachment(Attachment attachment) {
		dao.insertAttachment(attachment);
	}

	@Override
	public void updateAttachment(Attachment attachment) {
		dao.updateAttachment(attachment);
	}

	@Override
	public void removeAttachment(Attachment attachment) {
		dao.removeAttachment(attachment);
	}

	@Override
	public void redoAttachment(Attachment attachment) {
		dao.redoAttachment(attachment);
	}

	@Override
	public void deleteAttachment(Attachment attachment) {
		dao.deleteAttachment(attachment);
	}
	
	
}
