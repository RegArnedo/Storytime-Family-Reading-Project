package com.techelevator.dao;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.model.UserDashInfo;
import com.techelevator.model.UserDashInfoTitle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.techelevator.model.User;

@Service
public class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        return jdbcTemplate.queryForObject("select user_id from users where username = ?", int.class, username);
    }

	@Override
	public User getUserById(Long userId) {
		String sql = "SELECT * FROM users WHERE user_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
		if (results.next()) {
			return mapRowToUser(results);
		} else {
			throw new RuntimeException("userId " + userId + " was not found.");
		}
	}

    @Override
    public List <User> findAll() {
        List <User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        for (User user : this.findAll()) {
            if (user.getUsername().toLowerCase().equals(username.toLowerCase())) {
                return user;
            }
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String family_id, String firstName,
                          String lastName, String email, String username, String password, String role) {
        boolean userCreated = false;

        // create user
        String insertUser = "insert into users (username,password_hash,role,family_id,first_name,last_name,email,total_minutes,points_balance) values(?,?,?,?,?,?,?,0,0)";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        String ssRole = "ROLE_" + role.toUpperCase();
        //String family_id = "1";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String id_column = "user_id";
        userCreated = jdbcTemplate.update(con -> {
                    PreparedStatement ps = con.prepareStatement(insertUser, new String[]{id_column});
                    ps.setString(1, username);
                    ps.setString(2, password_hash);
                    ps.setString(3, ssRole);
                    ps.setString(4, family_id);
                    ps.setString(5, firstName);
                    ps.setString(6, lastName);
                    ps.setString(7, email);
                    return ps;
                }
                , keyHolder) == 1;
        int newUserId = (int) keyHolder.getKeys().get(id_column);

        return userCreated;
    }

    @Override
    public List<User> getUsersByFamilyId(String familyId) {
        String sql = "SELECT * FROM users WHERE family_id = ? ORDER BY first_name;";
        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(sql, familyId);
        List <User> results = new ArrayList<>();
        while (resultSet.next()) {
            results.add(mapRowToUser(resultSet));
        }
        return results;
    }

//    @Override
//    public List<UserDashInfo> getUserDashboardInfoByFamilyId(String familyId) {
//        String sql = "SELECT users.user_id, users.first_name, SUM(times_read) AS books_read, SUM(minutes_read) as total_minutes_read, points_balance\n" +
//                "FROM users \n" +
//                "LEFT JOIN users_books ON users.user_id = users_books.user_id \n" +
//                "LEFT JOIN book_info ON users_books.book_id = book_info.book_id \n" +
//                "WHERE users.family_id = ? \n" +
//                "GROUP BY users.user_id, first_name, points_balance \n" +
//                "ORDER BY users.first_name;";
//        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(sql, familyId);
//        List<UserDashInfo> results = new ArrayList<>();
//        while (resultSet.next()) {
//            results.add(mapRowToDashUser(resultSet));
//            }
//        return results;
//    }

    @Override
    public List<UserDashInfo> getUserDashboardInfoByFamilyId(String familyId) {
        String sql = "SELECT users.user_id, users.first_name, SUM(times_read) AS books_read, SUM(minutes_read) as total_minutes_read, points_balance\n" +
                "FROM users \n" +
                "LEFT JOIN users_books ON users.user_id = users_books.user_id \n" +
                "LEFT JOIN book_info ON users_books.book_id = book_info.book_id \n" +
                "WHERE users.family_id = ? \n" +
                "GROUP BY users.user_id, first_name, points_balance \n" +
                "ORDER BY users.user_id;";
        SqlRowSet resultSet = jdbcTemplate.queryForRowSet(sql, familyId);
        List<UserDashInfo> resultsA = new ArrayList<>();
        while (resultSet.next()) {
            resultsA.add(mapRowToDashUser(resultSet));
        }
        sql = "select distinct on (users.user_id) users.user_id, title AS current_book \n" +
                "FROM users \n" +
                "LEFT JOIN users_books ON users.user_id = users_books.user_id \n" +
                "LEFT JOIN book_info ON users_books.book_id = book_info.book_id \n" +
                "where users.family_id = ? \n" +
                "order by users.user_id, date_logged desc;";
        resultSet = jdbcTemplate.queryForRowSet(sql, familyId);
        List<UserDashInfoTitle> resultsB = new ArrayList<>();
        while (resultSet.next()) {
            resultsB.add(maptRowToDashUserTitle(resultSet));
        }
        for (int i = 0; i < resultsA.size(); i++) {
            resultsA.get(i).setCurrent_book(
                    resultsB.get(i).getCurrent_book());
        }

        return resultsA;
    }

    private UserDashInfoTitle maptRowToDashUserTitle(SqlRowSet rs) {
        UserDashInfoTitle userTitle = new UserDashInfoTitle();
        userTitle.setUser_id(rs.getLong("user_id"));
        userTitle.setCurrent_book(rs.getString("current_book"));
        return userTitle;
    }


    private UserDashInfo mapRowToDashUser(SqlRowSet rs) {
        UserDashInfo userDash = new UserDashInfo();
        userDash.setUser_id(rs.getLong("user_id"));
        userDash.setFirst_name(rs.getString("first_name"));
        userDash.setBooks_read(rs.getInt("books_read"));
        userDash.setTotal_minutes_read(rs.getInt("total_minutes_read"));
        userDash.setPoints_balance(rs.getInt("points_balance"));
        //userDash.setCurrent_book(rs.getString("current_book"));
        return userDash;

    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setFamily_id(rs.getString("family_id"));
        user.setFirst_name(rs.getString("first_name"));
        user.setLast_name(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setTotalMinutes(rs.getInt("total_minutes"));
        user.setPointsBalance(rs.getInt("points_balance"));
        user.setAuthorities(rs.getString("role"));
        user.setActivated(true);
        return user;
    }
}
