package com.sf.dao.impl;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;

import com.sf.connection.ConnectionProvider;
import com.sf.connection.impl.ConnectionProviderImpl;
import com.sf.dao.CandidateRegisterDao;
import com.sf.dao.exceptions.DatabaseException;
import com.sf.model.CandidateBackground;
import com.sf.model.User;
import com.sf.utilities.DateUtilities;

/**
 * 
 * @author I348491
 *
 */
public class CandidateRegisterDaoImpl implements CandidateRegisterDao {

	/**
	 * @param user
	 *            parameter of type User which needs to be inserted into database.
	 * @return a system generated Id for user. of data type int
	 * @throws DatabaseException
	 */
	public int registerUser(User user) throws DatabaseException {
		int status = -10;
		int userId = -1;
		Connection conn = null;
		ConnectionProvider provider = null;
		PreparedStatement ps = null;
		if (user == null)
			throw new IllegalArgumentException(
					"user Object is null at" + getClass().getName() + " " + getClass().getEnclosingMethod());
		try {
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();
			String sql = "Insert into USERS(USER_ID, EMAIL_ID, PASSWORD, FIRST_NAME, LAST_NAME, USER_TYPE, IS_ACTIVE, GENDER, DOB, REGISTRATION_DATE, LAST_LOGIN_DATE) values(USER_ID_SEQ.nextVal , ? , ? , ? , ? , ? , 1 , ? , to_date(?,'yyyy/MM/dd'), to_date(?, 'yyyy/MM/dd') , to_date(?, 'yyyy/MM/dd'))";

			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getEmailId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getUserType());
			ps.setString(6, user.getGender());
			ps.setString(7, user.getDob());
			ps.setString(8, DateUtilities.getCurrentDateAsString()); // registration Date
			ps.setString(9, DateUtilities.getCurrentDateAsString()); // LastLogin Date

			status = ps.executeUpdate();

			if (status>0) {
				ResultSet rs = null;
				PreparedStatement ps2 = null;
				ps2 = conn.prepareStatement("Select USER_ID from USERS where EMAIL_ID = ?");
				ps2.setString(1, user.getEmailId());
				rs = ps2.executeQuery();
				while (rs.next())
					userId = rs.getInt("USER_ID");
				if (ps2 != null)
					ps2.close();
				if (rs != null)
					rs.close();
			}
			System.out.println(status);
		} catch (SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
			throw new DatabaseException("Could not insert user data into Database", e);
		} catch (SQLException se) {
			throw new DatabaseException("Could not insert user data into Database ", se);
		} catch (Exception exception) {
			throw new DatabaseException("Could not insert user data into Database", exception);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw new DatabaseException("Exception", e);
			}
		}
		return userId;
	}

	public User getUserDataByUserId(int userId) throws DatabaseException {
		User user = null;
		Connection conn = null;
		ResultSet rs = null;
		ConnectionProvider provider = null;
		PreparedStatement ps = null;
		if (userId < 0)
			throw new IllegalArgumentException("Invalid user Id while inserting user Data into Database at"
					+ getClass().getName() + " " + getClass().getEnclosingMethod());
		try {
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();
			ps = conn.prepareStatement(
					"Select EMAIL_ID,FIRST_NAME,LAST_NAME,GENDER,DOB,CITY,STATE,COUNTRY,ZIP,PROFILE_IMAGE from USERS where USER_ID = ?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			user = new User(userId);
			while (rs.next()) {
				user.setAddress(rs.getString("CITY"), rs.getString("STATE"), rs.getString("COUNTRY"),
						rs.getString("ZIP"));
				user.setDob(DateUtilities.getLocaleBasedStringDate(rs.getString("DOB")));
				user.setName(rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"));
				user.setGender(rs.getString("GENDER"));
				user.setEmailId(rs.getString("EMAIL_ID"));
				Blob image = rs.getBlob("PROFILE_IMAGE");
				if (image != null)
					user.setProfileImage(image.getBytes(1, (int) image.length()));

			}
		} catch (SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
			throw new DatabaseException("Could not insert user data into Database", e);
		} catch (SQLException se) {
			throw new DatabaseException("Could not insert user data into Database ", se);
		} catch (Exception exception) {
			throw new DatabaseException("Could not insert user data into Database", exception);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw new DatabaseException("Exception", e);
			}

		}
		return user;
	}

	public ArrayList<CandidateBackground> getUserBackgroundByUserId(int userId) throws DatabaseException {
		Connection conn = null;
		ResultSet rs = null;
		ConnectionProvider provider = null;
		PreparedStatement ps = null;
		ArrayList<CandidateBackground> list = null;
		CandidateBackground cb = null;
		if (userId < 0)
			throw new IllegalArgumentException("Invalid user Id while inserting user Background Data into Database"
					+ getClass().getName() + " " + getClass().getEnclosingMethod());
		try {
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();
			ps = conn.prepareStatement("Select * from CANDIDATE_BACKGROUND where CANDIDATE_ID = ?");
			ps.setInt(1, userId);
			rs = ps.executeQuery();
			list = new ArrayList<CandidateBackground>();

			while (rs.next()) {
				String infoType = rs.getString("INFO_TYPE");
				switch (infoType) {
				case "experience":
				case "EXPERIENCE":
					cb = new CandidateBackground(userId, rs.getString("COMPANY_NAME"), rs.getString("JOB_TITLE"),
							rs.getInt("SALARY"));
					cb.setAddress(rs.getString("CITY"), rs.getString("STATE"), rs.getString("COUNTRY"));
					cb.setDates(DateUtilities.getLocaleBasedStringDate(rs.getString("START_DATE")),
							DateUtilities.getLocaleBasedStringDate(rs.getString("END_DATE")));
					break;
				case "education":
				case "EDUCATION":
					cb = new CandidateBackground(userId, rs.getString("INSTITUTE_NAME"), rs.getString("DEGREE"),
							rs.getString("MAJOR"), rs.getDouble("CGPA"), rs.getDouble("PERCENTAGE"));
					cb.setAddress(rs.getString("CITY"), rs.getString("STATE"), rs.getString("COUNTRY"));
					cb.setDates(DateUtilities.getLocaleBasedStringDate(rs.getString("START_DATE")),
							DateUtilities.getLocaleBasedStringDate(rs.getString("END_DATE")));
					break;
				case "skills":
				case "SKILLS":
					cb = new CandidateBackground(userId);
					cb.setSkillSet(rs.getString("SKILLSET"));
					break;
				}
				list.add(cb);
			}
		} catch (SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
			throw new DatabaseException("Could not fetch user background data into Database", e);
		} catch (SQLException se) {
			throw new DatabaseException("Could not fetch user background data into Database ", se);
		} catch (Exception exception) {
			throw new DatabaseException("Could not fetch user background data into Database", exception);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (rs != null)
					rs.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw new DatabaseException("Exception", e);
			}
		}
		return list;
	}

	public void insertBackgroundData(CandidateBackground cb) throws DatabaseException {
		Connection conn = null;
		ConnectionProvider provider = null;
		PreparedStatement ps = null;
		String sql = "";
		if (cb == null)
			throw new IllegalArgumentException("Null Candidate Background object while inserting into database "
					+ getClass().getName() + " " + getClass().getEnclosingMethod());
		try {
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();
			String infoType = cb.getInfoType();
			switch (infoType) {
			case "experience":
			case "EXPERIENCE":
				sql = "INSERT into CANDIDATE_BACKGROUND(CANDIDATE_ID,INFO_TYPE,COMPANY_NAME,JOB_TITLE,SALARY,START_DATE,END_DATE,CITY,STATE,COUNTRY) values(?,?,?,?,?,to_date(?,'yyyy/MM/dd'),to_date(?,'yyyy/MM/dd'),?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, cb.getCandidateId());
				ps.setString(2, cb.getInfoType());
				ps.setString(3, cb.getCompanyName());
				ps.setString(4, cb.getJobTitle());
				ps.setInt(5, cb.getSalary());
				ps.setString(6, cb.getStartDate());
				ps.setString(7, cb.getEndDate());
				ps.setString(8, cb.getCity());
				ps.setString(9, cb.getState());
				ps.setString(10, cb.getCountry());
				break;
			case "education":
			case "EDUCATION":
				sql = "INSERT into CANDIDATE_BACKGROUND(CANDIDATE_ID,INFO_TYPE,INSTITUTE_NAME,DEGREE,MAJOR,CGPA,PERCENTAGE,START_DATE,END_DATE,CITY,STATE,COUNTRY) values(?,?,?,?,?,?,?,to_date(?,'yyyy/MM/dd'),to_date(?,'yyyy/MM/dd'),?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, cb.getCandidateId());
				ps.setString(2, cb.getInfoType());
				ps.setString(3, cb.getInstituteName());
				ps.setString(4, cb.getDegree());
				ps.setString(5, cb.getMajor());
				ps.setDouble(6, cb.getCgpa());
				ps.setDouble(7, cb.getPercentage());
				ps.setString(8, cb.getStartDate());
				ps.setString(9, cb.getEndDate());
				ps.setString(10, cb.getCity());
				ps.setString(11, cb.getState());
				ps.setString(12, cb.getCountry());

				break;
			case "skills":
			case "SKILLS":
				sql = "INSERT into CANDIDATE_BACKGROUND(CANDIDATE_ID,INFO_TYPE,SKILLSET) values(?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, cb.getCandidateId());
				ps.setString(2, cb.getInfoType());
				ps.setString(3, cb.getSkillSet());
				break;
			}
			ps.execute();
		} catch (SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
			throw new DatabaseException("Could not insert user background data into Database", e);
		} catch (SQLException se) {
			throw new DatabaseException("Could not insert user background data into Database ", se);
		} catch (Exception exception) {
			throw new DatabaseException("Could not insert user background data into Database", exception);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw new DatabaseException("Exception", e);
			}
		}
	}

	@Override
	public void updateProfilePicture(User user) throws DatabaseException {
		Connection conn = null;
		ConnectionProvider provider = null;
		PreparedStatement ps = null;
		String sql = "";
		if (user == null)
			throw new IllegalArgumentException("Invalid user Id while updating profile picture" + getClass().getName()
					+ " " + getClass().getEnclosingMethod());
		try {
			provider = new ConnectionProviderImpl();
			conn = provider.getConnection();
			sql = "Update USERS SET PROFILE_IMAGE = ? where USER_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setBytes(1, user.getProfileImage());
			ps.setInt(2, user.getUserId());

			ps.execute();
		} catch (SQLDataException | SQLSyntaxErrorException | SQLIntegrityConstraintViolationException e) {
			throw new DatabaseException("Could not update profile picture", e);
		} catch (SQLException se) {
			throw new DatabaseException("Could not update profile picture ", se);
		} catch (Exception exception) {
			throw new DatabaseException("Could not update profile picture", exception);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				throw new DatabaseException("Exception", e);
			}
		}
	}
}