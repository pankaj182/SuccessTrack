package com.sf.service;

import java.util.ArrayList;

import com.sf.model.CandidateBackground;
import com.sf.model.User;
import com.sf.service.exceptions.ServiceExceptions;

public interface CandidateProfileServices {
	public int registerCandidate(User user) throws ServiceExceptions;
	public User getUserDataByUserId(int userId) throws ServiceExceptions;
	public void registerBackgroundInformation(CandidateBackground cb) throws ServiceExceptions;
	public ArrayList<CandidateBackground> getUserBackgroundByUserId(int userId) throws ServiceExceptions;
	public void updateProfileImage(User user) throws ServiceExceptions;
}
