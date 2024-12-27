package com.Bootcamp.FirstProject.service;

import com.Bootcamp.FirstProject.ScheduledEmail;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class ScheduleEmailService {

    private final JdbcTemplate jdbcTemplate;
//    @Autowired
//    private TransactionTemplate transactionTemplate;
    @Autowired
    private PlatformTransactionManager platformTransactionManager;


    public ScheduleEmailService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String saveScheduledEmail(@NotNull ScheduledEmail email) {
        String query = "INSERT INTO ScheduledEmail (receiver, sender, body, subject, Schedule_time) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, email.getReceiver(), email.getSender(),
                email.getBody(), email.getSubject(), email.getTime());
        return "Email Scheduled Successfully";
    }

    public List<ScheduledEmail> getAllScheduledEmails() {
        String query = "SELECT * FROM ScheduledEmail";
        return jdbcTemplate.query(query, (rs, rowNum) -> new ScheduledEmail(
                rs.getInt("id"),
                rs.getString("receiver"),
                rs.getString("sender"),
                rs.getString("body"),
                rs.getString("subject"),
                rs.getTimestamp("Schedule_time")
        ));
    }

    public ScheduledEmail getScheduledEmailById(int id) {
        String query = "SELECT * FROM ScheduledEmail WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> new ScheduledEmail(
                rs.getInt("id"),
                rs.getString("receiver"),
                rs.getString("sender"),
                rs.getString("body"),
                rs.getString("subject"),
                rs.getTimestamp("time")
        ));
    }

    public int deleteScheduledEmailById(int id) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = platformTransactionManager.getTransaction(def);

        String deleteSql = "DELETE FROM ScheduledEmail WHERE id = ?";
        int rowsAfftected = jdbcTemplate.update(deleteSql, id);
        platformTransactionManager.commit(status);
        return rowsAfftected;

    }
}