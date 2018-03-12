package com.revature.hydra.reporting.services;

import java.util.List;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.revature.beans.Assessment;
import com.revature.beans.Batch;
import com.revature.beans.Grade;
import com.revature.beans.Note;
import com.revature.beans.Panel;
import com.revature.beans.Trainee;

@Service
public class Messenger {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	//@Value("${revature.hydra.service.exchange}")
	private static final String SERVICE_EXCHANGE = "revature.hydra.service";
	
	//@Value("${revature.hydra.service.grade}")
	private static final String GRADE = "revature.hydra.service.grade";
	
	//@Value("${revature.hydra.service.batch}")
	private static final String BATCH = "revature.hydra.service.batch";
	
	//@Value("${revature.hydra.service.trainee")
	private static final String TRAINEE = "revature.hydra.service.trainee";
	
	//@Value("${revature.hydra.service.note}")
	private static final String NOTE = "revature.hydra.service.note";
	
	//@Value("${revature.hydra.service.assessment}")
	private static final String ASSESSMENT = "revature.hydra.service.assessment";
	
	//@Value("${revature.hydra.service.panel}")
	private static final String PANEL = "revature.hydra.service.panel";
	
	//@Value("${revature.hydra.service.batchList}")
	private static final String BATCH_LIST = "revature.hydra.service.batch.list";
	
	//@Value("${revature.hydra.service.noteList}")
	private static final String NOTE_LIST = "revature.hydra.service.note.list";
	
	//@Value("${revature.hydra.service.traineeList}")
	private static final String TRAINEE_LIST = "revature.hydra.service.trainee.list";
	
	//@Value("${revature.hydra.service.panelList}")
	private static final String PANEL_LIST = "revature.hydra.service.panel.list";
	
	//@Value("${revature.hydra.service.gradeList}")
	private static final String GRADE_LIST = "revature.hydra.service.grade.list";
	
	//@Value("${revature.hydra.service.assessmentList}")
	private static final String ASSESSMENT_LIST = "revature.hydra.service.assessment.list";
	
	public List<Note> findAllQCTraineeNotes(int batchId, int weekNumber) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findAllQCTraineeNotes");
		msg.addProperty("batchId", batchId);
		msg.addProperty("week", weekNumber);
		List<Note> reply = (List<Note>) amqpTemplate.convertSendAndReceive(SERVICE_EXCHANGE, NOTE_LIST, msg.toString());
		return reply;
	}

	public List<Batch> findAllCurrentWithNotesAndTrainees() {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findAllCurrentWithNotesAndTrainees");
		List<Batch> reply = (List<Batch>) amqpTemplate.convertSendAndReceive(SERVICE_EXCHANGE, BATCH_LIST, msg.toString());
		return reply;
	}

	public List<Batch> findAllCurrentWithNotes() {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findAllCurrentWithNotes");
		List<Batch> reply = (List<Batch>) amqpTemplate.convertSendAndReceive(SERVICE_EXCHANGE, BATCH_LIST, msg.toString());
		return reply;
	}

	public Note findQCBatchNotes(Integer batchId, Integer week) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findQCBatchNotes");
		msg.addProperty("batchId", batchId);
		msg.addProperty("week", week);
		Note reply = (Note) amqpTemplate.convertSendAndReceive(SERVICE_EXCHANGE, NOTE, msg.toString());
		return reply;
	}

	public List<Trainee> findAllByBatch(int batchId) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findAllByBatch");
		msg.addProperty("batchId", batchId);
		List<Trainee> reply = (List<Trainee>) amqpTemplate.convertSendAndReceive(SERVICE_EXCHANGE, TRAINEE_LIST, msg.toString());
		return reply;
	}

	public Batch findOneWithTraineesAndGrades(Integer batchId) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findOneWithTraineesAndGrades");
		msg.addProperty("batchId", batchId);
		Batch reply = (Batch) amqpTemplate.convertSendAndReceive(SERVICE_EXCHANGE, BATCH, msg.toString());
		
		return reply;
	}

	public List<Batch> findAllCurrentWithTrainees() {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findAllCurrentWithTrainees");
		List<Batch> reply  = (List<Batch>) amqpTemplate.convertSendAndReceive(SERVICE_EXCHANGE, BATCH_LIST, msg.toString());
		return reply;
	}

	public List<Panel> findBiWeeklyPanels() {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findBiWeeklyPanels");
		List<Panel> reply = (List<Panel>) amqpTemplate.convertSendAndReceive(SERVICE_EXCHANGE, PANEL_LIST, msg.toString());
		return reply;
	}

	public List<Grade> findByTrainee(Integer traineeId) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findByTrainee");
		msg.addProperty("traineeId", traineeId);
		List<Grade> reply = (List<Grade>) amqpTemplate.convertSendAndReceive(SERVICE_EXCHANGE, GRADE_LIST, msg.toString());
		return reply;
	}

	public List<Grade> findByBatch(Integer batchId) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findByBatch");
		msg.addProperty("batchId", batchId);
		List<Grade> reply = (List<Grade>) amqpTemplate.convertSendAndReceive(SERVICE_EXCHANGE, GRADE_LIST, msg.toString());
		return reply;
	}

	public List<Assessment> findByWeek(Integer batchId, Integer week) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findByBatchIdAndWeek");
		msg.addProperty("batchId", batchId);
		msg.addProperty("week", week);
		List<Assessment> reply = (List<Assessment>) amqpTemplate.convertSendAndReceive(SERVICE_EXCHANGE, ASSESSMENT_LIST, msg.toString());
		return reply;
	}

	public List<Batch> findAllAfterDate(int month, int day, int year) {
		JsonObject msg = new JsonObject();
		msg.addProperty("methodName", "findAllAfterDate");
		msg.addProperty("month", month);
		msg.addProperty("day", day);
		msg.addProperty("year", year);
		List<Batch> reply = (List<Batch>) amqpTemplate.convertSendAndReceive(SERVICE_EXCHANGE, BATCH_LIST, msg.toString());
		return reply;
	}

}
