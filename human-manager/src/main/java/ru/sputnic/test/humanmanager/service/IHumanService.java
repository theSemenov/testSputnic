package ru.sputnic.test.humanmanager.service;

import java.util.List;
import ru.sputnic.test.humanmanager.model.Human;


public interface IHumanService {
	List<Human> showAllHuman() throws HumanServiceException;
	void saveHuman(Human human) throws HumanServiceException;
	void updateHuman(Human human) throws HumanServiceException;
	void removeHuman(Long id) throws ResourceNotFoundException;
}
