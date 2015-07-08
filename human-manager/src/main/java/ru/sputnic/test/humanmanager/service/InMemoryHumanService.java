package ru.sputnic.test.humanmanager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import ru.sputnic.test.humanmanager.model.Human;


public class InMemoryHumanService implements IHumanService {
	private Map<Long, Human> humans = new HashMap<Long, Human>();
	private static Long humanSequence = 0l;
	
	public InMemoryHumanService() {}

	public List<Human> showAllHuman() {
		return new ArrayList<Human>(humans.values());
	}

	public void saveHuman(Human human) throws HumanServiceException {
		if (human == null) {
			throw new HumanServiceException(new NullPointerException("human is null"));
		}
		human.setId(humanSequence);
		humans.put(humanSequence, human);
		humanSequence++;
		
	
	}

	public void updateHuman(Human human) throws ResourceNotFoundException {
		if (human == null || human.getId() == null) {
			throw new ResourceNotFoundException(new NullPointerException("human or id is null"));
		}
		if(!humans.containsKey(human.getId())) {
			throw new ResourceNotFoundException();
		}
		humans.put(human.getId(), human);
	}

	public void removeHuman(Long id) throws ResourceNotFoundException {
		if (id != null && !humans.containsKey(id)) {
			throw new ResourceNotFoundException(new NullPointerException("human is null or not found"));
		}
		humans.remove(id);
	}
}
