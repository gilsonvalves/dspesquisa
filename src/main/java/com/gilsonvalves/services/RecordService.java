package com.gilsonvalves.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gilsonvalves.dto.RecordDTO;
import com.gilsonvalves.dto.RecordInsertDTO;
import com.gilsonvalves.entities.Game;
import com.gilsonvalves.entities.Record;
import com.gilsonvalves.repositories.GameRepository;
import com.gilsonvalves.repositories.RecordRepository;

@Service
public class RecordService {

	@Autowired
	private RecordRepository recordRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional
	public RecordDTO insert(RecordInsertDTO dto) {
		
		Record entity = new Record();
		
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity.setMoment(Instant.now());
		
		Game game = gameRepository.getOne(dto.getGameId());
		entity.setGame(game);
		
		recordRepository.save(entity);
		
		return new RecordDTO(entity);
	}

	@Transactional(readOnly = true)
	public Page<RecordDTO> findByMoments(Instant minDate, Instant maxDate, PageRequest pageRequest) {
		return recordRepository.findByMoments(minDate,maxDate,pageRequest).map(x -> new RecordDTO(x));
	}
}
