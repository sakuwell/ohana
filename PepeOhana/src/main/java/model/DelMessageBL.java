package model;

public class DelMessageBL {
	public boolean executeDeleteMessage(MessagesDto dto) {
		boolean successDelete=false;
		MessagesDao dao = new MessagesDao();
		successDelete=dao.doDeleteMessage(dto.getMessageId());
		
		return successDelete;
	}
}
