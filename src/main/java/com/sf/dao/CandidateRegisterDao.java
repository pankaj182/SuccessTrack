package com.sf.dao;

import java.util.ArrayList;

import com.sf.dao.exceptions.DatabaseException;
import com.sf.model.CandidateBackground;
import com.sf.model.User;
/**
 * This interface provides relevant methods useful while user registers.
 * @author I348491
 *
 */
public interface CandidateRegisterDao {
	/**
	 * Helper method to feed user's information into the database
	 * @param user bean containing information about the New User
	 * @return true if successfully registered else returns false
	 * @throws DatabaseException 
	 */
	public int registerUser(User user) throws DatabaseException;
	public User getUserDataByUserId(int userId) throws DatabaseException;
	public void insertBackgroundData(CandidateBackground cb) throws DatabaseException;
	public ArrayList<CandidateBackground> getUserBackgroundByUserId(int userId) throws DatabaseException;
	public void updateProfilePicture(User user) throws DatabaseException;
}
