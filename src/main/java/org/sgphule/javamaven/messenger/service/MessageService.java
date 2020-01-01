package org.sgphule.javamaven.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.sgphule.javamaven.messenger.database.DatabaseClass;
import org.sgphule.javamaven.messenger.exception.DataNotFoundException;
import org.sgphule.javamaven.messenger.model.Message;

public class MessageService {

	private Map<Long, Message> messages= DatabaseClass.getMessages();
	
	public MessageService(){ 
		messages.put(1L, new Message(1L, "Hello Germany","SGPhule1"));
		messages.put(2L,new Message(2L, "Hello India!","SGPhule2"));
	}
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}	
	
	public List<Message> getAllmessagesForYear(int year){
		List<Message> messageForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message : messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)==year) {
				messageForYear.add(message);
			}
		}
		return messageForYear;		
	}
	public List<Message> getAllMessagePaginated(int start, int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if((start + size) > list.size()) return new ArrayList<Message>();
		return list.subList(start, start + size);
	}	
	
	public Message getMessage(Long id) {
		Message message = messages.get(id);
		if(message == null) {
			throw new DataNotFoundException("Message with id: " + id + "not found");
		}
		return message;
	}	
	public Message addMessage(Message message) {
		message.setId(messages.size()+1);
		messages.put(message.getId(),message);
		return message;		
	}	
	public Message updateMessage(Message message)
	{
		if(message.getId() <=0){
			return null;
		}
		messages.put(message.getId(),message);
		return message;
	}
	public Message removeMessage(Long id) {
		return messages.remove(id);
	}
}
